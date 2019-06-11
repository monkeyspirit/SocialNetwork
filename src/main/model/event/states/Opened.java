package main.model.event.states;

import main.model.event.*;
import main.model.notifications.*;

import java.time.LocalDate;

public class Opened extends State {

    public Opened(String oldEvolution){
        super.setEvolution(oldEvolution + "\n Giorno apertura evento: " + LocalDate.now());
        super.setStateValue(StateValue.Aperta);
    };

    @Override
    public Notification changeState(Event event) {
        // se il numero di partecipanti e' uguale al numero massimo e non ci si può più ritirare --> CHIUSA
        if (event.isNumOfTotalParticipantsEqualsMaxPlusTolerance() && LocalDate.now().isAfter(event.getRetireDeadline().getValue())) {
            event.setState(new Closed(super.getEvolution()));
            return buildNotificationEventClosed(event);

        }

        // se il numero dei partecipanti è tra il numero minimo e la tolleranza e siamo oltre alla data di termine iscrizione --> CHIUSA
        if (event.isNumOfParticipantsMore() && LocalDate.now().isAfter(event.getRegistrationDeadline().getValue())) {
            event.setState(new Closed(super.getEvolution()));
            return buildNotificationEventClosed(event);
        }

        // se la data di termine e' oltre oggi e non abbiamo il numero di partecipanti --> FALLITA
        if (LocalDate.now().isAfter(event.getRegistrationDeadline().getValue()) && !event.isNumOfParticipantsMore()) {
            event.setState(new Failed(super.getEvolution()));
            return buildNotificationEventFailed(event);
        }
    return null;
    }

    public Notification buildNotificationEventClosed(Event event){
        return NotificationsBuilder.buildNotificationClosed((String) event.getTitle().getValue());
    }

    public Notification buildNotificationEventFailed(Event event){
        return NotificationsBuilder.buildNotificationFailed((String) event.getTitle().getValue());
    }

}
