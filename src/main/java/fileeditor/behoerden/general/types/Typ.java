package fileeditor.behoerden.general.types;

public enum Typ {

   ALPHA("Alphanumerisch"),
   NUM("Numerisch"),
   DATE_YYYYMMDD("Datum (JJJJMMTT)"),
   LEER("");

   private String bezeichnung;

   Typ(String bezeichnung) {
      this.bezeichnung = bezeichnung;
   }

   public String getBezeichnung() {
      return bezeichnung;
   }
   
   public boolean isNum() {
      return this == NUM;
   }
   public boolean isDate() {
      return this == DATE_YYYYMMDD;
   }
}
