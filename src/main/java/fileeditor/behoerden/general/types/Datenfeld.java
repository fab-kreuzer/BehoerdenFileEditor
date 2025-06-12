package fileeditor.behoerden.general.types;

import fileeditor.behoerden.general.ValidationInputParameters;
import fileeditor.behoerden.validators.FieldValidator;
import fileeditor.behoerden.validators.general.DateFormatValidator;
import fileeditor.behoerden.validators.general.NumberValidator;
import fileeditor.behoerden.validators.general.ValidCharsetValidator;
import fileeditor.behoerden.validators.general.ValidValueOrEmptyValidator;
import fileeditor.behoerden.validators.general.ValidValueValidator;
import fileeditor.behoerden.validators.general.ValidValueValidatorGeneral;
import fileeditor.behoerden.validators.response.DatenfeldValidationResponse;
import java.util.ArrayList;
import java.util.List;

public class Datenfeld {

   private String nummer;
   private String bezeichnung;
   private Typ typ;
   private int von;
   private int bis;
   private Art art;
   private String block;
   private List<FieldValidator> fieldValidators;
   private List<ValidValue> validValues;
   private boolean canBeEmpty;
   
   public Datenfeld(String bezeichnung) {
      this("", bezeichnung, Typ.LEER, 0, 0, Art.LEER, "", null);
   }

   @SuppressWarnings("java:S107")
   //With Validators
   public Datenfeld(String nummer, String bezeichnung, Typ typ, int von, int bis, Art art, String block, FieldValidator... fieldValidators) {
      this.nummer = nummer;
      this.bezeichnung = bezeichnung;
      this.typ = typ;
      this.von = von;
      this.bis = bis;
      this.art = art;
      this.block = block;
      this.fieldValidators = new ArrayList<>(List.of(fieldValidators));
      this.validValues = new ArrayList<>();
      this.canBeEmpty = false;
   }
   
   @SuppressWarnings("java:S107")
   //No Validators
   public Datenfeld(String nummer, String bezeichnung, Typ typ, int von, int bis, Art art, String block) {
      this.nummer = nummer;
      this.bezeichnung = bezeichnung;
      this.typ = typ;
      this.von = von;
      this.bis = bis;
      this.art = art;
      this.block = block;
      this.fieldValidators = new ArrayList<>();
      this.validValues = new ArrayList<>();
      this.canBeEmpty = false;

   }
   public Datenfeld(String nummer, String bezeichnung, Typ typ, int von, int bis, Art art, String block, List<ValidValue> validValues, boolean canBeEmpty) {
      this.nummer = nummer;
      this.bezeichnung = bezeichnung;
      this.typ = typ;
      this.von = von;
      this.bis = bis;
      this.art = art;
      this.block = block;
      this.fieldValidators = new ArrayList<>();
      this.validValues = validValues;
      this.canBeEmpty = canBeEmpty;
   }
   
   
   public DatenfeldValidationResponse validate(ValidationInputParameters param) {
      addStandardValidators();
      DatenfeldValidationResponse response = new DatenfeldValidationResponse();
      response.setDatenfeld(this);
      response.setErrors(fieldValidators.stream()
              .flatMap(validator -> validator.validate(param).stream())
              .toList());
      return response;
   }

   private void addStandardValidators() {
      
      fieldValidators.add(new ValidCharsetValidator());
      
      if (typ.isNum() && hasNoNumberValidator()) {
         fieldValidators.add(new NumberValidator());
      }
      
      if (typ.isDate() && hasNoDateValidator()) {
         fieldValidators.add(new DateFormatValidator("yyyyMMdd"));
      }
      
      if (hasNoValidValueValidator() && hasValidValues()) {
         fieldValidators.add(new ValidValueValidatorGeneral(this.validValues, this.canBeEmpty));
      }
   }

   private boolean hasValidValues() {
      return !validValues.isEmpty();
   }

   private boolean hasNoNumberValidator() {
      return fieldValidators.stream().noneMatch(NumberValidator.class::isInstance);
   }
   
   private boolean hasNoDateValidator() {
      return fieldValidators.stream().noneMatch(DateFormatValidator.class::isInstance);
   }
   
   private boolean hasNoValidValueValidator() {
      return fieldValidators.stream().noneMatch(ValidValueValidator.class::isInstance) || fieldValidators.stream().noneMatch(ValidValueOrEmptyValidator.class::isInstance);
   }

   public String getNummer() {
      return nummer;
   }

   public String getBezeichnung() {
      return bezeichnung;
   }

   public Typ getTyp() {
      return typ;
   }

   public int getVon() {
      return von;
   }

   public int getBis() {
      return bis;
   }

   public Art getArt() {
      return art;
   }

   public String getBlock() {
      return block;
   }

   public void setNummer(String nummer) {
      this.nummer = nummer;
   }

   public void setBezeichnung(String bezeichnung) {
      this.bezeichnung = bezeichnung;
   }

   public void setTyp(Typ typ) {
      this.typ = typ;
   }

   public void setVon(int von) {
      this.von = von;
   }

   public void setBis(int bis) {
      this.bis = bis;
   }

   public void setArt(Art art) {
      this.art = art;
   }

   public void setBlock(String block) {
      this.block = block;
   }

   public int getLaenge() {
      return bis - von + 1;
   }

   public String buildFehlerId() {
      StringBuilder builder = new StringBuilder();
      builder.append(getNummer());
      builder.append(" ");
      builder.append(getBezeichnung());
      builder.append(" (");
      builder.append(getVon());
      builder.append(" - ");
      builder.append(getBis());
      builder.append(")");
      return builder.toString();
   }

   @SuppressWarnings("java:S3776")
   public String buildSample(String key) {
      if (getArt() == Art.KEY) {
         return key;
      }
      StringBuilder builder = new StringBuilder();
      String sample = getTyp() == Typ.ALPHA ? "A" : "0";
      if (getArt() == Art.K) {
         sample = " ";
      } else if (getArt() == Art.M) {
         sample = getTyp() == Typ.ALPHA ? "M" : "9";
      } else if (getArt() == Art.G) {
         sample = getTyp() == Typ.ALPHA ? "G" : "0";
      }
      builder.append(sample.repeat(Math.max(0, getLaenge())));
      
      return builder.toString();
   }

   public List<ValidValue> getValidValues() {
      return validValues;
   }

   public boolean isCanBeEmpty() {
      return canBeEmpty;
   }
}
