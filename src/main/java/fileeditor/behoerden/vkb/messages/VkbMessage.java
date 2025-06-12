package fileeditor.behoerden.vkb.messages;

import fileeditor.behoerden.general.MessageLine;
import fileeditor.behoerden.vkb.editor.VkbEditorLine;
import fileeditor.behoerden.vkb.builder.VkbAtSatzartBuilderComplete;
import fileeditor.behoerden.vkb.validator.param.VkbValidationInputParameters;
import java.util.ArrayList;
import java.util.List;

public class VkbMessage {
   
   String fucode = "";
   List<MessageLine> lines = new ArrayList<>();
   
   public void addLine(VkbEditorLine line) {
      this.lines.add(new MessageLine(new VkbAtSatzartBuilderComplete().fromKey(line.getCompleteLine()).getDatenfelder(), line));
      checkFucode(line);
   }
   
   void checkFucode(VkbEditorLine line) {
      if (line.isSatzart01()) {
         this.fucode = line.getFucode();
      }
   }

   public List<String> validateMessage() {
      VkbValidationInputParameters params = new VkbValidationInputParameters("", fucode);
      return this.lines.stream()
              .flatMap(l -> l.validateLine(params).stream())
              .toList();
   }
}
