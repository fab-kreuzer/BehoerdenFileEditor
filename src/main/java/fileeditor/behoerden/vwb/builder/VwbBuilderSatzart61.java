package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Satzart;

public class VwbBuilderSatzart61 implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart("61");
      satzart.setBezeichnung("Ohne Beantwortung");
      satzart.setInfo("""
              Der Referenzsatz 'ohne Beantwortung' entspricht im Aufbau dem Datensatz 'Anfrage'. Er 
              enthält die Originaldaten, wie sie vom Nachversicherer angefragt wurden. Das Feld n-te Erinnerung 
              enthält die Anzahl der generierten Erinnerungen. Im Feld VU-/GSt-Nr. des Vorvers. 
              wird von der GDV DL eine Umschlüsselung vorgenommen, wenn eine unvollständige 
              VU-Nr. angegeben wurde oder der Bestand des angegebenen Vorversicherers inzwischen von 
              einem anderen VU übernommen wurde.
              Dieser Satz wird nur von der GDV DL erzeugt.
              """);
      satzart.setDatenfelder(buildAnfrageDatenfelder());
      return satzart;
   }
}
