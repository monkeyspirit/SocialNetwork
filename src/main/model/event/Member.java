package main.model.event;

/**
 * Classe che rappresenta le voci di spesa extra che un utente si impegna a pagare per un
 * evento
 */
public class Member {

    private String username;
    private float[] extra;

    public Member(String username, float[] extra) {
        this.username = username;
        this.extra = extra;
    }

    public String getUsername() {
        return username;
    }

    public float[] getExtra() {
        return extra;
    }

}
