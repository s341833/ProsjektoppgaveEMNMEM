package Dataamatorene;

import Dataamatorene.Datakomponenter.LagreKomponent;

import java.io.IOException;

public class ScriptKomponentTom {
    public static void main(String[] args) {
        try{
            //LagreKomponent.lagreHarddisk();
            //LagreKomponent.lagreHovedkort();
            //LagreKomponent.lagreKabinett();
            //LagreKomponent.lagreLydkort();
            //LagreKomponent.lagreMinne();
            //LagreKomponent.lagreMus();
            //LagreKomponent.lagreProsessor();
            //LagreKomponent.lagreSkjerm();
            //LagreKomponent.lagreSkjermkort();
            LagreKomponent.lagreTastatur();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
