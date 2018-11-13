package de.anves;

import java.util.Date;

public class Tarif extends DBManaged{

    private String anhaengerTyp;
    private long tarifNummer;
    private String tarifBezeichnung;
    private Date gueltigAb;
    private Date gueltigBis;
    private double preis;









    //Getter and Setter--------------------------------------
    public String getAnhaengerTyp() {
        return anhaengerTyp;
    }
    public Tarif setAnhaengerTyp(String anhaengerTyp) {
        this.anhaengerTyp = anhaengerTyp;
        return this;
    }
    public long getTarifNummer() {
        return tarifNummer;
    }
    public Tarif setTarifNummer(long tarifNummer) {
        this.tarifNummer = tarifNummer;
        return this;
    }
    public String getTarifBezeichnung() {
        return tarifBezeichnung;
    }
    public Tarif setTarifBezeichnung(String tarifBezeichnung) {
        this.tarifBezeichnung = tarifBezeichnung;
        return this;
    }
    public Date getGueltigAb() {
        return gueltigAb;
    }
    public Tarif setGueltigAb(Date gueltigAb) {
        this.gueltigAb = gueltigAb;
        return this;
    }
    public Date getGueltigBis() {
        return gueltigBis;
    }
    public Tarif setGueltigBis(Date gueltigBis) {
        this.gueltigBis = gueltigBis;
        return this;
    }
    public double getPreis() {
        return preis;
    }
    public Tarif setPreis(double preis) {
        this.preis = preis;
        return this;
    }

}
