package versione1;

import sample.NotificationController;
import versione2.notifications.Notification;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class User{

    //Attributi
    private String username;
    private ArrayList<Notification> notifications;
    private String ageRange;
    private ArrayList<String> categoryPref;

    public User(){
    }

    /**
     * Costruttore User: inizializza il valore dell'username scelto dall'utente
     * @param username
     */
    public User(String username) {
        this.username = username;
        notifications = new ArrayList<>();
    }

    //Metodi
    public String getUsername() {
        return username;
    }

    public ArrayList<Notification> getNotifications() { return notifications; }

    public void addNotification(Notification notificationToAdd){ notifications.add(notificationToAdd); }

    public ArrayList<String> getNotificationsMessages() {
        ArrayList<String> notificationsMessages = new ArrayList<>();
        for(Notification notification: notifications){
            notificationsMessages.add(notification.getNotificationMessage());
        }
        return notificationsMessages;
    }

    public void removeNotification(int notificationRemove){
            notifications.remove(notificationRemove);
    }

    public String getAgeRange() { return ageRange; }

    public void setAgeRange(String ageRange) { this.ageRange = ageRange; }

    public ArrayList<String> getCategoryPref() { return categoryPref; }

    public void setCategoryPref(ArrayList<String> categoryPref) { this.categoryPref = categoryPref; }


}
