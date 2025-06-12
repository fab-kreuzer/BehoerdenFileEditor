package fileeditor.behoerden.vkb.validator.custom;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import fileeditor.behoerden.validators.general.EmptyValidator;
import fileeditor.behoerden.validators.general.ValidValueValidator;
import fileeditor.behoerden.vkb.validator.param.VkbValidationInputParameters;
import java.util.List;

public class ZulassungStatusValidator implements FieldValidator {
   
   List<String> validValues = List.of("AN", "AB", "AU", "HI");

   @Override
   public List<String> validate(ValidationInputParameters input) {
      String fucode = ((VkbValidationInputParameters) input).getFucode();
      if (isMotorSteuerBefreiungFuCode(fucode)) {
         return new EmptyValidator().validate(input);
      }
      return new ValidValueValidator().validate(validValues, input.getFieldValue());
   }

   private static boolean isMotorSteuerBefreiungFuCode(String fucode) {
      return List.of("35", "36", "37").contains(fucode);
   }
}
