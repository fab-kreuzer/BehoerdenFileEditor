package fileeditor.ui.jwt.mapper;

import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.ui.jwt.data.JwtDatenFeld;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class DatenFeldMapper {

   public static DatenFeldMapper INSTANCE = Mappers.getMapper(DatenFeldMapper.class);

   public abstract JwtDatenFeld toJwtDatenfeld(Datenfeld datenfeld);

}
