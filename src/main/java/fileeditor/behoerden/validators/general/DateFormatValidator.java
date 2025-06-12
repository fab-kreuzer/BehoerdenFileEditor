package fileeditor.behoerden.validators.general;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateFormatValidator implements FieldValidator {
   
   String dateFormat;
   
   public DateFormatValidator(String dateFormat) {
      this.dateFormat = dateFormat;
   }
   
   public DateFormatValidator() {
      this("ddMMyyyy");
   }
   
   @Override
   public List<String> validate(ValidationInputParameters input) {
      if (isNoValidDate(input.getFieldValue())) {
         return List.of("Das Feld muss dem Datumsformat \"" + this.dateFormat + "\" entsprechen. Aktueller Wert: " + input.getFieldValue());
      }
      return List.of();
   }

   private boolean isNoValidDate(String input) {
      if (input.isBlank()) {
         return false;
      }
      SimpleDateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
      dateFormat.setLenient(false);

      try {
         dateFormat.parse(input);
         return false;
      } catch (ParseException e) {
         return true;
      }
   }
}
