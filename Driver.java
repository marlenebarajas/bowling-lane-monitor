package BowlingScoreboard;

import BowlingScoreboard.models.BowlingSession;
import BowlingScoreboard.views.SessionView;

public class Driver {
    public static void main(String[] args) {
        SessionView gui = new SessionView(BowlingSession.getInstance(8));
    }
}
