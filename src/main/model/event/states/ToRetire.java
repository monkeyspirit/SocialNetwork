package main.model.event.states;

import main.model.event.*;
import main.model.notifications.*;


public class ToRetire extends State {
    public ToRetire(String oldEvolution){
        super.setEvolution(oldEvolution);
        super.setStateValue(StateValue.DaRitirare);
    }
    public Notification changeState(Event event){
        event.setState(new Retired(super.getEvolution()));
        return buildNotificationEventRetired(event);
    }

    public Notification buildNotificationEventRetired(Event event){
        return NotificationsBuilder.buildNotificationRetiredEvent((String) event.getTitle().getValue());
    }
}
