package versione2;

import java.time.LocalDate;
import java.util.ArrayList;

public class State {

    // Attributi
    private StateValue state;
    private LocalDate switchDate;


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
