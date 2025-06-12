package fileeditor.behoerden.vkb.validator.custom;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import fileeditor.behoerden.validators.general.ValidValueValidator;
import java.util.List;

public class FuCodeValidator extends ValidValueValidator implements FieldValidator {
   
   List<String> validValues = List.of("01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
           "11", "12", "20", "21", "22", "23", "24", "30", "31", "32",
           "33", "34", "35", "36", "37", "41", "43");
   
   @Override
   public List<String> validate(ValidationInputParameters input) {
      return super.validate(validValues, input.getFieldValue());
   }
}
