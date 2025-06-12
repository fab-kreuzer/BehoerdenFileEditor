package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.SatzartBuilderComplete;
import java.util.HashMap;
import java.util.Map;

public class VkbAtSatzartBuilderComplete extends SatzartBuilderComplete {

   @Override
   public Map<String, Satzart> build() {

      Map<String, Satzart> satzarten = new HashMap<>();

      satzarten.put("Unbekannt", new BuilderSatzartUnbekannt().build());
      satzarten.put(VkbSatzartId.KZRSTA01.buildMenuEntry(), new BuilderSatzart01().build());
      satzarten.put(VkbSatzartId.KZRPRS01.buildMenuEntry(), new BuilderSatzart04Personal().build());
      satzarten.put(VkbSatzartId.VERADR01.buildMenuEntry(), new BuilderSatzart04Adresse1().build());
      satzarten.put(VkbSatzartId.VERADR02.buildMenuEntry(), new BuilderSatzart04Adresse2().build());
      satzarten.put(VkbSatzartId.KZRPRS02.buildMenuEntry(), new BuilderSatzart04Juristic().build());
      satzarten.put(VkbSatzartId.KZRZUL01.buildMenuEntry(), new BuilderSatzart03().build());
      satzarten.put(VkbSatzartId.VERERG01.buildMenuEntry(), new BuilderSatzart05().build());
      satzarten.put(VkbSatzartId.VERKZU01.buildMenuEntry(), new BuilderSatzart06().build());
      satzarten.put(VkbSatzartId.VERERG04.buildMenuEntry(), new BuilderSatzart10().build());
      return satzarten;
   }

   @Override
   public Satzart fromKey(String key) {
      
      if (key.isBlank() || isSatzart8(key)) {
         return build().get("Unbekannt");
      }
      
      key = key.substring(16, 24);
      for (Map.Entry<String, Satzart> entry : build().entrySet()) {
         if (entry.getValue().getSatzart().equals(key)) {
            return entry.getValue();
         }
      }
      return build().get("Unbekannt");
   }
   
   public Satzart fromMenuEntryLabel(String label) {
      Satzart satzart = build().get(label);
      return (satzart != null) ? satzart : build().get("Unbekannt");
   }

   private boolean isSatzart8(String key) {
      return key.substring(16, 22).startsWith("BPK");
   }

}
