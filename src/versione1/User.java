package versione1;

import versione1.notifications.Notification;

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
    }

    //Metodi
    public String getUsername() {
        return username;
    }

    public String toString(){
        return "Username: " + this.username;
    }
}
