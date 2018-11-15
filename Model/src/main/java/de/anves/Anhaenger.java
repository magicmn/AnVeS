package de.anves;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Anhaenger extends DBManaged {

    private long id;
    private AnhaengerTyp anhaengerTyp;
    private String kennzeichen;
    private Date naechsteHU;
    private List<Schadensbericht> schadensBerichte;

    /**
     * Leerer Konstruktor = Daten nachträglich setzen.
     */
    public Anhaenger() {
    }

    /**
     * Konstruktor der erzwingt alle Felder zu setzen um Fehler zu vermeiden.
     */
    public Anhaenger(long id, AnhaengerTyp anhaengerTyp, String kennzeichen, Date naechsteHU, List<Schadensbericht> schadensBerichte) {
        this.id = id;
        this.anhaengerTyp = anhaengerTyp;
        this.kennzeichen = kennzeichen;
        this.naechsteHU = naechsteHU;
        this.schadensBerichte = schadensBerichte;
    }

    public long getId() {
        return id;
    }

    public Anhaenger setId(long id) {
        this.id = id;
        return this;
    }

    public AnhaengerTyp getAnhaengerTyp() {
        return anhaengerTyp;
    }

    public Anhaenger setAnhaengerTyp(AnhaengerTyp anhaengerTyp) {
        this.anhaengerTyp = anhaengerTyp;
        return this;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public Anhaenger setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
        return this;
    }

    public Date getNaechsteHU() {
        return naechsteHU;
    }

    public Anhaenger setNaechsteHU(Date naechsteHU) {
        this.naechsteHU = naechsteHU;
        return this;
    }

    public List<Schadensbericht> getSchadensBerichte() {
        return schadensBerichte;
    }

    public Anhaenger setSchadensBerichte(List<Schadensbericht> schadensBerichte) {
        this.schadensBerichte = schadensBerichte;
        return this;
    }

    public String getSchadensBerichteString() {
        if (schadensBerichte == null || schadensBerichte.isEmpty()) {
            return "Keine";
        } else {
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat sdt = new SimpleDateFormat("dd.MM.yyyy");
            for (Schadensbericht schadensBericht : schadensBerichte) {
                sb.append("Am ").append(sdt.format(schadensBericht.getDatum())).append(schadensBericht.getBeschreibung()).append("\n");
            }
            return sb.toString();
        }
    }
}
