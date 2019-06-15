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



    /**
     * Costruttore SoccerMatchEvent: chiama il costruttore dei campi obbligatori
     * della classe madre e inizializza l'attributo valore di gender e ageGroup.
     */
    public SoccerMatchEvent(String title, int numOfParticipants, int extraParticipants, LocalDate registrationDeadline, LocalDate retiredDeadLineIns, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float individualTee, String teeInclude, LocalDate endDate, LocalTime endTime, String ageRange, Gender gender, String note, String creator) {
        super(TYPE, title,  numOfParticipants, extraParticipants,  registrationDeadline, retiredDeadLineIns,  placeIns,  dateIns,  timeIns,  durationIns, individualTee, teeInclude,  endDate, endTime, note, creator);
        this.gender.setValue(gender);
        this.ageRange.setValue(ageRange);
    }



    public Field getGender() {
        return gender;
    }

    public Field getAgeRange() {
        return ageRange;
    }


}
