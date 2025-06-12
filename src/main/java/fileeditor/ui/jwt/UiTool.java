package fileeditor.ui.jwt;

import fileeditor.FileEditor;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UiTool {

   private UiTool() {
      // Static use
   }

   public static JMenuItem buildMenuItem(ActionListener listener,
                                         String bezeichnung,
                                         String actionCommand,
                                         KeyStroke accelerator) {
      JMenuItem menuitem = buildMenuItem(listener, bezeichnung, actionCommand);
      menuitem.setAccelerator(accelerator);
      return menuitem;
   }

   public static JMenuItem buildMenuItem(ActionListener listener,
                                         String bezeichnung,
                                         String actionCommand,
                                         String toolTipText) {
      JMenuItem menuitem = buildMenuItem(listener, bezeichnung, actionCommand);
      menuitem.setName(toolTipText);
      menuitem.setToolTipText(toolTipText);
      return menuitem;
   }

   public static JMenuItem buildMenuItem(ActionListener listener, String bezeichnung, String actionCommand) {
      JMenuItem menuitem = new JMenuItem(bezeichnung);
      menuitem.setActionCommand(actionCommand);
      menuitem.addActionListener(listener);
      return menuitem;
   }

   public static JCheckBoxMenuItem buildMenuItem(ActionListener listener,
                                                 String bezeichnung,
                                                 String actionCommand,
                                                 boolean state,
                                                 KeyStroke accelerator) {
      JCheckBoxMenuItem menuitem = buildMenuItem(listener, bezeichnung, actionCommand, state);
      menuitem.setAccelerator(accelerator);
      return menuitem;
   }

   public static JCheckBoxMenuItem buildMenuItem(ActionListener listener,
                                                 String bezeichnung,
                                                 String actionCommand,
                                                 boolean state) {
      JCheckBoxMenuItem menuitem = new JCheckBoxMenuItem(bezeichnung, state);
      menuitem.setActionCommand(actionCommand);
      menuitem.addActionListener(listener);
      return menuitem;
   }

   public static JButton buildToolbarButton(ActionListener listener,
                                            String imageName,
                                            String actionCommand,
                                            String toolTipText,
                                            String altText) {
      return (JButton) loadAndSetImage(
              defineToolbarButton(new JButton(), listener, actionCommand, toolTipText),
              imageName,
              altText);
   }

   public static JComboBox<String> buildToolbarComboBox(ActionListener listener,
                                                        String actionCommand,
                                                        String toolTipText,
                                                        String[] values) {
      JComboBox<String> comboBox = new JComboBox<>(values);
      comboBox.setActionCommand(actionCommand);
      comboBox.setToolTipText(toolTipText);
      comboBox.setFocusable(false);
      comboBox.addActionListener(listener);
      return comboBox;
   }

   public static JToggleButton buildToolbarToggleButton(ActionListener listener,
                                                        String imageName,
                                                        String actionCommand,
                                                        String toolTipText,
                                                        String altText,
                                                        boolean state) {
      AbstractButton button = defineToolbarButton(new JToggleButton(), listener, actionCommand, toolTipText);
      button.setSelected(state);
      return (JToggleButton) loadAndSetImage(button, imageName, altText);
   }

   public static void setLookAndFeel() {
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
            | UnsupportedLookAndFeelException ex) {
         Logger.getLogger(FileEditor.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private static AbstractButton defineToolbarButton(AbstractButton button,
                                                     ActionListener listener,
                                                     String actionCommand,
                                                     String toolTipText) {
      button.setActionCommand(actionCommand);
      button.setToolTipText(toolTipText);
      button.setBorderPainted(false);
      button.setFocusable(false);
      button.addActionListener(listener);
      return button;
   }

   private static AbstractButton loadAndSetImage(AbstractButton button, String imageName, String altText) {
      String imgLocation = "images/" + imageName + ".png";
      URL resource = FileEditor.class.getClassLoader().getResource(imgLocation);

      if (resource != null) {
         button.setIcon(new ImageIcon(resource, altText));
         imgLocation = "images/" + imageName + "_selected.png";
         resource = FileEditor.class.getClassLoader().getResource(imgLocation);
         if (resource != null) {
            button.setSelectedIcon(new ImageIcon(resource, altText));
         }
      } else {
         button.setText(altText);
         Logger.getLogger(FileEditor.class.getName()).log(Level.SEVERE, "Resource not found: {0}", imgLocation);
      }
      return button;
   }
}
