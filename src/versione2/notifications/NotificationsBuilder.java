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



    public static Notification buildReminder(String title, LocalDate date, LocalTime time, String place, Float tee){
        String reminderString = "Ricordati che hai l'evento: "+title+" che si terra' "+date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALY)+" "+date.getDayOfMonth()+" "+date.getMonth().getDisplayName(TextStyle.FULL, Locale.ITALY)+ " del "+date.getYear()+" alle ore "+time.getHour()+":"+time.getMinute()+".\n";
        reminderString+="Il luogo di ritrovo e': "+place+".\n";
        if(tee!=0){
            reminderString+="Ricordati di pagare: "+tee+" €.\n";
        }

        Notification reminder = new Notification(NotificationType.Reminder, reminderString);
        return reminder;
    }

    /**
     * Costruisce il messaggio della notifica per un evento nuovo
     * @param eventName nome dell'evento
     */
    public static Notification buildNotificationNewEvent (String eventName) {
        String message = eventName + MSG_NEW;

        Notification aller = new Notification(NotificationType.Allert, message);
        return aller;
    }

    /**
     * Costruisce il messaggio della notifica per un evento confermato le cui iscrizioni si sono chiuse
     * @param eventName nome dell'evento
     */

    public static Notification buildNotificationClosed (String eventName) {
        String message = eventName + MSG_CLOSED_NO_DATE;

        Notification aller = new Notification(NotificationType.Allert, message);
        return aller;
    }

    /**
     * Costruisce il messaggio della notifica per un evento fallito
     * @param eventName nome dell'evento
     */
    public static Notification buildNotificationFailed (String eventName) {
        String message = eventName + MSG_FAILED;

        Notification aller = new Notification(NotificationType.Allert, message);
        return aller;
    }

    /**
     * Costruisce il messaggio della notifica per un evento terminato
     * @param eventName nome dell'evento
     */
    public static Notification buildNotificationTerminated (String eventName) {
        String message = eventName + MSG_TERMINATED;

        Notification aller = new Notification(NotificationType.Allert, message);
        return aller;
    }


}
