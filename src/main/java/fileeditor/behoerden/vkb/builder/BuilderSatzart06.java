package fileeditor.behoerden.vkb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import java.util.List;

public class BuilderSatzart06 implements VkbAtSatzartBuilder{

   @Override
   public Satzart build() {
      Satzart vkbAtSatzart = new Satzart();
      vkbAtSatzart.setSatzart(VkbSatzartId.VERKZU01.getId());
      vkbAtSatzart.setBezeichnung(VkbSatzartId.VERKZU01.getBezeichnung());
      vkbAtSatzart.setBemerkungen("Die Satzart enthält das bisherige und das neue Kennzeichen des Zulassungsfalls.\n" +
              "Die Satzart wird nur an den haftenden Versicherer übermittelt");
      vkbAtSatzart.setDatenfelder(buildDataFiels());
      return vkbAtSatzart;
   }
   
   
   private List<Datenfeld> buildDataFiels() {
      List<Datenfeld> datenfelder = buildInitList();
      int position = 24;

      datenfelder.add(
              new Datenfeld("5", "Neues Kennzeichen: Sachbereich", Typ.ALPHA, position + 1, position += 2, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("6", "Neues Kennzeichen: Vormerkteil", Typ.ALPHA, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("7", "Altes Kennzeichen: Sachbereich", Typ.ALPHA, position + 1, position += 2, Art.M, STATUSTEIL));
      datenfelder
              .add(new Datenfeld("8", "Altes Kennzeichen: Vormerkteil", Typ.ALPHA, position + 1, position += 6, Art.M, STATUSTEIL));
      datenfelder.add(
              new Datenfeld("9", "Altes Kennzeichen: Nummernkreis", Typ.ALPHA, position + 1, position += 1, Art.M, STATUSTEIL));

      return datenfelder;
   }


}
