package fileeditor.behoerden.general.types;

import java.util.List;

public class Satzart {

   @SuppressWarnings("java:S1700")
   private String satzart = "";
   private String bezeichnung = "";
   private String info = "";
   private String bemerkungen = "";
   private List<Datenfeld> datenfelder;

   public String getSatzart() {
      return satzart;
   }

   public String getBezeichnung() {
      return bezeichnung;
   }

   public String getInfo() {
      return info;
   }

   public String getBemerkungen() {
      return bemerkungen;
   }

   public List<Datenfeld> getDatenfelder() {
      return datenfelder;
   }

   public void setSatzart(String satzart) {
      this.satzart = satzart;
   }

   public void setBezeichnung(String bezeichnung) {
      this.bezeichnung = bezeichnung;
   }

   public void setInfo(String info) {
      this.info = info;
   }

   public void setBemerkungen(String bemerkungen) {
      this.bemerkungen = bemerkungen;
   }

   public void setDatenfelder(List<Datenfeld> datenfelder) {
      this.datenfelder = datenfelder;
   }

   public String buildSample() {
      StringBuilder builder = new StringBuilder();
      getDatenfelder().forEach(feld -> builder.append(feld.buildSample(getSatzart())));
      builder.append("\n");
      return builder.toString();
   }
}
