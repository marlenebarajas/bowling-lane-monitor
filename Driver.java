package BowlingScoreboard;

public class Driver {
    public static void main(String[] args) {
        SessionView gui = new SessionView(BowlingSession.getInstance(8));
    }
}
