package fileeditor.behoerden.utils;

import java.util.List;

public class StringUtils {
   
   public static String listToString(List<String> input) {
      return String.join(", ", input.stream().filter(value -> !value.isBlank()).toList());
   }
}
