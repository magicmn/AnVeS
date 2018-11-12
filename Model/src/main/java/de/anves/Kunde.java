package de.anves;

import java.util.Date;

public class Kunde extends DBManaged{
    private long id;
    private String vorname;
    private String nachname;
    private Date geburtsdatum;
    private String bankverbindung;
    private String ort;
    private String strasse;
    private String plz;
    private String hausnummer;

    //Getter and Setter----------------------------------
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getVorname() {
        return vorname;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    public String getNachname() {
        return nachname;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }
    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
    public String getBankverbindung() {
        return bankverbindung;
    }
    public void setBankverbindung(String bankverbindung) {
        this.bankverbindung = bankverbindung;
    }
    public String getOrt() {
        return ort;
    }
    public void setOrt(String ort) {
        this.ort = ort;
    }
    public String getStrasse() {
        return strasse;
    }
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }
    public String getPlz() {
        return plz;
    }
    public void setPlz(String plz) {
        this.plz = plz;
    }
    public String getHausnummer() {
        return hausnummer;
    }
    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

}
