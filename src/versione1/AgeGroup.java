package versione1;

import java.util.ArrayList;

/**
 * Classe di supporto, serve per poter avere un arraylist di numeri che torna utile nella rappresentazione sample
 * per la questione delle fasce di età, fino ai 20 anni i numeri ci sono tutti dai 20 in poi ci si muove di 10 in 10
 */

public class AgeGroup {
    private ArrayList<Integer> numeri;
    private String range;

    /**
     * Il costruttore inizializza l'array numeri con {1,2..19,20,30..90,100}
     */
    public AgeGroup(){
        numeri = new ArrayList<>();
        for(int i=1; i<100; i++){
            if(i<=20) {
                numeri.add(i);
            }
            else if(i>20 && (i%10==0)){
                numeri.add(i);
            }
        }
    }

    public ArrayList<Integer> getNumeri() {
        return numeri;
    }


    public ArrayList<Integer> getMinOf(int min){
        ArrayList<Integer> numMin = new ArrayList<>();

        for(int j=1; j<100; j++){
           if(j>min){
               if(j<=20) {
                   numMin.add(j);
               }
               else if(j>20 && (j%10==0)){
                   numMin.add(j);
               }
           }
        }

        return numMin;
    }

    public String getRange() {
        return range;
    }

    public void setRange(int min, int max) {
        String concat = String.valueOf(min)+"-"+String.valueOf(max);
        this.range = concat;
    }
}
