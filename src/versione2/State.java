package versione2;

import java.time.LocalDate;

public class State {

    // Attributi
    private Enum<StateValue> state;
    private LocalDate switchDate;


    // Setter & Getter
    public Enum<StateValue> getStateValue() { return state; }

    public void setStateValue(Enum<StateValue> state) { this.state = state; }

    public LocalDate getSwitchDate() { return switchDate; }

    public void setSwitchDate(LocalDate switchDate) { this.switchDate = switchDate; }


}
