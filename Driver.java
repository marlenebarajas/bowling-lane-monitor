package BowlingScoreboard;

import BowlingScoreboard.controllers.SessionController;
import BowlingScoreboard.models.BowlingGame;
import BowlingScoreboard.models.PinState;
import BowlingScoreboard.views.SessionView;

public class Driver {
    public static void main(String[] args) {
        SessionController controller = new SessionController(PinState.getInstance(), new BowlingGame(8));
        new SessionView(controller);
    }
}
