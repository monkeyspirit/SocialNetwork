package versione1;

import versione2.State;
import versione2.StateValue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observable;

import static versione2.StateValue.*;

public abstract class Event extends Observable {

    //Costanti della classe Event
	public static final String TITLE_NAME = "Titolo";
    public static final String TITLE_DESCRIPTION = "Campo facoltativo che consiste in un nome di fantasia attribuito all'evento";
    public static final String NUMPLAY_NAME = "Numero di partecipanti";
    public static final String NUMPLAY_DESCRIPTION = "Campo obbligatorio che stabilisce il numero di persone da coinvolgere nell'evento";
    public static final String REGDEADLINE_NAME = "Termine ultimo iscrizione";
    public static final String REGDEADLINE_DESCRIPTION = "Campo obbligatorio che inidica l'ultima data possibile per iscriversi";
    public static final String PLACE_NAME = "Luogo";
    public static final String PLACE_DESCRIPTION = "Campo obbligatorio che indica l'indirizzo del luogo che ospitera'  l'evento oppure, se l'evento e' itinerante, il luogo di ritrovo dei partecipanti";
    public static final String DATE_NAME = "Data";
    public static final String DATE_DESCRIPTION = "Campo obbligatorio che indica la data in cui l'evento proposto deve svolgersi o, nel caso l'evento non termini nello stesso giorno in cui ha inizio, la data di inizio dell'evento";
    public static final String TIME_NAME = "Ora";
    public static final String TIME_DESCRIPTION = "Campo obbligatorio che indica l'ora in cui i partecipanti dovranno trovarsi nel luogo 'Luogo' in data 'Data' per dare inizio all'evento";
    public static final String DURATION_NAME = "Durata";
    public static final String DURATION_DESCRIPTION =  "Campo facoltativo che indica la durata in termini di numero (approssimativo) di ore e minuti, per gli eventi che si esauriscono in un sol giorno, o in termini di numero esatto di giorni, per gli eventi che occupano piu' giorni consecutivi";
    public static final String INDTEE_NAME = "Quota individuale";
    public static final String INDTEE_DESCRIPTION = "Campo obbligatorio che indica la spesa (o una stima della stessa) che ogni partecipante all'iniziativa dovra'  sostenere (si noti che la spesa puo' anche essere nulla)";
    public static final String TEEINC_NAME = "Compreso nella quota";
    public static final String TEEINC_DESCRIPTION = "Campo facoltativo che indica tutte le voci di spesa comprese nell'ammontare indicato nella 'Quota individuale'";
    public static final String ENDDATE_NAME = "Data conclusiva";
    public static final String ENDDATE_DESCRIPTION = "Campo facoltativo che fissa la data di conclusione dell'evento";
    public static final String ENDTIME_NAME = "Ora conclusiva";
    public static final String ENDTIME_DESCRIPTION = "Campo facoltativo che stima l'ora di conclusione dell'evento";
    public static final String NOTE_NAME = "Note";
    public static final String NOTE_DESCRIPTION = "Campo facoltativo contenente informazioni aggiuntive circa l'evento";


	//Attributi della classe Event
    private String type; //serve per la serializzazione/deserializzazione in modo da poter distinguere il tipo specifico di Event
	private Field title = new Field(TITLE_NAME,TITLE_DESCRIPTION);
	private Field numOfParticipants = new Field(NUMPLAY_NAME, NUMPLAY_DESCRIPTION);
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

	private State state;

	private String creator; //serve per capire chi e' il creatore dell'utente

    private ArrayList<String> participants;


    /**
     * Costruttore vuoto: viene inizializzato l'array di campi, ciascuno dei quali con
     * nome e descrizione ma senza valore.
     */
	public Event() {
	}

    /**
     * Costruttore degli eventi, vengono settati tutti i valori dei cmapi e quello dell'utente creatore
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
    public Event(String type, String titleIns, int numParIns, LocalDate deadLineIns, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float indTeeIns, String totTeeIns, LocalDate endDateIns, LocalTime endTimeIns, String noteIns,  String creator) {
        this.type = type;
	    this.title.setValue(titleIns);
        this.numOfParticipants.setValue(numParIns);
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
        this.state = new State();
        this.state.setStateValue(Aperta);  // Appena costruisco l'evento il suo stato e' attivo
        //this.state.setSwitchDate(deadLineIns.plusDays(1));  // Appena costruisco l'evento setto la switch date alla deadLine
        participants = new ArrayList<>();
    }

    /**
     * Costruttore di Event che uso nel Main per velocizzare il processo e avere gia' eventi
     * @param type
     * @param name
     * @param Par
     * @param creator
     */
    public Event(String type, String name, int Par, String creator){
        this.type = type;
        this.title.setValue(name);
        this.numOfParticipants.setValue(Par);
        this.state = new State();
        this.state.setStateValue(Aperta);  // Appena costruisco l'evento il suo stato e' attivo
        this.state.setSwitchDate(null);  // Appena costruisco l'evento setto la switch date alla deadLine
        this.creator = creator;
        participants = new ArrayList<>();
    }


    //Setter e Getter


    public Field getTitle() {
        return title;
    }

