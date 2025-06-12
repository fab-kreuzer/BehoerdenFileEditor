package fileeditor.ui.jwt.component;

import fileeditor.FileEditor;
import fileeditor.ui.jwt.Constants;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JAboutBox extends JDialog implements ActionListener {

   private static final long serialVersionUID = 1412267721474371806L;

   JPanel panel1 = new JPanel();
   JPanel panel2 = new JPanel();
   JPanel insetsPanel1 = new JPanel();
   JPanel insetsPanel2 = new JPanel();
   JPanel insetsPanel3 = new JPanel();
   JButton buttonOK = new JButton("OK");
   JLabel imageControl1 = new JLabel();
   ImageIcon imageIcon;
   JLabel label1 = new JLabel(Constants.FRAME_TITLE + " " + Constants.VERSION);
   JLabel label2 = new JLabel("Developed by Andreas Kniest");
   JLabel label3 = new JLabel("Copyright (c) 2022 by Adcubum AG");
   JLabel label4 = new JLabel("All Rights Reserved.");
   BorderLayout borderLayout1 = new BorderLayout();
   BorderLayout borderLayout2 = new BorderLayout();
   FlowLayout flowLayout1 = new FlowLayout();
   FlowLayout flowLayout2 = new FlowLayout();
   GridLayout gridLayout1 = new GridLayout();
   JLabel jLabel1 = new JLabel();

   public JAboutBox(Frame parent) {
      super(parent);
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      try {
         componentInit();
      } catch (Exception e) {
         e.printStackTrace();
      }
      pack();
   }

   private void componentInit() {
      this.setTitle("Über...");
      String imgLocation = "images/VWBEditor.png";
      URL resource = FileEditor.class.getClassLoader().getResource(imgLocation);
      if (resource != null) {
         imageIcon = new ImageIcon(resource);
      } else {
         Logger.getLogger(FileEditor.class.getName()).log(Level.SEVERE, "Resource not found: {0}", imgLocation);
      }
      setResizable(false);
      panel1.setLayout(borderLayout1);
      panel2.setLayout(borderLayout2);
      insetsPanel1.setLayout(flowLayout1);
      insetsPanel2.setLayout(flowLayout1);
      insetsPanel2.setBorder(new EmptyBorder(10, 10, 10, 10));
      gridLayout1.setRows(4);
      gridLayout1.setColumns(1);
      insetsPanel3.setLayout(gridLayout1);
      insetsPanel3.setBorder(new EmptyBorder(10, 40, 10, 10));
      buttonOK.addActionListener(this);
      jLabel1.setIcon(imageIcon);
      insetsPanel2.add(imageControl1, null);
      insetsPanel2.add(jLabel1, null);
      panel2.add(insetsPanel2, BorderLayout.WEST);
      this.getContentPane().add(panel1, null);
      insetsPanel3.add(label1, null);
      insetsPanel3.add(label2, null);
      insetsPanel3.add(label3, null);
      insetsPanel3.add(label4, null);
      panel2.add(insetsPanel3, BorderLayout.CENTER);
      insetsPanel1.add(buttonOK, null);
      panel1.add(insetsPanel1, BorderLayout.SOUTH);
      panel1.add(panel2, BorderLayout.NORTH);
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

   @Override
   protected void processWindowEvent(WindowEvent e) {
      if (e.getID() == WindowEvent.WINDOW_CLOSING) {
         cancel();
      }
      super.processWindowEvent(e);
   }

   void cancel() {
      dispose();
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == buttonOK) {
         cancel();
      }
   }
}
