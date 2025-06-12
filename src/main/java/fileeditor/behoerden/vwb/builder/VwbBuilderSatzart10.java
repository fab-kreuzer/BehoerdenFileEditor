package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Satzart;

public class VwbBuilderSatzart10 implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart("10");
      satzart.setBezeichnung("Anfrage");
      satzart.setBemerkungen("""
              Ist die vollständige VU-/GSt-Nr. des Vorvers. nicht bekannt, müssen zumindest die vier
              Stellen der VU-Nummer angegeben werden, GSt-Nummer und Prüfziffer können als Space angegeben
              werden. In diesem Fall schlüsselt die GDV DL die Angaben so, dass die Anfrage an die
              Direktion des Vorversicherers gerichtet wird.
              """);
      satzart.setDatenfelder(buildAnfrageDatenfelder());
      return satzart;
   }
}
