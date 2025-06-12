package fileeditor.behoerden.vwb.validator.custom;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import java.time.Year;
import java.util.List;

public class RabattgrundjahrValidator implements FieldValidator {
   @Override
   public List<String> validate(ValidationInputParameters params) {
      if (isNotValid(params.getFieldValue())){
         return List.of("Das Rabattgrundjahr muss kleiner oder gleich dem aktuellen Jahr +1 sein. Außerdem muss der angegebene Wert größer als 1900 sein.");
      }
      return List.of();
   }

   private boolean isNotValid(String rbj) {
      if (rbj.isEmpty()) {
         return false;
      }
      Year jahr = Year.of(Integer.parseUnsignedInt(rbj));
      return jahr.isAfter(Year.now().plusYears(1)) || !jahr.isAfter(Year.of(1900));
   }
}
