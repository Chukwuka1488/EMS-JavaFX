package sample.ems.model;
import javafx.beans.property.*;
import java.sql.Date;

public class EmployeesData {
    private final IntegerProperty ID;

    private final IntegerProperty SAP_Personalnummer;

    private final StringProperty Spalte1; // imageUrl

    private final StringProperty Vorname;

    private final StringProperty Nachname;

    private final StringProperty RI;

    private final StringProperty Verfugbarkeit;

    private final StringProperty Berufserfahrung;


    private final StringProperty ANU;


    private final StringProperty Mobilitat;


    private final StringProperty Kompetenzen;


    private final StringProperty Tools;

    private final StringProperty Sprachen;

    private final StringProperty RT;

    private final StringProperty Aktionen;

    private final StringProperty Projektwunsch;

    private final StringProperty Schwerpunkt;

    private final StringProperty Division;

    private final StringProperty Einheit;

    private final StringProperty Position_RI;

    private final StringProperty Manager1;

    private final StringProperty Manager2;

    public EmployeesData(Integer id, Integer sap_personalnummer, String spalte1,
                         String vorname, String nachname, String ri,
                         String verfugbarkeit, String berufserfahrung, String anu,
                         String mobilitat, String kompetenzen, String tools,
                         String sprachen, String rt, String aktionen,
                         String projektwunsch, String schwerpunkt, String division,
                         String einheit, String position_ri, String manager1,
                         String manager2) {
        this.ID = new SimpleIntegerProperty(id);
        this.SAP_Personalnummer = new SimpleIntegerProperty(sap_personalnummer);
        this.Spalte1 = new SimpleStringProperty(spalte1);
        this.Vorname = new SimpleStringProperty(vorname);
        this.Nachname = new SimpleStringProperty(nachname);
        this.RI = new SimpleStringProperty(ri);
        this.Verfugbarkeit = new SimpleStringProperty(verfugbarkeit);
        this.Berufserfahrung = new SimpleStringProperty(berufserfahrung);
        this.ANU = new SimpleStringProperty(anu);
        this.Mobilitat = new SimpleStringProperty(mobilitat);
        this.Kompetenzen = new SimpleStringProperty(kompetenzen);
        this.Tools = new SimpleStringProperty(tools);
        this.Sprachen = new SimpleStringProperty(sprachen);
        this.RT = new SimpleStringProperty(rt);
        this.Aktionen = new SimpleStringProperty(aktionen);
        this.Projektwunsch = new SimpleStringProperty(projektwunsch);
        this.Schwerpunkt = new SimpleStringProperty(schwerpunkt);
        this.Division = new SimpleStringProperty(division);
        this.Einheit = new SimpleStringProperty(einheit);
        this.Position_RI = new SimpleStringProperty(position_ri);
        this.Manager1 = new SimpleStringProperty(manager1);
        this.Manager2 = new SimpleStringProperty(manager2);
    }

    // getter/setter ID JavaFX
    public IntegerProperty IDProperty() {
        return this.ID;
    }

    public Integer getID() {
        return this.IDProperty().get();
    }

    public void setID(final int ID) {
        this.IDProperty().set(ID);

    }

    // getter/setter ID JavaFX
    public IntegerProperty SAP_PersonalnummerProperty() {
        return this.SAP_Personalnummer;
    }

    public Integer getSAP_Personalnummer() {
        return this.SAP_PersonalnummerProperty().get();
    }

    public void setSAP_Personalnummer(final int SAP_Personalnummer) {
        this.SAP_PersonalnummerProperty().set(SAP_Personalnummer);
    }

    // getter/setter Spalte1 JavaFX
    public StringProperty Spalte1Property() {
        return this.Spalte1;
    }

    public String getSpalte1() {
        return this.Spalte1Property().get();
    }

    public void setSpalte1(final String Spalte1) {
        this.Spalte1Property().set(Spalte1);
    }

    // getter/setter Vorname JavaFX
    public StringProperty VornameProperty() {
        return this.Vorname;
    }

    public String getVorname() {
        return this.VornameProperty().get();
    }

    public void setVorname(final String Vorname) {
        this.VornameProperty().set(Vorname);
    }

    // getter/setter Nachname JavaFX
    public StringProperty NachnameProperty() {
        return this.Nachname;
    }

    public String getNachname() {
        return this.NachnameProperty().get();
    }

    public void setNachname(final String Nachname) {
        this.NachnameProperty().set(Nachname);
    }

    // getter/setter RI JavaFX
    public StringProperty RIProperty() {
        return this.RI;
    }

    public String getRI() {
        return this.RIProperty().get();
    }

    public void setRI(final String RI) {
        this.RIProperty().set(RI);
    }

    // getter/setter Verfugbarkeit JavaFX
    public StringProperty VerfugbarkeitProperty() {
        return this.Verfugbarkeit;
    }

