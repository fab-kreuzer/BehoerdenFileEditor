package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.validators.general.DateFormatValidator;
import fileeditor.behoerden.validators.general.NotEmptyValidator;
import fileeditor.behoerden.vwb.validator.custom.KorrekturgrundValidator;
import java.util.ArrayList;
import java.util.List;

public class VwbBuilderSatzart21 implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart("21");
      satzart.setBezeichnung("Korrektur einer Antwort");
      satzart.setInfo("""
              Um dem Nachversicherer mitzuteilen, welche Anfrage von der Korrektur betroffen ist, wird
              dieser Referenz-Satz vom Vorversicherer erstellt. Nach dem Korrektursatz folgen die Datensätze
              wie bei der Antwort.""");
      satzart.setBemerkungen("""
              • Im Fall der Korrektur einer Antwort mit den Gründen 1 bis 4 muss in allen zugehörigen Datensätzen
              anstelle einer Vorgangsnummer die Versicherungsscheinnummer des Vorversicherers
              oder ein anderes eindeutiges Merkmal eingetragen werden. Die Original-Vorgangsnummer
              (d.h. die Vorgangsnummer der vorangegangenen Anfrage und Antwort) darf nicht eingestellt werden.
              • Im Fall der Korrektur einer Antwort mit Grund 5 (= Zweitschrift) soll in allen zugehörigen
              Datensätzen die Vorgangsnummer der korrespondierenden Anfrage eingetragen werden.
              • In der GDV DL wird bei der Weiterleitung an den Nachversicherer dann wieder eine neue,
              vollständige Vorgangsnummer generiert.""");
      List<Datenfeld> datenfelder = new ArrayList<>();
      datenfelder.add(new Datenfeld("1", SATZART, Typ.ALPHA, 1, 2, Art.KEY,  ORGANISATORISCHE_DATEN));
      datenfelder.add(
              new Datenfeld("2", VS_NR_BEIM_VORVERS, Typ.ALPHA, 3, 20, Art.M,  ORGANISATORISCHE_DATEN));
      datenfelder.add(
              new Datenfeld("3", VU_G_ST_NR_DES_VORVERS, Typ.ALPHA, 21, 28, Art.M,  KORREKTUR_SATZ));
      datenfelder.add(
              new Datenfeld("4", "VU-/GSt-Nr. des Nachvers.", Typ.ALPHA, 29, 36, Art.M,  KORREKTUR_SATZ));
      datenfelder.add(
              new Datenfeld("5", "VS-Nr. beim Nachvers..", Typ.ALPHA, 37, 56, Art.M, KORREKTUR_SATZ, new NotEmptyValidator()));
      datenfelder.add(
              new Datenfeld("6", "Datum der Korrektur (TTMMJJJJ)", Typ.ALPHA, 57, 64, Art.K, 
                      KORREKTUR_SATZ, new DateFormatValidator()));
      datenfelder.add(
              new Datenfeld("7", "Art der Korrektur", Typ.ALPHA, 65, 65, Art.M, 
                      KORREKTUR_SATZ, new KorrekturgrundValidator()));
      datenfelder.add(new Datenfeld("8", FILLER, Typ.ALPHA, 66, 255, Art.K,  KORREKTUR_SATZ));
      satzart.setDatenfelder(datenfelder);
      return satzart;
   }
}
