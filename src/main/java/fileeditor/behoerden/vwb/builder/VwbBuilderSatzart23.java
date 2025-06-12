package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.validators.general.DateFormatValidator;
import fileeditor.behoerden.validators.general.NotEmptyValidator;
import fileeditor.behoerden.vwb.validator.custom.AnredeValidator;
import fileeditor.behoerden.vwb.validator.custom.RueckgabegrundValidator;
import java.util.ArrayList;
import java.util.List;

public class VwbBuilderSatzart23 implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart("23");
      satzart.setBezeichnung(SFR_RUECKGABE);
      satzart.setInfo("""
              Um dem Vorversicherer mitzuteilen, welche SFR von der Rückgabe betroffen ist, wird dieser
              Satz vom Nachversicherer erstellt. Die VN-Daten (Name und Anschrift) sowie VS-Nr. und Fahrzeug-
              Identifizierungsnummer werden der Antwort entnommen, aufgrund der die Rückgabe erstellt wird.
              """);
      satzart.setBemerkungen("""
              Das Feld Vorgangsnummer muss nicht gefüllt werden. Die GDV DL generiert bei der Weiterleitung
              an den Vorversicherer eine vollständige Vorgangsnummer.
              """);
      List<Datenfeld> datenfelder = new ArrayList<>();
      datenfelder.add(new Datenfeld("1", SATZART, Typ.ALPHA, 1, 2, Art.KEY, ORGANISATORISCHE_DATEN));
      datenfelder
              .add(new Datenfeld("2.1", VU_NUMMER, Typ.ALPHA, 3, 6, Art.K,  VERBANDS_VORGANGSNUMMER));
      datenfelder.add(
              new Datenfeld("2.2", DATUM_JJJJMMTT, Typ.ALPHA, 7, 14, Art.K,  VERBANDS_VORGANGSNUMMER, new DateFormatValidator("yyyyMMdd")));
      datenfelder.add(
              new Datenfeld("2.3", LAUFENDE_NUMMER, Typ.NUM, 15, 20, Art.K,  VERBANDS_VORGANGSNUMMER));
      datenfelder.add(
              new Datenfeld("3", "VU/GSt-Nr. des Nachvers.", Typ.ALPHA, 21, 28, Art.M, SFR_RUECKGABE));
      datenfelder
              .add(new Datenfeld("4", VU_G_ST_NR_DES_VORVERS, Typ.ALPHA, 29, 36, Art.M, SFR_RUECKGABE));
      datenfelder.add(
              new Datenfeld("5", "Rückgabegrund", Typ.ALPHA, 37, 37, Art.M, 
                      SFR_RUECKGABE, new RueckgabegrundValidator()));
      datenfelder.add(
              new Datenfeld("6", "VN-Anredeschlüssel beim Vorvers.", Typ.ALPHA, 38, 38, Art.M, 
                      SFR_RUECKGABE, new AnredeValidator()));
      datenfelder.add(
              new Datenfeld("7", "VN-Namenszeile 1 beim Vorvers.", Typ.ALPHA, 39, 68, Art.M, 
                      SFR_RUECKGABE, new NotEmptyValidator()));
      datenfelder.add(
              new Datenfeld("8", "VN-Namenszeile 2 beim Vorvers.", Typ.ALPHA, 69, 93, Art.K, 
                      SFR_RUECKGABE));
      datenfelder.add(
              new Datenfeld("9", "VN-Namenszeile 3 oder Vorname beim Vorvers.", Typ.ALPHA, 94, 113, Art.SA3X_A1,
                       SFR_RUECKGABE));
      datenfelder.add(
              new Datenfeld("10", "VN-Straße beim Vorvers.", Typ.ALPHA, 114, 143, Art.K,  SFR_RUECKGABE));
      datenfelder.add(
              new Datenfeld("11", "VN-LdKz beim Vorvers.", Typ.ALPHA, 144, 146, Art.K,  SFR_RUECKGABE));
      datenfelder.add(
              new Datenfeld("12", "VN-PLZ beim Vorvers.", Typ.ALPHA, 147, 152, Art.K,  SFR_RUECKGABE));
      datenfelder.add(
              new Datenfeld("13", "VN-Ort beim Vorvers.", Typ.ALPHA, 153, 177, Art.K,  SFR_RUECKGABE));
      datenfelder
              .add(new Datenfeld("14", VS_NR_BEIM_VORVERS, Typ.ALPHA, 178, 197, Art.M,  SFR_RUECKGABE));
      datenfelder.add(
              new Datenfeld("15", "Fahrzeugidentifizierungs-Nr. beim Vorvers.", Typ.ALPHA, 198, 214, Art.K, 
                      SFR_RUECKGABE));
      datenfelder.add(
              new Datenfeld("16", "Internes Zuordnungsmerkmal beim Nachvers.", Typ.ALPHA, 215, 234, Art.K, 
                      SFR_RUECKGABE));
      datenfelder.add(new Datenfeld("17", FILLER, Typ.ALPHA, 235, 255, Art.K,  SFR_RUECKGABE));
      satzart.setDatenfelder(datenfelder);
      return satzart;
   }
}
