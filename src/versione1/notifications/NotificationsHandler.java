package versione1.notifications;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 * Questa classe si occupa di creare l'array di notifiche.
 * Per farlo legge da un file la vecchia lista degli eventi salvata al momento della chiusura
 * dell'ultima sessione, e la confronta con quella degli eventi attuali che invece viene passata
 * come parametro (per evitare di leggere il file dei nuovi eventi in più punti del programma
 * lascio che sia l'oggetto chiamante a preoccuparsi di caricare i nuovi eventi dal file)
 */
public class NotificationsHandler {

    public static final String OLD_EVENTS_FILE_PATH = " "; //file degli eventi salvati dopo l'ultima chiusura

    public static final String MSG_CLOSED = " è ufficialmente chiusa e inizierà il giorno: ";
    public static final String MSG_FAILED = " è fallita";
    public static final String MSG_TERMINATED = " si è conclusa con successo.";


    public NotificationsHandler() {

    }

    /*public ArrayList<Notification> getNotifications(ArrayList<Event> currentEvents) throws FileNotFoundException {
        ArrayList<Notification> notifications = new ArrayList<>();

        // leggo da file la lista degli eventi in un JsonReader
        JsonReader readerEventiJson = new JsonReader(new FileReader(OLD_EVENTS_FILE_PATH));

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Type listType = new TypeToken<ArrayList<Persona>>(){}.getType(); //questo serve se l'oggetto è di tipo generic
        ArrayList<Event> oldEvents = gson.fromJson(readerEventiJson, listType);


        //costruisco il messaggio usando uno dei metodi privati di questa classe
        String message =
        //creo e aggiungo la notifica
        notifications.add(new Notification(message));

        return notifications;
    }*/

    /**
     * Costruisce il messaggio della notifica per un evento confermato le cui iscrizioni si sono chiuse
     * @param eventName nome dell'evento
     * @param startDate data d'inizio dell'evento
     */
    /*private String buildNotificationClosed (String eventName, Date startDate) {
        message = eventName + MSG_CLOSED + startDate;
        return message;
    }*/

    /**
     * Costruisce il messaggio della notifica per un evento fallito
     * @param eventName nome dell'evento
     */
    /*private String buildNotificationFailed (String eventName) {
        message = eventName + MSG_FAILED;
        return message;
    }*/

    /**
     * Costruisce il messaggio della notifica per un evento terminato
     * @param eventName nome dell'evento
     */
    /*private String buildNotificationTerminated (String eventName) {
        message = eventName + MSG_TERMINATED;
        return message;
    }*/


}
