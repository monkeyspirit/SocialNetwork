package main.model.soccerMatchCategory;

import main.model.event.Event;
import main.model.event.Field;
import main.model.event.Gender;
import main.model.event.StateValue;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Rappresenta un evento di tipo partita di calcio
 */
public class SoccerMatchEvent extends Event {

    //Costanti della classe SoccerMatchEvent
    public static final String TYPE = "SoccerMatchEvent"; //serve per la deserializzazione di una lista di eventi
    public static final String GENDER_NAME = "Sesso";
    public static final String GENDER_DESCRIPTION = "Sesso dei partecipanti";
    public static final String AGERANGE_NAME = "Fascia di eta";
    public static final String AGERANGE_DESCRIPTION = "limite inferiore e superiore di eta' dei partecipanti";

    //Attributi della classe SoccerMatchEvent
    private Field<Gender> gender = new Field(GENDER_NAME, GENDER_DESCRIPTION);
    private Field<String> ageRange = new Field(AGERANGE_NAME, AGERANGE_DESCRIPTION);


    public SoccerMatchEvent() {
        super(TYPE);
    }

    /**
     * Costruttore SoccerMatchEvent: chiama il costruttore dei campi obbligatori
     * della classe madre e inizializza l'attributo valore di gender e ageGroup.
     */
    public SoccerMatchEvent(String titleIns, int numParIns, int extraParIns, LocalDate deadLineIns, LocalDate retiredDeadLineIns, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float indTeeIns, String totTeeIns, LocalDate endDateIns, LocalTime endTimeIns, String ageRangeIns, Gender genderIns, StateValue stateValue, LocalDate stateSwitch, String noteIns, String creatorIns) {
        super(TYPE, titleIns,  numParIns, extraParIns  ,  deadLineIns, retiredDeadLineIns,  placeIns,  dateIns,  timeIns,  durationIns,  indTeeIns,  totTeeIns,  endDateIns,  endTimeIns,stateValue, stateSwitch, noteIns, creatorIns);
        this.gender.setValue(genderIns);
        this.ageRange.setValue(ageRangeIns);
    }



    public Field getGender() {
        return gender;
    }

    public Field getAgeRange() {
        return ageRange;
    }


}
