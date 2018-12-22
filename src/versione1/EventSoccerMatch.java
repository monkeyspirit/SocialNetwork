package versione1;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventSoccerMatch extends Event{

    //Costanti della classe EventSoccerMatch
    public static final String TYPE = "EventSoccerMatch"; //serve per la deserializzazione in caso di un'array di eventi
    public static final String GENDER_NAME = "Sesso";
    public static final String GENDER_DESCRIPTION = "Sesso dei partecipanti";
    public static final String AGERANGE_NAME = "Fascia di eta";
    public static final String AGERANGE_DESCRIPTION = "limite inferiore e superiore di eta' dei partecipanti";

    //Attributi della classe EventSoccerMatch
    private Field gender = new Field(GENDER_NAME, GENDER_DESCRIPTION);
    private Field ageRange = new Field(AGERANGE_NAME, AGERANGE_DESCRIPTION);


    /**
     * Costruttore EventSoccerMatch: chiama il costruttore dei campi obbligatori
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
     */
    public EventSoccerMatch(String titleIns, int numParIns, LocalDate deadLineIns, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float indTeeIns, String totTeeIns, LocalDate endDateIns, LocalTime endTimeIns, String ageRangeIns, Enum<Gender> genderIns, String noteIns) {
        super(TYPE, titleIns,  numParIns,  deadLineIns,  placeIns,  dateIns,  timeIns,  durationIns,  indTeeIns,  totTeeIns,  endDateIns,  endTimeIns, noteIns);
        this.gender.setValue(genderIns);
        this.ageRange.setValue(ageRangeIns);
    }

    public EventSoccerMatch(String name, int numPar){
        super(TYPE, name, numPar);
    }


    public Field getGender() {
        return gender;
    }

    public Field getAgeRange() {
        return ageRange;
    }
}
