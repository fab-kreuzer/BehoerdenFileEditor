package fileeditor.behoerden.vkb.validator.custom;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import fileeditor.behoerden.validators.general.ValidValueValidator;
import java.util.List;

public class JuristischesGeschlechtValidator extends ValidValueValidator implements FieldValidator {
   
   @Override
   public List<String> validate(ValidationInputParameters input) {
      return super.validate(List.of("3"), input.getFieldValue());
   }
}