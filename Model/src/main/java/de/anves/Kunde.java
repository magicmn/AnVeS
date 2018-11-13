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
    public Kunde setId(long id) {
        this.id = id;
        return this;
    }
    public String getVorname() {
        return vorname;
    }
    public Kunde setVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }
    public String getNachname() {
        return nachname;
    }
    public Kunde setNachname(String nachname) {
        this.nachname = nachname;
        return this;
    }
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }
    public Kunde setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
        return this;
    }
    public String getBankverbindung() {
        return bankverbindung;
    }
    public Kunde setBankverbindung(String bankverbindung) {
        this.bankverbindung = bankverbindung;
        return this;
    }
    public String getOrt() {
        return ort;
    }
    public Kunde setOrt(String ort) {
        this.ort = ort;
        return this;
    }
    public String getStrasse() {
        return strasse;
    }
    public Kunde setStrasse(String strasse) {
        this.strasse = strasse;
        return this;
    }
    public String getPlz() {
        return plz;
    }
    public Kunde setPlz(String plz) {
        this.plz = plz;
        return this;
    }
    public String getHausnummer() {
        return hausnummer;
    }
    public Kunde setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
        return this;
    }

}
