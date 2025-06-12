package fileeditor.behoerden.validators.general;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class ValidValueValidatorTest {

   /**
    * Tests for the ValidValueValidator class. The validate method checks if the input string conforms to the list of
    * valid values. If the input is invalid, the method returns a descriptive error message; otherwise, it returns an
    * empty list.
    */

   @Test
   @DisplayName("Gültige Eingabe: Erwartet leere Fehlerliste")
   void testValidate_withValidValue_returnsEmptyList() {
      // Arrange
      ValidValueValidator validator = new ValidValueValidator();
      List<String> validValues = List.of("ADMIN", "USER", "GUEST");
      String input = "USER";

      // Act
      List<String> result = validator.validate(validValues, input);

      // Assert
      assertEquals(List.of(), result, "Expected no errors when input is in valid values.");
   }

   @Test
   @DisplayName("Ungültige Eingabe: Erwartet eine Fehlermeldung")
   void testValidate_withInvalidValue_returnsErrorMessage() {
      // Arrange
      ValidValueValidator validator = new ValidValueValidator();
      List<String> validValues = List.of("ADMIN", "USER", "GUEST");
      String input = "MANAGER";

      // Act
      List<String> result = validator.validate(validValues, input);

      // Assert
      assertEquals(
         List.of("Das Feld darf nur ADMIN, USER, GUEST enthalten. Aktueller Wert: MANAGER"),
         result,
         "Expected an error message indicating the valid values and the invalid input.");
   }

   @Test
   @DisplayName("Leere Eingabe: Erwartet Fehlermeldung")
   void testValidate_withEmptyInput_returnsErrorMessage() {
      // Arrange
      ValidValueValidator validator = new ValidValueValidator();
      List<String> validValues = List.of("YES", "NO");
      String input = "";

      // Act
      List<String> result = validator.validate(validValues, input);

      // Assert
      assertEquals(
         List.of("Das Feld darf nur YES, NO enthalten. Aktueller Wert: "),
         result,
         "Expected an error message indicating the valid values and the empty input.");
   }

   @Test
   @DisplayName("Leere gültige Werte: Erwartet Fehlermeldung")
   void testValidate_withEmptyValidValues_returnsErrorMessage() {
      // Arrange
      ValidValueValidator validator = new ValidValueValidator();
      List<String> validValues = List.of();
      String input = "ANY_VALUE";

      // Act
      List<String> result = validator.validate(validValues, input);

      // Assert
      assertEquals(
         List.of("Das Feld darf nur  enthalten. Aktueller Wert: ANY_VALUE"),
         result,
         "Expected an error message indicating no valid values and the invalid input.");
   }
}
