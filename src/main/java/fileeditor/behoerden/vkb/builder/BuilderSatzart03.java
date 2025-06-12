package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import java.util.List;

public class BuilderSatzart03 implements VkbAtSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart vkbAtSatzart = new Satzart();
      vkbAtSatzart.setSatzart(VkbSatzartId.KZRZUL01.getId());
      vkbAtSatzart.setBezeichnung(VkbSatzartId.KZRZUL01.getBezeichnung());
      vkbAtSatzart.setBemerkungen("""
         Das Zulassungssegment enthält die spezifischen Daten einer Kfz-Zulassung, welche für die
         Versicherungsunternehmen und die Statistik Austria relevant sind. Die Übermittlung erfolgt bei den
         Funktionen 1, 05, 06, 07, 08, 09, 10, 11, 20 und 21.
         """);
      vkbAtSatzart.setDatenfelder(buildDataFiels());
      return vkbAtSatzart;
   }

   private List<Datenfeld> buildDataFiels() {
      List<Datenfeld> datenfelder = buildInitList();
      int position = 24;

      datenfelder
              .add(new Datenfeld("5", "Anmeldedatum", Typ.DATE_YYYYMMDD, position + 1, position += 8, Art.M, STATUSTEIL));
      datenfelder.add(new Datenfeld("6", "Anmeldeparagraph", Typ.NUM, position + 1, position += 3, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("7", "Datum der Befristung", Typ.DATE_YYYYMMDD, position + 1, position += 8, Art.M, STATUSTEIL));
      datenfelder.add(new Datenfeld("8", "Eindeutige Fahrzeugnummer", Typ.NUM, position + 1, position += 9, Art.M, STATUSTEIL));
      datenfelder.add(new Datenfeld("8", "FIN", Typ.ALPHA, position + 1, position += 20, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("9", "Motornummer", Typ.ALPHA, position + 1, position += 25, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("10", "Fahrzeugart", Typ.NUM, position + 1, position += 3, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("11", "Datum der erstmaligen Zulassung", Typ.DATE_YYYYMMDD, position + 1, position += 8, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("12", "Farbe", Typ.ALPHA, position + 1, position += 4, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("13", "Markenbezeichnung", Typ.ALPHA, position + 1, position += 25, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("14", "Typenbezeichnung", Typ.ALPHA, position + 1, position += 20, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("15", "Handelsbezeichnung", Typ.ALPHA, position + 1, position += 30, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("16", "(Typen-)Genehmigungsnummer", Typ.ALPHA, position + 1, position += 30, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("17", "Höchstes zulässiges Gesamtgewicht", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("18", "Fahrzeugklasse", Typ.ALPHA, position + 1, position += 10, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("19", "Elektrische Reichweite", Typ.NUM, position + 1, position += 4, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("20", "Fahrzeug Untergruppe", Typ.ALPHA, position + 1, position += 10, Art.M, VERSICHERUNGSDATEN));
      return datenfelder;
   }
}
