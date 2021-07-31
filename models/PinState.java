package BowlingScoreboard.models;

import BowlingScoreboard.controllers.SessionController;

import java.util.Observable;
import java.util.Random;

public class PinState extends Observable {
    private static PinState pinState = null;
    SessionController controller;
    boolean[] state;

    private PinState(){
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

    public void setController(SessionController controller){
        this.controller = controller;
    }

    public boolean[] getState(){
        return state;
    }

    /**
     * Resets pin state
     */
    public void reset(){
        roll(new boolean[]{true, true, true, true, true, true, true, true, true, true});
    }

    /**
     * Takes in new state of pins after a roll
     * @param update array of 10 booleans defining whether n-th pin is standing
     */
    public void roll(boolean[] update){
        this.state = update.clone();
        setChanged();
        notifyObservers(update);
    }

    /**
     * Randomly changes current state for a game simulation
     */
    public void runSimulated(){
        //            delay to mimic hardware delay
//        long startTime = System.currentTimeMillis();
//        long endTime = System.currentTimeMillis();
//        while((endTime-startTime)<2000){
//            endTime = System.currentTimeMillis();
//        }
        roll(randomRoll());
    }

    /**
     * Creates a random state change
     * @return a random, valid boolean[] state change
     */
    private boolean[] randomRoll(){
        boolean[] newState = getState();
        Random rnd = new Random();
        int changes = rnd.nextInt(11); //0-10
        int idx = rnd.nextInt(10);
        for(int i=0;i<changes;i++){
            if(state[idx]){
                newState[idx] = false;
            }
            if(idx==9) idx=0;
            else idx++;
        }
        return newState;
    }
}
