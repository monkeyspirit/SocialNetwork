package main.model.event;

import main.model.event.Event;

public interface StateHandler {
    public void checkChangeFromState(Event event);
}
