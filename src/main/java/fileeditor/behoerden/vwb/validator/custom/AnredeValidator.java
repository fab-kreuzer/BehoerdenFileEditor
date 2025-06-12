package fileeditor.behoerden.vwb.validator.custom;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import fileeditor.behoerden.validators.general.ValidValueValidator;
import java.util.List;

public class AnredeValidator implements FieldValidator {
   @Override
   public List<String> validate(ValidationInputParameters params) {
      return new ValidValueValidator().validate(List.of("0", "1", "2", "3", "4"), params.getFieldValue());
   }
}
