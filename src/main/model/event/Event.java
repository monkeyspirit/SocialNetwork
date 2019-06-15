package main.model.event;

import main.model.event.states.Created;
import main.model.notifications.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



public abstract class Event {

    //Costanti della classe Event
	public static final String TITLE_NAME = "Titolo";
    public static final String TITLE_DESCRIPTION = "Campo facoltativo che consiste in un nome di fantasia attribuito all'evento";
    public static final String NUMPLAY_NAME = "Numero di partecipanti";
    public static final String NUMPLAY_DESCRIPTION = "Campo obbligatorio che stabilisce il numero di persone da coinvolgere nell'evento";
    public static final String EXTRA_PARTECIPANTS_NAME= "Tolleranza numero di partecipanti";
    public static final String EXTRA_PARTECIPANTS_DESCRIPTION ="Campo facoltativo che indica quanti partecipanti siano eventualmente accettabili in esubero rispetto al \"Numero di partecipanti\"";
    public static final String REGDEADLINE_NAME = "Termine ultimo iscrizione";
    public static final String REGDEADLINE_DESCRIPTION = "Campo obbligatorio che inidica l'ultima data possibile per iscriversi";
    public static final String RETIRED_DEADLINE_NAME ="Termine ultimo di ritiro iscrizione";
    public static final String RETIRED_DEADLINE_DESCRIPTION ="Campo facoltativo che indica la data entro cui a ogni fruitore che ha aderito all’evento è concesso di cancellare la sua iscrizione e al fruitore che ha proposto l’evento di ritirare la proposta";
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
	private Field<String> title = new Field(TITLE_NAME,TITLE_DESCRIPTION);
	private Field<Integer> numOfParticipants = new Field(NUMPLAY_NAME, NUMPLAY_DESCRIPTION);
	private Field<Integer> extraParticipants = new Field(EXTRA_PARTECIPANTS_NAME, EXTRA_PARTECIPANTS_DESCRIPTION);
	private Field<LocalDate> registrationDeadline = new Field(REGDEADLINE_NAME, REGDEADLINE_DESCRIPTION);
    private Field<LocalDate> retireDeadline =  new Field(RETIRED_DEADLINE_NAME, RETIRED_DEADLINE_DESCRIPTION);
	private Field<String> place = new Field(PLACE_NAME, PLACE_DESCRIPTION);
	private Field<LocalDate> date = new Field(DATE_NAME,DATE_DESCRIPTION);
	private Field<LocalTime> time = new Field(TIME_NAME, TIME_DESCRIPTION);
	private Field<String> duration = new Field(DURATION_NAME, DURATION_DESCRIPTION);
	private Field<Float> individualTee = new Field(INDTEE_NAME,INDTEE_DESCRIPTION);
	private Field<String> teeInclude = new Field(TEEINC_NAME, TEEINC_DESCRIPTION);
	private Field<LocalDate> endDate = new Field(ENDDATE_NAME,ENDDATE_DESCRIPTION);
	private Field<LocalTime> endTime = new Field(ENDTIME_NAME, ENDTIME_DESCRIPTION);
	private Field<String> note = new Field(NOTE_NAME,NOTE_DESCRIPTION);

	private State state;
	private String creator; //serve per capire chi e' il creatore dell'utente
    private List<Member> participants;

    /**
     * Costruttore vuoto: viene inizializzata la lista di campi, ciascuno dei quali con
     * nome e descrizione ma senza valore.
     */
	public Event() {
	}



    /**
     * Costruttore degli eventi, vengono settati tutti i valori dei campi e quello dell'utente creatore
     * @param type tipo dell'evento, uguale al nome della classe
     * @param title titolo dell'evento
     * @param numOfParticipants numero di partecipanti
     * @param extraParticipants numero di partecipanti extra tollerati
     * @param deadline termine ultimo iscrizione
     * @param retiredDeadline termine ultimo per ritirare l'iscrizione
     * @param placeIns luogo dell'evento
     * @param dateIns data di inizio dell'evento
     * @param timeIns ora di inizio dell'evento
     * @param durationIns durata dell'evento
     * @param individualTee quota individuale
     * @param teeInclude spese comprese nella quota individuale
     * @param endDateIns data di termine dell'evento
     * @param endTimeIns ora di termine dell'evento
     * @param noteIns note aggiuntive riguardanti l'evento
     * @param creator username del creatore dell'evento
     */
    public Event(String type, String title, int numOfParticipants, int extraParticipants, LocalDate deadline, LocalDate retiredDeadline, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float individualTee, String teeInclude, LocalDate endDateIns, LocalTime endTimeIns, String noteIns, String creator) {
        this.type = type;
	    this.title.setValue(title);
        this.numOfParticipants.setValue(numOfParticipants);
        this.extraParticipants.setValue(extraParticipants);
        this.registrationDeadline.setValue(deadline);
        this.retireDeadline.setValue(retiredDeadline);
        this.place.setValue(placeIns);
        this.date.setValue(dateIns);
        this.time.setValue(timeIns);
        this.individualTee.setValue(individualTee);
        this.duration.setValue(durationIns);
        this.individualTee.setValue(individualTee);
        this.teeInclude.setValue(teeInclude);
        this.endDate.setValue(endDateIns);
        this.endTime.setValue(endTimeIns);
        this.note.setValue(noteIns);
        this.creator = creator;
        this.state = new Created();
        this.participants = new ArrayList<>();
    }



