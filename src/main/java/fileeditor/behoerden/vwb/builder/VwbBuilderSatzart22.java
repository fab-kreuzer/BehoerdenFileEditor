package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.validators.general.NotEmptyValidator;
import java.util.ArrayList;
import java.util.List;

public class VwbBuilderSatzart22 implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart("22");
      satzart.setBezeichnung("Antwort an Nicht-Teilnehmer");
      satzart.setInfo("""
              Diese Satzart wird immer dann eingesetzt, wenn dem teilnehmenden Vorversicherer Anfragen
              direkt übersandt werden (die dann keine GDV DL-Vorgangsnummer enthalten) und der Vorversicherer
              die Antwort sofort über das Verfahren abwickeln möchte, ohne die Belege bei der
              GDV DL erfassen zu lassen.
              Um dem Verband mitzuteilen, welches VU die Anfrage gestellt hat, erstellt der Vorversicherer
              diesen Referenzsatz, dem die Datensätze wie bei der positiven oder negativen Antwort folgen.
              """);
      satzart.setBemerkungen("""
              • In diesem Referenzsatz sowie in allen zugehörigen Antwortsätzen muss vom Vorversicherer
              anstelle der Vorgangsnummer die Versicherungsscheinnummer beim Vorversicherer oder
              ein anderes eindeutiges Merkmal eingetragen werden.
              • In der GDV DL wird aus dem Antwortvorgang ein Formular generiert und an den nicht-teilnehmenden
              Nachversicherer gesendet.
              """);
      List<Datenfeld> datenfelder = new ArrayList<>();
      datenfelder.add(new Datenfeld("1", SATZART, Typ.ALPHA, 1, 2, Art.KEY, ORGANISATORISCHE_DATEN));
      datenfelder.add(
              new Datenfeld("2", VS_NR_BEIM_VORVERS, Typ.ALPHA, 3, 20, Art.M,  ORGANISATORISCHE_DATEN, new NotEmptyValidator()));
      datenfelder.add(
              new Datenfeld("3", VU_G_ST_NR_DES_VORVERS, Typ.ALPHA, 21, 28, Art.M, REFERENZSATZ_NICHT_TEILNEHMER));
      datenfelder.add(
              new Datenfeld("4", "VU-/GSt-Nr. des Nachvers.", Typ.ALPHA, 29, 36, Art.M,REFERENZSATZ_NICHT_TEILNEHMER));
      datenfelder.add(
              new Datenfeld("5", "VS-Nr. beim Nachvers..", Typ.ALPHA, 37, 56, Art.M, REFERENZSATZ_NICHT_TEILNEHMER, new NotEmptyValidator()));
      datenfelder
              .add(new Datenfeld("6", FILLER, Typ.ALPHA, 57, 255, Art.K,  REFERENZSATZ_NICHT_TEILNEHMER));
      satzart.setDatenfelder(datenfelder);
      return satzart;
   }
}
