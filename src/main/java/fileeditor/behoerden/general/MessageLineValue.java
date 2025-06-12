package fileeditor.behoerden.general;

import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.validators.response.DatenfeldValidationResponse;

public class MessageLineValue {
   
   private String value;
   private Datenfeld datenfeld;
   
   public MessageLineValue(String value, Datenfeld datenfeld) {
    this.value = value;
    this.datenfeld = datenfeld;
   }

   public Datenfeld getDatenfeld() {
      return datenfeld;
   }

   public void setDatenfeld(Datenfeld datenfeld) {
      this.datenfeld = datenfeld;
   }
   
   public DatenfeldValidationResponse validateValue(ValidationInputParameters parameters) {
      parameters.setFieldValue(value);
      return this.datenfeld.validate(parameters);
   }
}
