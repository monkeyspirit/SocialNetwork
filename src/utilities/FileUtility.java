package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import versione1.Event;
import versione1.EventSoccerMatch;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Questa classe serve per leggere/scrivere da/su file liste di oggetti di un certo tipo.
 */
public class FileUtility {

    public static final String SOCCER_MATCH_EVENTS_FILE = "JsonFiles/SoccerMatchEvents.json";

    final RuntimeTypeAdapterFactory<Event> typeFactory;
    private GsonBuilder builder;
    private Gson gson;

    /**
     * Il costruttore della classe FileUtility si occupa di creare il RuntimeTypeAdaprterFactory
     * che serve per poter associare ad una superclasse, le possibili classi che la estendono.
     * Questo serve in caso si abbia una lista di Event, che pero' all'interno potrebbe
     * avere oggetti EventSoccerMatch ma anche altri.
     */
    public FileUtility() {
        typeFactory = RuntimeTypeAdapterFactory
                .of(Event.class, "type") // Qui si deve specificare il tipo della superclasse e quale attributo guardare per identificare i figli (attributo type)
                .registerSubtype(EventSoccerMatch.class, "EventSoccerMatch"); //Qui si associa il valore dell'attributo "type" all'interno di ciascuna classe figlia con il tipo corrispondente della classe (è necessario aggiungere una riga per ogni sottoclasse che si crea)

        builder = new GsonBuilder().registerTypeAdapterFactory(typeFactory);
        gson = builder.create();
    }

    /**
     * Serializza la lista di eventSoccerMatch e la scrive in un file .json
     * @param soccerMatchEvents La lista di eventi che si vuole serializzare
     */
    public void writeSoccerMatchEvents(ArrayList<Event> soccerMatchEvents) {
        System.out.println("EVENTI: " + soccerMatchEvents);
        //serializzo
        try {
            Writer writer = new FileWriter(SOCCER_MATCH_EVENTS_FILE);
            System.out.println("Scrivo il file: " + SOCCER_MATCH_EVENTS_FILE);
            gson.toJson(soccerMatchEvents, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Legge il file SoccerMatchEvents.json ed effettua la deserializzazione degli EventSoccerMatch
     * @return lista di EventSoccerMatch
     */
    public ArrayList<EventSoccerMatch> readSoccerMatchEvents() {
        Type listType = new TypeToken<ArrayList<EventSoccerMatch>>() {}.getType(); //listType contiene il tipo di elemento che si vuole deserializzare

        ArrayList<EventSoccerMatch> soccerMatchEventsDeserialized = null;
        try {
            JsonReader reader = new JsonReader(new FileReader(SOCCER_MATCH_EVENTS_FILE)); //leggo dal file SoccerMatchEvents.json la lista degli eventi di quel tipo
            soccerMatchEventsDeserialized = gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return soccerMatchEventsDeserialized;
    }

    //CON LO STESSO APPROCCIO SI LEGGONO E SCRIVONO UTENTI/EVENTI DI ALTRE CATEGORIE/..
}
