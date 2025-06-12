package fileeditor.behoerden.vwb.validator.custom;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import fileeditor.behoerden.validators.general.ValidValueOrEmptyValidator;
import java.util.List;

public class BetriebsartValidator implements FieldValidator {
   @Override
   public List<String> validate(ValidationInputParameters params) {
      return new ValidValueOrEmptyValidator().validate(List.of("T"), params.getFieldValue());
   }
}
