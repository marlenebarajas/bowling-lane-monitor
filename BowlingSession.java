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
    private int frame = 1; //the current frame
    private int roll = 1; //the current roll, within the frame
    private int bowlerTurn = 0; // idx of what bowler is waiting to roll


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

    public boolean isActive() {
        return active;
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

    /**
     * Forces changes to the hardware state. This method is only called in simulations
     * @param update boolean[] with desired state changed
     */
    public void setState(boolean[] update){
        pins.roll(update);
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

    public int getNumOfBowlers(){
        return numOfBowlers;
    }

    public void addBowler(Bowler bowler){
        bowlers[numOfBowlers] = bowler;
        ++numOfBowlers;
        //potentially update, for bowlersView?
        setChanged();
        notifyObservers(false);
    }

    /** NEEDS LOOKED AT
     * Ends game and decides winner
     */
    public void finish(){
        //calculate winner?
    }

    /**
     * Resets session info to defaults
     */
    public void reset(){
        pins.reset();
        this.numOfBowlers=0;
        this.frame=1;
        this.roll=1;
        this.bowlerTurn=0;
        bowlers = new Bowler[bowlerLimit];
        setActive(false);
    }

    /**
     * Called when PinState changes, signaling that a roll was thrown.
     * @param o PinState
     * @param arg int with the score the most recent roll received
     */
    @Override
    public void update(Observable o, Object arg) {
        if(arg!=null){
            bowlers[bowlerTurn].addRoll(frame, roll, (Integer) arg);
            if(frame<10){ //two rolls per frame
                if(roll==2) {
                    roll=1;
                    pins.reset();
                    if(bowlerTurn==(numOfBowlers-1)){
                        bowlerTurn=0;
                        frame++;
                    } else bowlerTurn++;
                } else roll++;
            } else{ //three rolls in the last frame
                if(roll==3) {
                    roll = 1;
                    pins.reset();
                    if (bowlerTurn == (numOfBowlers-1)) {
                        //end game
                        finish();
                        reset();
                    } else bowlerTurn++;
                } else roll++;
            }
        }
    }
}
