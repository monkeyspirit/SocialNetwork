package main.model.event.states;

import main.model.event.*;
import main.model.notifications.*;

import java.time.LocalDate;

public class Closed extends State {

    public Closed(String oldEvolution){
        super.setEvolution(oldEvolution + "\nGiorno chiusura evento: " + LocalDate.now());
        super.setStateValue(StateValue.Chiusa);
    }
    public Notification changeState(Event event){
        if (event.getEndDate().getValue() != null && LocalDate.now().isAfter(event.getEndDate().getValue())) {
            event.setState(new Ended(super.getEvolution()));
            return buildNotificationEventTerminated(event);
        }
        return null;
    }

    public Notification buildNotificationEventTerminated(Event event){
        return  NotificationsBuilder.buildNotificationTerminated((String) event.getTitle().getValue());
    }
}
