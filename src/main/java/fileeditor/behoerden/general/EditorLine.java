package fileeditor.behoerden.general;

import fileeditor.behoerden.general.types.Datenfeld;

public abstract class EditorLine {
   
   protected String line = "";
   int number = 0;

   public EditorLine(String line, int number) {
      this.line = line;
      this.number = number;
   }

   public int getNumber() {
      return this.number;
   }
   
   public String getCompleteLine() {
      return this.line;
   }
   public String getValueFromDatenfeld(Datenfeld datenfeld) {
      if (this.line.length() < datenfeld.getVon() - 1 + datenfeld.getLaenge()) {
         return "";
      }
      return this.line.substring(datenfeld.getVon() - 1, datenfeld.getVon() - 1 + datenfeld.getLaenge());
   }
   
   protected abstract String getKeyForLineInMessage();
   protected abstract String getKeyForGroupingMessage();
}
