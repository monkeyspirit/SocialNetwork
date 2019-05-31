package main.model.event;

import java.time.LocalDate;

/**
 * La classe State rappresenta lo stato di un evento e la data in cui questo è stato impostato
 */
public class State {

    // Attributi
    private StateValue state;
    private LocalDate switchDate;

    /**
     * @param state stato dell'evento
     * @param switchDate data di cambio dello stato
     */
    public State(StateValue state, LocalDate switchDate) {
        this.state = state;
        this.switchDate = switchDate;
    }

    // Setter & Getter
    public StateValue getStateValue() { return state; }

    public void setStateValue(StateValue state) { this.state=state; }

    public LocalDate getSwitchDate() {
        return switchDate;
    }

    public void setSwitchDate(LocalDate switchDate) {
        this.switchDate = switchDate;
    }



}
