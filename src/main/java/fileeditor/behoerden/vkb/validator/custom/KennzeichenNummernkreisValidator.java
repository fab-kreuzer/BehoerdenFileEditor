package fileeditor.behoerden.vkb.validator.custom;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import fileeditor.behoerden.validators.general.ValidValueValidator;
import java.util.List;

public class KennzeichenNummernkreisValidator implements FieldValidator {
   
   List<String> validValues = List.of("-", "0", "1", "2", "9");
   
   @Override
   public List<String> validate(ValidationInputParameters input) {
      return new ValidValueValidator().validate(validValues, input.getFieldValue());
   }
}
