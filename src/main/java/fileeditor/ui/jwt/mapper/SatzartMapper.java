package fileeditor.ui.jwt.mapper;

import fileeditor.behoerden.general.types.Satzart;
import fileeditor.ui.jwt.data.JwtSatzart;
import java.util.HashMap;
import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class SatzartMapper {

   public static SatzartMapper INSTANCE = Mappers.getMapper(SatzartMapper.class);

   public Map<String, JwtSatzart> mapToJwtSatzartMap(Map<String, Satzart> satzartMap) {
      if (satzartMap == null) {
         return null;
      }
      Map<String, JwtSatzart> jwtSatzartMap = new HashMap<>();
      for (Map.Entry<String, Satzart> entry : satzartMap.entrySet()) {
         jwtSatzartMap.put(entry.getKey(), toJwtSatzart(entry.getValue()));
      }
      return jwtSatzartMap;
   }

   public abstract JwtSatzart toJwtSatzart(Satzart satzart);
}
