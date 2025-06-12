package fileeditor.ui.jwt.data;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Typ;
import java.util.List;

public class JwtDatenFeld {

   private String nummer;
   private String bezeichnung;
   private Typ typ;
   private int von;
   private int bis;
   private Art art;
   private String block;
   private List<JwtValidValue> validValues;
   private boolean canBeEmpty;

   public boolean canBeEmpty() {
      return canBeEmpty;
   }

   public void setCanBeEmpty(boolean canBeEmpty) {
      this.canBeEmpty = canBeEmpty;
   }

   public List<JwtValidValue> getValidValues() {
      return validValues;
   }
   
   public boolean hasValidValues() {
      return validValues != null && !validValues.isEmpty();
   }

   public String[] getValidValueDescriptions() {
      return validValues.stream()
              .map(v -> v.value() + " - " + v.description())
              .toArray(String[]::new);
   }
   
   public void setValidValues(List<JwtValidValue> validValues) {
      this.validValues = validValues;
   }

   public String getNummer() {
      return nummer;
   }

   public void setNummer(String nummer) {
      this.nummer = nummer;
   }

   public String getBezeichnung() {
      return bezeichnung;
   }

   public void setBezeichnung(String bezeichnung) {
      this.bezeichnung = bezeichnung;
   }

   public Typ getTyp() {
      return typ;
   }

   public void setTyp(Typ typ) {
      this.typ = typ;
   }

   public int getVon() {
      return von;
   }

   public void setVon(int von) {
      this.von = von;
   }

   public int getBis() {
      return bis;
   }

   public void setBis(int bis) {
      this.bis = bis;
   }

   public Art getArt() {
      return art;
   }

   public void setArt(Art art) {
      this.art = art;
   }

   public String getBlock() {
      return block;
   }

   public void setBlock(String block) {
      this.block = block;
   }

   public int getLaenge() {
      return bis - von + 1;
   }

   public JwtDatenFeld() {
   }

   public JwtDatenFeld(String bezeichnung) {
      this("", bezeichnung, Typ.LEER, 0, 0, Art.LEER, "");
   }

   public JwtDatenFeld(String nummer, String bezeichnung, Typ typ, int von, int bis, Art art,
                       String block) {
      this.nummer = nummer;
      this.bezeichnung = bezeichnung;
      this.typ = typ;
      this.von = von;
      this.bis = bis;
      this.art = art;
      this.block = block;
   }

   public String buildTabellenzeile() {
      StringBuilder builder = new StringBuilder();
      builder.append(getNummer());
      builder.append("\t");
      builder.append(getTyp());
      builder.append("\t");
      builder.append(getVon());
      builder.append("\t");
      builder.append(getBis());
      builder.append("\t");
      builder.append(getLaenge());
      builder.append("\t");
      builder.append(getBezeichnung());
      builder.append("\n");
      return builder.toString();
   }

   public String buildInfopanelText() {
      StringBuilder builder = new StringBuilder();
      builder.append("Datenfeld:\t");
      builder.append(getNummer());
      builder.append(" ");
      builder.append(getBezeichnung());
      builder.append("\n");
      builder.append("Block:\t");
      builder.append(getBlock());
      builder.append("\n");
      builder.append("Feldtyp:\t");
      builder.append(getTyp().getBezeichnung());
      builder.append("\n");
      builder.append("Feldlänge:\t");
      builder.append(getLaenge());
      builder.append("\n");
      builder.append("Art:\t");
      builder.append(getArt().getBezeichnung());
      builder.append("\n");
      
      return builder.toString();
   }

   public String buildNewFieldValue(String newValue) {
      StringBuilder builder = new StringBuilder();
      if (getTyp() == Typ.ALPHA) {
         builder.append(newValue);
         builder.append(" ".repeat(Math.max(0, getLaenge())));
         return builder.substring(0, getLaenge());
      } else if (getTyp() == Typ.NUM) {
         builder.append("0".repeat(Math.max(0, getLaenge())));
         builder.append(newValue.replaceAll(" '\\D'", ""));
         return builder.substring(builder.length() - getLaenge());
      }
      return newValue;
   }
}
