package versione5;

public class Member {

    private String username;
    private int[] extra;

    public Member(String username, int[] extra) {
        this.username = username;
        this.extra = extra;
    }

    public String getUsername() {
        return username;
    }

    public int[] getExtra() {
        return extra;
    }

}
