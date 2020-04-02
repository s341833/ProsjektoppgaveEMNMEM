package Dataamatorene.Datakomponenter;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Lydkort extends Datakomponent implements Serializable, Comparable<Lydkort> {

    protected boolean integrert;
    protected double frekvens;

    public Lydkort(String navn, double pris, int varekode, boolean integrert, double frekvens) {
        super(navn, pris, varekode);
        this.integrert = integrert;
        this.frekvens = frekvens;
    }

    public String getIntegrert() {
        if (integrert) return "true";
        else return "false";
    }

    public void setIntegrert(boolean integrert) {
        this.integrert = integrert;
    }

    public String getFrekvens() {
        return String.valueOf(frekvens);
    }

    public void setFrekvens(double frekvens) {
        this.frekvens = frekvens;
    }

    @Override
    public String toString() {
        if(integrert){
            return String.format("%s %s integrert, %skr, VNr: %s", navn, frekvens, pris, varekode);
        } else return String.format("%s %s ikke integrert, %skr, VNr: %s", navn, frekvens, pris, varekode);

    }

    @Override
    public int compareTo(@NotNull Lydkort o) {
        return this.navn.compareTo(o.navn);
    }
}
