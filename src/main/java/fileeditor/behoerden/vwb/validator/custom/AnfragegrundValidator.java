package fileeditor.behoerden.vwb.validator.custom;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import fileeditor.behoerden.validators.general.ValidValueValidator;
import java.util.List;

public class AnfragegrundValidator implements FieldValidator {
   @Override
   public List<String> validate(ValidationInputParameters params) {
      return new ValidValueValidator().validate(List.of("01", "02", "03", "04", "05", "07", "08"), params.getFieldValue());
   }
}
