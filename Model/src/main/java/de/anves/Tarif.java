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
    public void setAnhaengerTyp(String anhaengerTyp) {
        this.anhaengerTyp = anhaengerTyp;
    }
    public long getTarifNummer() {
        return tarifNummer;
    }
    public void setTarifNummer(long tarifNummer) {
        this.tarifNummer = tarifNummer;
    }
    public String getTarifBezeichnung() {
        return tarifBezeichnung;
    }
    public void setTarifBezeichnung(String tarifBezeichnung) {
        this.tarifBezeichnung = tarifBezeichnung;
    }
    public Date getGueltigAb() {
        return gueltigAb;
    }
    public void setGueltigAb(Date gueltigAb) {
        this.gueltigAb = gueltigAb;
    }
    public Date getGueltigBis() {
        return gueltigBis;
    }
    public void setGueltigBis(Date gueltigBis) {
        this.gueltigBis = gueltigBis;
    }
    public double getPreis() {
        return preis;
    }
    public void setPreis(double preis) {
        this.preis = preis;
    }

}
