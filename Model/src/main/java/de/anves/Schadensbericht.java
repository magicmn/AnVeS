package de.anves;

import java.util.Date;

public class Schadensbericht extends DBManaged{

    private long id;
    private String beschreibung;
    private Date datum;
    private String anmerkungKunde;







    //Getter and Setter--------------------------------------------------
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    public Date getDatum() {
        return datum;
    }
    public void setDatum(Date datum) {
        this.datum = datum;
    }
    public String getAnmerkungKunde() {
        return anmerkungKunde;
    }
    public void setAnmerkungKunde(String anmerkungKunde) {
        this.anmerkungKunde = anmerkungKunde;
    }

}
