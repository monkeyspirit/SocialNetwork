package versione2.notifications;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;


/**
 * Questa classe si occupa di creare il messaggio di una notifica/reminder
 */
public class NotificationsBuilder {

    public static final String MSG_RETIRED = " è stata ritirata dal suo creatore.";
    public static final String MSG_CLOSED_NO_DATE = " è ufficialmente chiusa.";
    public static final String MSG_FAILED = " è fallita in quanto non è stato raggiunto il numero minimo di partecipanti.";
    public static final String MSG_TERMINATED = " si è conclusa con successo.";
    public static final String MSG_NEW = " è stato creato mentre non c'eri.";
    public static final String MSG_INVITE = "Sei stato invitato a partecipare a ";


    public static Notification buildReminder(String title, LocalDate date, LocalTime time, String place, Float tee, int[] extra){
        String reminderString = "Ricordati che hai l'evento: "+title+" che si terra' "+date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALY)+" "+date.getDayOfMonth()+" "+date.getMonth().getDisplayName(TextStyle.FULL, Locale.ITALY)+ " del "+date.getYear()+" alle ore "+time.getHour()+":"+time.getMinute()+".\n";
        reminderString += "Il luogo di ritrovo e': " + place + ".\n";
        if(tee!=0 ){
            reminderString += "Ricordati di pagare: " + tee + extra[0] + extra[1] + extra[2] + " €.\n";
        }
        else {
            if(extra[0]!= 0 && extra[1]!=0 && extra[2]!=0){
                reminderString += "Ricordati di pagare: " + extra[0] + extra[1] + extra[2] + " €.\n";

            }
        }

        if(extra[0]!= 0 && extra[1]!=0 && extra[2]!=0){
            reminderString +="Di cui:";
            if( extra[0]!=0){
                reminderString += extra[0] + "€ per i Gadget\t";
            }
            if( extra[1]!=0){
                reminderString += extra[1] + "€ per il rinfresco\t";
            }
            if( extra[2]!=0){
                reminderString += extra[2] + "€ per i pasti\t";
            }
        }



        Notification reminder = new Notification(NotificationType.Reminder, reminderString);
        return reminder;
    }


    /**
     * Costruisce il messaggio della notifica per un evento nuovo
     * @param eventName nome dell'evento
     */
    public static Notification buildNotificationInvite (String eventName) {
        String message = MSG_INVITE+  eventName +".";
        return new Notification(NotificationType.Invite, message, eventName);
    }


    /**
     * Costruisce il messaggio della notifica per un evento nuovo
     * @param eventName nome dell'evento
     */
    public static Notification buildNotificationNewEvent (String eventName) {
        String message = "L'evento: " + eventName + MSG_NEW;
        return new Notification(NotificationType.Alert, message);
    }

    /**
     * Costruisce il messaggio della notifica per un evento ritirato
     * @param eventName nome dell'evento
     */
    public static Notification buildNotificationRetiredEvent (String eventName) {
        String message = eventName + MSG_RETIRED;
        return new Notification(NotificationType.Alert, message);
    }

    /**
     * Costruisce il messaggio della notifica per un evento confermato le cui iscrizioni si sono chiuse
     * @param eventName nome dell'evento
     */
    public static Notification buildNotificationClosed (String eventName) {
        String message = eventName + MSG_CLOSED_NO_DATE;

        return new Notification(NotificationType.Alert, message);
    }

    /**
     * Costruisce il messaggio della notifica per un evento fallito
     * @param eventName nome dell'evento
     */
    public static Notification buildNotificationFailed (String eventName) {
        String message = eventName + MSG_FAILED;
        return new Notification(NotificationType.Alert, message);
    }

    /**
     * Costruisce il messaggio della notifica per un evento terminato
     * @param eventName nome dell'evento
     */
    public static Notification buildNotificationTerminated (String eventName) {
        String message = eventName + MSG_TERMINATED;
        return new Notification(NotificationType.Alert, message);
    }


}
