package de.anves.view.formModel;

import de.anves.AnhaengerTypEnum;

public class AnhaengerSuchenForm {
    private AnhaengerTypEnum anhaengerTypEnum;
    private String zeitraumVon;
    private String zeitraumAb;
    private int tage;

    public AnhaengerTypEnum getAnhaengerTypEnum() {
        return anhaengerTypEnum;
    }

    public void setAnhaengerTypEnum(AnhaengerTypEnum anhaengerTypEnum) {
        this.anhaengerTypEnum = anhaengerTypEnum;
    }

    public String getZeitraumVon() {
        return zeitraumVon;
    }

    public void setZeitraumVon(String zeitraumVon) {
        this.zeitraumVon = zeitraumVon;
    }

    public String getZeitraumAb() {
        return zeitraumAb;
    }

    public void setZeitraumAb(String zeitraumAb) {
        this.zeitraumAb = zeitraumAb;
    }

    public int getTage() {
        return tage;
    }

    public void setTage(int tage) {
        this.tage = tage;
    }
}
