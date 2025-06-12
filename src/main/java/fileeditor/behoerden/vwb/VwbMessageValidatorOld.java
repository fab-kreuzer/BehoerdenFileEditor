package fileeditor.behoerden.vwb;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.validators.MessageValidator;
import fileeditor.behoerden.validators.response.MessageValidationResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class VwbMessageValidatorOld implements MessageValidator {

   private static final String REGEX_NUM = "[0-9]";
   private static final String IST_FEHLERHAFT = "' ist fehlerhaft (";
   private static final String DOPPELPUNKT_HOCHKOMMA = ": '";
   @SuppressWarnings("java:S5869")
   private static final String ZEILE_TEXT = "Zeile ";

   private Map<String, Satzart> satzarten;
   private List<String> fileText;
   private List<String> fehler;

   public VwbMessageValidatorOld(List<String> fileText, Map<String, Satzart> satzarten) {
      this.fileText = fileText;
      this.satzarten = satzarten;
   }

   public MessageValidationResponse validate() {
      fehler = new ArrayList<>();
      validateSatzarten();

      return new MessageValidationResponse(fehler);
   }

   private void validateSatzarten() {
      int lastLine = fileText.size();

      if (lastLine > 0 && fileText.get(lastLine - 1).isEmpty()) {
         lastLine--;
      }

      for (int line = 1; line <= lastLine; line++) {
         String currentLine = fileText.get(line - 1);
         String satzartStr = currentLine.length() > 12 ? currentLine.substring(0, 12) : currentLine;
         satzartStr = satzartStr.replace("\n", "");
         
         validateSatzartKontrolleBN(satzartStr, line, lastLine);
         validateSatzartKontrolle(satzartStr, line);
      }
   }

   private void validateSatzartKontrolleBN(String satzartStr, int line, int lastLine) {
      if (satzartStr.equals("KONTROLLE BN")) {
         if (line != lastLine) {
            fehler.add(ZEILE_TEXT + line + ": Die Satzart 'KONTROLLE BN' darf nur in der letzten Zeile stehen.");
         }

         String anzSaetze = String.format("%08d", lastLine);

         String letzteZeile = fileText.get(lastLine - 1);
         String text = "";
         if (letzteZeile.length() >= 32) {
            text = letzteZeile.substring(24, 32);
         }

         if (!anzSaetze.equals(text)) {
            fehler.add(
               ZEILE_TEXT + line + ": Die Anzahl Datensätze (" + text + ") in der Satzart '" + satzartStr
                  + "' stimmt nicht mit der tatsächlichen Anzahl überein (" + anzSaetze + ").");
         }
      } else if (line == lastLine) {
         fehler.add(ZEILE_TEXT + line + ": Die Satzart '" + satzartStr + "' darf nicht in der letzten Zeile stehen.");
      }
   }

   private void validateSatzartKontrolle(String satzartStr, int line) {
      Satzart satzart = satzarten.get(satzartStr);

      String currentLine = fileText.get(line - 1);

      for (Datenfeld datenfeld : satzart.getDatenfelder()) {
         String text = "";
         if (datenfeld.getVon() - 1 >= 0 && datenfeld.getVon() - 1 + datenfeld.getLaenge() <= currentLine.length()) {
            text = currentLine.substring(datenfeld.getVon() - 1, datenfeld.getVon() - 1 + datenfeld.getLaenge());
         }

         validateArt(datenfeld, line, text);
      }
   }
   

   @SuppressWarnings("java:S3776")
   private void validateArt(Datenfeld datenfeld, int line, String text) {
      if (Arrays.asList(Art.M, Art.SA40_A1).contains(datenfeld.getArt()) && text.trim().isEmpty()) {
         fehler.add(ZEILE_TEXT + line + DOPPELPUNKT_HOCHKOMMA + datenfeld.buildFehlerId() + "' muss gefüllt sein.");
      }
      if (datenfeld.getArt() == Art.SA10_A1 && isAbhaengigUndLeer(line, text, 76, 1, "1", "2")) {
         fehler.add(
            ZEILE_TEXT + line + DOPPELPUNKT_HOCHKOMMA + datenfeld.buildFehlerId() + IST_FEHLERHAFT
               + Art.SA10_A1.getBezeichnung() + ").");
      }
      if (datenfeld.getArt() == Art.SA3X_A1 && isAbhaengigUndLeer(line, text, 76, 1, "1", "2", "4")) {
         fehler.add(
            ZEILE_TEXT + line + DOPPELPUNKT_HOCHKOMMA + datenfeld.buildFehlerId() + IST_FEHLERHAFT
               + Art.SA3X_A1.getBezeichnung() + ").");
      }
      if (datenfeld.getArt() == Art.SA10_A2
         && isAbhaengigUndLeer(line, text, 49, 2, "01", "03", "04", "05", "07", "08")) {
         fehler.add(
            ZEILE_TEXT + line + DOPPELPUNKT_HOCHKOMMA + datenfeld.buildFehlerId() + IST_FEHLERHAFT
               + Art.SA10_A2.getBezeichnung() + ").");
      }
      if (datenfeld.getArt() == Art.SA10_A3 && isAbhaengigUndLeer(line, text, 49, 2, "05", "07")) {
         fehler.add(
            ZEILE_TEXT + line + DOPPELPUNKT_HOCHKOMMA + datenfeld.buildFehlerId() + IST_FEHLERHAFT
               + Art.SA10_A3.getBezeichnung() + ").");
      }
      if (datenfeld.getArt() == Art.SA3X_A3 && isAbhaengigUndLeer(line, text, 233, 1, "1", "3")) {
         fehler.add(
            ZEILE_TEXT + line + DOPPELPUNKT_HOCHKOMMA + datenfeld.buildFehlerId() + IST_FEHLERHAFT
               + Art.SA3X_A3.getBezeichnung() + ").");
      }
      if (datenfeld.getArt() == Art.SA3X_A4 && isAbhaengigUndLeer(line, text, 233, 1, "1")) {
         fehler.add(
            ZEILE_TEXT + line + DOPPELPUNKT_HOCHKOMMA + datenfeld.buildFehlerId() + IST_FEHLERHAFT
               + Art.SA3X_A4.getBezeichnung() + ").");
      }
      if (datenfeld.getArt() == Art.SA5X_A3 && isGefuellt(line, datenfeld.getVon() - 10, 2)
         && isAbhaengigUndLeer(line, text, 1, 2, "51")) {
         fehler.add(
            ZEILE_TEXT + line + DOPPELPUNKT_HOCHKOMMA + datenfeld.buildFehlerId() + IST_FEHLERHAFT
               + Art.SA5X_A3.getBezeichnung() + ").");
      }
      if (datenfeld.getArt() == Art.SA40_A2A3 && isAbhaengigUndLeer(line, text, 29, 2, "03")) {
         fehler.add(
            ZEILE_TEXT + line + DOPPELPUNKT_HOCHKOMMA + datenfeld.buildFehlerId() + IST_FEHLERHAFT
               + Art.SA40_A2A3.getBezeichnung() + ").");
      }
      if (datenfeld.getArt() == Art.SA40_A4 && isAbhaengigUndLeer(line, text, 29, 2, "09")) {
         fehler.add(
            ZEILE_TEXT + line + DOPPELPUNKT_HOCHKOMMA + datenfeld.buildFehlerId() + IST_FEHLERHAFT
               + Art.SA40_A4.getBezeichnung() + ").");
      }
   }

   private boolean isAbhaengigUndLeer(int line, String text, int pos, int len, String... abhaengigVon) {
      if (line <= 0 || line > fileText.size()) {
         return false;
      }

      String currentLine = fileText.get(line - 1);

      if (pos - 1 >= 0 && pos - 1 + len <= currentLine.length()) {
         String abhaengigtext = currentLine.substring(pos - 1, pos - 1 + len);

         if (Arrays.asList(abhaengigVon).contains(abhaengigtext)) {
            return text.trim().isEmpty();
         }
      }

      return false;
   }

   private boolean isGefuellt(int line, int pos, int len) {
      if (line <= 0 || line > fileText.size()) {
         return false;
      }

      String currentLine = fileText.get(line - 1);

      if (pos - 1 >= 0 && pos - 1 + len <= currentLine.length()) {
         String text = currentLine.substring(pos - 1, pos - 1 + len);
         return !text.trim().isEmpty();
      }

      return false;
   }
   

}
