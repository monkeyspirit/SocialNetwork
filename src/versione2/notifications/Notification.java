package versione2.notifications;

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

    public Notification(NotificationType type, String message){
        this.notificationType = type;
        this.notificationMessage = message;
    }

    public NotificationType getNotificationType() { return notificationType; }

    public String getNotificationMessage() { return notificationMessage; }
}
