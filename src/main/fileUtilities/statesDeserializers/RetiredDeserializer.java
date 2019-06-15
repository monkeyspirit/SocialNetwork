package main.fileUtilities.statesDeserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import main.model.event.states.Opened;
import main.model.event.states.Retired;

import java.lang.reflect.Type;

public class RetiredDeserializer implements JsonDeserializer<Retired> {
    public Retired deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return new Retired();
    }
}
