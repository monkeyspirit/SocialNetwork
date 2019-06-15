package main.fileUtilities.statesDeserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import main.model.event.states.Created;

import java.lang.reflect.Type;

public class CreatedDeserializer implements JsonDeserializer<Created> {
    public Created deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return new Created();
    }
}
