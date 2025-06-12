package fileeditor.ui.jwt.data;

import java.util.List;

public class JwtSatzart {

   private static final JwtDatenFeld UNBEKANNT = new JwtDatenFeld("Außerhalb der Spezifikation");

   private String satzart = "";
   private String bezeichnung = "";
   private String info = "";
   private String bemerkungen = "";
   private List<JwtDatenFeld> datenfelder;

   public String getSatzart() {
      return satzart;
   }

   public void setSatzart(String satzart) {
      this.satzart = satzart;
   }

   public String getBezeichnung() {
      return bezeichnung;
   }

   public void setBezeichnung(String bezeichnung) {
      this.bezeichnung = bezeichnung;
   }

   public String getInfo() {
      return info;
   }

   public void setInfo(String info) {
      this.info = info;
   }

   public String getBemerkungen() {
      return bemerkungen;
   }

   public void setBemerkungen(String bemerkungen) {
      this.bemerkungen = bemerkungen;
   }

   public List<JwtDatenFeld> getDatenfelder() {
      return datenfelder;
   }

   public void setDatenfelder(List<JwtDatenFeld> datenfelder) {
      this.datenfelder = datenfelder;
   }

   public JwtDatenFeld determineDatenfeld(int col) {
      return datenfelder.stream().filter(df -> col >= df.getVon() && col <= df.getBis()).findFirst().orElse(UNBEKANNT);
   }

   public String buildInfoPanelAllFields() {
      StringBuilder builder = new StringBuilder();
      buildInfopanelSatzart(builder);

      builder.append("Alle Datenfelder:\n");
      builder.append("Nr.");
      builder.append("\t");
      builder.append("Typ");
      builder.append("\t");
      builder.append("Von");
      builder.append("\t");
      builder.append("Bis");
      builder.append("\t");
      builder.append("Länge");
      builder.append("\t");
      builder.append("Datenfeld");
      builder.append("\n");

      getDatenfelder().forEach(feld -> builder.append(feld.buildTabellenzeile()));
      return builder.toString();
   }

   public String buildInfopanelText(JwtDatenFeld aktuellesDatenfeld) {
      StringBuilder builder = new StringBuilder();
      buildInfopanelSatzart(builder);
      builder.append(aktuellesDatenfeld.buildInfopanelText());
      return builder.toString();
   }

   private void buildInfopanelSatzart(StringBuilder builder) {
      builder.append("Satzart: ");
      builder.append(getSatzart());
      builder.append(" ");
      builder.append(getBezeichnung());
      if (!getInfo().isEmpty()) {
         builder.append("\n\n");
         builder.append(getInfo());
      }
      if (!getBemerkungen().isEmpty()) {
         builder.append("\n\n");
         builder.append("Bemerkungen\n");
         builder.append(getBemerkungen());
      }
      builder.append("\n\n");
   }
}
