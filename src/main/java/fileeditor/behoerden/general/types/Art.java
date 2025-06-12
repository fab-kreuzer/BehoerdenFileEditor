package fileeditor.behoerden.general.types;

public enum Art {

   LEER(""),
   M("Muss-Feld"),
   K("Kann-Feld"),
   G("Verband generiert"),
   KEY("Schlüssel"),
   SA10_A1("Muss-Feld, wenn Anredeschlüssel = 1 oder = 2"),
   SA10_A2("Muss-Feld, wenn Anfragegrund = 01, 03, 04, 05, 07 oder 08"),
   SA10_A3(
           "Muss-Feld, wenn Grund = 05 (= anderer VN) oder 07 (= Betriebsübernahme) ist (das AKZ aus der SFR-Abtretungserklärung übernehmen)."),
   SA3X_A1("Muss-Feld, wenn Anredeschlüssel = 1 oder = 2 oder = 4"),
   SA3X_A2(
           "Muss-Feld, wenn das RGJ größer oder gleich dem Vertragsende ist (wobei der 01.01. dem 31.12. des Vorjahres gleichgesetzt wird)."),
   SA3X_A3("Muss-Feld, wenn Vertragsstatus 1 oder 3; dann Datumsformat"),
   SA3X_A4("Muss-Feld, wenn der Vertrag beendet ist"),
   SA3X_A5(
           "Die Summe der beiden Anzahlfelder muss > 0 sein, wenn in den korrespondierenden Schadenfolgesätzen rückstufungsrelevante Schäden gemeldet werden"),
   SA5X_A1A2(
             "Muss-Feld, wenn Anz. belastender Schäden (in der positiven Antwort) ungleich Null ist\n"
                + "Muss-Feld, wenn das Schadendatum mit einem Datumswert gefüllt ist"),
   SA5X_A3("Muss-Feld, wenn Satzart = 51 (= KH) ist"),
   SA40_A1(
           "Muss-Feld; es hat in Abhängigkeit vom Negativantwort-Grund folgende Bedeutungen:\n"
              + "• Negativantwort-Grund = 03 -> Datum der Erteilung der VWB\n"
              + "• Negativantwort-Grund = 04 -> Datum, seit dem der Vertrag ruht\n"
              + "• Negativantwort-Grund = 05 -> Datum der Antragstellung\n"
              + "• Negativantwort-Grund = 06 -> Datum des Vertragsbeginns"),
   SA40_A2A3("Muss-Feld, wenn Negativantwort-Grund = 03 ist"),
   SA40_A4("Muss-Feld, wenn Negativantwort-Grund = 09 ist.");

   private String bezeichnung;

   Art(String bezeichnung) {
      this.bezeichnung = bezeichnung;
   }

   public String getBezeichnung() {
      return bezeichnung;
   }
}
