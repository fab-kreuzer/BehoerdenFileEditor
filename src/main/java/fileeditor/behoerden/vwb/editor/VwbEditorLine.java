package fileeditor.behoerden.vwb.editor;

import fileeditor.behoerden.general.EditorLine;

public class VwbEditorLine extends EditorLine {
   
   public VwbEditorLine(String line, int number) {
      super(line, number);
   }
   
   public String getKeyForGroupingMessage() {
      return getKeyForLineInMessage();
   }

   public String getKeyForLineInMessage() {
      String key = super.line.substring(0, 12);

      if (isKontrolleKey(key)) {
         return key;
      }
      return key.substring(0,2);
   }
   
   private static boolean isKontrolleKey(String key) {
      return key.startsWith("KONTROLLE ");
   }

}
