package versione1;

import versione2.notifications.Notification;

import java.util.ArrayList;

public class User {

    //Attributi
    private String username;
    private ArrayList<Notification> notifications;

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


    public ArrayList<String> getNotificationsMessage() {
        ArrayList<String> message = new ArrayList<>();


        for(int i=0; i< notifications.size(); i++){
            message.add(notifications.get(i).getMessage());
        }

        return message;
    }
}
