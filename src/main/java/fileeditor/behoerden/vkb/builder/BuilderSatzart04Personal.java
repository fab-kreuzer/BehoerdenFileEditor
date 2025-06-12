package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.vkb.validator.custom.NatuerlichesGeschlechtValidator;
import java.util.List;

public class BuilderSatzart04Personal implements VkbAtSatzartBuilder {

   private static final String STATUSTEIL = "Statusteil";

   @Override
   public Satzart build() {
      Satzart vkbAtSatzart = new Satzart();
      vkbAtSatzart.setSatzart(VkbSatzartId.KZRPRS01.getId());
      vkbAtSatzart.setBezeichnung(VkbSatzartId.KZRPRS01.getBezeichnung());
      vkbAtSatzart.setBemerkungen("""
              Die Satzart Personendaten enthält die Daten eines Zulassungsbesitzers. Pro Zulassungsbesitzer wird
              ein Satz übermittelt. Die Anzahl der Segmente ist im Statussegment eingetragen. Maximal sind acht 
              Personensätze pro Zulassung zulässig. Die Übermittlung von Personendaten entnehmen Sie bitte der 
              Funktionsbeschreibung. Der zustellbevollmächtigte Zulassungsbesitzer wird an erster Stelle übermittelt.
              Die Personensegmente VERADR01, VERADR02 und ZULPRS01 bzw. ZULPRS02 können optional ab 
              Einsatz KFA Ver. 4.9 an die Versicherungsunternehmen übermittelt werden.
              Die Adresssegmente VERADR01 und VERADR02
              werden ab Einsatz KFA Ver. 6.05 immer an die Statistik Austria übermittelt.
              Das ergänzende Adresssegment ERGPRS01 wird ab Einsatz KFA Ver. 7.02 nur an die Statistik Austria 
              übermittelt
              """);
      vkbAtSatzart.setDatenfelder(buildDataFiels());
      return vkbAtSatzart;
   }

   private List<Datenfeld> buildDataFiels() {
      List<Datenfeld> datenfelder = buildInitList();
      int position = 24;

      datenfelder.add(
         new Datenfeld("5", "Geschlecht", Typ.NUM, position + 1, position += 1, Art.M, STATUSTEIL, new NatuerlichesGeschlechtValidator()));
      datenfelder.add(
         new Datenfeld("6", "Pesronen-ID", Typ.NUM, position + 1, position += 14, Art.M, STATUSTEIL));
      datenfelder
            .add(new Datenfeld("7", "Familienname", Typ.ALPHA, position + 1, position += 50, Art.M, STATUSTEIL));
      datenfelder
            .add(new Datenfeld("8", "Vorname", Typ.ALPHA, position + 1, position += 32, Art.M, STATUSTEIL));
      datenfelder.add(
         new Datenfeld("9", "Geburtsdatum", Typ.DATE_YYYYMMDD, position + 1, position += 8, Art.M, STATUSTEIL));
      datenfelder.add(
         new Datenfeld("10", "Akademischer Grad", Typ.ALPHA, position + 1, position += 13, Art.M, STATUSTEIL));
      datenfelder
            .add(new Datenfeld("11", "Berufsschlüssel", Typ.ALPHA, position + 1, position += 3, Art.M, STATUSTEIL));
      datenfelder
            .add(new Datenfeld("12", "Adresse: Straße", Typ.ALPHA, position + 1, position += 40, Art.M, STATUSTEIL));
      datenfelder
            .add(new Datenfeld("13", "Adresse: Postleitzahl", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder
            .add(new Datenfeld("14", "Adresse: Ortsbezeichnung", Typ.ALPHA, position + 1, position += 35, Art.M, STATUSTEIL));
      datenfelder.add(
         new Datenfeld("15", "Adresse: Staatenschlüssel", Typ.ALPHA, position + 1, position += 4, Art.M, STATUSTEIL));
      datenfelder.add(
         new Datenfeld("16", "Gemeindezahl", Typ.NUM, position + 1, position += 5, Art.M, STATUSTEIL));
      datenfelder.add(
         new Datenfeld("17", "Personenart", Typ.ALPHA, position + 1, position += 3, Art.M, STATUSTEIL));

      return datenfelder;
   }

}
