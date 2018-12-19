package versione1;

import java.util.ArrayList;
import java.util.Date;

public abstract class Event extends ArrayList<Event> {

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
	private Field title = new Field(TITLE_NAME,TITLE_DESCRIPTION,false);
	private Field numOfPartecipants = new Field(NUMPLAY_NAME, NUMPLAY_DESCRIPTION,true);
	private Field registrationDeadline = new Field(REGDEADLINE_NAME, REGDEADLINE_DESCRIPTION,true);
	private Field place = new Field(PLACE_NAME, PLACE_DESCRIPTION,true);
	private Field date = new Field(DATE_NAME,DATE_DESCRIPTION,true);
	private Field time = new Field(TIME_NAME, TIME_DESCRIPTION,true);
	private Field duration = new Field(DURATION_NAME, DURATION_DESCRIPTION,false);
	private Field indTee = new Field(INDTEE_NAME,INDTEE_DESCRIPTION,true);
	private Field teeInclude = new Field(TEEINC_NAME, TEEINC_DESCRIPTION,false);
	private Field endDate = new Field(ENDDATE_NAME,ENDDATE_DESCRIPTION,false);
	private Field endTime = new Field(ENDTIME_NAME, ENDTIME_DESCRIPTION,false);
	private Field note = new Field(NOTE_NAME,NOTE_DESCRIPTION,false);

    /**
     * Costruttore vuoto: viene inizializzato l'array di campi, ciascuno dei quali con
     * nome e descrizione ma senza valore.
     */
	public Event() {
	}

    /**
     * Costruttore dei campi obbligatori: viene popolato l'attributo valore di ciascun campo obbligatorio.
     * @param numOfPartecipants numero dei partecipanti
     * @param registrationDeadline termine ultimo iscrizione
     * @param place luogo dell'evento
     * @param date data dell'evento
     * @param time ora dell'evento
     * @param indTee quota individuale
     */
	public Event(int numOfPartecipants, Date registrationDeadline, String place, Date date, Date time, float indTee) {
	    this.numOfPartecipants.setValue(numOfPartecipants);
	    this.registrationDeadline.setValue(registrationDeadline);
	    this.place.setValue(place);
	    this.date.setValue(date);
	    this.time.setValue(time);
	    this.indTee.setValue(indTee);
    }


    /**
     * Costruttore Event : lo uso solo perc
    */
    public Event(Field title) {
        this.title = title;
    }

    public Event(String name, int Par){
        this.title.setValue(name);
        this.numOfPartecipants.setValue(Par);
    }

    //Setter e Getter


    public void setTitle(Field title) {
        this.title = title;
    }

    public void setDuration(Field duration) {
        this.duration = duration;
    }

    public void setEndDate(Field endDate) {
        this.endDate = endDate;
    }

    public void setEndTime(Field endTime) {
        this.endTime = endTime;
    }

    public void setNote(Field note) {
        this.note = note;
    }

    public Field getTitle() {
        return title;
    }

    public Field getNumOfPartecipants() {
        return numOfPartecipants;
    }

    public Field getRegistrationDeadline() {
        return registrationDeadline;
    }

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




}
