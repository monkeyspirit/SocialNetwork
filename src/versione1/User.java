package versione1;
import versione2.notifications.Notification;

import java.util.ArrayList;
import java.util.List;
public class User{

    //Attributi
    private String username;
    private List<Notification> notifications;
    private String ageRange;
    private List<String> categoryPref;

    public User(){
    }

    /**
     * Costruttore User: inizializza il valore dell'username scelto dall'utente
     * @param username
     */
    public User(String username) {
        this.username = username;
        this.notifications = new ArrayList<>();
        this.categoryPref = new ArrayList<>();
        this.ageRange = "";
    }

    //Metodi
    public String getUsername() {
        return username;
    }

    public List<Notification> getNotifications() { return notifications; }

    public void addNotification(Notification notificationToAdd){ notifications.add(notificationToAdd); }

    public List<String> getNotificationsMessages() {
        List<String> notificationsMessages = new ArrayList<>();
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

    public List<String> getCategoryPref() { return categoryPref; }

    public void setCategoryPref(List<String> categoryPref) {
        this.categoryPref = categoryPref;
    }

    public void addCategoryPref(String categoryName) {
        this.categoryPref.add(categoryName);
    }


}
