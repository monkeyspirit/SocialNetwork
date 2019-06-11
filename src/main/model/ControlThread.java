package main.model;

import main.model.event.Event;
import main.model.event.StateValue;
import main.model.notifications.Notification;
import main.model.notifications.NotificationsBuilder;
import main.model.event.StateHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static main.model.event.StateValue.Fallita;

/**
 * Classe che si occupa di osservare la lista di eventi per riconoscerne i cambiamenti e aggiornare
 * lo stato del social network in base a questi. Per esempio cambia lo stato di un evento quando i
 * requisiti per la transizione sono verificati
 */
public class ControlThread extends Thread implements StateHandler {


    SocialNetwork socialNetwork;
    private HashMap<StateValue,StateHandler> stateHandlers = new HashMap<StateValue,StateHandler>();



    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
        this.initializeStateHandlers();
    }

    @Override
    public void run() {

        while (true) {
            if(socialNetwork != null) {
                controlState();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public Category getCategoryFromEvent(Event event){
        for(Category cat : this.socialNetwork.getCategories()){
            if(cat.getEvents().contains((event)))
                return cat;
        }
    return null;
    }

    public void sendNotificationForCreation(Event event, Notification notification){
        Category cat = getCategoryFromEvent(event);
        for (User user: socialNetwork.getUsers() ) {
            if(!(user.equals(socialNetwork.getLoggedUser())) && user.getCategoryPref().contains(cat.getName())){
                user.addNotification(notification);
            }
        }
    }

    public void sendNotificationAndReminder(Event event, Notification notification, boolean reminder){
        User sendTo;
        for(int i=0; i<event.getParticipants().size(); i++){
             sendTo=socialNetwork.findUserByName(event.getParticipants().get(i).getUsername());
             sendTo.addNotification(notification);

            if(reminder){
                sendTo.addNotification( NotificationsBuilder.buildReminder((String) event.getTitle().getValue(),
                        (LocalDate) event.getDate().getValue(), (LocalTime) event.getTime().getValue(), (String) event.getPlace().getValue(),
                        (Float) event.getIndTee().getValue(), event.getParticipants().get(i).getExtra() )  );

            }
        }
    }

    // inizializzazione dell'hash map: assegno ad ogni stato il relativo metodo

    public void initializeStateHandlers(){
        this.stateHandlers.put(StateValue.Creata,(Event event) -> changeStateFromCreated(event));
        this.stateHandlers.put(StateValue.Aperta,(Event event) -> changeStateFromOpened(event));
        this.stateHandlers.put(StateValue.Chiusa,(Event event) -> changeStateFromClosed(event));
        this.stateHandlers.put(StateValue.DaRitirare,(Event event) -> changeStateFromRetired(event));
    }

    @Override
    public void checkChangeFromState(Event event) {
        for(StateValue state : stateHandlers.keySet()){
            if( state == event.getLastState() ){
                 stateHandlers.get(state).checkChangeFromState(event);
            }
        }
    }

    // --------------------- METODI PER CAMBIARE STATO -------------------------

    public void changeStateFromCreated(Event event){
            event.setState(new main.model.event.State(StateValue.Aperta, LocalDate.now()));
            sendNotificationForCreation(event,buildNotificationNewEvent(event) );
    }

    public void changeStateFromOpened(Event event){
            // se il numero di partecipanti e' uguale al numero massimo e non ci si può più ritirare --> CHIUSA
            if (event.isNumOfTotalParticipantsEqualsMaxPlusTolerance() && LocalDate.now().isAfter(event.getRetireDeadline().getValue())) {
                event.setState(new main.model.event.State(StateValue.Chiusa, LocalDate.now()));
                sendNotificationAndReminder(event, buildNotificationEventClosed(event),true );

            }

            // se il numero dei partecipanti è tra il numero minimo e la tolleranza e siamo oltre alla data di termine iscrizione --> CHIUSA
            if (event.isNumOfParticipantsMore() && LocalDate.now().isAfter(event.getRegistrationDeadline().getValue())) {
                event.setState(new main.model.event.State(StateValue.Chiusa, LocalDate.now()));
                sendNotificationAndReminder(event,buildNotificationEventClosed(event), true);
            }

            // se la data di termine e' oltre oggi e non abbiamo il numero di partecipanti --> FALLITA
            if (LocalDate.now().isAfter(event.getRegistrationDeadline().getValue()) && !event.isNumOfParticipantsMore()) {
                event.setState(new main.model.event.State(Fallita, LocalDate.now()));
                sendNotificationAndReminder(event,buildNotificationEventFailed(event), false);
            }
    }

    public void changeStateFromClosed(Event event){
            //se la data di termine è oltre oggi --> CONCLUSA
            if (event.getEndDate().getValue() != null && LocalDate.now().isAfter(event.getEndDate().getValue())) {
                event.setState(new main.model.event.State(StateValue.Conclusa, LocalDate.now()));
                sendNotificationAndReminder(event,buildNotificationEventTerminated(event), false);
            }
    }

    public void changeStateFromRetired(Event event){
            event.setState(new main.model.event.State(StateValue.Ritirata, LocalDate.now()));
            sendNotificationAndReminder(event,buildNotificationEventRetired(event), false);
    }


    /**
     * Controlla e modifica lo stato degli eventi
     */
    public void controlState(){
        for (Category cat : socialNetwork.getCategories()) {
            for(Event event : (ArrayList<Event>) cat.getEvents()){
                checkChangeFromState(event);
            }
        }
    }


    // ------------------- METODI PER LA CREAZIONE DI NOTIFICHE -------------------------

    public Notification buildNotificationNewEvent(Event event){
        return NotificationsBuilder.buildNotificationNewEvent ((String) event.getTitle().getValue());
    }

    public Notification buildNotificationEventClosed(Event event){
        return NotificationsBuilder.buildNotificationClosed((String) event.getTitle().getValue());
    }
    public Notification buildNotificationEventFailed(Event event){
        return NotificationsBuilder.buildNotificationFailed((String) event.getTitle().getValue());
    }

    public Notification buildNotificationEventTerminated(Event event){
        return  NotificationsBuilder.buildNotificationTerminated((String) event.getTitle().getValue());
    }

    public Notification buildNotificationEventRetired(Event event){
        return NotificationsBuilder.buildNotificationRetiredEvent((String) event.getTitle().getValue());
    }

}

