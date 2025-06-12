package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.validators.general.YesNoValidator;
import fileeditor.behoerden.vkb.validator.custom.FuCodeValidator;
import fileeditor.behoerden.vkb.validator.custom.KennzeichenNummernkreisValidator;
import fileeditor.behoerden.validators.general.NumberValidator;
import fileeditor.behoerden.vkb.validator.custom.ZulassungStatusValidator;
import java.util.List;

public class BuilderSatzart01 implements VkbAtSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart vkbAtSatzart = new Satzart();
      vkbAtSatzart.setSatzart(VkbSatzartId.KZRSTA01.getId());
      vkbAtSatzart.setBezeichnung(VkbSatzartId.KZRSTA01.getBezeichnung());
      vkbAtSatzart.setBemerkungen("""
              Die Satzart umfasst zwei Segmente:
              Statusteil (Segment 1)
              Das Statusteil enthält technische Informationen zur Durchführung und Übermittlung des
              gegenständlichen Geschäftsvorfalls, den Status und die Gültigkeit der Funktion.
              Versicherungsdaten (Segment 2)
              Das Versicherungssegment wird nur an die betroffenen Versicherungen übermittelt. An die 
              Versicherungen erfolgt die Übermittlung immer gemeinsam mit dem Statusteil, weil das Feld VERS den 
              Empfänger des Datensatzes enthält. An ‚STAT werden die Felder mit BLANK belegt. Bei allen 
              Funktionen außer dem Versicherungswechsel (FUCODE=12) wird die aktuell haftende Versicherung mit 
              der haftungsbegründenden Versicherungsbestätigung übermittelt.
              """);
      vkbAtSatzart.setDatenfelder(buildDataFiels());
      return vkbAtSatzart;
   }

   private List<Datenfeld> buildDataFiels() {
      List<Datenfeld> datenfelder = buildInitList();
      int position = 24;

      datenfelder
              .add(new Datenfeld("5", "Funktionscode", Typ.NUM, position + 1, position += 2, Art.M, STATUSTEIL, new FuCodeValidator(), new NumberValidator()));
      datenfelder.add(new Datenfeld("6", "Durchführungsdatum", Typ.DATE_YYYYMMDD, position + 1, position += 8, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("7", "Durchführungszeit", Typ.NUM, position + 1, position += 7, Art.M, STATUSTEIL));
      datenfelder.add(new Datenfeld("8", "laufende Nummer", Typ.NUM, position + 1, position += 9, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("9", "durchführende Zulassungsstelle", Typ.ALPHA, position + 1, position += 7, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("10", "Fehlerinformationen", Typ.NUM, position + 1, position += 2, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("11", "Zulassungs-ID", Typ.NUM, position + 1, position += 9, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("12", "Gültigkeitsdatum", Typ.DATE_YYYYMMDD, position + 1, position += 8, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("13", "Kennzeichen: zuständige Behörde", Typ.NUM, position + 1, position += 3, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("14", "Kennzeichen: Sachbereich", Typ.ALPHA, position + 1, position += 2, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("15", "Kennzeichen: Vormerkteil", Typ.ALPHA, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("16", "Kennzeichen: Jahreszahl", Typ.NUM, position + 1, position += 2, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("17", "Kennzeichen: Nummernkreis", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL, new KennzeichenNummernkreisValidator()));
      datenfelder
              .add(new Datenfeld("18", "Status der Zulassung", Typ.ALPHA, position + 1, position += 2, Art.M, STATUSTEIL, new ZulassungStatusValidator()));
      datenfelder
              .add(new Datenfeld("19", "Anzahl Zulassungsbesitzer", Typ.NUM, position + 1, position += 1, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("20", "Segmentkennung", Typ.ALPHA, position + 1, position += 8, Art.M, VERSICHERUNGSDATEN));
      datenfelder
              .add(new Datenfeld("21", "Versicherung: Empfänger", Typ.ALPHA, position + 1, position += 2, Art.M, VERSICHERUNGSDATEN));
      datenfelder
              .add(new Datenfeld("22", "VB-Nummer", Typ.ALPHA, position + 1, position += 20, Art.M, VERSICHERUNGSDATEN));
      datenfelder
              .add(new Datenfeld("23", "Gültigkeitsdatum VB-Nummer", Typ.DATE_YYYYMMDD, position + 1, position += 8, Art.M, VERSICHERUNGSDATEN));
      datenfelder.add(
              new Datenfeld("24", "Versicherung: Alter Versicherer", Typ.ALPHA, position + 1, position += 2, Art.M, VERSICHERUNGSDATEN));
      datenfelder.add(
              new Datenfeld("25", "Versicherung: Neuer Versicherer", Typ.ALPHA, position + 1, position += 2, Art.M, VERSICHERUNGSDATEN));
      datenfelder.add(
              new Datenfeld("26", "MBV-Befreiung zuerkannt", Typ.ALPHA, position + 1, position += 1, Art.M, VERSICHERUNGSDATEN, new YesNoValidator()));

      return datenfelder;
   }
}
