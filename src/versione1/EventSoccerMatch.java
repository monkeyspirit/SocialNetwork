package versione1;

import java.util.Date;

public class EventSoccerMatch extends Event{





    //Costanti della classe EventSoccerMatch
    public static final String GENDER_NAME = "Gender";
    public static final String GENDER_DESCRIPTION = "Gender of the partecipant";
    public static final String AGEGROUP_NAME = "Age group";
    public static final String AGEGROUP_DESCRIPTION = "Lower and upper limit of the partecipant's age";

    //Attributi della classe EventSoccerMatch
    private Field gender = new Field(GENDER_NAME, GENDER_DESCRIPTION);
    private Field ageGroup = new Field(AGEGROUP_NAME, AGEGROUP_DESCRIPTION);

    //Costruttore che chiama il costruttore della classe madre Event
    public EventSoccerMatch() {
        super();
    }

    /**
     * Costruttore EventSoccerMatch: chiama il costruttore dei campi obbligatori
     * della classe madre e inizializza l'attributo valore di gender e ageGroup.
     *
     * @param numOfPartecipants numero di partecipanti
     * @param registrationDeadline termine ultimo iscrizione
     * @param place luogo dell'evento
     * @param date data dell'evento
     * @param time ora dell'evento
     * @param indTee quota individuale
     * @param gender genere dei partecipanti
     * @param ageGroup fascia d'età dei partecipanti
     */
    public EventSoccerMatch(int numOfPartecipants, Date registrationDeadline, String place, Date date, Date time, float indTee, Gender gender, AgeGroup ageGroup) {
        super(numOfPartecipants, registrationDeadline, place, date, time, indTee);
        this.gender.setValue(gender);
        this.ageGroup.setValue(ageGroup);
    }

    public Field<Gender> getGender() {
        return gender;
    }

    public Field<AgeGroup> getAgeGroup() {
        return ageGroup;
    }
}
