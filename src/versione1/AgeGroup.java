package versione1;

import java.util.ArrayList;

public class AgeGroup {
    ArrayList<Integer> numeri;

    public AgeGroup(){
        numeri = new ArrayList<>();
        for(int i=1; i<100; i++){
            numeri.add(i);
        }
    }

    public ArrayList<Integer> getNumeri() {
        return numeri;
    }

    public ArrayList<Integer> getMinOf(int min){
        ArrayList<Integer> numMin = new ArrayList<>();

        for(int j=1; j<100; j++){
           if(j>min){
               numMin.add(j);
           }
        }

        return numMin;
    }
}
