package fileeditor.behoerden.validators.general;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.DisplayName;

class ValidValueOrEmptyValidatorTest {

   /**
    * This class tests the `validate` method in the `ValidValueOrEmptyValidator` class. The `validate` method ensures
    * that the input string is either contained within the list of valid values or is empty. If not, an error message is
    * returned. Otherwise, an empty list is returned.
    */

   @ParameterizedTest
   @DisplayName("Gültigkeitsprüfung von Eingaben mit erlaubten Werten oder leerem Feld.")
   @CsvSource({"Value1, ''", "' ', ''",
           "InvalidValue, 'Das Feld darf nur Value1, Value2, Value3 enthalten oder leer sein. Aktueller Wert: InvalidValue'",
           "' Value2 ', ''"})
   void testValidate(String input, String expectedMessage) {
      ValidValueOrEmptyValidator validator = new ValidValueOrEmptyValidator();
      List<String> validValues = List.of("Value1", "Value2", "Value3");

      List<String> result = validator.validate(validValues, input);

      if (expectedMessage.isEmpty()) {
         assertEquals(List.of(), result, "The input is valid or empty, so the result should be an empty list.");
      } else {
         assertEquals(
                 List.of(expectedMessage),
                 result,
                 "The input is invalid, so an error message should be returned.");
      }
   }
}
