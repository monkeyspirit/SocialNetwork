package main.fileUtilities.statesDeserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import main.model.event.states.ToRetire;

import java.lang.reflect.Type;

public class ToRetireDeserializer implements JsonDeserializer<ToRetire> {
    public ToRetire deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return new ToRetire();
    }
}
