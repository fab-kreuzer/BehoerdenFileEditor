package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.general.types.ValidValue;
import java.util.List;

public class BuilderSatzart05 implements VkbAtSatzartBuilder{

   @Override
   public Satzart build() {
      Satzart vkbAtSatzart = new Satzart();
      vkbAtSatzart.setSatzart(VkbSatzartId.VERERG01.getId());
      vkbAtSatzart.setBezeichnung(VkbSatzartId.VERERG01.getBezeichnung());
      vkbAtSatzart.setBemerkungen("""
               Das Segment enthält diejenigen Datenfelder, die an die Versicherungsunternehmen und an die Statistik
               Austria geliefert werden.
              """);
      vkbAtSatzart.setDatenfelder(buildDataFiels());
      return vkbAtSatzart;
   }

   private List<Datenfeld> buildDataFiels() {
      List<Datenfeld> datenfelder = buildInitList();
      int position = 24;

      datenfelder.add(
              new Datenfeld("5", "Verwendungsbestimmung 1", Typ.ALPHA, position + 1, position += 2, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("6", "Verwendungsbestimmung 2", Typ.ALPHA, position + 1, position += 2, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("7", "Antragsteller", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("8", "Fabrikneu /-gebraucht", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL, List.of(new ValidValue("1", "Fabrikneu"), new ValidValue("2", "Gebraucht")), true));
      datenfelder.add(
              new Datenfeld("9", "Importkennzeichen", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("10", "Befreiung Versicherungspflicht", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("11", "Punkt 19 Genehmigungsgrundlage", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("12", "Zahl der Typengenehmigung", Typ.ALPHA, position + 1, position += 20, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("13", "Prüfnummer Typengenehmigung", Typ.ALPHA, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("13", "Datum der Typengenehmigung", Typ.DATE_YYYYMMDD, position + 1, position += 8, Art.M, STATUSTEIL));

      datenfelder.add(
              new Datenfeld("14", "Nationaler Typencode", Typ.ALPHA, position + 1, position += 19, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("15", "Art des Aufbaues", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("16", "Art des Antriebs", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("17", "Sitzplätze gesamt, mit Lenker", Typ.NUM, position + 1, position += 3, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("18", "Eigengewicht in Kg", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("19", "Nutzlast in Kg", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("19", "Hubraum", Typ.NUM, position + 1, position += 5, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("20", "Leistung in KW", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("21", "Bauartgeschwindigkeit", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("22", "Geräuschpegel", Typ.ALPHA, position + 1, position += 9, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("23", "Schwärzungszahl/Trübungszahl", Typ.NUM, position + 1, position += 4, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("24", "Anhängelast ungebremst", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("25", "Anhängelast gebremst", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("26", "Vertikale Stützlast", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("27", "Anzahl der Stehplätze", Typ.NUM, position + 1, position += 3, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("27", "Höchste zul. Sattellast", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("28", "Achslasten 1", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("29", "Achslasten 2", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("30", "Achslasten 3", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("31", "Achslasten 4", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("32", "Geländegängig", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("33", "Zusatzausrüstung", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("34", "Zusatzausrüstung", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("35", "Kraftstoffverbrauch nach...", Typ.ALPHA, position + 1, position += 30, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("36", "Kraftstoffverbrauch NEFZ", Typ.NUM, position + 1, position += 4, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("37", "CO2 Emmision NEFZ", Typ.NUM, position + 1, position += 3, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("38", "Aufbaucode", Typ.ALPHA, position + 1, position += 2, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("39", "Abgasklasse", Typ.ALPHA, position + 1, position += 10, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("40", "Einheit für Verbrauch", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("41", "Klasse Basisfahrzeug", Typ.ALPHA, position + 1, position += 10, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("42", "Besondere Zweckbestimmung", Typ.ALPHA, position + 1, position += 2, Art.M, STATUSTEIL));

      return datenfelder;
   }


}
