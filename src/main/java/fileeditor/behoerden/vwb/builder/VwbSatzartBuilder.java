package fileeditor.behoerden.vwb.builder;

import fileeditor.behoerden.general.types.Art;
import fileeditor.behoerden.general.types.Datenfeld;
import fileeditor.behoerden.general.types.Satzart;
import fileeditor.behoerden.general.types.Typ;
import fileeditor.behoerden.validators.general.DateFormatValidator;
import fileeditor.behoerden.validators.general.NotEmptyValidator;
import fileeditor.behoerden.validators.general.NotGreaterThanValidator;
import fileeditor.behoerden.validators.general.NumberValidator;
import fileeditor.behoerden.validators.general.YesNoValidator;
import fileeditor.behoerden.vwb.validator.custom.AnfragegrundValidator;
import fileeditor.behoerden.vwb.validator.custom.AnredeValidator;
import fileeditor.behoerden.vwb.validator.custom.FreistellungValidator;
import fileeditor.behoerden.vwb.validator.custom.LicencePlateValidator;
import fileeditor.behoerden.vwb.validator.custom.RabattgrundjahrValidator;
import fileeditor.behoerden.vwb.validator.custom.VertragsstatusValidator;
import java.util.ArrayList;
import java.util.List;

public interface VwbSatzartBuilder {

   String SFR_RUECKGABE = "SFR-Rückgabe";
   String NEGATIVE_ANTWORT = "Negative Antwort";
   String FOLGESATZ_SCHADEN = "Folgesatz Schaden ";
   String POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU = "Positive Antwort KH, VK, TK-neu, VK-neu";
   String REFERENZSATZ_NICHT_TEILNEHMER = "Referenzsatz 'Nicht-Teilnehmer'";
   String KORREKTUR_SATZ = "Korrektur-Satz";
   String VS_NR_BEIM_VORVERS = "VS-Nr. beim Vorvers.";
   String VU_G_ST_NR_DES_VORVERS = "VU-/GSt-Nr. des Vorvers.";
   String LAUFENDE_NUMMER = "laufende Nummer";
   String DATUM_JJJJMMTT = "Datum (JJJJMMTT)";
   String VU_NUMMER = "VU-Nummer";
   String SATZART = "Satzart";
   String ANFRAGEDATEN = "Anfragedaten";
   String VERBANDS_VORGANGSNUMMER = "Verbands-Vorgangsnummer";
   String ORGANISATORISCHE_DATEN = "Organisatorische Daten";
   String KONTROLLINFORMATION_FUSSSATZ = "Kontrollinformation 'Fußsatz'";
   String KONTROLLINFORMATION_KOPFSATZ = "Kontrollinformation 'Kopfsatz'";
   String FILLER = "Filler";

