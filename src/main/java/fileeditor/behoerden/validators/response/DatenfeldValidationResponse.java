package fileeditor.behoerden.validators.response;

import fileeditor.behoerden.general.types.Datenfeld;
import java.util.ArrayList;
import java.util.List;

public class DatenfeldValidationResponse {
   
   private List<String> errors = new ArrayList<>();
   private Datenfeld datenfeld;

   public void setErrors(List<String> errors) {
      this.errors = errors;
   }

   public void setDatenfeld(Datenfeld datenfeld) {
      this.datenfeld = datenfeld;
   }
   

   public List<String> buildErrorMessage(int lineNumber) {
      List<String> errorMessages = new ArrayList<>();
      for (String error : this.errors) {
         errorMessages.add("Zeile " + lineNumber + " - Position " + this.datenfeld.getVon() + " - Feld " + this.datenfeld.getBezeichnung() + ": " + error);
      }
      return errorMessages;
   }
   
}
