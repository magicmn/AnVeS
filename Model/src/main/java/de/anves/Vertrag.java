package de.anves;

import java.util.Date;

public class Vertrag extends DBManaged{

    private long id;
    private Reservierung reservierung;
    private Tarif tarif;
    private Date uebergabe;
    private Date rueckgabe;
    private Schadensbericht schadensbericht;

    //Getter and Setter--------------------------------------
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Reservierung getReservierung() {
        return reservierung;
    }
    public void setReservierung(Reservierung reservierung) {
        this.reservierung = reservierung;
    }
    public Tarif getTarif() {
        return tarif;
    }
    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }
    public Date getUebergabe() {
        return uebergabe;
    }
    public void setUebergabe(Date uebergabe) {
        this.uebergabe = uebergabe;
    }
    public Date getRueckgabe() {
        return rueckgabe;
    }
    public void setRueckgabe(Date rueckgabe) {
        this.rueckgabe = rueckgabe;
    }
    public Schadensbericht getSchadensbericht() {
        return schadensbericht;
    }
    public void setSchadensbericht(Schadensbericht schadensbericht) {
        this.schadensbericht = schadensbericht;
    }

}
