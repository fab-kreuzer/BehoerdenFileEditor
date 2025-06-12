package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.SatzartBuilderComplete;
import fileeditor.ui.jwt.Constants;
import java.util.HashMap;
import java.util.Map;

public class VwbSatzartBuilderComplete extends SatzartBuilderComplete {

   @Override
   public Map<String, Satzart> build() {
      Map<String, Satzart> result = new HashMap<>();

      result.put(Constants.UNBEKANNT, new VwbBuilderSatzartUnbekannt().build());
      result.put("KONTROLLE BV", new VwbBuilderSatzartKontrolleBV().build());
      result.put("KONTROLLE BN", new VwbBuilderSatzartKontrolleBN().build());
      result.put("10", new VwbBuilderSatzart10().build());
      result.put("20", new VwbBuilderSatzart20().build());
      result.put("21", new VwbBuilderSatzart21().build());
      result.put("22", new VwbBuilderSatzart22().build());
      result.put("23", new VwbBuilderSatzart23().build());
      result.put("31", new VwbBuilderSatzart31().build());
      result.put("32", new VwbBuilderSatzart32().build());
      result.put("40", new VwbBuilderSatzart40().build());
      result.put("51", new VwbBuilderSatzart51().build());
      result.put("52", new VwbBuilderSatzart52().build());
      result.put("61", new VwbBuilderSatzart61().build());

      return result;
   }

   @Override
   public Satzart fromKey(String key) {
      var map = build();

      if (key.isBlank()) {
         return map.get(Constants.UNBEKANNT);
      }
      
      String numberKey = key.substring(0, 2);
      if (map.containsKey(numberKey)) {
         return map.get(numberKey);
      } else if (map.containsKey(key.substring(0, 12))) {
         return map.get(key.substring(0, 12));
      } else {
         return map.get(Constants.UNBEKANNT);
      }
   }
}
