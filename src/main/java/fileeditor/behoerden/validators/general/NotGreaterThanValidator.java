package fileeditor.behoerden.validators.general;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import java.util.List;

public class NotGreaterThanValidator implements FieldValidator {
   
   int max;
   
   public NotGreaterThanValidator(int max) {
      this.max = max;
   }
   
   @Override
   public List<String> validate(ValidationInputParameters params) {
      if ( params.getFieldValue().isEmpty() || Integer.parseUnsignedInt(params.getFieldValue()) > max ) {
         return List.of("Der Wert darf maximal " + max + " sein. Aktueller Wert:" + params.getFieldValue());
      }
      return List.of();
   }
}
