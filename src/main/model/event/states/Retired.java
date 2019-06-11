package main.model.event.states;

import main.model.event.*;
import main.model.notifications.*;

import java.time.LocalDate;

public class Retired extends State {
    public Retired(String oldEvolution){
        super.setEvolution(oldEvolution + "\n Giorno ritiro evento: " + LocalDate.now());
        super.setStateValue(StateValue.Ritirata);
    }

    public Notification changeState(Event event){
        return null;
    }
}
