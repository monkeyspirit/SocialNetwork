package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.scene.Scene;
import versione1.Event;
import versione1.SoccerMatchEvent;
import versione1.User;
import versione5.CinemaEvent;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Questa classe ha lo scopo di serializzare/deserializzare oggetti su/da file.
 * Può essere utilizzata per salvare collezioni di oggetti e quindi poter
 * ripristinare lo stato dell'applicazione in un secondo momento. *
 */
public class FileUtility {

    public static final String EVENTS_FILE_PATH = "JsonFiles/";
    public static final String SOCCER_MATCH_EVENTS_FILE = "SoccerMatchEvents";
    public static final String CINEMA_EVENTS_FILE = "CinemaEvents";
    public static final String USERS_LIST_FILE_PATH = "JsonFiles/users/usersList.json";

    private RuntimeTypeAdapterFactory<Event> typeFactory;
    private GsonBuilder builder;

    private Gson gson;

    /**
     * Il costruttore della classe FileUtility si occupa di creare il RuntimeTypeAdaprterFactory
     * che serve per poter associare ad una superclasse, le possibili classi che la estendono.
     * Questo serve in caso si abbia una lista di Event, che pero' all'interno potrebbe
     * avere oggetti SoccerMatchEvent ma anche altri.
     */
    public FileUtility() {
        typeFactory = RuntimeTypeAdapterFactory
                .of(Event.class, "type") // Qui si deve specificare il tipo della superclasse e quale attributo guardare per identificare i figli (attributo type)
                .registerSubtype(SoccerMatchEvent.class, "SoccerMatchEvent") //Qui si associa il valore dell'attributo "type" all'interno di ciascuna classe figlia con il tipo corrispondente della classe (e' necessario aggiungere una riga per ogni sottoclasse che si crea)
                .registerSubtype(CinemaEvent.class, "CinemaEvent");
        builder = new GsonBuilder().registerTypeAdapterFactory(typeFactory);
        gson = builder.create();
    }


    /**
     * Serializza la lista di eventSoccerMatch e la scrive in un file .json
     *
     * @param soccerMatchEvents La lista di eventi che si vuole serializzare
     */
    public void writeSoccerMatchEvents(List<SoccerMatchEvent> soccerMatchEvents) {
        String fileName = PathBuilder.buildJsonFilePath(EVENTS_FILE_PATH, SOCCER_MATCH_EVENTS_FILE);
        writeEventListToJsonFile(soccerMatchEvents, fileName);
    }

    /**
     * Legge il file SoccerMatchEvents.json ed effettua la deserializzazione degli SoccerMatchEvent
     *
     * @return lista di SoccerMatchEvent
     */
    public List<SoccerMatchEvent> readSoccerMatchEvents() {
        String filePath = PathBuilder.buildJsonFilePath(EVENTS_FILE_PATH, SOCCER_MATCH_EVENTS_FILE);
        return readSoccerMatchEventsFromJsonFile(filePath);
    }

    /**
     * Serializza la lista di CinemaEvent e la scrive in un file .json
     *
     * @param cinemaEvents La lista di eventi che si vuole serializzare
     */
    public void writeCinemaEvents(List<CinemaEvent> cinemaEvents) {
        String fileName = PathBuilder.buildJsonFilePath(EVENTS_FILE_PATH, CINEMA_EVENTS_FILE);
        writeEventListToJsonFile(cinemaEvents, fileName);
    }

    /**
     * Legge il file CinemaEvents.json ed effettua la deserializzazione dei cinemaEvent
     *
     * @return lista di CinemaEvent
     */
    public List<CinemaEvent> readCinemaEvents() {
        String filePath = PathBuilder.buildJsonFilePath(EVENTS_FILE_PATH, CINEMA_EVENTS_FILE);
        return readCinemaEventsFromJsonFile(filePath);
    }
    /**
     * Serializza la lista di eventSoccerMatch dell'utente e la scrive in un file .json
     *
     * @param user              l'utente attualmente loggato
     * @param soccerMatchEvents La lista di eventi che si vuole serializzare
     * @deprecated si è scelto di utilizzare un approccio migliore
     */
    @Deprecated
    public void writeUserLocalSoccerMatchEvents(User user, List<SoccerMatchEvent> soccerMatchEvents) {
        String fileName = PathBuilder.buildJsonFilePath(EVENTS_FILE_PATH, SOCCER_MATCH_EVENTS_FILE, user.getUsername());
        writeEventListToJsonFile(soccerMatchEvents, fileName);
    }

