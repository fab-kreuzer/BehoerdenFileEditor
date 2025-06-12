package fileeditor.behoerden.validators.general;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import fileeditor.behoerden.general.ValidationInputParameters;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberValidatorTest {

   /**
    * Test class for the NumberValidator class. Ensures that the validate method works as expected by checking various
    * inputs.
    */

   @Test
   @DisplayName("G�ltige Eingabe: Validation sollte f�r valide numerische Eingaben erfolgreich sein")
   void testValidNumberInput() {
      NumberValidator validator = new NumberValidator();
      ValidationInputParameters inputParams = new ValidationInputParameters("12345");

      List<String> result = validator.validate(inputParams);

      assertTrue(result.isEmpty(), "Validation should pass for valid numeric input.");
   }

   @Test
   @DisplayName("Ung�ltige Eingabe: Validation sollte f�r nicht-numerische Eingaben einen Fehler zur�ckgeben")
   void testInvalidNumberInput() {
      NumberValidator validator = new NumberValidator();
      ValidationInputParameters inputParams = new ValidationInputParameters("abc123");

      List<String> result = validator.validate(inputParams);

      assertEquals(1, result.size(), "Validation should return one error for invalid non-numeric input.");
      assertEquals(
              "Das Feld darf nur Ziffern enthalten. Aktueller Wert: abc123",
              result.get(0),
              "Error message should match the format for invalid input.");
   }

   @Test
   @DisplayName("Leere Eingabe: Validation sollte f�r leere Eingaben einen Fehler zur�ckgeben")
   void testEmptyInput() {
      NumberValidator validator = new NumberValidator();
      ValidationInputParameters inputParams = new ValidationInputParameters("");

      List<String> result = validator.validate(inputParams);

      assertTrue(result.isEmpty(), "Validation should pass for empty input.");
   }

   @Test
   @DisplayName("Eingabe mit Sonderzeichen: Validation sollte f�r Eingaben mit Sonderzeichen einen Fehler zur�ckgeben")
   void testInputWithSpecialCharacters() {
      NumberValidator validator = new NumberValidator();
      ValidationInputParameters inputParams = new ValidationInputParameters("123@#");

      List<String> result = validator.validate(inputParams);

      assertEquals(1, result.size(), "Validation should return one error for input with special characters.");
      assertEquals(
              "Das Feld darf nur Ziffern enthalten. Aktueller Wert: 123@#",
              result.get(0),
              "Error message should match the format for special characters.");
   }

   @Test
   @DisplayName("Eingabe mit Leerzeichen: Validation sollte f�r Eingaben mit Leerzeichen einen Fehler zur�ckgeben")
   void testInputWithSpaces() {
      NumberValidator validator = new NumberValidator();
      ValidationInputParameters inputParams = new ValidationInputParameters("123 456");

      List<String> result = validator.validate(inputParams);

      assertEquals(1, result.size(), "Validation should return one error for input with spaces.");
      assertEquals(
              "Das Feld darf nur Ziffern enthalten. Aktueller Wert: 123 456",
              result.get(0),
              "Error message should match the format for input with spaces.");
   }
}
