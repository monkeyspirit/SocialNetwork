package versione1;

import versione2.StateValue;

import java.time.LocalDate;
import java.time.LocalTime;

public class SoccerMatchEvent extends Event{

    //Costanti della classe SoccerMatchEvent
    public static final String TYPE = "SoccerMatchEvent"; //serve per la deserializzazione di una lista di eventi
    public static final String GENDER_NAME = "Sesso";
    public static final String GENDER_DESCRIPTION = "Sesso dei partecipanti";
    public static final String AGERANGE_NAME = "Fascia di eta";
    public static final String AGERANGE_DESCRIPTION = "limite inferiore e superiore di eta' dei partecipanti";

    //Attributi della classe SoccerMatchEvent
    private Field<Gender> gender = new Field(GENDER_NAME, GENDER_DESCRIPTION);
    private Field<String> ageRange = new Field(AGERANGE_NAME, AGERANGE_DESCRIPTION);


    /**
     * Costruttore SoccerMatchEvent: chiama il costruttore dei campi obbligatori
     * della classe madre e inizializza l'attributo valore di gender e ageGroup.
     * @param titleIns
     * @param numParIns
     * @param deadLineIns
     * @param placeIns
     * @param dateIns
     * @param timeIns
     * @param durationIns
     * @param indTeeIns
     * @param totTeeIns
     * @param endDateIns
     * @param endTimeIns
     * @param ageRangeIns
     * @param genderIns
     * @param noteIns
     * @param creatorIns
     */
    public SoccerMatchEvent(String titleIns, int numParIns, LocalDate deadLineIns, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float indTeeIns, String totTeeIns, LocalDate endDateIns, LocalTime endTimeIns, String ageRangeIns, Gender genderIns, StateValue stateValue, LocalDate stateSwitch, String noteIns, String creatorIns) {
        super(TYPE, titleIns,  numParIns,  deadLineIns,  placeIns,  dateIns,  timeIns,  durationIns,  indTeeIns,  totTeeIns,  endDateIns,  endTimeIns,stateValue, stateSwitch, noteIns, creatorIns);
        this.gender.setValue(genderIns);
        this.ageRange.setValue(ageRangeIns);
    }


    public SoccerMatchEvent(){

    }

    public Field getGender() {
        return gender;
    }

    public Field getAgeRange() {
        return ageRange;
    }


}
