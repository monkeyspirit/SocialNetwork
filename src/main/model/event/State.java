package main.model.event;

import main.model.notifications.*;

/**
 * La classe State rappresenta lo stato di un evento e la data in cui questo è stato impostato
 */
public abstract class State {

    // Attributi
    private String evolution;
    private StateValue stateValue;
    public abstract Notification changeState(Event event);


    public void setEvolution(String evolution){
        this.evolution = evolution;
    }

    public String getEvolution(){
        return this.evolution;
    }

    public void setStateValue(StateValue stateValue){
        this.stateValue = stateValue;
    }

    public StateValue getStateValue(){
        return this.stateValue;
    }
}
