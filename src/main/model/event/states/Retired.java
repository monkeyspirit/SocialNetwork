package main.model.event.states;

import main.model.event.*;
import main.model.notifications.*;

import java.time.LocalDate;

public class Retired extends State {
    public Retired(){
        super.setStateValue(StateValue.Ritirata);
    }

    public Notification changeState(Event event){
        return null;
    }
}
