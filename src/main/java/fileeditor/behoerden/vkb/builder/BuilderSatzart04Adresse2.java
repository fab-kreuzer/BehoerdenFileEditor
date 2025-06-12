package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import java.util.List;

public class BuilderSatzart04Adresse2 implements VkbAtSatzartBuilder {

   private static final String STATUSTEIL = "Statusteil";

   @Override
   public Satzart build() {
      Satzart vkbAtSatzart = new Satzart();
      vkbAtSatzart.setSatzart(VkbSatzartId.VERADR02.getId());
      vkbAtSatzart.setBezeichnung(VkbSatzartId.VERADR02.getBezeichnung());
      vkbAtSatzart.setBemerkungen(" Vollständige Adresse für natürliche und juristische Person mit getrennten Adressdatenfeldern");
      vkbAtSatzart.setDatenfelder(buildDataFiels());
      return vkbAtSatzart;
   }

   private List<Datenfeld> buildDataFiels() {
      List<Datenfeld> datenfelder = buildInitList();
      int position = 24;

      datenfelder.add(
         new Datenfeld("5", "Postleitzahl", Typ.ALPHA, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
         new Datenfeld("6", "Ortsbezeichnung", Typ.ALPHA, position + 1, position += 120, Art.M, STATUSTEIL));
      datenfelder
            .add(new Datenfeld("7", "Gemeindebezeichnung", Typ.ALPHA, position + 1, position += 60, Art.M, STATUSTEIL));
      return datenfelder;
   }

}
