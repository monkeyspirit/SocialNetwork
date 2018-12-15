package versione1;

public class User {

    //Attributi
    private String username;

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
