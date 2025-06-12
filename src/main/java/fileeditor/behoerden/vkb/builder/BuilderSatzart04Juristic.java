package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.vkb.validator.custom.JuristischesGeschlechtValidator;
import java.util.List;

public class BuilderSatzart04Juristic implements VkbAtSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart vkbAtSatzart = new Satzart();
      vkbAtSatzart.setSatzart(VkbSatzartId.KZRPRS02.getId());
      vkbAtSatzart.setBezeichnung(VkbSatzartId.KZRPRS02.getBezeichnung());
      vkbAtSatzart.setBemerkungen("""
              Die Satzart Personendaten enthÃ¤lt die Daten eines Zulassungsbesitzers. Pro Zulassungsbesitzer wird
              ein Satz Ã¼bermittelt. Die Anzahl der Segmente ist im Statussegment eingetragen. Maximal sind acht 
              PersonensÃ¤tze pro Zulassung zulÃ¤ssig. Die Ãbermittlung von Personendaten entnehmen Sie bitte der 
              Funktionsbeschreibung. Der zustellbevollmÃ¤chtigte Zulassungsbesitzer wird an erster Stelle Ã¼bermittelt.
              Die Personensegmente VERADR01, VERADR02 und ZULPRS01 bzw. ZULPRS02 kÃ¶nnen optional ab 
              Einsatz KFA Ver. 4.9 an die Versicherungsunternehmen Ã¼bermittelt werden.
              Die Adresssegmente VERADR01 und VERADR02
              werden ab Einsatz KFA Ver. 6.05 immer an die Statistik Austria Ã¼bermittelt.
              Das ergÃ¤nzende Adresssegment ERGPRS01 wird ab Einsatz KFA Ver. 7.02 nur an die Statistik Austria 
              Ã¼bermittelt
              """);
      vkbAtSatzart.setDatenfelder(buildDataFiels());
      return vkbAtSatzart;
   }

   private List<Datenfeld> buildDataFiels() {
      List<Datenfeld> datenfelder = buildInitList();
      int position = 24;

      datenfelder.add(
              new Datenfeld("5", "Geschlecht", Typ.NUM, position + 1, position += 1, Art.M, STATUSTEIL, new JuristischesGeschlechtValidator()));
      datenfelder.add(
              new Datenfeld("6", "Personen-ID", Typ.NUM, position + 1, position += 14, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("7", "Firmenbezeichnung", Typ.ALPHA, position + 1, position += 80, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("8", "Firmenbuch", Typ.ALPHA, position + 1, position += 23, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("9", "Branche, Berufsschlüssel", Typ.ALPHA, position + 1, position += 3, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("10", "Adresse: Straße", Typ.ALPHA, position + 1, position += 40, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("11", "Adresse: Postleitzahl", Typ.ALPHA, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("12", "Adresse: Ortsbezeichnung", Typ.ALPHA, position + 1, position += 35, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("13", "Adresse: Staatenschlüssel", Typ.ALPHA, position + 1, position += 4, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("14", "Gemeindezahl", Typ.NUM, position + 1, position += 5, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("15", "Zählersprengelnummer", Typ.NUM, position + 1, position += 3, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("15", "Personenart", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));

      return datenfelder;
   }

}
