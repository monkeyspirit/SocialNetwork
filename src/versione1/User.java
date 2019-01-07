package versione1;

import sample.NotificationController;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class User implements Observer {

    //Attributi
    private String username;
    private ArrayList<String> notifications;

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

    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        notifications.add(message);

    }

    public void removeNotification(int notificationRemove){
            notifications.remove(notificationRemove);
    }



}
