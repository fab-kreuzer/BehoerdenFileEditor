package fileeditor.behoerden.validators;

import fileeditor.behoerden.general.ValidationInputParameters;
import java.util.List;

public interface FieldValidator {

   String REGEX_CHAR = "[a-zA-Z]";
   String REGEX_NUM = "[0-9]";

   List<String> validate(ValidationInputParameters params);
}
