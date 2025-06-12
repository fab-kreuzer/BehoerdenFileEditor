package fileeditor.behoerden.validators.response;

import java.util.List;

public class MessageValidationResponse {

   private List<String> errors;

   public MessageValidationResponse(List<String> errors) {
      this.errors = errors;
   }

   public boolean hasErrors() {
      return !errors.isEmpty();
   }

   public List<String> getErrors() {
      return errors;
   }
}
