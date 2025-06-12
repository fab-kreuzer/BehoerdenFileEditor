package fileeditor.behoerden.general;

import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.vkb.builder.VkbAtSatzartBuilderComplete;
import fileeditor.behoerden.vwb.builder.VwbSatzartBuilderComplete;
import java.util.Map;

public abstract class SatzartBuilderComplete {

   public abstract Map<String, Satzart> build();

   public abstract Satzart fromKey(String key);

   public static Map<String, Satzart> getSatzartMapByKey(String behoerde) {
      return switch (behoerde.toLowerCase()) {
         case "vkb" -> new VkbAtSatzartBuilderComplete().build();
         case "vwb" -> new VwbSatzartBuilderComplete().build();
         default -> null;
      };
   }

   public static Satzart getSatzartFromLine(String behoerde, String line) {
      return switch (behoerde.toLowerCase()) {
         case "vkb" -> new VkbAtSatzartBuilderComplete().fromKey(line);
         case "vwb" -> new VwbSatzartBuilderComplete().fromKey(line);
         default -> null;
      };
   }
   
   public static Satzart getSatzartFromMenuEntry(String behoerde, String line) {
      return switch (behoerde.toLowerCase()) {
         case "vkb" -> new VkbAtSatzartBuilderComplete().fromMenuEntryLabel(line);
         case "vwb" -> new VwbSatzartBuilderComplete().fromKey(line);
         default -> null;
      };
   }
}
