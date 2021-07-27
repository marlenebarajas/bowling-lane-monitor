package BowlingScoreboard;

import java.util.Observable;
import java.util.Observer;

public class BowlingSession extends Observable implements Observer {
    //session parameters
    private static BowlingSession session = null;
    private static final PinState pins = PinState.getInstance();
    private static Bowler[] bowlers;
    private int bowlerLimit = 8;

    //running session info
    private boolean active = false; //is the game currently running?
    private int numOfBowlers = 0; //how many players in session
    private int frame; //the current frame
    private int roll; //the current roll, within the frame
    private int bowlerTurn; // idx of what bowler is waiting to roll


    public BowlingSession(int limit){
        pins.addObserver(this);
        this.bowlerLimit = limit;
        bowlers = new Bowler[bowlerLimit];
    }

    public static BowlingSession getInstance(int limit){
        if (session == null) {
            synchronized (BowlingSession.class) {
                if (session == null) {
                    session = new BowlingSession(limit);
                }
            }
        }
        return session;
    }

    public void setActive(boolean tf){
        this.active = tf;
        setChanged();
        notifyObservers(tf);
    }

    public void setFrame(int frame){
        this.frame = frame;
    }

    public void setBowlerLimit(int limit){
        this.bowlerLimit = limit;
    }

    public boolean[] getState(){
        return pins.state.clone();
    }

    public int getBowlerLimit(){
        return bowlerLimit;
    }

    public Bowler[] getBowlers(){
        return bowlers;
    }

    public void addBowler(Bowler bowler){
        bowlers[numOfBowlers] = bowler;
        ++numOfBowlers;
        //potentially update, for bowlersView?
        setChanged();
        notifyObservers(false);
    }

    /**
     * Ends game and decides winner
     */
    public void finish(){

    }

    /**
     * Resets session info to defaults
     */
    public void reset(){
        //remember to reset pinstate
        pins.reset();
        this.numOfBowlers=0;
        bowlers = new Bowler[bowlerLimit];
    }

    /**
     * Called when PinState changes, signaling that a roll was thrown.
     * @param o PinState
     * @param arg int with the score the most recent roll received
     */
    @Override
    public void update(Observable o, Object arg) {
        if(arg!=null) bowlers[bowlerTurn].addRoll(frame, roll, (Integer) arg);
    }
}
