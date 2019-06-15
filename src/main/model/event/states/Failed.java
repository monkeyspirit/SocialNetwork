package main.model.event.states;

import main.model.event.*;
import main.model.notifications.*;

import java.time.LocalDate;

public class Failed extends State {

    public Failed(){
        super.setStateValue(StateValue.Fallita);
    }

    public Notification changeState(Event event){
        return null;
    }
}
