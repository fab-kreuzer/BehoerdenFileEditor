package fileeditor.ui.jwt;

import fileeditor.FileEditor;
import java.awt.Frame;
import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JSplitPane;

public class PreferenceManager {

   private static final Preferences prefs = Preferences.userNodeForPackage(FileEditor.class);
   private static final String FRAME_WIDTH = "FRAME_WIDTH";
   private static final String FRAME_HEIGHT = "FRAME_HEIGHT";
   private static final String FRAME_X = "FRAME_X";
   private static final String FRAME_Y = "FRAME_Y";
   private static final String FRAME_STATE = "FRAME_STATE";
   private static final String SHOW_HIGHLIGHT = "SHOW_HIGHLIGHT";
   private static final String AREA_FONTSIZE = "AREA_FONTSIZE";
   private static final String SIDEBAR_ORIENTATION = "SIDEBAR_ORIENTATION";
   private static final String SIDEBAR_FONTSIZE = "SIDEBAR_FONTSIZE";
   private static final String SIDEBAR_VISIBLE = "SIDEBAR_VISIBLE";
   private static final String DIVIDER_LOCATION = "DIVIDER_LOCATION";
   private static final String RULER_VISIBLE = "RULER_VISIBLE";
   private static final String LINENUMBERS_VISIBLE = "LINENUMBERS_VISIBLE";
   private static final String FOOTER_VISIBLE = "FOOTER_VISIBLE";
   private static final String AREA_EDITMODE = "AREA_EDITMODE";
   private static final String AREA_UPPERCASEMODE = "AREA_UPPERCASEMODE";
   private static final String RECENT_FILES = "RECENT_FILES_";

   private PreferenceManager() {
      // Nope
   }

   public static void loadPreferences(JwtEditor jwtEditor) {
      jwtEditor.frame.setLocation(prefs.getInt(FRAME_X, 0), prefs.getInt(FRAME_Y, 0));
      jwtEditor.frame.setSize(
         prefs.getInt(FRAME_WIDTH, Constants.DEFAULT_FRAME_WIDTH),
         prefs.getInt(FRAME_HEIGHT, Constants.DEFAULT_FRAME_HEIGHT));
      jwtEditor.frame.setExtendedState(prefs.getInt(FRAME_STATE, Frame.NORMAL));

      jwtEditor.isShowHighlight = prefs.getBoolean(SHOW_HIGHLIGHT, true);
      jwtEditor.actionViewShowHighlight(jwtEditor.isShowHighlight);
      jwtEditor.areafontsize = prefs.getInt(AREA_FONTSIZE, Constants.DEFAULT_FONTSIZE);
      jwtEditor.sidebarfontsize = prefs.getInt(SIDEBAR_FONTSIZE, Constants.DEFAULT_FONTSIZE);

      jwtEditor.showRulerMenuItem.setState(prefs.getBoolean(RULER_VISIBLE, true));
      jwtEditor.actionViewShowRuler(jwtEditor.showRulerMenuItem.getState());

      jwtEditor.showLineNumbersMenuItem.setState(prefs.getBoolean(LINENUMBERS_VISIBLE, true));
      jwtEditor.actionViewShowLineNumbers(jwtEditor.showLineNumbersMenuItem.getState());

      jwtEditor.showFooterMenuItem.setState(prefs.getBoolean(FOOTER_VISIBLE, true));
      jwtEditor.actionViewShowFooter(jwtEditor.showFooterMenuItem.getState());

      jwtEditor.splitpane.setOrientation(prefs.getInt(SIDEBAR_ORIENTATION, JSplitPane.HORIZONTAL_SPLIT));
      jwtEditor.splitpane.setDividerLocation(prefs.getInt(DIVIDER_LOCATION, Constants.DEFAULT_DIVIDER_LOCATION));
      boolean isSidebarVisible = prefs.getBoolean(SIDEBAR_VISIBLE, true);
      if (!isSidebarVisible) {
         jwtEditor.actionViewShowSidebar(false);
      } else if (!jwtEditor.showSidebarMenuItem.getState()) {
         jwtEditor.actionViewShowSidebar(true);
      }
      jwtEditor.showSidebarMenuItem.setState(isSidebarVisible);

      jwtEditor.editmodeMenuItem.setState(prefs.getBoolean(AREA_EDITMODE, true));
      jwtEditor.actionEditMode(jwtEditor.editmodeMenuItem.getState());

      jwtEditor.uppercaseMenuItem.setState(prefs.getBoolean(AREA_UPPERCASEMODE, true));
      jwtEditor.actionUppercaseMode(jwtEditor.uppercaseMenuItem.getState());

      for (int i = 0; i < Constants.MAX_RECENT_FILES; i++) {
         String filename = prefs.get(RECENT_FILES + i, "");
         if (!filename.isEmpty()) {
            File file = new File(filename);
            if (file.exists()) {
               jwtEditor.recentFiles.add(file);
            }
         }
      }
      jwtEditor.setRecentFileMenu();
   }

   public static void savePreferences(JwtEditor jwtEditor) {
      clearPrefs();
      if (jwtEditor.frame.getExtendedState() == Frame.NORMAL) {
         prefs.putInt(FRAME_WIDTH, jwtEditor.frame.getWidth());
         prefs.putInt(FRAME_HEIGHT, jwtEditor.frame.getHeight());
         prefs.putInt(FRAME_X, jwtEditor.frame.getX());
         prefs.putInt(FRAME_Y, jwtEditor.frame.getY());
      }
      if (jwtEditor.frame.getExtendedState() != Frame.ICONIFIED) {
         prefs.putInt(FRAME_STATE, jwtEditor.frame.getExtendedState());
      }
      prefs.putBoolean(SHOW_HIGHLIGHT, jwtEditor.isShowHighlight);
      prefs.putInt(AREA_FONTSIZE, jwtEditor.areafontsize);
      prefs.putInt(SIDEBAR_FONTSIZE, jwtEditor.sidebarfontsize);

      prefs.putBoolean(RULER_VISIBLE, jwtEditor.showRulerMenuItem.getState());
      prefs.putBoolean(LINENUMBERS_VISIBLE, jwtEditor.showLineNumbersMenuItem.getState());
      prefs.putBoolean(FOOTER_VISIBLE, jwtEditor.showFooterMenuItem.getState());
      prefs.putInt(SIDEBAR_ORIENTATION, jwtEditor.splitpane.getOrientation());
      prefs.putBoolean(SIDEBAR_VISIBLE, jwtEditor.showSidebarMenuItem.getState());
      if (jwtEditor.showSidebarMenuItem.getState()) {
         prefs.putInt(DIVIDER_LOCATION, jwtEditor.splitpane.getDividerLocation());
      } else {
         prefs.putInt(DIVIDER_LOCATION, jwtEditor.lastSplitpanePos);
      }
      prefs.putBoolean(AREA_EDITMODE, jwtEditor.editmodeMenuItem.getState());
      prefs.putBoolean(AREA_UPPERCASEMODE, jwtEditor.uppercaseMenuItem.getState());

      for (int i = 0; i < jwtEditor.recentFiles.size(); i++) {
         prefs.put(RECENT_FILES + i, jwtEditor.recentFiles.get(i).getAbsolutePath());
      }
   }

   public static void resetPreferences(JwtEditor jwtEditor) {
      clearPrefs();
      loadPreferences(jwtEditor);
   }

   private static void clearPrefs() {
      try {
         prefs.clear();
      } catch (BackingStoreException e) {
         e.printStackTrace();
      }
   }
}
