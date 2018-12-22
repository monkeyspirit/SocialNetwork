package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import versione1.Event;
import versione1.EventSoccerMatch;
import versione1.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Questa classe serve per leggere/scrivere da/su file liste di oggetti di un certo tipo.
 *
 * ### DISTINZIONE TRA FILE LOCALI E GLOBALI ###
 * il motivo principale e' di poter distinguere il file globale che contiene lo stato piu'
 * aggiornato dell'applicazione, dal file locale che contiene lo stato locale al momento
 * del logout di un certo utente (meccanismo che sfrutteremo per generare le notifiche)
 */
public class FileUtility {

    public static final String SOCCER_MATCH_EVENTS_FILE_PATH = "JsonFiles/";
    public static final String SOCCER_MATCH_EVENTS_FILE = "SoccerMatchEvents";

    private RuntimeTypeAdapterFactory<Event> typeFactory;
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
                .registerSubtype(EventSoccerMatch.class, "EventSoccerMatch"); //Qui si associa il valore dell'attributo "type" all'interno di ciascuna classe figlia con il tipo corrispondente della classe (e' necessario aggiungere una riga per ogni sottoclasse che si crea)

        builder = new GsonBuilder().registerTypeAdapterFactory(typeFactory);
        gson = builder.create();
    }

    /**
     * Serializza la lista di eventSoccerMatch e la scrive in un file .json
     * @param soccerMatchEvents La lista di eventi che si vuole serializzare
     */
    public void writeGlobalSoccerMatchEvents(ArrayList<Event> soccerMatchEvents) {
        String fileName = PathBuilder.buildJsonFilePath(SOCCER_MATCH_EVENTS_FILE_PATH, SOCCER_MATCH_EVENTS_FILE);
        eventListToJsonFile(soccerMatchEvents, fileName);
    }

    /**
     * Legge il file SoccerMatchEvents.json ed effettua la deserializzazione degli EventSoccerMatch
     * @return lista di EventSoccerMatch
     */
    public ArrayList<EventSoccerMatch> getGlobalSoccerMatchEvents() {
        String filePath = PathBuilder.buildJsonFilePath(SOCCER_MATCH_EVENTS_FILE_PATH, SOCCER_MATCH_EVENTS_FILE);
        return readSoccerMatchEvents(filePath);
    }

    /**
     * Serializza la lista di eventSoccerMatch dell'utente e la scrive in un file .json
     * @param user l'utente attualmente loggato
     * @param soccerMatchEvents La lista di eventi che si vuole serializzare
     */
    public void writeUserLocalSoccerMatchEvents(User user, ArrayList<Event> soccerMatchEvents) {
        String fileName = PathBuilder.buildJsonFilePath(SOCCER_MATCH_EVENTS_FILE_PATH, SOCCER_MATCH_EVENTS_FILE, user.getUsername());
        eventListToJsonFile(soccerMatchEvents, fileName);
    }

    public ArrayList<EventSoccerMatch> getUserLocalSoccerMatchEvents(User user) {
        String filePath = PathBuilder.buildJsonFilePath(SOCCER_MATCH_EVENTS_FILE_PATH, SOCCER_MATCH_EVENTS_FILE, user.getUsername());
        return readSoccerMatchEvents(filePath);
    }


    private ArrayList<EventSoccerMatch> readSoccerMatchEvents(String filePath) {
        Type listType = new TypeToken<ArrayList<EventSoccerMatch>>() {}.getType(); //listType contiene il tipo di elemento che si vuole deserializzare

        ArrayList<EventSoccerMatch> soccerMatchEventsDeserialized = null;
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath)); //leggo dal file SoccerMatchEvents.json la lista degli eventi di quel tipo
            soccerMatchEventsDeserialized = gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return soccerMatchEventsDeserialized;
    }

    /**
     * Scrive una lista di eventi nel file .json specificato
     * @param eventList lista di eventi
     * @param fileName nome del file
     */
    private void eventListToJsonFile(ArrayList<Event> eventList, String fileName) {
        //serializzo
        try {
            Writer writer = new FileWriter(fileName);
            System.out.println("Scrivo il file: " + fileName);
            gson.toJson(eventList, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //CON LO STESSO APPROCCIO SI LEGGONO E SCRIVONO UTENTI/EVENTI DI ALTRE CATEGORIE/..
}
