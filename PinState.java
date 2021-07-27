package BowlingScoreboard;

import java.util.Observable;

public class PinState extends Observable {
    private static PinState pinState = null;
    Boolean[] state;

    private PinState(){
        //connect to hardware?
        //would ensure hardware is at the appropriate state to start game
        this.state = new Boolean[]{true, true, true, true, true, true, true, true, true, true};
    }

    public static PinState getInstance(){
        if (pinState == null) {
            synchronized (PinState.class) {
                if (pinState == null) {
                    pinState = new PinState();
                }
            }
        }
        return pinState;
    }

    /**
     * Takes the new state of pins in the lane after a roll
     * @param update array of 10 booleans defining whether n-th pin is standing
     */
    private void roll(Boolean[] update){
        this.state = update.clone();
        setChanged();
        notifyObservers();
    }
}
