package versione2.notifications;

import java.time.LocalDate;

/**
 * Questa classe ha il compito di costruire le notifiche attraverso un tipo e una stringa.
 * Nasce dal fatto che un Promemoria viene gestito diversamente dalla Notifica di chiusura o fallimento o completamento di un evento.
 *
 *
 * Penso tornerà utile dalla versione 3/4 in poi
 */
public class Notification {

    private NotificationType notificationType;
    private String notificationMessage;
    private LocalDate notificationDate;
    private String eventName;

    public Notification(NotificationType type, String message){
        this.notificationType = type;
        this.notificationMessage = message;
        this.notificationDate = LocalDate.now();
    }

    // Costruttore che è utile nel caso degli inviti
    public Notification(NotificationType type, String message, String eventName){
        this.notificationType = type;
        this.notificationMessage = message;
        this.eventName = eventName;
        this.notificationDate = LocalDate.now();
    }


    public NotificationType getNotificationType() { return notificationType; }

    public String getNotificationMessage() { return notificationMessage; }

    public LocalDate getNotificationDate() { return notificationDate; }

    public String getEventName() { return eventName; }
}
