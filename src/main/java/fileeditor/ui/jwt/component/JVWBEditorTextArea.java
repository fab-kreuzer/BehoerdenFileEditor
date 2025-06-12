package fileeditor.ui.jwt.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

public class JVWBEditorTextArea extends JTextArea {

   private static final long serialVersionUID = 583512738591052071L;

   private boolean isOvertypeMode;
   private boolean isUppercaseMode;

   private transient Caret defaultCaret;
   private transient Caret overtypeCaret;

   public JVWBEditorTextArea() {
      super();
      setCaretColor(Color.black);
      defaultCaret = getCaret();
      overtypeCaret = new OvertypeCaret();
      overtypeCaret.setBlinkRate(defaultCaret.getBlinkRate());
      setOvertypeMode(true);
      setUppercaseMode(true);

      new DropTarget(this, new DropTargetAdapter() {
         @Override
         public void drop(DropTargetDropEvent dtde) {
            try {
               dtde.acceptDrop(DnDConstants.ACTION_COPY);

               Transferable transferable = dtde.getTransferable();
               if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                  List<File> droppedFiles = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);

                  for (File file : droppedFiles) {
                     if (file.isFile()) {
                        String content = readFile(file);
                        append(content);
                        break;
                     }
                  }
                  dtde.dropComplete(true);
               } else {
                  dtde.rejectDrop();
               }
            } catch (Exception ex) {
               ex.printStackTrace();
               dtde.rejectDrop();
            }
         }
      });
   }

   private String readFile(File file) throws IOException {
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
         StringBuilder sb = new StringBuilder();
         String line;
         while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
         }
         return sb.toString();
      }
   }

   /*
    * Return the overtype/insert mode
    */
   public boolean isOvertypeMode() {
      return isOvertypeMode;
   }

   /*
    * Set the caret to use depending on overtype/insert mode
    */
   public void setOvertypeMode(boolean isOvertypeMode) {
      this.isOvertypeMode = isOvertypeMode;
      int pos = getCaretPosition();

      if (isOvertypeMode()) {
         setCaret(overtypeCaret);
      } else {
         setCaret(defaultCaret);
      }
      setCaretPosition(pos);
   }

   /*
    * Return the uppercase mode
    */
   public boolean isUppercaseMode() {
      return isUppercaseMode;
   }

   /*
    * Set uppercase mode
    */
   public void setUppercaseMode(boolean isUppercaseMode) {
      this.isUppercaseMode = isUppercaseMode;
   }

   /*
    *  Override method from JComponent
    */
   @Override
   public void replaceSelection(String text) {
      //  Implement overtype mode by selecting the character at the current
      //  caret position
      String replaceText = text;
      if (isUppercaseMode()) {
         replaceText = text.toUpperCase().replace("Ä", "AE").replace("Ö", "OE").replace("Ü", "UE");
      }

      if (getSelectedText() == null && isOvertypeMode() && !replaceText.equals("\n") && !replaceText.equals("\t")) {
         int pos = getCaretPosition();
         try {
            int lineend = getLineEndOffset(getLineOfOffset(pos)) - 1;
            int lenght = Math.min(replaceText.length(), lineend - pos);

            if (pos < lineend) {
               moveCaretPosition(pos + lenght);
            }
         } catch (BadLocationException e) {
            // Do nothing
         }
      }
      super.replaceSelection(replaceText);
   }

   @Override
   public void replaceRange(String text, int start, int end) {
      String replaceText = text;
      if (isUppercaseMode()) {
         replaceText = text.toUpperCase().replace("Ä", "AE").replace("Ö", "OE").replace("Ü", "UE");
         if (replaceText.length() != text.length()) {
            replaceText = replaceText.substring(0, text.length());
         }
      }
      super.replaceRange(replaceText, start, end);
   }

   @Override
   protected void processKeyEvent(KeyEvent e) {
      super.processKeyEvent(e);

      //  Handle release of Insert key to toggle overtype/insert mode
      if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_INSERT) {
         setOvertypeMode(!isOvertypeMode());
      }
   }

   /*
    *  Paint a horizontal line the width of a column and 1 pixel high
    */
   class OvertypeCaret extends DefaultCaret {

      private static final long serialVersionUID = -2537032891384839977L;

      @Override
      public void paint(Graphics g) {
         if (isVisible()) {
            try {
               JTextComponent component = getComponent();
               TextUI mapper = component.getUI();
               Rectangle r = mapper.modelToView(component, getDot());
               g.setColor(component.getCaretColor());
               int width = g.getFontMetrics().charWidth('w');
               int y = r.y + r.height - 2;
               g.drawLine(r.x, y, r.x + width - 2, y);
            } catch (BadLocationException e) {
               // Do nothing.
            }
         }
      }

      @Override
      protected synchronized void damage(Rectangle r) {
         if (r != null) {
            JTextComponent component = getComponent();
            x = r.x;
            y = r.y;
            width = component.getFontMetrics(component.getFont()).charWidth('w');
            height = r.height;
            repaint();
         }
      }
   }
}