    /**
     * Legge il file SoccerMatchEvents di uno specifico utente ed effettua la deserializzazione degli SoccerMatchEvent
     *
     * @param user utente del quale si vuole ottenere la lista di eventSoccerMatch corrispondente
     * @return lista di SoccerMatchEvent
     * @deprecated si è scelto di utilizzare un approccio migliore
     */
    @Deprecated
    public List<SoccerMatchEvent> readUserLocalSoccerMatchEvents(User user) {
        String filePath = PathBuilder.buildJsonFilePath(EVENTS_FILE_PATH, SOCCER_MATCH_EVENTS_FILE, user.getUsername());
        return readSoccerMatchEventsFromJsonFile(filePath);
    }

    /**
     * Serializza e scrive in un file la lista degli utenti registrati
     *
     * @param users lista degli utenti
     */
    public void writeUsersList(List<User> users) {
        writeUsersListToJsonFile(users, USERS_LIST_FILE_PATH);
    }

    /**
     * Legge da un file e deserializza la lista degli utenti registrati
     *
     * @return la lista degli utenti registrati
     */
    public List<User> readUsersList() {
        return readUsersListFromJsonFile(USERS_LIST_FILE_PATH);
    }

    /**
     * Deserializza un file json contenente una lista di SoccerMatchEvent
     *
     * @param filePath percorso del file json
     * @return lista di SoccerMatchEvent
     */
    private List<SoccerMatchEvent> readSoccerMatchEventsFromJsonFile(String filePath) {
        Type listType = new TypeToken<List<SoccerMatchEvent>>() {
        }.getType(); //listType contiene il tipo di elemento che si vuole deserializzare ossia <List<SoccerMatchEvent>>

        List<SoccerMatchEvent> soccerMatchEventsDeserialized = null;
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath)); //leggo dal file SoccerMatchEvents.json la lista degli eventi di quel tipo
            soccerMatchEventsDeserialized = gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return soccerMatchEventsDeserialized;
    }

    /**
     * Deserializza un file json contenente una lista di CinemaEvent
     *
     * @param filePath percorso del file json
     * @return lista di CinemaEvent
     */
    private List<CinemaEvent> readCinemaEventsFromJsonFile(String filePath) {
        Type listType = new TypeToken<List<CinemaEvent>>() {
        }.getType(); //listType contiene il tipo di elemento che si vuole deserializzare ossia <List<CinemaEvent>>

        List<CinemaEvent> cinemaEventsDeserialized = null;
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath)); //leggo dal file CinemaEvents.json la lista degli eventi di quel tipo
            cinemaEventsDeserialized = gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cinemaEventsDeserialized;
    }

    /**
     * Scrive una lista di eventi nel file .json specificato
     *
     * @param eventList lista di eventi
     * @param filePath  nome del file
     */
    private void writeEventListToJsonFile(List<? extends Event> eventList, String filePath) {
        //serializzo
        try {
            Writer writer = new FileWriter(filePath);
//            System.out.println("Scrivo il file: " + filePath);
            gson.toJson(eventList, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializza un file json conenente una lista di utenti registrati
     *
     * @param filePath percorso del file json
     * @return lista di utenti registrati
     */
    private List<User> readUsersListFromJsonFile(String filePath) {
        Type listType = new TypeToken<List<User>>() {
        }.getType(); //listType contiene il tipo di elemento che si vuole deserializzare ossia <List<User>>

        List<User> usersListDeserialized = null;
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath)); //leggo dal file la lista degli utenti registrati
            usersListDeserialized = gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return usersListDeserialized;
    }

    /**
     * Scrive una lista di utenti nel file .json specificato
     *
     * @param users    lista di utenti
     * @param filePath nome del file
     */
    private void writeUsersListToJsonFile(List<User> users, String filePath) {
        //serializzo
        try {
            Writer writer = new FileWriter(filePath);
//            System.out.println("Scrivo il file: " + filePath);
            gson.toJson(users, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //CON LO STESSO APPROCCIO SI LEGGONO E SCRIVONO UTENTI/EVENTI DI ALTRE CATEGORIE/..


}
