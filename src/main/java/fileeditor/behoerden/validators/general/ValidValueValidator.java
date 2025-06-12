package fileeditor.behoerden.validators.general;

import fileeditor.behoerden.utils.StringUtils;
import java.util.List;

public class ValidValueValidator {
   
   public List<String> validate(List<String> validValues, String input) {
      if (hasInvalidValue(validValues, input)) {
         return List.of("Das Feld darf nur " + StringUtils.listToString(validValues) + " enthalten. Aktueller Wert: " + input);
      }
      return List.of();
   }
   
   private boolean hasInvalidValue(List<String> validValues, String input) {
      return !validValues.contains(input);
   }
}
