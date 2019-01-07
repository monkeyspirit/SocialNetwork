package versione1;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class User implements Observer {

    //Attributi
    private String username;
//    private ArrayList<Notification> notifications;
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

    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        notifications.add(message);
    }


//    // Da usare con Notification e NotificationHandler
//    public ArrayList<Notification> getNotifications() { return this.notifications; }
//
//    public void setNotifications(ArrayList<Notification> notifications) { this.notifications = notifications; }
//
//    public ArrayList<String> getNotificationsMessage() {
//        ArrayList<String> message = new ArrayList<>();
//
//
//        for(int i=0; i< notifications.size(); i++){
//            message.add(notifications.get(i).getMessage());
//        }
//
//        return message;
//    }


    public ArrayList<String> getNotifications() { return notifications; }
}
