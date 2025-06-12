package fileeditor.behoerden.general;

import fileeditor.behoerden.general.types.Datenfeld;
import java.util.ArrayList;
import java.util.List;

public class MessageLine {

   private List<MessageLineValue> values = new ArrayList<>();
   private int lineNumber;
   
   public MessageLine(List<Datenfeld> datenfelder, EditorLine line) {
      this.lineNumber = line.getNumber();
      for (Datenfeld datenfeld : datenfelder) {
         values.add(new MessageLineValue(line.getValueFromDatenfeld(datenfeld), datenfeld));
      }
   }

   public List<String> validateLine(ValidationInputParameters params) {
      List<String> errors = new ArrayList<>();
      for (var value : values) {
         errors.addAll(value.validateValue(params).buildErrorMessage(lineNumber));
      }
      return errors;
   }

   public List<String> validateLine() {
      List<String> errors = new ArrayList<>();
      for (var value : values) {
         errors.addAll(value.validateValue(new ValidationInputParameters()).buildErrorMessage(lineNumber));
      }
      return errors;
   }
}
