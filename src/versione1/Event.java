package versione1;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Event {

    //Costanti della classe Event
	public static final String TITLE_NAME = "Titolo";
    public static final String TITLE_DESCRIPTION = "Campo facoltativo che consiste in un nome di fantasia attribuito allâ€™evento";
    public static final String NUMPLAY_NAME = "Numero di partecipanti";
    public static final String NUMPLAY_DESCRIPTION = "Campo obbligatorio che stabilisce il numero di persone da coinvolgere nell'evento";
    public static final String REGDEADLINE_NAME = "Termine ultimo iscrizione";
    public static final String REGDEADLINE_DESCRIPTION = "Campo obbligatorio che inidica l'ultima data possibile per iscriversi";
    public static final String PLACE_NAME = "Luogo";
    public static final String PLACE_DESCRIPTION = "Campo obbligatorio che indica lâ€™indirizzo del luogo che ospiterÃ  lâ€™evento oppure, se lâ€™evento Ã¨ itinerante, il luogo di ritrovo dei partecipanti";
    public static final String DATE_NAME = "Data";
    public static final String DATE_DESCRIPTION = "Campo obbligatorio che indica la data in cui lâ€™evento proposto deve svolgersi o, nel caso lâ€™evento non termini nello stesso giorno in cui ha inizio, la data di inizio dellâ€™evento";
    public static final String TIME_NAME = "Ora";
    public static final String TIME_DESCRIPTION = "Campo obbligatorio che indica lâ€™ora in cui i partecipanti dovranno trovarsi nel luogo â€œLuogoâ€� in data â€œDataâ€� per dare inizio allâ€™evento";
    public static final String DURATION_NAME = "Durata";
    public static final String DURATION_DESCRIPTION =  "Campo facoltativo che indica la durata in termini di numero (approssimativo) di ore e minuti, per gli eventi che si esauriscono in un sol giorno, o in termini di numero esatto di giorni, per gli eventi che occupano piÃ¹ giorni consecutivi";
    public static final String INDTEE_NAME = "Quota individuale";
    public static final String INDTEE_DESCRIPTION = "Campo obbligatorio che indica la spesa (o una stima della stessa) che ogni partecipante allâ€™iniziativa dovrÃ  sostenere (si noti che la spesa puÃ² anche essere nulla)";
    public static final String TEEINC_NAME = "Compreso nella quota";
    public static final String TEEINC_DESCRIPTION = "Campo facoltativo che indica tutte le voci di spesa comprese nellâ€™ammontare indicato nella â€œQuota individualeâ€�";
    public static final String ENDDATE_NAME = "Data conclusiva";
    public static final String ENDDATE_DESCRIPTION = "Campo facoltativo che fissa la data di conclusione dellâ€™evento";
    public static final String ENDTIME_NAME = "Ora conclusiva";
    public static final String ENDTIME_DESCRIPTION = "Campo facoltativo che stima lâ€™ora di conclusione dellâ€™evento";
    public static final String NOTE_NAME = "Note";
    public static final String NOTE_DESCRIPTION = "Campo facoltativo contenente informazioni aggiuntive circa lâ€™evento";


	//Attributi della classe Event
    private String type; //serve per la serializzazione/deserializzazione in modo da poter distinguere il tipo specifico di Event

	private Field title = new Field(TITLE_NAME,TITLE_DESCRIPTION);
	private Field numOfPartecipants = new Field(NUMPLAY_NAME, NUMPLAY_DESCRIPTION);
	private Field registrationDeadline = new Field(REGDEADLINE_NAME, REGDEADLINE_DESCRIPTION);
	private Field place = new Field(PLACE_NAME, PLACE_DESCRIPTION);
	private Field date = new Field(DATE_NAME,DATE_DESCRIPTION);
	private Field time = new Field(TIME_NAME, TIME_DESCRIPTION);
	private Field duration = new Field(DURATION_NAME, DURATION_DESCRIPTION);
	private Field indTee = new Field(INDTEE_NAME,INDTEE_DESCRIPTION);
	private Field teeInclude = new Field(TEEINC_NAME, TEEINC_DESCRIPTION);
	private Field endDate = new Field(ENDDATE_NAME,ENDDATE_DESCRIPTION);
	private Field endTime = new Field(ENDTIME_NAME, ENDTIME_DESCRIPTION);
	private Field note = new Field(NOTE_NAME,NOTE_DESCRIPTION);

	private User creator; //serve per capire chi è il creatore dell'utente

    /**
     * Costruttore vuoto: viene inizializzato l'array di campi, ciascuno dei quali con
     * nome e descrizione ma senza valore.
     */
	public Event() {
	}

    /**
     * Coatruttore degli eventi, vengono settati tutti i valori dei cmapi e quello dell'utente creatore
     * @param type
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
     * @param noteIns
     * @param creator
     */
    public Event(String type, String titleIns, int numParIns, LocalDate deadLineIns, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float indTeeIns, String totTeeIns, LocalDate endDateIns, LocalTime endTimeIns, String noteIns,  User creator) {
        this.type = type;
	    this.title.setValue(titleIns);
        this.numOfPartecipants.setValue(numParIns);
        this.registrationDeadline.setValue(deadLineIns);
        this.place.setValue(placeIns);
        this.date.setValue(dateIns);
        this.time.setValue(timeIns);
        this.indTee.setValue(indTeeIns);
        this.duration.setValue(durationIns);
        this.indTee.setValue(indTeeIns);
        this.teeInclude.setValue(totTeeIns);
        this.endDate.setValue(endDateIns);
        this.endTime.setValue(endTimeIns);
        this.note.setValue(noteIns);
        this.creator = creator;
    }

    /**
     * Costruttore di Event che uso nel Main per velocizzare il processo e avere già eventi
     * @param type
     * @param name
     * @param Par
     * @param creator
     */
    public Event(String type, String name, int Par, User creator){
        this.type = type;
        this.title.setValue(name);
        this.numOfPartecipants.setValue(Par);
        this.creator = creator;
    }


    //Setter e Getter


    public Field getTitle() {
        return title;
    }

    public Field getNumOfPartecipants() {
        return numOfPartecipants;
    }

    public Field getRegistrationDeadline() { return registrationDeadline; }

    public Field getPlace() {
        return place;
    }

    public Field getDate() {
        return date;
    }

    public Field getTime() {
        return time;
    }

    public Field getDuration() {
        return duration;
    }

    public Field getIndTee() {
        return indTee;
    }

    public Field getTeeInclude() {
        return teeInclude;
    }

    public Field getEndDate() {
        return endDate;
    }

    public Field getEndTime() {
        return endTime;
    }

    public Field getNote() {
        return note;
    }

    public User getCreator() { return creator; }

}
