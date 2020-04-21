package Dataamatorene.DatakomponenterReserve;

import Dataamatorene.Datakomponenter.Datakomponent;

import java.io.Serializable;

public class Hovedkort2 extends Datakomponent2 implements Serializable {

    protected int antallPorter;

    public Hovedkort2(String navn, double pris, int varekode, int antallPorter) {
        super(navn, pris, varekode);
        this.antallPorter = antallPorter;
    }

    public String getAntallPorter() {
        return String.valueOf(antallPorter);
    }

    public void setAntallPorter(int antallPorter) {
        this.antallPorter = antallPorter;
    }

    @Override
    public String toString() {
        return String.format("%s %s porter, %skr, VNr: %s", navn, antallPorter, pris, varekode);
    }
}
