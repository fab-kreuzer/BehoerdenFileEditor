package fileeditor.behoerden.validators.general;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.general.types.ValidValue;
import fileeditor.behoerden.validators.FieldValidator;
import java.util.ArrayList;
import java.util.List;

public class ValidValueValidatorGeneral implements FieldValidator {
   
   List<String> validValues = new ArrayList<>();
   boolean canBeEmpty;
   
   public ValidValueValidatorGeneral(List<ValidValue> validValues, boolean canBeEmpty) {
      validValues.forEach(validValue -> this.validValues.add(validValue.value()));
      this.canBeEmpty = canBeEmpty;
   }
   
   @Override
   public List<String> validate(ValidationInputParameters input) {
      if (this.canBeEmpty) {
         return new ValidValueOrEmptyValidator().validate(validValues, input.getFieldValue());
      }
      return new ValidValueValidator().validate(validValues, input.getFieldValue());
   }
}
