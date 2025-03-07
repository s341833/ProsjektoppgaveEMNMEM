package Dataamatorene.Brukere;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Bruker implements Serializable, Comparable<Bruker> {

    public enum BrukerType {
        ADMIN,
        BRUKER
    }

    // Datafelt
    private String brukernavn;
    private String passord;
    private String navn;
    private String tlfNummer;
    private String email;
    private BrukerType superbruker;

    // Konstruktør
    public Bruker(String brukernavn, String passord, String navn, String tlfNummer, String email, BrukerType superbruker){
        this.brukernavn = brukernavn;
        this.passord = passord;
        this.navn = navn;
        this.tlfNummer = tlfNummer;
        this.email = email;
        this.superbruker = superbruker;
    }

    // Gettere og settere
    public String getBrukernavn() {
        return brukernavn;
    }

    public String getPassord() {
        return passord;
    }

    public String getNavn() {
        return navn;
    }

    public String getTlfNummer() {
        return tlfNummer;
    }

    public String getEmail() {
        return email;
    }

    public String getRettigheter() {
        if(superbruker == BrukerType.ADMIN) return "Admin";
        else return "Bruker";
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setTlfNummer(String tlfNummer) {
        this.tlfNummer = tlfNummer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSuperbruker(String rettigheter){
        superbruker = BrukerType.valueOf(rettigheter.toUpperCase());
    }

    public BrukerType isSuperbruker() {
        return superbruker;
    }

    // toString
    @Override
    public String toString() {
        return String.format("%s, %s", brukernavn, getRettigheter());
    }

    // compareTo
    @Override
    public int compareTo(@NotNull Bruker o) {
        return this.navn.compareTo(o.navn);
    }
}
