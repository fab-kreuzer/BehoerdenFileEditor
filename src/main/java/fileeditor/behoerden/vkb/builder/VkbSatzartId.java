package fileeditor.behoerden.vkb.builder;

public enum VkbSatzartId {

   UNBEKANT(0,"UNBEKANT", "Unbekannt"),
   KZRSTA01(1,"KZRSTA01", "Identifikation"),
   KZRPRS01(4,"KZRPRS01", "Personendaten - natürlich"),
   KZRPRS02(4,"KZRPRS02", "Personendaten - juristisch"),
   VERADR01(4,"VERADR01", "Personendaten - Adressteil 1"),
   VERADR02(4,"VERADR02", "Personendaten - Adressteil 2"),
   KZRZUL01(3,"KZRZUL01", "Zulassungsdaten"),
   VERERG01(5,"VERERG01", "Ergänzungsdaten"),
   VERERG04(10,"VERERG04", "Zusätzliche Ergänzungsdaten Fahrzeug"),
   VERKZU01(6,"VERKZU01", "Kennzeichenzuweisung");

   private final String bezeichnung;
   private final String id;
   private final int satzart;

   VkbSatzartId(int satzart, String id, String bezeichnung) {
      this.bezeichnung = bezeichnung;
      this.id = id;
      this.satzart = satzart;
   }

   public String getBezeichnung() {
      return bezeichnung;
   }

   public String getId() {
      return id;
   }
   
   public String buildMenuEntry() {
      return satzart + " - " + bezeichnung;
   }

}
