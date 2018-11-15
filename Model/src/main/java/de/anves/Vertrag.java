package de.anves;

public class Vertrag extends DBManaged{

    private long id;
    private Reservierung reservierung;
    private Tarif tarif;
    private Uebergabe uebergabe;
    private Rueckgabe rueckgabe;
    private Schadensbericht schadensbericht;


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
    public Uebergabe getUebergabe() {
        return uebergabe;
    }
    public Vertrag setUebergabe(Uebergabe uebergabe) {
        this.uebergabe = uebergabe;
        return this;
    }
    public Rueckgabe getRueckgabe() {
        return rueckgabe;
    }
    public Vertrag setRueckgabe(Rueckgabe rueckgabe) {
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

}
