package fileeditor.behoerden.validators;

import fileeditor.behoerden.general.ValidationInputParameters;
import java.util.Collections;
import java.util.List;

public class DefaultFieldValidator implements FieldValidator {
   @Override
   public List<String> validate(ValidationInputParameters input) {
      return Collections.emptyList();
   }
}
