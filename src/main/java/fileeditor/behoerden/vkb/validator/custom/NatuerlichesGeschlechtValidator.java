package fileeditor.behoerden.vkb.validator.custom;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import fileeditor.behoerden.validators.general.ValidValueValidator;
import java.util.List;

public class NatuerlichesGeschlechtValidator extends ValidValueValidator implements FieldValidator {
   
   @Override
   public List<String> validate(ValidationInputParameters input) {
      return super.validate(List.of("1", "2", "4", "5", "6", "7", "9"), input.getFieldValue());
   }
}