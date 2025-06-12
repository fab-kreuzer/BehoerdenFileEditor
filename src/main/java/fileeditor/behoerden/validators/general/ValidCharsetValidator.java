package fileeditor.behoerden.validators.general;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import java.util.ArrayList;
import java.util.List;

public class ValidCharsetValidator implements FieldValidator {

   private static final String VALID_CHARS_REGEX =
           "[ ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789\\.\\+&*-/,'\"\\(\\);\\?!:<>=§@\n]";

   @Override
   public List<String> validate(ValidationInputParameters input) {
      if (!getInvalidCharacter(input.getFieldValue().toUpperCase()).isEmpty()) {
         return List.of("Das Feld enthält ungültigen Zeichen: " + getInvalidCharacter(input.getFieldValue().toUpperCase()) + " ");
      }
      return List.of();
   }

   private List<Character> getInvalidCharacter(String input) {
      List<Character> invalidChars = new ArrayList<>();
      if (input != null) {
         for (char c : input.toCharArray()) {
            if (!String.valueOf(c).matches(VALID_CHARS_REGEX)) {
               invalidChars.add(c);
            }
         }
      }
      return invalidChars;
   }
}
