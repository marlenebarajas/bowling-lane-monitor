package BowlingScoreboard.models;

import java.util.Observable;

public class PinState extends Observable {
    private static PinState pinState = null;
    boolean[] state;

    private PinState(){
        //connect to hardware?
        //would ensure hardware is at the appropriate state to start game
        this.state = new boolean[]{true, true, true, true, true, true, true, true, true, true};
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
     * Takes in new state of pins in the lane after a roll and calculates the score earned
     * @param update array of 10 booleans defining whether n-th pin is standing
     */
    public void roll(boolean[] update){
        int score = 0;
        for(int i=0; i<10; i++){
            boolean pin = update[i];
            if(!pin){
                if(state[i]) score++;
            }
        }
        this.state = update.clone();
        setChanged();
        notifyObservers(score);
    }

    /**
     * Resets pin state
     */
    public void reset(){
        this.state = new boolean[]{true, true, true, true, true, true, true, true, true, true};
    }
}
