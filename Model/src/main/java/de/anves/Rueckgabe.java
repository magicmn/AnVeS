package de.anves;

import java.util.Date;

public class Rueckgabe extends DBManaged {

    private Mitarbeiter mitarbeiter;
    private Date datum;
    private Vertrag vertrag;
    private long vertragid;

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public Rueckgabe setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
        return this;
    }

    public Date getDatum() {
        return datum;
    }

    public Rueckgabe setDatum(Date datum) {
        this.datum = datum;
        return this;
    }

    public Rueckgabe setVertragid(long vertragid) {
        this.vertragid = vertragid;
        return this;
    }

    public long getVertragid() {
        return vertragid;
    }


    public Vertrag getVetrag() {
        return vertrag;
    }

    public Rueckgabe setVetrag(Vertrag vertrag) {
        this.vertrag = vertrag;
        return this;
    }
}