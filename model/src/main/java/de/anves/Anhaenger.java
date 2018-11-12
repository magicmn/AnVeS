package de.anves;

import java.util.Date;
import java.util.List;

public class Anhaenger {

    private long id;
    private String anhaengerTyp;
    private String kennzeichen;
    private Date naechsteHU;
    private List<Schadensbericht> schadensBerichte;


//Getter and Setter-------------------------------

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getAnhaengerTyp() {
        return anhaengerTyp;
    }
    public void setAnhaengerTyp(String anhaengerTyp) {
        this.anhaengerTyp = anhaengerTyp;
    }
    public String getKennzeichen() {
        return kennzeichen;
    }
    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }
    public Date getNaechsteHU() {
        return naechsteHU;
    }
    public void setNaechsteHU(Date naechsteHU) {
        this.naechsteHU = naechsteHU;
    }
    public List<Schadensbericht> getSchadensBerichte() {
        return schadensBerichte;
    }
    public void setSchadensBerichte(List<Schadensbericht> schadensBerichte) {
        this.schadensBerichte = schadensBerichte;
    }

}
