package main.model.event.states;

import main.model.event.*;
import main.model.notifications.*;



public class Created extends State{

    public Created(){
        super.setStateValue(StateValue.Creata);
    }
    @Override
    public Notification changeState(Event event) {
        event.setState(new Opened());
        return buildNotificationNewEvent( event );
    }

    public Notification buildNotificationNewEvent(Event event){
        return NotificationsBuilder.buildNotificationNewEvent ((String) event.getTitle().getValue());
    }
}
