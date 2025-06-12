package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.vwb.validator.custom.BetriebsartValidator;
import java.util.ArrayList;
import java.util.List;

public class VwbBuilderSatzartKontrolleBV implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart("KONTROLLE BV");
      satzart.setBezeichnung("Kopfsatz");
      satzart.setInfo("""
              Der Kopfsatz ist der erste Datensatz in der Datei. Er wird vom Verband und von den Unternehmen
              in die jeweiligen Dateien eingestellt.
              Mit diesem Datensatz soll sichergestellt werden, dass die Daten im richtigen Sachgebiet verarbeitet werden.
              Außerdem können die Unternehmen anhand dieses Satzes feststellen, ob eine vorliegende
              KVB-Datei eine Ausgangsdatei ist (als VU-Nummer ist die des Unternehmens eingetragen) oder
              von der GDV DL zur Verarbeitung zur Verfügung gestellt wurde (als VU-Nummer ist die Nummer
              der GDV DL eingestellt).
              """);
      satzart.setBemerkungen("""
              • Die in der vorstehenden Tabelle genannten Ausprägungen der Felder 2 und 5 beziehen sich
              auf einen Kopfsatz, der vom VU generiert wird.
              • Der von der GDV DL mitgelieferte Kopfsatz enthält in Feld 2 die VU-Nummer und in Feld 5
              die Schlüsselung '8333000'.
              • Während der Testphase ist in Feld 4 der Wert 'T' (= 'Testphase') einzustellen. Dadurch wird
              sichergestellt, dass die Daten nicht in den Produktionsbetrieb einfließen.
              """);
      List<Datenfeld> datenfelder = new ArrayList<>();
      datenfelder.add(
              new Datenfeld("1", "Konstante 'KONTROLLE BV'", Typ.ALPHA, 1, 12, Art.KEY, KONTROLLINFORMATION_KOPFSATZ));
      datenfelder.add(
              new Datenfeld("2", "VU-Nr. des Empfängers, Konstante '8333' (für GDV DL)", Typ.ALPHA, 13, 16, Art.M, KONTROLLINFORMATION_KOPFSATZ));
      datenfelder.add(
              new Datenfeld("3", "Sachgebiet, Konstante 'KVB'", Typ.ALPHA, 17, 19, Art.M, KONTROLLINFORMATION_KOPFSATZ));
      datenfelder.add(
              new Datenfeld("4", "Konstante ' ' oder 'T'", Typ.ALPHA, 20, 20, Art.K, KONTROLLINFORMATION_KOPFSATZ, new BetriebsartValidator()));
      datenfelder.add(
              new Datenfeld("5", "VU/GSt-Nr. des Absenders", Typ.ALPHA, 21, 27, Art.M, KONTROLLINFORMATION_KOPFSATZ));
      datenfelder
              .add(new Datenfeld("6", FILLER, Typ.ALPHA, 28, 255, Art.K,  KONTROLLINFORMATION_KOPFSATZ));
      satzart.setDatenfelder(datenfelder);
      return satzart;
   }
}
