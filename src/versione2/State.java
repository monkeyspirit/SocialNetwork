package versione2;

import java.time.LocalDate;

public class State {

    // Attributi
    private StateValue state;
    private LocalDate switchDate;


    // Setter & Getter
    public StateValue getStateValue() {
        return state;
    }

    public void setStateValue(StateValue state) {
        this.state = state;
    }

    public LocalDate getSwitchDate() {
        return switchDate;
    }

    public void setSwitchDate(LocalDate switchDate) {
        this.switchDate = switchDate;
    }


}
