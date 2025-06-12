package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.validators.general.NumberValidator;
import java.util.ArrayList;
import java.util.List;

public interface VkbAtSatzartBuilder {
   
   String HEADER = "Header";
   String STATUSTEIL = "Statusteil";
   String VERSICHERUNGSDATEN = "\tVersicherungsdaten";

   Satzart build();
   
   default List<Datenfeld> buildInitList() {
      int position = 0;
      return new ArrayList<>(List.of(new Datenfeld("1", "Erstellungsdatum", Typ.DATE_YYYYMMDD, position + 1, position += 8, Art.M, HEADER, new NumberValidator()),
              new Datenfeld("2", "Laufnummer Geschäftsfall", Typ.NUM, position + 1, position += 6, Art.M, HEADER, new NumberValidator()),
              new Datenfeld("3", "Satznummer innerhalb Geschäftsfall", Typ.NUM, position + 1, position += 2, Art.M, HEADER, new NumberValidator()),
              new Datenfeld("4", "Segmentkennung", Typ.ALPHA, position + 1, position += 8, Art.KEY, STATUSTEIL)));
   }
}
