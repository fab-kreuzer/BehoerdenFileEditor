package fileeditor.ui.jwt.component;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JTextAreaSearchDialog extends JDialog implements ActionListener {

   private static final long serialVersionUID = 1375290703876134779L;

   private static final String BUTTONTEXT_FIND = "Suchen";
   private static final String BUTTONTEXT_REPLACE = "Ersetzen";
   private static final String BUTTONTEXT_REPLACEALL = "Ersetze alles";

   private static final String BUTTON_FIND = "FIND";
   private static final String BUTTON_REPLACE = "REPLACE";
   private static final String BUTTON_REPLACEALL = "REPLACEALL";

   private transient JTextField txtFind = new JTextField();
   private transient JTextField txtReplace = new JTextField();
   private transient JCheckBox cbCaseSensitiv;
   private transient JTextArea area;

   private int findpos = -1;
   private int endpos = -1;

   public JTextAreaSearchDialog(JFrame frame, JTextArea area) {
      super(frame);
      this.area = area;

      setTitle("Suchen / Ersetzen");

      setResizable(false);
      setLayout(new GridLayout(3, 4));

      JButton btnFind = buildButton(BUTTONTEXT_FIND, BUTTON_FIND);
      JButton btnReplace = buildButton(BUTTONTEXT_REPLACE, BUTTON_REPLACE);
      JButton btnReplaceAll = buildButton(BUTTONTEXT_REPLACEALL, BUTTON_REPLACEALL);

      add(new JLabel("  Suchen nach:"));
      add(txtFind);
      add(new JLabel(""));
      add(btnFind);

      add(new JLabel("  Ersetze mit:"));
      add(txtReplace);
      add(new JLabel(""));
      add(btnReplace);

      add(new JLabel("  Einstellungen:"));
      cbCaseSensitiv = new JCheckBox("Groß-/Kleinschreibung");
      add(cbCaseSensitiv);
      add(new JLabel(""));
      add(btnReplaceAll);

      pack();
   }

   @Override
   public void setVisible(boolean b) {
      if (b) {
         setLocation(
            getParent().getX() + (getParent().getWidth() / 2) - getWidth(),
            getParent().getY() + (getParent().getHeight() / 2));
      }
      super.setVisible(b);
   }

   private JButton buildButton(String text, String actionCommand) {
      JButton btnFind = new JButton(text);
      btnFind.setActionCommand(actionCommand);
      btnFind.addActionListener(this);
      return btnFind;
   }

   public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
         case BUTTON_FIND:
            actionSearch();
            break;
         case BUTTON_REPLACE:
            actionReplace();
            break;
         case BUTTON_REPLACEALL:
            actionReplaceAll();
            break;
         default:
            break;
      }
   }

   private void actionSearch() {
      if (txtFind.getText().isEmpty()) {
         return;
      }
      String searchStr = txtFind.getText();
      String text = area.getText();
      if (!cbCaseSensitiv.isSelected()) {
         text = text.toLowerCase();
         searchStr = searchStr.toLowerCase();
      }
      findpos = text.indexOf(searchStr, findpos + 1);
      if (findpos >= 0) {
         area.setSelectionStart(findpos);
         endpos = findpos + searchStr.length();
         area.setSelectionEnd(endpos);
      } else {
         area.setSelectionStart(area.getCaretPosition());
         area.setSelectionEnd(area.getCaretPosition());
         endpos = -1;
      }
   }

   private void actionReplace() {
      if (txtReplace.getText().isEmpty() || findpos == -1) {
         return;
      }
      area.replaceRange(txtReplace.getText(), findpos, endpos);
   }

   private void actionReplaceAll() {
      while (!txtReplace.getText().isEmpty() && findpos != -1) {
         actionReplace();
         actionSearch();
      }
   }
}
