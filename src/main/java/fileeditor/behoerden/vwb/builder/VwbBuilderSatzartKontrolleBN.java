package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import java.util.ArrayList;
import java.util.List;

public class VwbBuilderSatzartKontrolleBN implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart("KONTROLLE BN");
      satzart.setBezeichnung("Fußsatz");
      satzart.setInfo("""
              Der Fußsatz ist der letzte Satz in der Datei. Er wird vom Verband und von den Unternehmen \
              in die jeweiligen Dateien eingestellt.
              Er enthält Informationen über die aktuelle Lieferung (Liefernummer, Anzahl der Sätze, Erstellungsdatum).
              Darüber hinaus enthält er Informationen über die letzte Lieferung (Liefernummer,
              Erstellungsdatum), so dass automatisiert geprüft werden kann, ob die Dateien lückenlos
              übertragen worden sind.
              """);
      satzart.setBemerkungen("""
              • Die Liefernummer wird in jedem Kalenderjahr auf 0001 zurückgesetzt.
              • Bei jeder Lieferung wird die Liefernummer um 1 erhöht.
              • Die Anzahl der Datensätze wird inklusive Kopf- und Fußsatz angegeben.
              """);
      List<Datenfeld> datenfelder = new ArrayList<>();
      datenfelder.add(
              new Datenfeld("1", "Konstante 'KONTROLLE BN'", Typ.ALPHA, 1, 12, Art.KEY, 
                      KONTROLLINFORMATION_FUSSSATZ));
      datenfelder.add(
              new Datenfeld("2", "Datum der Dateierstellung (JJJJMMTT)", Typ.ALPHA, 13, 20, Art.M, 
                      KONTROLLINFORMATION_FUSSSATZ));
      datenfelder.add(
              new Datenfeld("3", "Liefernummer", Typ.NUM, 21, 24, Art.M,  KONTROLLINFORMATION_FUSSSATZ));
      datenfelder.add(
              new Datenfeld("4", "Anzahl Datensätze", Typ.NUM, 25, 32, Art.M, 
                      KONTROLLINFORMATION_FUSSSATZ));
      datenfelder.add(
              new Datenfeld("5", "Datum der Vorlieferung (JJJJMMTT)", Typ.ALPHA, 33, 40, Art.M, 
                      KONTROLLINFORMATION_FUSSSATZ));
      datenfelder.add(
              new Datenfeld("6", "Liefernummer der Vorlieferung", Typ.NUM, 41, 44, Art.M, 
                      KONTROLLINFORMATION_FUSSSATZ));
      datenfelder
              .add(new Datenfeld("7", FILLER, Typ.ALPHA, 45, 255, Art.K,  KONTROLLINFORMATION_FUSSSATZ));
      satzart.setDatenfelder(datenfelder);
      return satzart;
   }
}
