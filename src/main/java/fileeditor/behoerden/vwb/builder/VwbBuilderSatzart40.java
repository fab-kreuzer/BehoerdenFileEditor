package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.validators.general.DateFormatValidator;
import fileeditor.behoerden.validators.general.NotEmptyValidator;
import fileeditor.behoerden.vwb.validator.custom.NegativgrundValidator;
import java.util.ArrayList;
import java.util.List;

public class VwbBuilderSatzart40 implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart("40");
      satzart.setBezeichnung(NEGATIVE_ANTWORT);
      List<Datenfeld> datenfelder = new ArrayList<>();
      datenfelder.add(new Datenfeld("1", SATZART, Typ.ALPHA, 1, 2, Art.KEY, ORGANISATORISCHE_DATEN));
      datenfelder.add(new Datenfeld("2.1", VU_NUMMER, Typ.ALPHA, 3, 6, Art.M,  VERBANDS_VORGANGSNUMMER));
      datenfelder.add(new Datenfeld("2.2", DATUM_JJJJMMTT, Typ.ALPHA, 7, 14, Art.M,  VERBANDS_VORGANGSNUMMER, new DateFormatValidator("yyyyMMdd")));
      datenfelder.add(new Datenfeld("2.3", LAUFENDE_NUMMER, Typ.NUM, 15, 20, Art.M,  VERBANDS_VORGANGSNUMMER));
      datenfelder.add(new Datenfeld("3", VU_G_ST_NR_DES_VORVERS, Typ.ALPHA, 21, 28, Art.M, NEGATIVE_ANTWORT));
      datenfelder.add(
              new Datenfeld("4", "Grund", Typ.ALPHA, 29, 30, Art.M,  NEGATIVE_ANTWORT, new NegativgrundValidator()));
      datenfelder.add(
              new Datenfeld("5", "Datum (TTMMJJJJ)", Typ.ALPHA, 31, 38, Art.SA40_A1,  NEGATIVE_ANTWORT, new DateFormatValidator()));
      datenfelder
              .add(new Datenfeld("6", "VU/GS", Typ.ALPHA, 39, 46, Art.SA40_A2A3, NEGATIVE_ANTWORT));
      datenfelder.add(new Datenfeld("7", "VS-Nr", Typ.ALPHA, 47, 66, Art.SA40_A2A3,  NEGATIVE_ANTWORT));
      datenfelder
              .add(new Datenfeld("8", "Kommentar", Typ.ALPHA, 67, 136, Art.SA40_A4,  NEGATIVE_ANTWORT, new NotEmptyValidator()));
      datenfelder.add(
              new Datenfeld("9", "Internes Zuordnungsmerkmal beim Vorversicherer", Typ.ALPHA, 137, 156, Art.K,
                       NEGATIVE_ANTWORT));
      datenfelder.add(new Datenfeld("10", FILLER, Typ.ALPHA, 157, 255, Art.K,  NEGATIVE_ANTWORT));
      satzart.setDatenfelder(datenfelder);
      return satzart;
   }
   
}
