package fileeditor.behoerden.validators.general;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import java.util.List;

public class NumberValidator implements FieldValidator {

   @Override
   public List<String> validate(ValidationInputParameters input) {
      String fieldValue = input.getFieldValue().trim();

      if (fieldValue.isEmpty()) {
         return List.of();
      }

      if (isNotANumber(fieldValue)) {
         return List.of("Das Feld darf nur Ziffern enthalten. Aktueller Wert: " + input.getFieldValue());
      }

      return List.of();
   }

   private boolean isNotANumber(String input) {
      return !input.matches("\\d+");
   }
}
