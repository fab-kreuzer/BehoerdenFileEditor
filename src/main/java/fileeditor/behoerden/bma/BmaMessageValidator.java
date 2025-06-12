package fileeditor.behoerden.bma;

import fileeditor.behoerden.validators.MessageValidator;
import fileeditor.behoerden.validators.response.MessageValidationResponse;
import java.util.Collections;

public class BmaMessageValidator implements MessageValidator {

   @Override
   public MessageValidationResponse validate() {
      return new MessageValidationResponse(Collections.emptyList());
   }
}
