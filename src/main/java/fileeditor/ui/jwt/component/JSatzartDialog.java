package fileeditor.ui.jwt.component;

import fileeditor.ui.jwt.data.JwtDatenFeld;
import fileeditor.ui.jwt.data.JwtSatzart;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

public class JSatzartDialog extends JDialog implements ActionListener {

   private static final long serialVersionUID = 8632722600704211625L;

   private static final String BUTTONTEXT_OK = "Übernehmen";
   private static final String BUTTONTEXT_CANCEL = "Abbrechen";

   private static final String BUTTON_OK = "OK";
   private static final String BUTTON_CANCEL = "CANCEL";

   private transient JVWBEditorTextArea area;
   private transient JwtSatzart satzart;
   private transient Map<String, JComponent> components = new HashMap<>();
   private transient int oldCaretPos;

   public JSatzartDialog(JFrame frame, JVWBEditorTextArea area, JwtSatzart satzart) {
      super(frame);
      this.area = area;
      this.satzart = satzart;
      buildGui();
   }

   private void buildGui() {
      oldCaretPos = area.getCaretPosition();

      setTitle("Bearbeite Satzart " + satzart.getSatzart() + " " + satzart.getBezeichnung());

      rootPane.registerKeyboardAction(
         this,
         BUTTON_OK,
         KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
         JComponent.WHEN_IN_FOCUSED_WINDOW);
      rootPane.registerKeyboardAction(
         this,
         BUTTON_CANCEL,
         KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
         JComponent.WHEN_IN_FOCUSED_WINDOW);

      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(satzart.getDatenfelder().size(), 2));

      buildEditFields(panel);

      JScrollPane scrollPane = new JScrollPane(panel);
      add(scrollPane, BorderLayout.CENTER);

      JPanel btnPanel = new JPanel();
      btnPanel.setLayout(new GridLayout(0, 2));
      btnPanel.add(buildButton(BUTTONTEXT_OK, BUTTON_OK));
      btnPanel.add(buildButton(BUTTONTEXT_CANCEL, BUTTON_CANCEL));
      add(btnPanel, BorderLayout.SOUTH);

      pack();
      setSize(Math.min(getWidth() + 100, getParent().getWidth()), Math.min(getHeight(), getParent().getHeight()));
      setLocation(
         getParent().getX() + (getParent().getWidth() / 2) - (getWidth() / 2),
         getParent().getY() + (getParent().getHeight() / 2) - (getHeight() / 2));

      setModal(true);
      setVisible(true);
   }

   private void buildEditFields(JPanel panel) {
      try {
         int lineStartOffset = area.getLineStartOffset(area.getLineOfOffset(area.getCaretPosition()));
         for (JwtDatenFeld datenfeld : satzart.getDatenfelder()) {
            panel.add(new JLabel(datenfeld.getBezeichnung() + ":"));
            JComponent component = buildEditComponent(
               datenfeld,
               area.getText(lineStartOffset + datenfeld.getVon() - 1, datenfeld.getLaenge()).replaceAll("\\s+$", ""));
            if (datenfeld.getNummer().equals("1")) {
               component.setEnabled(false);
            }
            panel.add(component);
            components.put(datenfeld.getNummer(), component);
         }
      } catch (BadLocationException e) {
         // Do nothing
      }
   }

   @SuppressWarnings({ "unchecked", "rawtypes", "java:S135" })
   private JComponent buildEditComponent(JwtDatenFeld datenfeld, String text) {
      
      if (datenfeld.hasValidValues()) {
         JComboBox comboBox = new JComboBox(datenfeld.getValidValueDescriptions());
         comboBox.setEditable(false);
         return comboBox;
      }
      JVWBEditorTextField textField = new JVWBEditorTextField();
      textField.setText(text);
      textField.setOvertypeMode(area.isOvertypeMode());
      textField.setUppercaseMode(area.isUppercaseMode());
      textField.setCaretPosition(0);
      return textField;
   }

   private JButton buildButton(String text, String actionCommand) {
      JButton button = new JButton(text);
      button.setActionCommand(actionCommand);
      button.addActionListener(this);
      return button;
   }

   public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
         case BUTTON_OK:
            actionOk();
            break;
         case BUTTON_CANCEL:
            actionCancel();
            break;
         default:
            break;
      }
   }

   private void actionOk() {
      boolean cancel = false;
      Map<String, String> newValues = new HashMap<>();
      for (JwtDatenFeld datenfeld : satzart.getDatenfelder()) {
         if (components.containsKey(datenfeld.getNummer())) {
            String text = determineTextFrom(components.get(datenfeld.getNummer()));
            String newValue = datenfeld.buildNewFieldValue(text);
            int dialogresult = JOptionPane.OK_OPTION;
            if (text.length() > datenfeld.getLaenge()) {
               dialogresult = JOptionPane.showConfirmDialog(
                  this,
                  "Der einzufügende Text\n'" + text + "'\nist länger als das Feld und wird gekürzt zu '" + newValue
                     + "'.",
                  datenfeld.getBezeichnung(),
                  JOptionPane.OK_CANCEL_OPTION);
            }
            if (dialogresult == JOptionPane.OK_OPTION) {
               newValues.put(datenfeld.getNummer(), newValue);
            } else {
               cancel = true;
               break;
            }
         }
      }
      if (!cancel) {
         try {
            int lineOffset = area.getLineStartOffset(area.getLineOfOffset(area.getCaretPosition()));
            for (JwtDatenFeld datenfeld : satzart.getDatenfelder()) {
               String text = newValues.get(datenfeld.getNummer());
               area.replaceRange(text, lineOffset + datenfeld.getVon() - 1, lineOffset + datenfeld.getBis());
            }
            area.setCaretPosition(oldCaretPos);
         } catch (BadLocationException e) {
            // Do nothing
         }
         dispose();
      }
   }

   @SuppressWarnings({ "rawtypes", "java:S3358" })
   private String determineTextFrom(JComponent component) {
      if (component instanceof JVWBEditorTextField) {
         return ((JVWBEditorTextField) component).getText();
      }
      if (component instanceof JComboBox) {
         return ((String) ((JComboBox) component).getSelectedItem()).split("\t")[0];
      }
      return "";
   }

   void actionCancel() {
      dispose();
   }

   @Override
   protected void processWindowEvent(WindowEvent e) {
      if (e.getID() == WindowEvent.WINDOW_CLOSING) {
         actionCancel();
      }
      super.processWindowEvent(e);
   }
}
