package fileeditor.behoerden.vwb.validator.custom;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import java.util.List;

public class LicencePlateValidator implements FieldValidator {
   @Override
   public List<String> validate(ValidationInputParameters params) {
      if (isValid(params.getFieldValue())) {
         return List.of();
      }
      return List.of("Das Amtliche Kennzeichen (AKZ) muss in der Form '4 2 4' (vier Buchstaben, zwei Buchstaben sowie vier Stellen Zahlen) vorliegen. Aktueller Wert: " + params.getFieldValue());
   }

   private boolean isValid(String fieldValue) {
      return fieldValue.isEmpty() || (fieldValue.length() == 10 && fieldValue.substring(0, 4).trim().replaceAll(REGEX_CHAR, "").isEmpty()
              && fieldValue.substring(4, 6).trim().replaceAll(REGEX_CHAR, "").isEmpty()
              && fieldValue.substring(6, 10).trim().replaceAll(REGEX_NUM, "").isEmpty());

   }
}
