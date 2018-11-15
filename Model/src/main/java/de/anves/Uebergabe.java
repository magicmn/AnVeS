package de.anves;

import java.util.Date;

public class Uebergabe extends DBManaged{

    private Mitarbeiter mitarbeiter;
    private Date datum;
    private long vertragid;
    private Vertrag vertrag;

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public Uebergabe setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
        return this;
    }

    public Date getDatum() {
        return datum;
    }

    public Uebergabe setDatum(Date datum) {
        this.datum = datum;
        return this;
    }

    public Uebergabe setVertragid(long vertragid){
        this.vertragid = vertragid;
        return this;
    }

    public long getVertragid(){
        return vertragid;
    }

    public Vertrag getVetrag() {
        return vertrag;
    }

    public Uebergabe setVetrag(Vertrag vertrag) {
        this.vertrag = vertrag;
        return this;
    }
}