    //Setter e Getter
    public Field<String> getTitle() {
        return title;
    }

    public Field<Integer> getNumOfParticipants() {
        return numOfParticipants;
    }

    public Field<Integer> getExtraParticipants() { return extraParticipants; }

    public Field<LocalDate> getRegistrationDeadline() { return registrationDeadline; }

    public Field<LocalDate> getRetireDeadline() { return retireDeadline; }

    public Field<String> getPlace() {
        return place;
    }

    public Field<LocalDate> getDate() {
        return date;
    }

    public Field<LocalTime> getTime() {
        return time;
    }

    public Field<String> getDuration() {
        return duration;
    }

    public Field<Float> getIndividualTee() {
        return individualTee;
    }

    public Field<String> getTeeInclude() {
        return teeInclude;
    }

    public Field<LocalDate> getEndDate() {
        return endDate;
    }

    public Field<LocalTime> getEndTime() {
        return endTime;
    }

    public Field<String> getNote() {
        return note;
    }

    public String getCreator() { return creator; }

    public List<Member> getParticipants() { return participants; }

    /**
     * Permette di ottenere la lista dei nomi dei partecipanti
     * @return lista dei nomi dei partecipanti
     */
    public List<String> getParticipantsNames() {
        List<String> names = new ArrayList<>();

        for(Member participant : participants){
            names.add(participant.getUsername());
        }

        return names;
    }

    public StateValue getStateValue() { return state.getStateValue(); }

    public void setState(State state) { this.state = state; }

    public State getState() {  return state; }

    public Notification checkChangeState(){
        return this.state.changeState(this);
    }

    public String getType() { return type; }




    // METODI

    /**
     * Verifica se un certo utente è il creatore dell'evento
     * @param user l'username dell'utente
     * @return true, se user è il creatore dell'evento corrente, false altrimenti
     */
    public boolean isUserCreator(String user){
        if(user.equalsIgnoreCase(this.creator)){
            return true;
        }
        return false;
    }

    /**
     * Aggiunge il partecipante specificato alla lista di partecipanti
     * @param participantUsername username del partecipante
     * @param extra array di voci di spesa extra che l'utente si è impegnato a pagare
     */
    public void addParticipant(String participantUsername, float[] extra){
        participants.add(new Member(participantUsername, extra));
    }

    /**
     * Aggiunge il partecipante specificato alla lista di partecipanti
     * @param participantUsername username del partecipante
     */
    public void addParticipant(String participantUsername){
        float[] extra = {0,0,0};
        participants.add(new Member(participantUsername, extra));
    }

    /**
     * Rimuove il partecipante specificato dalla lista di partecipanti
     * @param participantRemove username del partecipante da rimuovere
     */
    public void removeParticipant(String participantRemove){

        List<Member> removeAll = new ArrayList<>();

        for(Member removeThis : participants){
            if(participantRemove.equalsIgnoreCase(removeThis.getUsername())){
                removeAll.add(removeThis);
            }
        }

        participants.removeAll(removeAll);

    }

    /**
     * Controlla se un utente e' gia' registrato nella lista partecipanti
     * @param userToCheck l'username dell'utente che si vuole cercare
     * @return true se l'utente si trova nell'elenco dei partecipanti, false altrimenti
     */
    public boolean isUserAlreadyRegistered(String userToCheck){
        for (Member participant : participants) {
            if(userToCheck.equalsIgnoreCase(participant.getUsername()))
                return true;
        }
        return false;
    }

    /**
     * Controlla se il numero di utenti e' uguale al massimo più la tolleranza
     * @return true se il numero di partecipanti ha raggiunto il massimo, false altrimenti
     */
    public boolean isNumOfTotalParticipantsEqualsMaxPlusTolerance(){
        if(participants.size() >= ( numOfParticipants.getValue() + extraParticipants.getValue()))
            return true;
        return false;
    }

    /**
     * Controlla se il numero di utenti e' uguale al massimo
     * @return true se il numero di partecipanti ha raggiunto il massimo, false altrimenti
     */
    public boolean isNumOfParticipantsMore(){
        if(participants.size() >=  numOfParticipants.getValue())
            return true;
        return false;
    }


    /**
     *  Trova un utente tra i membri, utilizzato nella sample per impostare i checkbox degli extra
     */
    public boolean[] extraCheckBoxSettings(String username){

        boolean[] settings = { false, false, false};

        for(Member participant: participants){
            if(username.equalsIgnoreCase(participant.getUsername())){
                float[] extraPaid = participant.getExtra();
                for(int i=0; i<3; i++){
                    if(extraPaid[i]!=0){
                        settings[i] = true;
                    }
                }
            }
        }

        return settings;
    }
}
