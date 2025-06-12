package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Satzart;
import java.util.Collections;

public class BuilderSatzartUnbekannt implements VkbAtSatzartBuilder {
   
   @Override
   public Satzart build() {

      Satzart vkbAtSatzart = new Satzart();
      vkbAtSatzart.setSatzart(VkbSatzartId.UNBEKANT.getId());
      vkbAtSatzart.setBezeichnung(VkbSatzartId.UNBEKANT.getBezeichnung());
      vkbAtSatzart.setDatenfelder(Collections.emptyList());

      return vkbAtSatzart;
   }
}
