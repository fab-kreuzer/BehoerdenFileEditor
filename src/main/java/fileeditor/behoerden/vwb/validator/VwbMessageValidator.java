package fileeditor.behoerden.vwb.validator;

import fileeditor.behoerden.validators.MessageValidator;
import fileeditor.behoerden.validators.response.MessageValidationResponse;
import fileeditor.behoerden.vwb.builder.VwbSatzartBuilderComplete;
import fileeditor.behoerden.vwb.editor.VwbEditorLine;
import fileeditor.behoerden.vwb.messages.VwbMessage;
import fileeditor.ui.jwt.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VwbMessageValidator implements MessageValidator {
   
   private final List<String> responseMessages;
   private final Map<String, VwbMessage> messagesMap = new HashMap<>();
   private final List<VwbEditorLine> lines = new ArrayList<>();

   public VwbMessageValidator(List<String> messageLines) {
      this.responseMessages = new ArrayList<>();
      for (int i = 0; i < messageLines.size(); i++) {
         lines.add(new VwbEditorLine(messageLines.get(i), i+1));
      }
   }

   @Override
   public MessageValidationResponse validate() {
      createVwbMessage();
      validateAllMessages();
      lines.forEach(this::validateSingleLine);
      return new MessageValidationResponse(responseMessages);
   }

   private void createVwbMessage() {
      lines.forEach(line -> messagesMap.computeIfAbsent(line.getKeyForGroupingMessage(), v -> new VwbMessage()).addLine(line));
   }

   private void validateSingleLine(VwbEditorLine line) {
     checkLength(line);
     checkSupportedSatzart(line);
     checkKontrolleBVPosition(line);
   }

   private void checkSupportedSatzart(VwbEditorLine line) {
      if (new VwbSatzartBuilderComplete().fromKey(line.getCompleteLine()).getSatzart().equals(Constants.UNBEKANNT)) {
         responseMessages.add("Zeile " + line.getNumber() + ": Diese Satzart wird für VWB nicht unterstützt. Bitte eine gültige Satzart angeb.");
      }
   }

   private void checkKontrolleBVPosition(VwbEditorLine line) {
      if (line.getCompleteLine().startsWith("KONTROLLE BV") && line.getNumber() != 1) {
         responseMessages.add("Die Zeile \"KONTROLLE BV\" muss in Zeile 1 stehen. Aktuelle Zeile: " + line.getNumber());
      }
   }

   private void checkLength(VwbEditorLine line) {
      int length = line.getCompleteLine().replace("\n", "").length();
      if (length != 255) {
         responseMessages.add("Zeile " + line.getNumber() + ": Die Zeile muss genau 255 Zeichen lang sein. Tatsächliche Länge: " + length);
      }
   }

   private void validateAllMessages() {
      messagesMap.values()
              .stream()
              .map(VwbMessage::validateMessage)
              .forEach(responseMessages::addAll);
   }
}
