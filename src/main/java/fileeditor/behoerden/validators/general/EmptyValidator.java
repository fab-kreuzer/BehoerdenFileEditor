package fileeditor.behoerden.validators.general;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import java.util.List;

public class EmptyValidator implements FieldValidator {
   
   @Override
   public List<String> validate(ValidationInputParameters input) {
      if (isNotEmpty(input.getFieldValue())) {
         return List.of("Das Feld muss leer sein. Aktueller Wert: " + input.getFieldValue());
      }
      return List.of();
   }

   private static boolean isNotEmpty(String input) {
      return !input.isBlank();
   }
}
