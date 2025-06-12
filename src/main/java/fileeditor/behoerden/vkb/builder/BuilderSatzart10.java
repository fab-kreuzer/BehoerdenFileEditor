package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import java.util.List;

public class BuilderSatzart10 implements VkbAtSatzartBuilder{

   @Override
   public Satzart build() {
      Satzart vkbAtSatzart = new Satzart();
      vkbAtSatzart.setSatzart(VkbSatzartId.VERERG04.getId());
      vkbAtSatzart.setBezeichnung(VkbSatzartId.VERERG04.getBezeichnung());
      vkbAtSatzart.setBemerkungen("Die Satzart wird nur an Versicherungsunternehmen übermittelt");
      vkbAtSatzart.setDatenfelder(buildDataFiels());
      return vkbAtSatzart;
   }
   
   
   private List<Datenfeld> buildDataFiels() {
      List<Datenfeld> datenfelder = buildInitList();
      int position = 24;

      datenfelder.add(
              new Datenfeld("5", "CO2 Emission WLTP/WMTC", Typ.NUM, position + 1, position += 3, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("6", "Kraftstoffverbrauch WLATP", Typ.NUM, position + 1, position += 4, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("7", "Leistung Elektromotor", Typ.NUM, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("8", "Elektro: Stromverbrauch gewichtet", Typ.NUM, position + 1, position += 5, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("9", "Hybrid-(Elektro-)Farzeug Ja/Nein, Code", Typ.ALPHA, position + 1, position += 10, Art.M, STATUSTEIL));

      return datenfelder;
   }


}
