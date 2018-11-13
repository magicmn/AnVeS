package de.anves;

import java.util.Date;

public class Vertrag extends DBManaged{

    private long id;
    private Reservierung reservierung;
    private Tarif tarif;
    private Date uebergabe;
    private Date rueckgabe;
    private Schadensbericht schadensbericht;
    private Mitarbeiter uebergabeMitarbeiter;
    private Mitarbeiter rueckgabeMitarbeiter;

    //Getter and Setter--------------------------------------
    public long getId() {
        return id;
    }
    public Vertrag setId(long id) {
        this.id = id;
        return this;
    }
    public Reservierung getReservierung() {
        return reservierung;
    }
    public Vertrag setReservierung(Reservierung reservierung) {
        this.reservierung = reservierung;
        return this;
    }
    public Tarif getTarif() {
        return tarif;
    }
    public Vertrag setTarif(Tarif tarif) {
        this.tarif = tarif;
        return this;
    }
    public Date getUebergabe() {
        return uebergabe;
    }
    public Vertrag setUebergabe(Date uebergabe) {
        this.uebergabe = uebergabe;
        return this;
    }
    public Date getRueckgabe() {
        return rueckgabe;
    }
    public Vertrag setRueckgabe(Date rueckgabe) {
        this.rueckgabe = rueckgabe;
        return this;
    }
    public Schadensbericht getSchadensbericht() {
        return schadensbericht;
    }
    public Vertrag setSchadensbericht(Schadensbericht schadensbericht) {
        this.schadensbericht = schadensbericht;
        return this;
    }
    public Mitarbeiter getUebergabeMitarbeiter() {
        return uebergabeMitarbeiter;
    }
    public Vertrag setUebergabeMitarbeiter(Mitarbeiter uebergabeMitarbeiter) {
        this.uebergabeMitarbeiter = uebergabeMitarbeiter;
        return this;
    }
    public Mitarbeiter getRueckgabeMitarbeiter() {
        return rueckgabeMitarbeiter;
    }
    public Vertrag setRueckgabeMitarbeiter(Mitarbeiter rueckgabeMitarbeiter) {
        this.rueckgabeMitarbeiter = rueckgabeMitarbeiter;
        return this;
    }
}
