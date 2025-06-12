package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import java.util.List;

public class VwbBuilderSatzart32 implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart("32");
      satzart.setBezeichnung("Positive Antwort VK");
      satzart.setBemerkungen("""
              Der Vorversicherer stellt im Feld korrekte VS-Nr. beim Vorvers.die korrigierte VS-Nr. zur
              Verfügung. Der Nachversicherer sollte anstelle der in der Anfrage genannten VS-Nr. die hier
              genannte korrekte VS-Nr. speichern.
              """);
      List<Datenfeld> datenfelder = buildPositiveAntwortDatenfelder();
      satzart.setDatenfelder(datenfelder);
      return satzart;
   }
}
