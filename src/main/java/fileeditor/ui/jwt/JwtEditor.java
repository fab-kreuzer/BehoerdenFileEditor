package fileeditor.ui.jwt;

import fileeditor.behoerden.general.SatzartBuilderComplete;
import fileeditor.behoerden.validators.MessageValidator;
import fileeditor.behoerden.validators.response.MessageValidationResponse;
import fileeditor.behoerden.vkb.validator.VkbMessageValidator;
import fileeditor.behoerden.vwb.validator.VwbMessageValidator;
import fileeditor.ui.jwt.component.JAboutBox;
import fileeditor.ui.jwt.component.JSatzartDialog;
import fileeditor.ui.jwt.component.JTextAreaSearchDialog;
import fileeditor.ui.jwt.component.JTextLineNumber;
import fileeditor.ui.jwt.component.JVWBEditorTextArea;
import fileeditor.ui.jwt.data.JwtDatenFeld;
import fileeditor.ui.jwt.data.JwtSatzart;
import fileeditor.ui.jwt.mapper.SatzartMapper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.undo.UndoManager;

public final class JwtEditor extends JFrame implements ActionListener {

   private static final long serialVersionUID = 419681287425443759L;

   private static final Highlighter.HighlightPainter ORANGE_HIGHLIGHTER =
         new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE);

   protected transient JFrame frame = new JFrame();
   protected transient JVWBEditorTextArea area = new JVWBEditorTextArea();
   private transient JTextAreaSearchDialog searchDialog = new JTextAreaSearchDialog(frame, area);
   private transient UndoManager undomanager = new UndoManager();
   private transient Clipboard clipboardManager = Toolkit.getDefaultToolkit().getSystemClipboard();

   private transient JButton areazoominToolButton;
   private transient JButton areazoomoutToolButton;
   private transient JComboBox<String> behoerdenAuswahl;
   private transient JButton copyToolButton;
   private transient JButton cutToolButton;
   private transient JButton dialogToolButton;
   private transient JButton markFieldToolButton;
   private transient JButton nextFieldToolButton;
   private transient JButton pasteSpecialToolButton;
   private transient JButton pasteToolButton;
   private transient JButton prevFieldToolButton;
   private transient JButton redoToolButton;
   private transient JButton saveToolButton;
   private transient JButton undoToolButton;
   protected transient JCheckBoxMenuItem editmodeMenuItem;
   protected transient JCheckBoxMenuItem showFooterMenuItem;
   protected transient JCheckBoxMenuItem showHighlightMenuItem;
   protected transient JCheckBoxMenuItem showLineNumbersMenuItem;
   protected transient JCheckBoxMenuItem showRulerMenuItem;
   protected transient JCheckBoxMenuItem showSidebarMenuItem;
   protected transient JCheckBoxMenuItem uppercaseMenuItem;
   private transient JLabel infozeile;
   private transient JMenu recentMenu;
   private transient JMenu insertSubMenu;
   private transient JMenuItem areazoomdefMenuItem;
   private transient JMenuItem areazoominMenuItem;
   private transient JMenuItem areazoomoutMenuItem;
   private transient JMenuItem copyMenuItem;
   private transient JMenuItem cutMenuItem;
   private transient JMenuItem dialogMenuItem;
   private transient JMenuItem markFieldMenuItem;
   private transient JMenuItem nextFieldMenuItem;
   private transient JMenuItem pasteMenuItem;
   private transient JMenuItem pasteSpecialMenuItem;
   private transient JMenuItem prevFieldMenuItem;
   private transient JMenuItem redoMenuItem;
   private transient JMenuItem saveMenuItem;
   private transient JMenuItem sidebarzoomdefMenuItem;
   private transient JMenuItem sidebarzoominMenuItem;
   private transient JMenuItem sidebarzoomoutMenuItem;
   private transient JMenuItem sidebarRightMenuItem;
   private transient JMenuItem sidebarBottomMenuItem;
   private transient JMenuItem undoMenuItem;
   private transient JScrollPane scrollArea;
   private transient JScrollPane scrollinfo;
   protected transient JSplitPane splitpane;
   private transient JTextArea sidebar;
   private transient JTextField ruler;
   private transient JTextLineNumber lineNumbers;
   private transient JToggleButton showHighlightToolButton;
   private transient Object areaHighlight;
   private transient Object rulerHighlight;

   private transient Map<String, JwtSatzart> satzarten =
         SatzartMapper.INSTANCE.mapToJwtSatzartMap(SatzartBuilderComplete.getSatzartMapByKey("vkb"));
   protected transient List<File> recentFiles = new ArrayList<>();
   private transient JwtSatzart aktuelleSatzart;
   private transient JwtDatenFeld aktuellesDatenfeld;
   private transient File filename;
   private transient int savedTextHash;
   protected transient int lastSplitpanePos;
   protected transient int areafontsize = Constants.DEFAULT_FONTSIZE;
   protected transient int sidebarfontsize = Constants.DEFAULT_FONTSIZE;
   protected transient boolean isShowHighlight = true;

   public JwtEditor(String name) {
      createGui();
      if (name.length() > 0) {
         filename = new File(name);
         if (filename.exists()) {
            loadFile();
         }
      }
   }

   private void createGui() {
      UiTool.setLookAndFeel();
      frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      frame.addWindowListener(new java.awt.event.WindowAdapter() {

         @Override
         public void windowClosing(WindowEvent windowEvent) {
            actionFileQuit();
         }
      });

      area.setSelectedTextColor(Color.RED);
      area.addCaretListener(new CaretPositionListener());
      area.getDocument().addUndoableEditListener(undomanager);

      splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
      splitpane.setResizeWeight(1);
      frame.add(splitpane, BorderLayout.CENTER);

      scrollArea = new JScrollPane(area);
      splitpane.setLeftComponent(scrollArea);

      ruler = new JTextField(Constants.TEXT_RULER);
      ruler.setDisabledTextColor(Color.BLACK);
      ruler.setEnabled(false);
      ruler.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
      scrollArea.setColumnHeaderView(ruler);

      lineNumbers = new JTextLineNumber(area);
      scrollArea.setRowHeaderView(lineNumbers);

      sidebar = new JTextArea();
      sidebar.setEnabled(false);
      sidebar.setDisabledTextColor(Color.DARK_GRAY);
      sidebar.setLineWrap(true);

      scrollinfo = new JScrollPane(sidebar);
      splitpane.setRightComponent(scrollinfo);

      infozeile = new JLabel(" ");
      infozeile.setBorder(BorderFactory.createLoweredBevelBorder());
      frame.add(infozeile, BorderLayout.PAGE_END);

      frame.setJMenuBar(buildMainMenu());
      frame.add(buildToolbar(), BorderLayout.PAGE_START);

      frame.setSize(Constants.DEFAULT_FRAME_WIDTH, Constants.DEFAULT_FRAME_HEIGHT);
      splitpane.setDividerLocation(Constants.DEFAULT_DIVIDER_LOCATION);

      PreferenceManager.loadPreferences(this);
      setAreaFont();
      setSidebarFont();
      actionFileNew();
      frame.setVisible(true);

      try {
         rulerHighlight = ruler.getHighlighter().addHighlight(0, 0, ORANGE_HIGHLIGHTER);
         areaHighlight = area.getHighlighter().addHighlight(0, 0, ORANGE_HIGHLIGHTER);
      } catch (BadLocationException e) {
         // Do nothing.
      }
   }

   private JMenuBar buildMainMenu() {
      JMenuBar mainMenu = new JMenuBar();

      JMenu subMenu = new JMenu(Constants.MENUTEXT_FILE);
      mainMenu.add(subMenu);
      subMenu.add(UiTool.buildMenuItem(this, Constants.MENUTEXT_FILE_NEW, Constants.MENU_FILE_NEW));
      subMenu.addSeparator();
      subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_FILE_OPEN,
            Constants.MENU_FILE_OPEN,
            KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.META_DOWN_MASK)));
      recentMenu = new JMenu(Constants.MENUTEXT_FILE_RECENT);
      subMenu.add(recentMenu);
      subMenu.addSeparator();
      saveMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_FILE_SAVE,
            Constants.MENU_FILE_SAVE,
            KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK)));
      subMenu.add(UiTool.buildMenuItem(this, Constants.MENUTEXT_FILE_SAVEAS, Constants.MENU_FILE_SAVEAS));
      subMenu.addSeparator();
      subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_FILE_VALIDATE,
            Constants.MENU_FILE_VALIDATE,
            KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.META_DOWN_MASK)));
      subMenu.addSeparator();
      subMenu.add(UiTool.buildMenuItem(this, Constants.MENUTEXT_FILE_QUIT, Constants.MENU_FILE_QUIT));

      subMenu = new JMenu(Constants.MENUTEXT_EDIT);
      mainMenu.add(subMenu);
      undoMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_UNDO,
            Constants.MENU_EDIT_UNDO,
            KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.META_DOWN_MASK)));
      redoMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_REDO,
            Constants.MENU_EDIT_REDO,
            KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.META_DOWN_MASK)));
      subMenu.addSeparator();
      cutMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_CUT,
            Constants.MENU_EDIT_CUT,
            KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.META_DOWN_MASK)));
      copyMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_COPY,
            Constants.MENU_EDIT_COPY,
            KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.META_DOWN_MASK)));
      pasteMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_PASTE,
            Constants.MENU_EDIT_PASTE,
            KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.META_DOWN_MASK)));
      pasteSpecialMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_PASTE_SPECIAL,
            Constants.MENU_EDIT_PASTE_SPECIAL,
            KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.ALT_DOWN_MASK | InputEvent.META_DOWN_MASK)));
      subMenu.addSeparator();
      subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_FIND,
            Constants.MENU_EDIT_FIND,
            KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.META_DOWN_MASK)));
      subMenu.addSeparator();
      editmodeMenuItem = (JCheckBoxMenuItem) subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_MODE,
            Constants.MENU_EDIT_MODE,
            true,
            KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.META_DOWN_MASK)));
      uppercaseMenuItem = (JCheckBoxMenuItem) subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_UPPERCASE,
            Constants.MENU_EDIT_UPPERCASE,
            true,
            KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.META_DOWN_MASK)));
      subMenu.addSeparator();
      dialogMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_DIALOG,
            Constants.MENU_EDIT_DIALOG,
            KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0)));
      subMenu.addSeparator();
      prevFieldMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_PREV_FIELD,
            Constants.MENU_EDIT_PREV_FIELD,
            KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0)));
      nextFieldMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_NEXT_FIELD,
            Constants.MENU_EDIT_NEXT_FIELD,
            KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0)));
      markFieldMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_EDIT_MARK_FIELD,
            Constants.MENU_EDIT_MARK_FIELD,
            KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0)));

      insertSubMenu = new JMenu(Constants.MENUTEXT_INSERT);
      mainMenu.add(insertSubMenu);
      updateSatzartMenu();

      subMenu = new JMenu(Constants.MENUTEXT_VIEW);
      mainMenu.add(subMenu);
      areazoominMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_VIEW_AREAZOOMIN,
            Constants.MENU_VIEW_AREAZOOMIN,
            KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.META_DOWN_MASK)));
      areazoomoutMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_VIEW_AREAZOOMOUT,
            Constants.MENU_VIEW_AREAZOOMOUT,
            KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.META_DOWN_MASK)));
      areazoomdefMenuItem = subMenu.add(
         UiTool.buildMenuItem(
            this,
            Constants.MENUTEXT_VIEW_AREAZOOMDEF,
            Constants.MENU_VIEW_AREAZOOMDEF,
            KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.META_DOWN_MASK)));
      subMenu.addSeparator();
      sidebarzoominMenuItem = subMenu
            .add(UiTool.buildMenuItem(this, Constants.MENUTEXT_VIEW_SIDEBARZOOMIN, Constants.MENU_VIEW_SIDEBARZOOMIN));
      sidebarzoomoutMenuItem = subMenu.add(
         UiTool.buildMenuItem(this, Constants.MENUTEXT_VIEW_SIDEBARZOOMOUT, Constants.MENU_VIEW_SIDEBARZOOMOUT));
      sidebarzoomdefMenuItem = subMenu.add(
         UiTool.buildMenuItem(this, Constants.MENUTEXT_VIEW_SIDEBARZOOMDEF, Constants.MENU_VIEW_SIDEBARZOOMDEF));
      subMenu.addSeparator();
      sidebarRightMenuItem = subMenu
            .add(UiTool.buildMenuItem(this, Constants.MENUTEXT_VIEW_SIDEBAR_RIGHT, Constants.MENU_VIEW_SIDEBAR_RIGHT));
      sidebarBottomMenuItem = subMenu.add(
         UiTool.buildMenuItem(this, Constants.MENUTEXT_VIEW_SIDEBAR_BOTTOM, Constants.MENU_VIEW_SIDEBAR_BOTTOM));
      subMenu.addSeparator();
      showRulerMenuItem = (JCheckBoxMenuItem) subMenu
            .add(UiTool.buildMenuItem(this, Constants.MENUTEXT_VIEW_RULER, Constants.MENU_VIEW_SHOWRULER, true));
      showLineNumbersMenuItem = (JCheckBoxMenuItem) subMenu.add(
         UiTool.buildMenuItem(this, Constants.MENUTEXT_VIEW_LINENUMBERS, Constants.MENU_VIEW_SHOWLINENUMBERS, true));
      showSidebarMenuItem = (JCheckBoxMenuItem) subMenu
            .add(UiTool.buildMenuItem(this, Constants.MENUTEXT_VIEW_SIDEBAR, Constants.MENU_VIEW_SHOWSIDEBAR, true));
      showFooterMenuItem = (JCheckBoxMenuItem) subMenu
            .add(UiTool.buildMenuItem(this, Constants.MENUTEXT_VIEW_FOOTER, Constants.MENU_VIEW_SHOWFOOTER, true));
      showHighlightMenuItem = (JCheckBoxMenuItem) subMenu.add(
         UiTool.buildMenuItem(this, Constants.MENUTEXT_VIEW_HIGHLIGHT, Constants.MENU_VIEW_SHOWHIGHLIGHT, true));

      subMenu = new JMenu(Constants.MENUTEXT_WINDOW);
      subMenu.add(UiTool.buildMenuItem(this, Constants.MENUTEXT_WINDOW_RESET, Constants.MENU_WINDOW_RESET));
      subMenu.add(UiTool.buildMenuItem(this, Constants.MENUTEXT_WINDOW_ABOUT, Constants.MENU_WINDOW_ABOUT));
      mainMenu.add(subMenu);

      setRecentFileMenu();
      return mainMenu;
   }

   private void updateSatzartMenu() {
      insertSubMenu.removeAll();
      for (String satzart : new TreeSet<>(satzarten.keySet())) {
         if (!satzarten.get(satzart).getDatenfelder().isEmpty()) {
            insertSubMenu.add(
               UiTool.buildMenuItem(
                  this,
                  Constants.MENUTEXT_INSERT_SATZART + satzart,
                  Constants.MENU_INSERT_SATZART,
                  satzart));
         }
      }
   }

   protected void setRecentFileMenu() {
      boolean hasEntries = false;
      recentMenu.removeAll();
      for (File recentFile : recentFiles) {
         if (recentFile.exists()) {
            recentMenu.add(
               UiTool.buildMenuItem(
                  this,
                  recentFile.getName(),
                  Constants.MENU_FILE_RECENT,
                  recentFile.getAbsolutePath()));
            hasEntries = true;
         }
      }
      recentMenu.addSeparator();
      recentMenu
            .add(UiTool.buildMenuItem(this, Constants.MENUTEXT_FILE_RECENT_CLEAR, Constants.MENU_FILE_RECENT_CLEAR));
      recentMenu.setEnabled(hasEntries);
   }

   private void addFilenameToRecentFiles() {
      if (!recentFiles.contains(filename)) {
         if (recentFiles.size() == Constants.MAX_RECENT_FILES) {
            recentFiles.remove(0);
         }
         recentFiles.add(filename);
      }
   }

   private JToolBar buildToolbar() {
      JToolBar toolbar = new JToolBar();
      toolbar.setFloatable(false);
      toolbar.setRollover(true);

      toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "new",
            Constants.MENU_FILE_NEW,
            Constants.MENUTEXT_FILE_NEW,
            Constants.MENUTEXT_FILE_NEW));

      toolbar.addSeparator();

      toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "open",
            Constants.MENU_FILE_OPEN,
            "Datei öffnen",
            Constants.MENUTEXT_FILE_OPEN));

      saveToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "save",
            Constants.MENU_FILE_SAVE,
            "Datei speichern",
            Constants.MENUTEXT_FILE_SAVE));

      toolbar.addSeparator();

      toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "check",
            Constants.MENU_FILE_VALIDATE,
            Constants.MENUTEXT_FILE_VALIDATE,
            Constants.MENUTEXT_FILE_VALIDATE));

      toolbar.addSeparator();

      dialogToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "dialog",
            Constants.MENU_EDIT_DIALOG,
            Constants.MENUTEXT_EDIT_DIALOG,
            Constants.MENUTEXT_EDIT_DIALOG));

      toolbar.addSeparator();

      undoToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "undo",
            Constants.MENU_EDIT_UNDO,
            "Änderung rückgängig machen",
            Constants.MENUTEXT_EDIT_UNDO));
      redoToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "redo",
            Constants.MENU_EDIT_REDO,
            "Rückgängig gemachte Änderung wieder herstellen",
            Constants.MENUTEXT_EDIT_REDO));

      toolbar.addSeparator();

      toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "find",
            Constants.MENU_EDIT_FIND,
            Constants.MENUTEXT_EDIT_FIND,
            Constants.MENUTEXT_EDIT_FIND));

      toolbar.addSeparator();

      prevFieldToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "previous",
            Constants.MENU_EDIT_PREV_FIELD,
            Constants.MENUTEXT_EDIT_PREV_FIELD,
            "Zurück"));
      nextFieldToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "next",
            Constants.MENU_EDIT_NEXT_FIELD,
            Constants.MENUTEXT_EDIT_NEXT_FIELD,
            "Weiter"));

      toolbar.addSeparator();

      markFieldToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "selectall",
            Constants.MENU_EDIT_MARK_FIELD,
            Constants.MENUTEXT_EDIT_MARK_FIELD,
            "Selektieren"));

      toolbar.addSeparator();

      cutToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "cut",
            Constants.MENU_EDIT_CUT,
            Constants.MENUTEXT_EDIT_CUT,
            Constants.MENUTEXT_EDIT_CUT));
      copyToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "copy",
            Constants.MENU_EDIT_COPY,
            Constants.MENUTEXT_EDIT_COPY,
            Constants.MENUTEXT_EDIT_COPY));
      pasteToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "paste",
            Constants.MENU_EDIT_PASTE,
            Constants.MENUTEXT_EDIT_PASTE,
            Constants.MENUTEXT_EDIT_PASTE));
      pasteSpecialToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "pastespecial",
            Constants.MENU_EDIT_PASTE_SPECIAL,
            Constants.MENUTEXT_EDIT_PASTE_SPECIAL,
            Constants.MENUTEXT_EDIT_PASTE_SPECIAL));

      toolbar.addSeparator();

      showHighlightToolButton = (JToggleButton) toolbar.add(
         UiTool.buildToolbarToggleButton(
            this,
            "highlight",
            Constants.MENU_VIEW_SHOWHIGHLIGHT,
            Constants.MENUTEXT_VIEW_HIGHLIGHT,
            Constants.MENUTEXT_VIEW_HIGHLIGHT,
            true));
      toolbar.addSeparator();
      areazoomoutToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "zoomout",
            Constants.MENU_VIEW_AREAZOOMOUT,
            Constants.MENUTEXT_VIEW_AREAZOOMOUT,
            Constants.MENUTEXT_VIEW_AREAZOOMOUT));
      areazoominToolButton = (JButton) toolbar.add(
         UiTool.buildToolbarButton(
            this,
            "zoomin",
            Constants.MENU_VIEW_AREAZOOMIN,
            Constants.MENUTEXT_VIEW_AREAZOOMIN,
            Constants.MENUTEXT_VIEW_AREAZOOMIN));
      toolbar.addSeparator();
      behoerdenAuswahl = (JComboBox<String>) toolbar.add(
         UiTool.buildToolbarComboBox(
            this,
            Constants.MENU_VIEW_CHOOSEBEHOERDE,
            Constants.MENUTEXT_VIEW_CHOOSEBEHOERDE,
            getBehoerdenOptions()));

      return toolbar;
   }

   private String[] getBehoerdenOptions() {
      return new String[] { "vkb", "vwb", "bma" };
   }

   private void setAreaFont() {
      Font font = new Font("monospaced", Font.PLAIN, areafontsize);
      area.setFont(font);
      ruler.setFont(font);
      setEnabledDisabled();
   }

   private void setSidebarFont() {
      Font font = sidebar.getFont();
      sidebar.setFont(new Font(font.getName(), font.getStyle(), sidebarfontsize));
      setEnabledDisabled();
   }

   private void setFrameTitle() {
      StringBuilder title = new StringBuilder();
      title.append(Constants.FRAME_TITLE);
      title.append(" ");
      title.append(Constants.VERSION);
      title.append(" - ");
      if (filename != null) {
         title.append("Datei: ");
         title.append(filename.getAbsolutePath());
      } else {
         title.append("Unbenannter Text");
      }
      if (savedTextHash != area.getText().hashCode()) {
         title.append(" *");
      }
      frame.setTitle(title.toString());
      setEnabledDisabled();
   }

   private void setEnabledDisabled() {
      Transferable transferable = clipboardManager.getContents(null);
      String clipboardStr = "";
      if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
         try {
            clipboardStr = (String) transferable.getTransferData(DataFlavor.stringFlavor);
         } catch (UnsupportedFlavorException | IOException e) {
            // Do nothing
         }
      }
      areazoomdefMenuItem.setEnabled(areafontsize != Constants.DEFAULT_FONTSIZE);
      areazoominMenuItem.setEnabled(areafontsize < Constants.MAXFONTSIZE);
      areazoominToolButton.setEnabled(areafontsize < Constants.MAXFONTSIZE);
      areazoomoutMenuItem.setEnabled(areafontsize > Constants.MINFONTSIZE);
      areazoomoutToolButton.setEnabled(areafontsize > Constants.MINFONTSIZE);
      copyMenuItem.setEnabled(area.getSelectedText() != null && !area.getSelectedText().isEmpty());
      copyToolButton.setEnabled(area.getSelectedText() != null && !area.getSelectedText().isEmpty());
      cutMenuItem.setEnabled(area.getSelectedText() != null && !area.getSelectedText().isEmpty());
      cutToolButton.setEnabled(area.getSelectedText() != null && !area.getSelectedText().isEmpty());
      dialogMenuItem.setEnabled(aktuelleSatzart != null && !Constants.UNBEKANNT.equals(aktuelleSatzart.getSatzart()));
      dialogToolButton.setEnabled(aktuelleSatzart != null && !Constants.UNBEKANNT.equals(aktuelleSatzart.getSatzart()));
      markFieldMenuItem.setEnabled(aktuellesDatenfeld != null && !aktuellesDatenfeld.getNummer().isEmpty());
      markFieldToolButton.setEnabled(aktuellesDatenfeld != null && !aktuellesDatenfeld.getNummer().isEmpty());
      nextFieldMenuItem.setEnabled(area.getCaretPosition() < area.getText().length());
      nextFieldToolButton.setEnabled(area.getCaretPosition() < area.getText().length());
      pasteMenuItem.setEnabled(!clipboardStr.isEmpty());
      pasteSpecialMenuItem.setEnabled(
         !clipboardStr.isEmpty() && aktuellesDatenfeld != null && !aktuellesDatenfeld.getNummer().isEmpty());
      pasteSpecialToolButton.setEnabled(
         !clipboardStr.isEmpty() && aktuellesDatenfeld != null && !aktuellesDatenfeld.getNummer().isEmpty());
      pasteToolButton.setEnabled(!clipboardStr.isEmpty());
      prevFieldMenuItem.setEnabled(area.getCaretPosition() > 0);
      prevFieldToolButton.setEnabled(area.getCaretPosition() > 0);
      redoMenuItem.setEnabled(undomanager.canRedo());
      redoToolButton.setEnabled(undomanager.canRedo());
      saveMenuItem.setEnabled(filename != null);
      saveToolButton.setEnabled(filename != null);
      sidebarBottomMenuItem
            .setEnabled(splitpane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT && showSidebarMenuItem.getState());
      sidebarRightMenuItem
            .setEnabled(splitpane.getOrientation() == JSplitPane.VERTICAL_SPLIT && showSidebarMenuItem.getState());
      sidebarzoomdefMenuItem.setEnabled(sidebarfontsize != Constants.DEFAULT_FONTSIZE);
      sidebarzoominMenuItem.setEnabled(sidebarfontsize < Constants.MAXFONTSIZE);
      sidebarzoomoutMenuItem.setEnabled(sidebarfontsize > Constants.MINFONTSIZE);
      undoMenuItem.setEnabled(undomanager.canUndo());
      undoToolButton.setEnabled(undomanager.canUndo());
   }

   @Override
   @SuppressWarnings("java:S1479")
   public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
         case Constants.MENU_VIEW_CHOOSEBEHOERDE:
            actionChooseBehoerde();
            break;
         case Constants.MENU_FILE_QUIT:
            actionFileQuit();
            break;
         case Constants.MENU_FILE_NEW:
            actionFileNew();
            break;
         case Constants.MENU_FILE_OPEN:
            actionFileOpen();
            break;
         case Constants.MENU_FILE_SAVE:
            actionFileSave();
            break;
         case Constants.MENU_FILE_SAVEAS:
            actionFileSaveAs();
            break;
         case Constants.MENU_FILE_VALIDATE:
            actionFileValidate();
            break;
         case Constants.MENU_FILE_RECENT:
            actionFileRecent(((JMenuItem) e.getSource()).getName());
            break;
         case Constants.MENU_FILE_RECENT_CLEAR:
            actionFileClearRecent();
            break;
         case Constants.MENU_EDIT_DIALOG:
            actionEditDialog();
            break;
         case Constants.MENU_EDIT_UNDO:
            actionEditUndo();
            break;
         case Constants.MENU_EDIT_REDO:
            actionEditRedo();
            break;
         case Constants.MENU_EDIT_CUT:
            area.cut();
            setEnabledDisabled();
            break;
         case Constants.MENU_EDIT_COPY:
            area.copy();
            setEnabledDisabled();
            break;
         case Constants.MENU_EDIT_PASTE:
            area.paste();
            break;
         case Constants.MENU_EDIT_PASTE_SPECIAL:
            actionPasteSpecial();
            break;
         case Constants.MENU_EDIT_FIND:
            actionEditFind();
            break;
         case Constants.MENU_EDIT_MODE:
            actionEditMode(((JCheckBoxMenuItem) e.getSource()).getState());
            break;
         case Constants.MENU_EDIT_UPPERCASE:
            actionUppercaseMode(((JCheckBoxMenuItem) e.getSource()).getState());
            break;
         case Constants.MENU_EDIT_PREV_FIELD:
            actionPrevField();
            break;
         case Constants.MENU_EDIT_NEXT_FIELD:
            actionNextField();
            break;
         case Constants.MENU_EDIT_MARK_FIELD:
            actionMarkField();
            break;
         case Constants.MENU_INSERT_SATZART:
            actionInsertSample(((JMenuItem) e.getSource()).getName());
            break;
         case Constants.MENU_VIEW_AREAZOOMIN:
            actionAreaZoom(areafontsize + 1);
            break;
         case Constants.MENU_VIEW_AREAZOOMOUT:
            actionAreaZoom(areafontsize - 1);
            break;
         case Constants.MENU_VIEW_AREAZOOMDEF:
            actionAreaZoom(Constants.DEFAULT_FONTSIZE);
            break;
         case Constants.MENU_VIEW_SIDEBARZOOMIN:
            actionSidebarZoom(sidebarfontsize + 1);
            break;
         case Constants.MENU_VIEW_SIDEBARZOOMOUT:
            actionSidebarZoom(sidebarfontsize - 1);
            break;
         case Constants.MENU_VIEW_SIDEBARZOOMDEF:
            actionSidebarZoom(Constants.DEFAULT_FONTSIZE);
            break;
         case Constants.MENU_VIEW_SHOWRULER:
            actionViewShowRuler(((JCheckBoxMenuItem) e.getSource()).getState());
            break;
         case Constants.MENU_VIEW_SHOWLINENUMBERS:
            actionViewShowLineNumbers(((JCheckBoxMenuItem) e.getSource()).getState());
            break;
         case Constants.MENU_VIEW_SHOWSIDEBAR:
            actionViewShowSidebar(((JCheckBoxMenuItem) e.getSource()).getState());
            break;
         case Constants.MENU_VIEW_SHOWFOOTER:
            actionViewShowFooter(((JCheckBoxMenuItem) e.getSource()).getState());
            break;
         case Constants.MENU_VIEW_SHOWHIGHLIGHT:
            actionViewShowHighlight(!isShowHighlight);
            break;
         case Constants.MENU_VIEW_SIDEBAR_BOTTOM:
            actionViewSidebarOrientation(JSplitPane.VERTICAL_SPLIT);
            break;
         case Constants.MENU_VIEW_SIDEBAR_RIGHT:
            actionViewSidebarOrientation(JSplitPane.HORIZONTAL_SPLIT);
            break;
         case Constants.MENU_WINDOW_RESET:
            actionWindowReset();
            break;
         case Constants.MENU_WINDOW_ABOUT:
            JAboutBox about = new JAboutBox(frame);
            about.setVisible(true);
            break;
         default:
            break;
      }
   }

   private void actionChooseBehoerde() {

      satzarten = SatzartMapper.INSTANCE
            .mapToJwtSatzartMap(SatzartBuilderComplete.getSatzartMapByKey((String) behoerdenAuswahl.getSelectedItem()));
      updateSatzartMenu();

      JOptionPane.showMessageDialog(
         this,
         "Satzarten wurden erfolgreich aktualisiert.",
         "Information",
         JOptionPane.INFORMATION_MESSAGE);
   }

   private void actionFileQuit() {
      int dialogresult = JOptionPane.YES_OPTION;
      if (savedTextHash != area.getText().hashCode()) {
         dialogresult = JOptionPane.showConfirmDialog(
            this,
            "Die Änderungen wurden noch nicht gespeichert. Trotzdem beenden?",
            Constants.FRAME_TITLE,
            JOptionPane.YES_NO_OPTION);
      }
      if (dialogresult == JOptionPane.YES_OPTION) {
         PreferenceManager.savePreferences(this);
         System.exit(0);
      }
   }

   private void actionFileNew() {
      int dialogresult = JOptionPane.YES_OPTION;
      if (savedTextHash != area.getText().hashCode()) {
         dialogresult = JOptionPane.showConfirmDialog(
            this,
            "Die Änderungen wurden noch nicht gespeichert. Trotzdem verwerfen?",
            Constants.FRAME_TITLE,
            JOptionPane.YES_NO_OPTION);
      }
      if (dialogresult == JOptionPane.YES_OPTION) {
         area.setText("");
         undomanager.discardAllEdits();
         savedTextHash = area.getText().hashCode();
         filename = null;
         setFrameTitle();
      }
   }

   private void actionFileOpen() {
      if (savedTextHash != area.getText().hashCode()) {
         JOptionPane.showMessageDialog(
            this,
            "Die Änderungen wurden noch nicht gespeichert. Vorher speichern oder Neu auswählen!");
         return;
      }
      JFileChooser jfc = new JFileChooser(filename);
      if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
         filename = new File(jfc.getSelectedFile().getAbsolutePath());
         loadFile();
      }
   }

   private void loadFile() {
      try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);
            Scanner scan = new Scanner(reader)) {
         StringBuilder ingest = new StringBuilder();
         while (scan.hasNextLine()) {
            String line = scan.nextLine() + "\n";
            ingest.append(line);
         }
         scan.close();
         area.setText(ingest.toString());
         area.setCaretPosition(0);
         undomanager.discardAllEdits();
         savedTextHash = area.getText().hashCode();
         addFilenameToRecentFiles();
         setRecentFileMenu();
         setFrameTitle();
         actionCaretMoved();
      } catch (IOException ex) {
         ex.printStackTrace();
      }
   }

   private void actionFileSaveAs() {
      JFileChooser jfc = new JFileChooser(filename);
      if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
         filename = new File(jfc.getSelectedFile().getAbsolutePath());
         int dialogresult = JOptionPane.YES_OPTION;
         if (filename.exists()) {
            dialogresult = JOptionPane.showConfirmDialog(
               this,
               "Die Datei existiert bereits, soll sie überschrieben werden?",
               Constants.FRAME_TITLE,
               JOptionPane.YES_NO_OPTION);
         }
         if (dialogresult == JOptionPane.YES_OPTION) {
            actionFileSave();
         }
      }
   }

   private void actionFileSave() {
      if (filename != null) {
         try (FileWriter out = new FileWriter(filename)) {
            out.write(area.getText());
            savedTextHash = area.getText().hashCode();
            addFilenameToRecentFiles();
            setRecentFileMenu();
            setFrameTitle();
         } catch (FileNotFoundException ex) {
            JComponent f = null;
            JOptionPane.showMessageDialog(f, "Datei nicht gefunden.");
         } catch (IOException ex) {
            JComponent f = null;
            JOptionPane.showMessageDialog(f, "Es ist ein Fehler beim Speichern aufgetreten.");
         }
      }
   }

   private void actionFileValidate() {
      String content = area.getText();
      List<String> fileText = Arrays.asList(content.split("\n"));

      MessageValidator messageValidator = switch (String.valueOf(behoerdenAuswahl.getSelectedItem())) {
         case "vkb" -> new VkbMessageValidator(fileText);
         case "vwb" -> new VwbMessageValidator(fileText);
         default -> null;
      };
      
      if (messageValidator == null) {
         JOptionPane.showMessageDialog(frame, "Für diese Satzart ist noch kein Validator hinterlegt. Bitte melde dich beim Support.", "Fehlende Validierung", JOptionPane.INFORMATION_MESSAGE);
         return;
      }
      
      MessageValidationResponse response = messageValidator.validate();

      if (response.hasErrors()) {
         StringBuilder builder = new StringBuilder();
         response.getErrors().stream().forEach(t -> builder.append(t).append("\n"));
         JOptionPane.showMessageDialog(frame, builder.toString(), "Validierungsfehler", JOptionPane.ERROR_MESSAGE);
      } else {
         JOptionPane.showMessageDialog(this.frame, "Keine Fehler gefunden", "Validierung", JOptionPane.PLAIN_MESSAGE);
      }
   }

   private void actionFileRecent(String file) {
      if (savedTextHash != area.getText().hashCode()) {
         JOptionPane.showMessageDialog(
            this,
            "Die Änderungen wurden noch nicht gespeichert. Vorher speichern oder Neu auswählen!");
         return;
      }
      filename = new File(file);
      loadFile();

   }

   private void actionFileClearRecent() {
      recentFiles.clear();
      setRecentFileMenu();
   }

   private void actionPasteSpecial() {
      try {
         Transferable transferable = clipboardManager.getContents(null);
         if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            String insertStr = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            if (insertStr.isEmpty()) {
               return;
            }
            String newValue = aktuellesDatenfeld.buildNewFieldValue(insertStr);
            int dialogresult = JOptionPane.YES_OPTION;
            if (insertStr.length() > aktuellesDatenfeld.getLaenge()) {
               dialogresult = JOptionPane.showConfirmDialog(
                  this,
                  "Der einzufügende Text ist länger als das gewählte Feld und würde gekürzt werden.\nSoll '" + newValue
                     + "' eingefügt werden?",
                  Constants.FRAME_TITLE,
                  JOptionPane.YES_NO_OPTION);
            }
            if (dialogresult == JOptionPane.YES_OPTION) {
               int lineOffset = area.getLineStartOffset(area.getLineOfOffset(area.getCaretPosition()));
               area.replaceRange(
                  newValue,
                  lineOffset + aktuellesDatenfeld.getVon() - 1,
                  lineOffset + aktuellesDatenfeld.getBis());
            }
         }
      } catch (UnsupportedFlavorException | IOException | BadLocationException e) {
         // Do nothing
      }
   }

   private void actionEditDialog() {
      new JSatzartDialog(frame, area, aktuelleSatzart);
   }

   private void actionEditUndo() {
      undomanager.undo();
      if (area.isOvertypeMode() && undomanager.canUndo()) {
         undomanager.undo();
      }
   }

   private void actionEditRedo() {
      undomanager.redo();
      if (area.isOvertypeMode() && undomanager.canRedo()) {
         undomanager.redo();
      }
   }

   private void actionEditFind() {
      searchDialog.setVisible(!searchDialog.isVisible());
   }

   protected void actionEditMode(boolean state) {
      area.setOvertypeMode(state);
   }

   protected void actionUppercaseMode(boolean state) {
      area.setUppercaseMode(state);
   }

   private void actionPrevField() {
      if (aktuelleSatzart != null && aktuellesDatenfeld != null) {
         try {
            JwtDatenFeld prevDatenfeld = aktuelleSatzart.determineDatenfeld(aktuellesDatenfeld.getVon() - 1);
            int caretPosition = area.getCaretPosition();
            int line = area.getLineOfOffset(caretPosition);
            if (prevDatenfeld.getNummer().length() > 0) {
               area.setCaretPosition(area.getLineStartOffset(line) + prevDatenfeld.getVon() - 1);
            } else {
               line = line - ((area.getLineEndOffset(line) - 1 != caretPosition) ? 1 : 0);
               int lineStartOffset = area.getLineStartOffset(line);
               JwtSatzart satzart = determineSatzart(area.getText(lineStartOffset, 50));
               JwtDatenFeld datenfeld = satzart.determineDatenfeld(area.getLineEndOffset(line) - lineStartOffset - 1);
               area.setCaretPosition(lineStartOffset + datenfeld.getVon() - 1);
            }
         } catch (BadLocationException e1) {
            // Do nothing
         }
      }
   }

   private void actionNextField() {
      if (aktuelleSatzart != null && aktuellesDatenfeld != null) {
         try {
            JwtDatenFeld nextDatenfeld = aktuelleSatzart.determineDatenfeld(aktuellesDatenfeld.getBis() + 1);
            if (nextDatenfeld.getNummer().length() > 0) {
               area.setCaretPosition(
                  area.getLineStartOffset(area.getLineOfOffset(area.getCaretPosition())) + nextDatenfeld.getVon() - 1);
            } else {
               area.setCaretPosition(area.getLineStartOffset(area.getLineOfOffset(area.getCaretPosition()) + 1));
            }
         } catch (BadLocationException e1) {
            // Do nothing
         }
      }
   }

   private void actionMarkField() {
      if (aktuelleSatzart != null && aktuellesDatenfeld != null) {
         try {
            int lineStartOffset = area.getLineStartOffset(area.getLineOfOffset(area.getCaretPosition()));
            area.select(
               lineStartOffset + aktuellesDatenfeld.getVon() - 1,
               lineStartOffset + aktuellesDatenfeld.getBis());
         } catch (BadLocationException e1) {
            // Do nothing
         }
      }
   }

   private void actionInsertSample(String satzartKey) {
      try {
         area.insert(
            SatzartBuilderComplete.getSatzartFromMenuEntry(behoerdenAuswahl.getSelectedItem().toString(), satzartKey)
                  .buildSample(),
            area.getLineEndOffset(area.getLineOfOffset(area.getCaretPosition())));
      } catch (BadLocationException e) {
         // Do nothing
      }
   }

   private void actionAreaZoom(int newsize) {
      if (newsize >= Constants.MINFONTSIZE && newsize <= Constants.MAXFONTSIZE) {
         areafontsize = newsize;
         setAreaFont();
      }
   }

   private void actionSidebarZoom(int newsize) {
      if (newsize >= Constants.MINFONTSIZE && newsize <= Constants.MAXFONTSIZE) {
         sidebarfontsize = newsize;
         setSidebarFont();
      }
   }

   protected void actionViewShowFooter(boolean state) {
      infozeile.setVisible(state);
   }

   protected void actionViewShowHighlight(boolean state) {
      isShowHighlight = state;
      showHighlightMenuItem.setState(isShowHighlight);
      showHighlightToolButton.setSelected(isShowHighlight);
      actionCaretMoved();
   }

   protected void actionViewShowRuler(boolean state) {
      if (state) {
         scrollArea.setColumnHeaderView(ruler);
      } else {
         scrollArea.setColumnHeaderView(null);
      }
   }

   protected void actionViewShowLineNumbers(boolean state) {
      if (state) {
         scrollArea.setRowHeaderView(lineNumbers);
      } else {
         scrollArea.setRowHeaderView(null);
      }
   }

   protected void actionViewShowSidebar(boolean state) {
      if (state) {
         splitpane.setRightComponent(scrollinfo);
         splitpane.setDividerSize(new JSplitPane().getDividerSize());
         splitpane.setDividerLocation(lastSplitpanePos);
      } else {
         lastSplitpanePos = splitpane.getDividerLocation();
         splitpane.setRightComponent(null);
         splitpane.setDividerSize(0);
      }
      setEnabledDisabled();
   }

   private void actionViewSidebarOrientation(int newOrientation) {
      splitpane.setOrientation(newOrientation);
      setEnabledDisabled();
   }

   private void actionWindowReset() {
      PreferenceManager.resetPreferences(this);
      setAreaFont();
      setSidebarFont();
      setEnabledDisabled();
   }

   @SuppressWarnings("java:S3398")
   private void actionCaretMoved() {
      try {
         if (areaHighlight != null) {
            area.getHighlighter().changeHighlight(areaHighlight, 0, 0);
            ruler.getHighlighter().changeHighlight(rulerHighlight, 0, 0);
         }
         int caretpos = area.getCaretPosition();
         int line = area.getLineOfOffset(caretpos);
         int col = caretpos - area.getLineStartOffset(line) + 1;

         aktuelleSatzart = determineSatzart(area.getText(area.getLineStartOffset(line), area.getLineEndOffset(line) - area.getLineStartOffset(line)));
         if (aktuelleSatzart == null) {
            infozeile.setText("Satzart ist noch nicht implementiert.");
            return;
         }
         aktuellesDatenfeld = aktuelleSatzart.determineDatenfeld(col);

         if (isShowHighlight) {
            area.getHighlighter()
                  .changeHighlight(
                     areaHighlight,
                     area.getLineStartOffset(line) + aktuellesDatenfeld.getVon() - 1,
                     area.getLineStartOffset(line) + aktuellesDatenfeld.getBis());
            ruler.getHighlighter()
                  .changeHighlight(rulerHighlight, aktuellesDatenfeld.getVon() - 1, aktuellesDatenfeld.getBis());
         }

         String infotext = "Col: " + col + " Satzart: '" + aktuelleSatzart.getBezeichnung() + "' Datenfeld: "
            + aktuellesDatenfeld.getBezeichnung();
         if (aktuellesDatenfeld.getVon() != 0) {
            String inhalt = area.getText(
               area.getLineStartOffset(line) + aktuellesDatenfeld.getVon() - 1,
               aktuellesDatenfeld.getLaenge());
            infotext += " Feldinhalt: '" + inhalt + "'";
         }
         infozeile.setText(infotext);
         if (col == 1) {
            sidebar.setText(aktuelleSatzart.buildInfoPanelAllFields());
         } else {
            sidebar.setText(aktuelleSatzart.buildInfopanelText(aktuellesDatenfeld));
         }
      } catch (BadLocationException e1) {
         infozeile.setText(" ");
         sidebar.setText(" ");
         // aktuelleSatzart = satzarten.get(Constants.UNBEKANNT);
         //aktuellesDatenfeld = aktuelleSatzart.determineDatenfeld(0);
      }
      setFrameTitle();
   }

   @SuppressWarnings("java:S3358")
   private JwtSatzart determineSatzart(String line) {
      return SatzartMapper.INSTANCE.toJwtSatzart(
         SatzartBuilderComplete.getSatzartFromLine(behoerdenAuswahl.getSelectedItem().toString(), line));
   }

   private final class CaretPositionListener implements CaretListener {

      @Override
      public void caretUpdate(CaretEvent e) {
         actionCaretMoved();
      }
   }
}
