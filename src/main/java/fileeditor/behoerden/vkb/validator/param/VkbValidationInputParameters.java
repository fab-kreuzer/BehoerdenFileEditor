package fileeditor.behoerden.vkb.validator.param;

import fileeditor.behoerden.general.ValidationInputParameters;

public class VkbValidationInputParameters extends ValidationInputParameters {
   
   String fucode;
   
   public VkbValidationInputParameters(String fieldValue, String fucode) {
      super(fieldValue);
      this.fucode = fucode;
   }

   public String getFucode() {
      return fucode;
   }
}