    public Field getNumOfParticipants() {
        return numOfParticipants;
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

    public String getCreator() { return creator; }

    public ArrayList<String> getParticipants() { return participants; }

    public State getState() { return state; }

    public String getType() { return type; }

    public void addParticipants(String participantUsername){
        participants.add(participantUsername);
    }

    /**
     * Controlla se un utente e' gia' registrato nella lista partecipanti
     * @param userToCheck l'username dell'utente che si vuole cercare
     * @return true se l'utente si trova nell'elenco dei partecipanti, false altrimenti
     */
    public boolean isUserAlreadyRegistered(String userToCheck){
        for (String participant : participants) {
            if(userToCheck.equalsIgnoreCase(participant))
                return true;
        }
        return false;
    }

    /**
     * Controlla se il numero di utenti e' uguale al massimo
     * @return true se il numero di partecipanti ha raggiunto il massimo, false altrimenti
     */
    public boolean isNumOfParticipantsEqualsMax(){
        if(participants.size() == (int) numOfParticipants.getValue())
            return true;
        return false;
    }

    /**
     * Controlla se siamo oltre la data di termine iscrizione
     * @return true se il termine ultimo iscrizione e' stato superato, false altrimenti
     */
    public boolean isDeadlineAfter(){
        LocalDate deadLineReg = (LocalDate) registrationDeadline.getValue();

        if(deadLineReg.isAfter(LocalDate.now()))
            return true;
        return false;
    }


    /**
     * Controlla e modifica lo stato degli eventi (spostiamo i sendNotification all'interno
     * dei setStateValue ? )
     */
    public void controlState(){

        // Per gli eventi aperti:
        switch (state.getStateValue()) {
            case Aperta:
                // se la data di cambio stato non e' nulla ed e' uguale ad oggi fai:
                if(state.getSwitchDate() != null && LocalDate.now().equals(state.getSwitchDate())){
                    // se il numero di partecipanti e' uguale al numero richiesto e' chiusa
                    if(numberOfPartecipantsIsMaximum()){
                        state.setStateValue(Chiusa);
                        sendNotification(this.title.getValue() +" e' stata chiusa.");
                    }
                    // se il numero di partecipanti e' minore al numero richiesto e' fallita
                    else {
                        state.setStateValue(Fallita);
                        sendNotification(this.title.getValue() +" e' fallita.");
                    }
                    break;
                }
                // Questo else if  lo usano gli eventi di sistema
                else if(state.getSwitchDate() == null){
                    // se il numero di partecipanti e' uguale al numero richiesto e' chiusa
                    if(participants.size() == (int) numOfParticipants.getValue()){
                        state.setStateValue(Chiusa);
                        sendNotification(this.title.getValue() +" e' stata chiusa.");
                    }
                    break;
                }
            case Chiusa:
                //se la data di termine equivale ad oggi
                if(endDate.getValue() != null && LocalDate.now().equals(endDate.getValue())) {
                    state.setStateValue(StateValue.Conclusa);
                    sendNotification(this.title.getValue() +" e' conclusa.");
                }
        }
    }

    /*
    public void controlState() {
    	// Per gli eventi aperti:
        if(state.getStateValue().equals(StateValue.Aperta)){
        	//controllo se la data attuale coincide con quella di scadenza per le iscrizioni
        	if(LocalDate.now().equals(registrationDeadline.getValue()) || LocalDate.now().isAfter((ChronoLocalDate) registrationDeadline.getValue())) {
        		state.setStateValue(StateValue.Fallita);
        		state.setSwitchDate("La proposta e' fallita il giorno:" + LocalDate.now());
        		sendNotification(this.title.getValue() +" e' fallita.");
        	}
        	else {
        		if(participants.size() == (int)numOfParticipants.getValue()) {
        			state.setStateValue(StateValue.Chiusa);
            		state.setSwitchDate("La proposta e' stata chiusa il giorno:" + LocalDate.now());
            		sendNotification(this.title.getValue() +" e' chiusa.");
        		}
        	}
        }
     // Quando sono chiuse le imposto concluse quando terminano
        else if(state.getStateValue().equals(StateValue.Chiusa)) {
        	if(endDate != null) {
        		if(LocalDate.now().equals(endDate.getValue())) {
        			state.setStateValue(StateValue.Conclusa);
            		state.setSwitchDate(state.getSwitchDate() + "\n L'evento e' concluso il giorno:" + LocalDate.now());
            		sendNotification(this.title.getValue() +" e' conclusa.");
        		}
        	}
        	else if(LocalDate.now().isAfter((ChronoLocalDate) date.getValue())){
        		state.setStateValue(StateValue.Conclusa);
        		state.setSwitchDate(state.getSwitchDate() + "\n L'evento e' concluso il giorno:" + LocalDate.now());
        		sendNotification(this.title.getValue() +" e' conclusa.");
        	}
        }
    }
    */
    private void sendNotification(String stateChange){
        setChanged();
        notifyObservers(stateChange);
    }

    /**
     * Controlla se il numero di partecipanti ha raggiunto il massimo
     */
    private boolean numberOfPartecipantsIsMaximum() {
        if(participants.size() >= (int) numOfParticipants.getValue())
            return true;
        return false;
    }
}
