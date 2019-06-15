package main.model.event.states;

import main.model.event.*;
import main.model.notifications.*;

import java.time.LocalDate;

public class Ended extends State {
    public Ended(){
        super.setStateValue(StateValue.Conclusa);
    }
    public Notification changeState(Event event){
        return null;
    }
}
