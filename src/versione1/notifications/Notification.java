package versione1.notifications;

public class Notification {

    private String message;
    private boolean read; // per distinguere notifiche gia' lette e non

    public Notification(String message) {
        this.message = message;
        read = false;
    }

    public void setMessage (String message) {
        this.message = message;
    }

    public void setRead (boolean read) {
        this.read = read;
    }

    public String getMessage() {
        return message;
    }

    public boolean getRead () {
        return read;
    }
}