   default List<Datenfeld> buildAnfrageDatenfelder() {
      List<Datenfeld> datenfelder = new ArrayList<>();
      datenfelder.add(new Datenfeld("1", SATZART, Typ.ALPHA, 1, 2, Art.KEY, ORGANISATORISCHE_DATEN));
      datenfelder
              .add(new Datenfeld("2.1", VU_NUMMER, Typ.ALPHA, 3, 6, Art.K, VERBANDS_VORGANGSNUMMER));
      datenfelder.add(
              new Datenfeld("2.2", DATUM_JJJJMMTT, Typ.ALPHA, 7, 14, Art.K, VERBANDS_VORGANGSNUMMER, new DateFormatValidator("yyyyMMdd")));
      datenfelder.add(
              new Datenfeld("2.3", LAUFENDE_NUMMER, Typ.NUM, 15, 20, Art.K, VERBANDS_VORGANGSNUMMER));
      datenfelder.add(
              new Datenfeld("3", "VU/GSt-Nr. des Nachvers.", Typ.ALPHA, 21, 28, Art.M, ANFRAGEDATEN));
      datenfelder
              .add(new Datenfeld("4", "VS-Nr. beim Nachvers.", Typ.ALPHA, 29, 48, Art.M, ANFRAGEDATEN, new NotEmptyValidator()));
      datenfelder.add(
              new Datenfeld("5", "Anfragegrund", Typ.NUM, 49, 50, Art.M, ANFRAGEDATEN, new AnfragegrundValidator()));
      datenfelder.add(
              new Datenfeld("6", "Fahrzeugidentifizierungs-Nr. beim Nachvers.", Typ.ALPHA, 51, 67, Art.M,
                      ANFRAGEDATEN));
      datenfelder.add(
              new Datenfeld("7", "Versich.beginn beim Nachvers.", Typ.ALPHA, 68, 75, Art.SA10_A2,
                      ANFRAGEDATEN, new DateFormatValidator()));
      datenfelder.add(
              new Datenfeld("8", "VN-Anredeschlüssel beim Nachvers.", Typ.ALPHA, 76, 76, Art.M,
                      ANFRAGEDATEN, new AnredeValidator()));
      datenfelder.add(
              new Datenfeld("9", "VN-Namenszeile 1 beim Nachvers.", Typ.ALPHA, 77, 106, Art.M,
                      ANFRAGEDATEN, new NotEmptyValidator()));
      datenfelder.add(
              new Datenfeld("10", "VN-Namenszeile 2 beim Nachvers.", Typ.ALPHA, 107, 131, Art.K,
                      ANFRAGEDATEN));
      datenfelder.add(
              new Datenfeld("11", "VN-Namenszeile 3 oder Vorname beim Nachvers.", Typ.ALPHA, 132, 151, Art.SA10_A1,
                       ANFRAGEDATEN));
      datenfelder.add(
              new Datenfeld("12", "VN-Straße beim Nachvers.", Typ.ALPHA, 152, 181, Art.K, ANFRAGEDATEN));
      datenfelder.add(
              new Datenfeld("13", "VN-LdKz beim Nachvers.", Typ.ALPHA, 182, 184, Art.K, ANFRAGEDATEN));
      datenfelder.add(
              new Datenfeld("14", "VN-PLZ beim Nachvers.", Typ.ALPHA, 185, 190, Art.K, ANFRAGEDATEN));
      datenfelder.add(
              new Datenfeld("15", "VN-Ort beim Nachvers.", Typ.ALPHA, 191, 215, Art.K, ANFRAGEDATEN));
      datenfelder.add(
              new Datenfeld("16", VU_G_ST_NR_DES_VORVERS, Typ.ALPHA, 216, 223, Art.M, ANFRAGEDATEN));
      datenfelder
              .add(new Datenfeld("17", VS_NR_BEIM_VORVERS, Typ.ALPHA, 224, 243, Art.M, ANFRAGEDATEN, new NotEmptyValidator()));
      datenfelder.add(
              new Datenfeld("18", "AKZ beim Vorvers.", Typ.ALPHA, 244, 253, Art.SA10_A3, ANFRAGEDATEN, new LicencePlateValidator()));
      datenfelder.add(
              new Datenfeld("19", "Bescheinigung gem. Par. 5 PflVersG wurde vorgelegt", Typ.ALPHA, 254, 254, Art.K,
                       ANFRAGEDATEN, new YesNoValidator()));
      datenfelder.add(new Datenfeld("20", "n-te Erinnerung", Typ.NUM, 255, 255, Art.G, ANFRAGEDATEN));
      return datenfelder;
   }
   default List<Datenfeld> buildPositiveAntwortDatenfelder() {
      List<Datenfeld> datenfelder = new ArrayList<>();
      datenfelder.add(new Datenfeld("1", SATZART, Typ.ALPHA, 1, 2, Art.KEY, ORGANISATORISCHE_DATEN));
      datenfelder.add(new Datenfeld("2.1", VU_NUMMER, Typ.ALPHA, 3, 6, Art.M, VERBANDS_VORGANGSNUMMER));
      datenfelder.add(new Datenfeld("2.2", DATUM_JJJJMMTT, Typ.ALPHA, 7, 14, Art.M, VERBANDS_VORGANGSNUMMER, new DateFormatValidator("yyyyMMdd")));
      datenfelder.add(new Datenfeld("2.3", LAUFENDE_NUMMER, Typ.NUM, 15, 20, Art.M, VERBANDS_VORGANGSNUMMER));
      datenfelder.add(new Datenfeld("3", VU_G_ST_NR_DES_VORVERS, Typ.ALPHA, 21, 28, Art.M, NEGATIVE_ANTWORT));
      datenfelder.add(new Datenfeld("4", "Datum Antwort (TTMMJJJJ)", Typ.ALPHA, 29, 36, Art.K, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new DateFormatValidator()));
      datenfelder.add(new Datenfeld("5", "VN-Anredeschlüssel beim Vorvers.", Typ.ALPHA, 37, 37, Art.M, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new AnredeValidator()));
      datenfelder.add(new Datenfeld("6", "VN-Namenszeile 1 beim Vorvers.", Typ.ALPHA, 38, 67, Art.M, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new NotEmptyValidator()));
      datenfelder.add(new Datenfeld("7", "VN-Namenszeile 2 beim Vorvers.", Typ.ALPHA, 68, 92, Art.K, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      datenfelder.add(new Datenfeld("8", "VN-Namenszeile 3 beim Vorvers. oder Vorname beim Vorvers.", Typ.ALPHA, 93, 112, Art.SA3X_A1, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      datenfelder.add(new Datenfeld("9", "VN-Straße beim Vorvers.", Typ.ALPHA, 113, 142, Art.K, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      datenfelder.add(new Datenfeld("10", "VN-LdKz beim Vorvers.", Typ.ALPHA, 143, 145, Art.K, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      datenfelder.add(new Datenfeld("11", "VN-PLZ beim Vorvers.", Typ.ALPHA, 146, 151, Art.K, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      datenfelder.add(new Datenfeld("12", "VN-Ort beim Vorvers.", Typ.ALPHA, 152, 176, Art.K, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      datenfelder.add(new Datenfeld("13", "korrekte VS-Nr. beim Vorvers.", Typ.ALPHA, 177, 196, Art.M, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      datenfelder.add(new Datenfeld("14", "Fahrzeugidentifizierungs-Nr. beim Vorvers.", Typ.ALPHA, 197, 213, Art.K, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      datenfelder.add(new Datenfeld("15", "WKZ", Typ.ALPHA, 214, 216, Art.M, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      datenfelder.add(new Datenfeld("16", "Beginn des Vertrages", Typ.ALPHA, 217, 224, Art.K, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new DateFormatValidator()));
      datenfelder.add(new Datenfeld("17", "Vertragsende / Datum Rabattübertragung", Typ.ALPHA, 225, 232, Art.SA3X_A3, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new DateFormatValidator()));
      datenfelder.add(new Datenfeld("18", "Vertragsstatus", Typ.ALPHA, 233, 233, Art.M, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new VertragsstatusValidator()));
      datenfelder.add(new Datenfeld("19", "Rabattgrundjahr", Typ.NUM, 234, 237, Art.M, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new RabattgrundjahrValidator()));
      datenfelder.add(new Datenfeld("20", "S/SF-Stufe", Typ.ALPHA, 238, 239, Art.SA3X_A2, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      datenfelder.add(new Datenfeld("21", "Anz. belastende Schäden ohne Auswirkung auf SFR", Typ.NUM, 240, 242, Art.SA3X_A5, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new NumberValidator()));
      datenfelder.add(new Datenfeld("22", "Anz. belastende Schäden mit Auswirkung auf SFR", Typ.NUM, 243, 245, Art.SA3X_A5, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new NumberValidator()));
      datenfelder.add(new Datenfeld("23", "Unterbrechungstage im Kalenderjahr der Beendigung", Typ.NUM, 246, 248, Art.SA3X_A4, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new NotGreaterThanValidator(366)));
      datenfelder.add(new Datenfeld("24", "Bescheinigung gem. § 5 PflVersG wurde erteilt", Typ.ALPHA, 249, 249, Art.K, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU, new YesNoValidator()));
      datenfelder.add(new Datenfeld("25", FILLER, Typ.ALPHA, 250, 255, Art.K, POSITIVE_ANTWORT_KH_VK_TK_NEU_VK_NEU));
      return datenfelder;
   }
   default List<Datenfeld> buildAntwortFolgesatzDatenfelder() {
      List<Datenfeld> datenfelder = new ArrayList<>();
      datenfelder.add(new Datenfeld("1", SATZART, Typ.ALPHA, 1, 2, Art.KEY, ORGANISATORISCHE_DATEN));
      datenfelder
              .add(new Datenfeld("2.1", VU_NUMMER, Typ.ALPHA, 3, 6, Art.M, VERBANDS_VORGANGSNUMMER));
      datenfelder.add(
              new Datenfeld("2.2", DATUM_JJJJMMTT, Typ.ALPHA, 7, 14, Art.M, VERBANDS_VORGANGSNUMMER, new DateFormatValidator("yyyyMMdd")));
      datenfelder.add(
              new Datenfeld("2.3", LAUFENDE_NUMMER, Typ.NUM, 15, 20, Art.M, VERBANDS_VORGANGSNUMMER));
      datenfelder
              .add(new Datenfeld("3", VU_G_ST_NR_DES_VORVERS, Typ.ALPHA, 21, 28, Art.M, "Folgesatz"));
      datenfelder.add(
              new Datenfeld("4", "Meldejahr Schaden 1", Typ.ALPHA, 29, 30, Art.SA5X_A1A2,
                      FOLGESATZ_SCHADEN + "1"));
      datenfelder.add(
              new Datenfeld("5", "Schadendatum Schaden 1", Typ.ALPHA, 31, 38, Art.K,
                      FOLGESATZ_SCHADEN + "1", new NumberValidator()));
      datenfelder.add(
              new Datenfeld("6", "Freistellung Schaden 1", Typ.ALPHA, 39, 39, Art.SA5X_A3,
                       FOLGESATZ_SCHADEN + "1", new FreistellungValidator()));
      datenfelder.add(new Datenfeld("7", FILLER, Typ.ALPHA, 40, 48, Art.K, FOLGESATZ_SCHADEN + "1"));
      datenfelder.add(
              new Datenfeld("8", "Meldejahr Schaden 2", Typ.ALPHA, 49, 50, Art.SA5X_A1A2,
                      FOLGESATZ_SCHADEN + "2"));
      datenfelder.add(
              new Datenfeld("9", "Schadendatum Schaden 2", Typ.ALPHA, 51, 58, Art.K,
                      FOLGESATZ_SCHADEN + "2", new NumberValidator()));
      datenfelder.add(
              new Datenfeld("10", "Freistellung Schaden 2", Typ.ALPHA, 59, 59, Art.SA5X_A3,
                       FOLGESATZ_SCHADEN + "2", new FreistellungValidator()));
      datenfelder.add(new Datenfeld("11", FILLER, Typ.ALPHA, 60, 68, Art.K, FOLGESATZ_SCHADEN + "2"));
      datenfelder.add(
              new Datenfeld("12", "Meldejahr Schaden 3", Typ.ALPHA, 69, 70, Art.SA5X_A1A2,
                      FOLGESATZ_SCHADEN + "3"));
      datenfelder.add(
              new Datenfeld("13", "Schadendatum Schaden 3", Typ.ALPHA, 71, 78, Art.K,
                      FOLGESATZ_SCHADEN + "3", new NumberValidator()));
      datenfelder.add(
              new Datenfeld("14", "Freistellung Schaden 3", Typ.ALPHA, 79, 79, Art.SA5X_A3,
                       FOLGESATZ_SCHADEN + "3", new FreistellungValidator()));
      datenfelder.add(new Datenfeld("15", FILLER, Typ.ALPHA, 80, 88, Art.K, FOLGESATZ_SCHADEN + "3"));
      datenfelder.add(
              new Datenfeld("16", "Meldejahr Schaden 4", Typ.ALPHA, 89, 90, Art.SA5X_A1A2,
                      FOLGESATZ_SCHADEN + "4"));
      datenfelder.add(
              new Datenfeld("17", "Schadendatum Schaden 4", Typ.ALPHA, 91, 98, Art.K,
                      FOLGESATZ_SCHADEN + "4", new NumberValidator()));
      datenfelder.add(
              new Datenfeld("18", "Freistellung Schaden 4", Typ.ALPHA, 99, 99, Art.SA5X_A3,
                       FOLGESATZ_SCHADEN + "4", new FreistellungValidator()));
      datenfelder
              .add(new Datenfeld("19", FILLER, Typ.ALPHA, 100, 108, Art.K, FOLGESATZ_SCHADEN + "4"));
      datenfelder.add(
              new Datenfeld("20", "Meldejahr Schaden 5", Typ.ALPHA, 109, 110, Art.SA5X_A1A2,
                      FOLGESATZ_SCHADEN + "5"));
      datenfelder.add(
              new Datenfeld("21", "Schadendatum Schaden 5", Typ.ALPHA, 111, 118, Art.K,
                      FOLGESATZ_SCHADEN + "5", new NumberValidator()));
      datenfelder.add(
              new Datenfeld("22", "Freistellung Schaden 5", Typ.ALPHA, 119, 119, Art.SA5X_A3,
                       FOLGESATZ_SCHADEN + "5", new FreistellungValidator()));
      datenfelder
              .add(new Datenfeld("23", FILLER, Typ.ALPHA, 120, 128, Art.K, FOLGESATZ_SCHADEN + "5"));
      datenfelder.add(
              new Datenfeld("24", "Meldejahr Schaden 6", Typ.ALPHA, 129, 130, Art.SA5X_A1A2,
                      FOLGESATZ_SCHADEN + "6"));
      datenfelder.add(
              new Datenfeld("25", "Schadendatum Schaden 6", Typ.ALPHA, 131, 138, Art.K,
                      FOLGESATZ_SCHADEN + "6", new NumberValidator()));
      datenfelder.add(
              new Datenfeld("26", "Freistellung Schaden 6", Typ.ALPHA, 139, 139, Art.SA5X_A3,
                       FOLGESATZ_SCHADEN + "6", new FreistellungValidator()));
      datenfelder
              .add(new Datenfeld("27", FILLER, Typ.ALPHA, 140, 148, Art.K, FOLGESATZ_SCHADEN + "6"));
      datenfelder.add(
              new Datenfeld("28", "Meldejahr Schaden 7", Typ.ALPHA, 149, 150, Art.SA5X_A1A2,
                      FOLGESATZ_SCHADEN + "7"));
      datenfelder.add(
              new Datenfeld("29", "Schadendatum Schaden 7", Typ.ALPHA, 151, 158, Art.K,
                      FOLGESATZ_SCHADEN + "7", new NumberValidator()));
      datenfelder.add(
              new Datenfeld("30", "Freistellung Schaden 7", Typ.ALPHA, 159, 159, Art.SA5X_A3,
                       FOLGESATZ_SCHADEN + "7", new FreistellungValidator()));
      datenfelder
              .add(new Datenfeld("31", FILLER, Typ.ALPHA, 160, 168, Art.K, FOLGESATZ_SCHADEN + "7"));
      datenfelder.add(
              new Datenfeld("32", "Meldejahr Schaden 8", Typ.ALPHA, 169, 170, Art.SA5X_A1A2,
                      FOLGESATZ_SCHADEN + "8"));
      datenfelder.add(
              new Datenfeld("33", "Schadendatum Schaden 8", Typ.ALPHA, 171, 178, Art.K,
                      FOLGESATZ_SCHADEN + "8", new NumberValidator()));
      datenfelder.add(
              new Datenfeld("34", "Freistellung Schaden 8", Typ.ALPHA, 179, 179, Art.SA5X_A3,
                       FOLGESATZ_SCHADEN + "8", new FreistellungValidator()));
      datenfelder
              .add(new Datenfeld("35", FILLER, Typ.ALPHA, 180, 188, Art.K, FOLGESATZ_SCHADEN + "8"));
      datenfelder.add(
              new Datenfeld("36", "Meldejahr Schaden 9", Typ.ALPHA, 189, 190, Art.SA5X_A1A2,
                      FOLGESATZ_SCHADEN + "9"));
      datenfelder.add(
              new Datenfeld("37", "Schadendatum Schaden 9", Typ.ALPHA, 191, 198, Art.K,
                      FOLGESATZ_SCHADEN + "9", new NumberValidator()));
      datenfelder.add(
              new Datenfeld("38", "Freistellung Schaden 9", Typ.ALPHA, 199, 199, Art.SA5X_A3,
                       FOLGESATZ_SCHADEN + "9", new FreistellungValidator()));
      datenfelder
              .add(new Datenfeld("39", FILLER, Typ.ALPHA, 200, 208, Art.K, FOLGESATZ_SCHADEN + "9"));
      datenfelder.add(
              new Datenfeld("40", "Meldejahr Schaden 10", Typ.ALPHA, 209, 210, Art.SA5X_A1A2,
                      FOLGESATZ_SCHADEN + "10"));
      datenfelder.add(
              new Datenfeld("41", "Schadendatum Schaden 10", Typ.ALPHA, 211, 218, Art.K,
                      FOLGESATZ_SCHADEN + "10", new NumberValidator()));
      datenfelder.add(
              new Datenfeld("42", "Freistellung Schaden 10", Typ.ALPHA, 219, 219, Art.SA5X_A3,
                       FOLGESATZ_SCHADEN + "10", new FreistellungValidator()));
      datenfelder
              .add(new Datenfeld("43", FILLER, Typ.ALPHA, 220, 228, Art.K, FOLGESATZ_SCHADEN + "10"));
      datenfelder.add(new Datenfeld("44", FILLER, Typ.ALPHA, 229, 255, Art.K, "Folgesatz"));
      return datenfelder;
   }
   
   Satzart build();

}
