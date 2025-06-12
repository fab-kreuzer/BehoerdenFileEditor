package fileeditor.behoerden.general;

public class ValidationInputParameters {
   
   String fieldValue;
   
   public ValidationInputParameters(String fieldValue) {
      this.fieldValue = fieldValue;
   }
   public ValidationInputParameters() {
      this.fieldValue = "";
   }

   public String getFieldValue() {
      return fieldValue;
   }
   
   public void setFieldValue(String fieldValue) {
      this.fieldValue = fieldValue;
   }
   
}
