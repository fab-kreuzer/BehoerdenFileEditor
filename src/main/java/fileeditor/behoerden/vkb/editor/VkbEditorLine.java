package fileeditor.behoerden.vkb.editor;

import fileeditor.behoerden.general.EditorLine;
import fileeditor.behoerden.vkb.builder.VkbSatzartId;

public class VkbEditorLine extends EditorLine {

   public VkbEditorLine(String line, int number) {
      super(line, number);
   }

   public String getKeyForGroupingMessage() {
      return super.line.substring(0,14);
   }

   public String getKeyForLineInMessage() {
      if (super.line.length() < 24) {
         return super.line.substring(16,22);
      }
      return super.line.substring(16,24);
   }
   
   public boolean isSatzart01() {
      return getKeyForLineInMessage().equals(VkbSatzartId.KZRSTA01.getId());
   }
   
   public String getFucode() {
      return super.line.substring(24,26);
   }

}
