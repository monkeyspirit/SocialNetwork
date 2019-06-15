package main.fileUtilities.statesDeserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import main.model.event.states.Opened;

import java.lang.reflect.Type;

public class OpenedDeserializer implements JsonDeserializer<Opened> {
    public Opened deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return new Opened();
    }
}
