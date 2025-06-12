package fileeditor.ui.jwt.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;

public class JVWBEditorTextField extends JTextField {

   private static final long serialVersionUID = -7565470807232067911L;

   private boolean isOvertypeMode;
   private boolean isUppercaseMode;

   private transient Caret defaultCaret;
   private transient Caret overtypeCaret;

   private transient UndoManager undoManager = new UndoManager();

   public JVWBEditorTextField() {
      super();
      setCaretColor(Color.black);
      defaultCaret = getCaret();
      overtypeCaret = new OvertypeCaret();
      overtypeCaret.setBlinkRate(defaultCaret.getBlinkRate());
      setOvertypeMode(true);
      setUppercaseMode(true);
      getDocument().addUndoableEditListener(undoManager);
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
            int lineend = getText().length();
            int lenght = Math.min(replaceText.length(), lineend - pos);

            if (pos < lineend) {
               moveCaretPosition(pos + lenght);
            }
         } catch (IllegalArgumentException e) {
            // Do nothing
         }
      }
      super.replaceSelection(replaceText);
   }

   @Override
   @SuppressWarnings("java:S3776")
   protected void processKeyEvent(KeyEvent e) {
      super.processKeyEvent(e);
      if (e.getID() == KeyEvent.KEY_RELEASED) {
         if (e.getKeyCode() == KeyEvent.VK_INSERT) {
            setOvertypeMode(!isOvertypeMode());
         }
         if (e.getModifiersEx() == InputEvent.META_DOWN_MASK) {
            if (e.getKeyCode() == KeyEvent.VK_U) {
               setOvertypeMode(!isOvertypeMode());
            }
            if (e.getKeyCode() == KeyEvent.VK_G) {
               setUppercaseMode(!isUppercaseMode());
            }
            if (e.getKeyCode() == KeyEvent.VK_Z && undoManager.canUndo()) {
               undoManager.undo();
            }
            if (e.getKeyCode() == KeyEvent.VK_Y && undoManager.canRedo()) {
               undoManager.redo();
            }
         }
      }
   }

   /*
    *  Paint a horizontal line the width of a column and 1 pixel high
    */
   class OvertypeCaret extends DefaultCaret {

      private static final long serialVersionUID = 9019761836073350126L;

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
