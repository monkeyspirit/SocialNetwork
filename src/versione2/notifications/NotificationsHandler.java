package versione2.notifications;

import utilities.FileUtility;
import versione1.Event;
import versione1.EventSoccerMatch;
import versione1.User;
import versione2.StateValue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Questa classe si occupa di creare l'array di notifiche.
 * Per farlo legge da un file la vecchia lista degli eventi salvata al momento della chiusura
 * dell'ultima sessione, e la confronta con quella degli eventi attuali che invece viene passata
 * come parametro (per evitare di leggere il file dei nuovi eventi in piu' punti del programma
 * lascio che sia l'oggetto chiamante a preoccuparsi di caricare i nuovi eventi dal file)
 */
public class NotificationsHandler {

    public static final String OLD_EVENTS_FILE_PATH = " "; //file degli eventi salvati dopo l'ultima chiusura

    public static final String MSG_CLOSED = " è ufficialmente chiusa e inizierà il giorno: ";
    public static final String MSG_FAILED = " è fallita in quanto non è stato raggiunto il numero minimo di partecipanti.";
    public static final String MSG_TERMINATED = " si è conclusa con successo.";
    public static final String MSG_NEW = " è stato creato mentre non c'eri.";


    public NotificationsHandler() {
    }

    public ArrayList<Notification> getSoccerMatchNotifications(User user, ArrayList<Event> currentEvents) throws FileNotFoundException {
        ArrayList<Notification> notifications = new ArrayList<>();

//        ArrayList<EventSoccerMatch> oldEvents = new FileUtility().getUserLocalSoccerMatchEvents(user);
        /* Adesso devo confrontare le due liste currentEvents e oldEvents per ottenere
           informazioni come:
           1) nuovi eventi creati da altri utenti mentre ero offline: confronto
           le due liste e comunico la presenza di eventi in currentEvents
           che non ci sono in oldEvents.
           2) eventi chiusi
           3) eventi falliti
           4) eventi terminati

            costruisco ciascun messaggio usando uno dei metodi privati di questa classe
        String message = buildNotification...();
            creo e aggiungo la notifica
        notifications.add(new Notification(message));

        */

//        for(Event event: currentEvents){
//                if(!oldEvents.contains(event)){
//                   String messageNew = buildNotificationNewEvent((String) event.getTitle().getValue());
//                   notifications.add(new Notification(messageNew));
//                }
//        }

        for (Event event: currentEvents) {
            if(event.getState().getStateValue().equals(StateValue.Chiusa)){
                String messageClosed = buildNotificationClosed((String) event.getTitle().getValue());
                notifications.add(new Notification(messageClosed));
            }
            else if (event.getState().getStateValue().equals(StateValue.Fallita)){
                String messageFailed = buildNotificationFailed((String) event.getTitle().getValue());
                notifications.add(new Notification(messageFailed));
            }
            else if(event.getState().getStateValue().equals(StateValue.Conclusa)){
                String messageConclused = buildNotificationTerminated((String) event.getTitle().getValue());
                notifications.add(new Notification(messageConclused));
            }
        }

        return notifications;
    }

    /**
     * Costruisce il messaggio della notifica per un evento nuovo
     * @param eventName nome dell'evento
     */
    private String buildNotificationNewEvent (String eventName) {
        String message = eventName + MSG_NEW;
        return message;
    }

    /**
     * Costruisce il messaggio della notifica per un evento confermato le cui iscrizioni si sono chiuse
     * @param eventName nome dell'evento
     * @param startDate data d'inizio dell'evento
     */
    private String buildNotificationClosed (String eventName, Date startDate) {
        String message = eventName + MSG_CLOSED + startDate;
        return message;
    }

    private String buildNotificationClosed (String eventName) {
        String message = eventName + MSG_CLOSED;
        return message;
    }

    /**
     * Costruisce il messaggio della notifica per un evento fallito
     * @param eventName nome dell'evento
     */
    private String buildNotificationFailed (String eventName) {
        String message = eventName + MSG_FAILED;
        return message;
    }

    /**
     * Costruisce il messaggio della notifica per un evento terminato
     * @param eventName nome dell'evento
     */
    private String buildNotificationTerminated (String eventName) {
        String message = eventName + MSG_TERMINATED;
        return message;
    }


}