    public String getVerfugbarkeit() {

        return this.VerfugbarkeitProperty().get();
    }

    public void setVerfugbarkeit(final String Verfugbarkeit) {
        this.VerfugbarkeitProperty().set(Verfugbarkeit);
    }

    // getter/setter Berufserfahrung JavaFX
    public StringProperty BerufserfahrungProperty() {
        return this.Berufserfahrung;
    }

    public String getBerufserfahrung() {
        return this.BerufserfahrungProperty().get();
    }

    public void setBerufserfahrung(final String Berufserfahrung) {
        this.BerufserfahrungProperty().set(Berufserfahrung);
    }

    // getter/setter ANU JavaFX
    public StringProperty ANUProperty() {
        return this.ANU;
    }

    public String getANU() {
        return this.ANUProperty().get();
    }

    public void setANU(final String ANU) {
        this.ANUProperty().set(ANU);
    }

    // getter/setter Mobilitat JavaFX
    public StringProperty MobilitatProperty() {
        return this.Mobilitat;
    }

    public String getMobilitat() {
        return this.MobilitatProperty().get();
    }

    public void setMobilitat(final String Mobilitat) {
        this.MobilitatProperty().set(Mobilitat);
    }

    // getter/setter Kompetenzen JavaFX
    public StringProperty KompetenzenProperty() {
        return this.Kompetenzen;
    }

    public String getKompetenzen() {
        return this.KompetenzenProperty().get();
    }

    public void setKompetenzen(final String Kompetenzen) {
        this.KompetenzenProperty().set(Kompetenzen);
    }

    // getter/setter Tools JavaFX
    public StringProperty ToolsProperty() {
        return this.Tools;
    }

    public String getTools() {
        return this.ToolsProperty().get();
    }

    public void setTools(final String Tools) {
        this.ToolsProperty().set(Tools);
    }

    // getter/setter Sprachen JavaFX
    public StringProperty SprachenProperty() {
        return this.Sprachen;
    }

    public String getSprachen() {
        return this.SprachenProperty().get();
    }

    public void setSprachen(final String Sprachen) {
        this.SprachenProperty().set(Sprachen);
    }

    // getter/setter RT JavaFX
    public StringProperty RTProperty() {
        return this.RT;
    }

    public String getRT() {
        return this.RTProperty().get();
    }

    public void setRT(final String RT) {
        this.RTProperty().set(RT);
    }

    // getter/setter Aktionen JavaFX
    public StringProperty AktionenProperty() {
        return this.Aktionen;
    }

    public String getAktionen() {
        return this.AktionenProperty().get();
    }

    public void setAktionen(final String Aktionen) {
        this.AktionenProperty().set(Aktionen);
    }

    // getter/setter Projektwunsch JavaFX
    public StringProperty ProjektwunschProperty() {
        return this.Projektwunsch;
    }

    public String getProjektwunsch() {
        return this.ProjektwunschProperty().get();
    }

    public void setProjektwunsch(final String Projektwunsch) {
        this.ProjektwunschProperty().set(Projektwunsch);
    }

    // getter/setter Schwerpunkt JavaFX
    public StringProperty SchwerpunktProperty() {
        return this.Schwerpunkt;
    }

    public String getSchwerpunkt() {
        return this.SchwerpunktProperty().get();
    }

    public void setSchwerpunkt(final String Schwerpunkt) {
        this.SchwerpunktProperty().set(Schwerpunkt);
    }

    // getter/setter Division JavaFX
    public StringProperty DivisionProperty() {
        return this.Division;
    }

    public String getDivision() {
        return this.DivisionProperty().get();
    }

    public void setDivision(final String Division) {
        this.DivisionProperty().set(Division);
    }

    // getter/setter Einheit JavaFX
    public StringProperty EinheitProperty() {
        return this.Einheit;
    }

    public String getEinheit() {
        return this.EinheitProperty().get();
    }

    public void setEinheit(final String Einheit) {
        this.EinheitProperty().set(Einheit);
    }

    // getter/setter Position_RI JavaFX
    public StringProperty Position_RIProperty() {
        return this.Position_RI;
    }

    public String getPosition_RI() {
        return this.Position_RIProperty().get();
    }

    public void setPosition_RI(final String Position_RI) {
        this.Position_RIProperty().set(Position_RI);
    }

    // getter/setter Manager1 JavaFX
    public StringProperty Manager1Property() {
        return this.Manager1;
    }

    public String getManager1() {
        return this.Manager1Property().get();
    }

    public void setManager1(final String Manager1) {
        this.Manager1Property().set(Manager1);
    }

    // getter/setter Manager2 JavaFX
    public StringProperty Manager2Property() {
        return this.Manager2;
    }

    public String getManager2() {
        return this.Manager2Property().get();
    }

    public void setManager2(final String Manager2) {
        this.Manager2Property().set(Manager2);
    }


}
















