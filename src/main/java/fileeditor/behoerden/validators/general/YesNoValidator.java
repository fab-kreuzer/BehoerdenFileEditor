package fileeditor.behoerden.validators.general;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import java.util.List;

public class YesNoValidator implements FieldValidator {
   
   @Override
   public List<String> validate(ValidationInputParameters input) {
      return new ValidValueValidator().validate(List.of("J", "N"), input.getFieldValue());
   }
}
