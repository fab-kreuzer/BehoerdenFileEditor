package fileeditor.behoerden.validators.general;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import java.util.List;

public class NotEmptyValidator implements FieldValidator {
   
   @Override
   public List<String> validate(ValidationInputParameters input) {
      if (isBlank(input.getFieldValue())) {
         return List.of("Das Feld muss gefüllt sein.");
      }
      return List.of();
   }

   private static boolean isBlank(String input) {
      return input.isBlank();
   }
}
