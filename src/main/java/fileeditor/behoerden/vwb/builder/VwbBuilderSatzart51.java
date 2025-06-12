package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import java.util.List;

public class VwbBuilderSatzart51 implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart("51");
      satzart.setBezeichnung("Antwort-Folgesatz KH");
      satzart.setBemerkungen("""
              In jedem Folgesatz können bis zu 10 Schäden dokumentiert werden. Sofern Tag und Monat \
              unbekannt sind, dürfen diese mit '00' angegeben werden. Das Jahr muss in jedem Fall einen \
              gültigen Wert haben.
              """);
      List<Datenfeld> datenfelder = buildAntwortFolgesatzDatenfelder();
      satzart.setDatenfelder(datenfelder);
      return satzart;
   }
}
