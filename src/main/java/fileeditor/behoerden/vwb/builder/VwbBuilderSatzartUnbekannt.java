package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Satzart;
import fileeditor.ui.jwt.Constants;
import java.util.Collections;

public class VwbBuilderSatzartUnbekannt implements VwbSatzartBuilder {
   
   @Override
   public Satzart build() {
      Satzart satzart = new Satzart();
      satzart.setSatzart(Constants.UNBEKANNT);
      satzart.setBezeichnung("Unbekannte Satzart");
      satzart.setDatenfelder(Collections.emptyList());
      return satzart;
   }
}
