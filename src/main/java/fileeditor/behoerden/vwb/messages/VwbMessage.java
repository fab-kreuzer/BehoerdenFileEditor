package fileeditor.behoerden.vwb.messages;

import fileeditor.behoerden.general.MessageLine;
import fileeditor.behoerden.vwb.builder.VwbSatzartBuilderComplete;
import fileeditor.behoerden.vwb.editor.VwbEditorLine;
import java.util.ArrayList;
import java.util.List;

public class VwbMessage {
   
   String fucode = "";
   List<MessageLine> lines = new ArrayList<>();

   public void addLine(VwbEditorLine line) {
      this.lines.add(new MessageLine(new VwbSatzartBuilderComplete().fromKey(line.getCompleteLine()).getDatenfelder(), line));
      checkFucode(line);
   }

   void checkFucode(VwbEditorLine line) {
   }

   public List<String> validateMessage() {
      return this.lines.stream()
              .flatMap(l -> l.validateLine().stream())
              .toList();
   }

}
