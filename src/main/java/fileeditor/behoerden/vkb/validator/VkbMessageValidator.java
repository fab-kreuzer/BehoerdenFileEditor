package fileeditor.behoerden.vkb.validator;

import fileeditor.behoerden.validators.MessageValidator;
import fileeditor.behoerden.validators.response.MessageValidationResponse;
import fileeditor.behoerden.vkb.editor.VkbEditorLine;
import fileeditor.behoerden.vkb.messages.VkbMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VkbMessageValidator implements MessageValidator {
   
   private final List<String> responseMessages;
   private final Map<String, VkbMessage> messagesMap = new HashMap<>();
   private final List<VkbEditorLine> lines = new ArrayList<>();
   
   public VkbMessageValidator(List<String> messageLines) {
      this.responseMessages = new ArrayList<>();
      for (int i = 0; i < messageLines.size(); i++) {
         lines.add(new VkbEditorLine(messageLines.get(i), i+1));
      }
   }

   @Override
   public MessageValidationResponse validate() {
      createVkbMessage();
      validateEachLine();
      return new MessageValidationResponse(responseMessages);
   }

   private void createVkbMessage() {
      lines.forEach(line -> messagesMap.computeIfAbsent(line.getKeyForGroupingMessage(), v -> new VkbMessage()).addLine(line));
   }

   private void validateEachLine() {
      messagesMap.values()
              .stream()
              .map(VkbMessage::validateMessage)
              .forEach(responseMessages::addAll);
   }
}
