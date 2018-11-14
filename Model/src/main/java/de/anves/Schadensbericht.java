package de.anves;

import java.util.Date;

public class Schadensbericht extends DBManaged{

    private long anhaengerid;
    private String beschreibung;
    private Date datum;
    private String anmerkungKunde;







    //Getter and Setter--------------------------------------------------
    public long getId() {
        return anhaengerid;
    }
    public Schadensbericht setId(long id) {
        this.anhaengerid = id;
        return this;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public Schadensbericht setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
        return this;
    }
    public Date getDatum() {
        return datum;
    }
    public Schadensbericht setDatum(Date datum) {
        this.datum = datum;
        return this;
    }
    public String getAnmerkungKunde() {
        return anmerkungKunde;
    }
    public Schadensbericht setAnmerkungKunde(String anmerkungKunde) {
        this.anmerkungKunde = anmerkungKunde;
        return this;
    }

}
