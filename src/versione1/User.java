package versione1;

import sample.NotificationController;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class User{

    //Attributi
    private String username;
    private ArrayList<String> notifications;

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

    public ArrayList<String> getNotifications() { return notifications; }

    public void addNotification(String notificationToAdd){ notifications.add(notificationToAdd); }


    public void removeNotification(int notificationRemove){
            notifications.remove(notificationRemove);
    }



}
