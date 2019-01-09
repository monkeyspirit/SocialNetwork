package versione2.notifications;

import versione1.Event;
import versione1.User;
import versione2.StateValue;

import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Questa classe si occupa di creare l'array di notifiche.
 * Per farlo legge da un file la vecchia lista degli eventi salvata al momento della chiusura
 * dell'ultima sessione, e la confronta con quella degli eventi attuali che invece viene passata
 * come parametro (per evitare di leggere il file dei nuovi eventi in piu' punti del programma
 * lascio che sia l'oggetto chiamante a preoccuparsi di caricare i nuovi eventi dal file)
 */
public class NotificationsBuilder {

    public static final String MSG_CLOSED = " è ufficialmente chiusa e inizierà il giorno: ";
    public static final String MSG_CLOSED_NO_DATE = " è ufficialmente chiusa.";
    public static final String MSG_FAILED = " è fallita in quanto non è stato raggiunto il numero minimo di partecipanti.";
    public static final String MSG_TERMINATED = " si è conclusa con successo.";
    public static final String MSG_NEW = " è stato creato mentre non c'eri.";



    public static String buildReminder(String title, LocalDate date, LocalTime time, String place, Float tee){
        String reminder = "Ricordati che hai l'evento: "+title+" che si terra' "+date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALY)+" "+date.getDayOfMonth()+" "+date.getMonth().getDisplayName(TextStyle.FULL, Locale.ITALY)+ " del "+date.getYear()+" alle ore "+time.getHour()+":"+time.getMinute()+".\n";
        reminder+="Il luogo di ritrovo e': "+place+".\n";
        if(tee!=0){
            reminder+="Ricordati di pagare: "+tee+" €.\n";
        }

        return reminder;
    }

    /**
     * Costruisce il messaggio della notifica per un evento nuovo
     * @param eventName nome dell'evento
     */
    public static String buildNotificationNewEvent (String eventName) {
        String message = eventName + MSG_NEW;
        return message;
    }

    /**
     * Costruisce il messaggio della notifica per un evento confermato le cui iscrizioni si sono chiuse
     * @param eventName nome dell'evento
     * @param startDate data d'inizio dell'evento
     */
    public static String buildNotificationClosed (String eventName, Date startDate) {
        String message = eventName + MSG_CLOSED + startDate;
        return message;
    }

    public static String buildNotificationClosed (String eventName) {
        String message = eventName + MSG_CLOSED_NO_DATE;
        return message;
    }

    /**
     * Costruisce il messaggio della notifica per un evento fallito
     * @param eventName nome dell'evento
     */
    public static String buildNotificationFailed (String eventName) {
        String message = eventName + MSG_FAILED;
        return message;
    }

    /**
     * Costruisce il messaggio della notifica per un evento terminato
     * @param eventName nome dell'evento
     */
    public static String buildNotificationTerminated (String eventName) {
        String message = eventName + MSG_TERMINATED;
        return message;
    }


}
