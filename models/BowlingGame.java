package BowlingScoreboard.models;

import java.util.Comparator;
import java.util.Observable;
import java.util.PriorityQueue;

public class BowlingGame extends Observable {
    //running session info
    private boolean active = false; //is the game currently running?
    private int frame = 1; //the current frame
    private int roll = 1; //the current roll, within the frame
    private boolean[] prevState;
    private Bowler[] bowlers;
    private int currentTurn;
    private int numOfBowlers = 0;
    private final int bowlerLimit;
    PriorityQueue<Bowler> leaderboard = new PriorityQueue<>(new Comparator<Bowler>() {
        @Override
        public int compare(Bowler b1, Bowler b2) {
            return b2.getScore() - b1.getScore();
        }
    });

    public BowlingGame(int limit){
        this.bowlerLimit = limit;
        this.bowlers = new Bowler[limit];
        this.prevState = new boolean[]{true, true, true, true, true, true, true, true, true, true};
    }

    /**
     * @return number of bowlers currently participating in this session
     */
    public int getNumOfBowlers(){
        return numOfBowlers;
    }

    /**
     * @return Bowler[] of bowlers in current session
     */
    public Bowler[] getBowlers(){
        return bowlers;
    }

    /**
     * @return PriorityQueue<Bowler> in decreasing order by score
     */
    public PriorityQueue<Bowler> getLeaderboard(){
        return leaderboard;
    }

    /**
     * @return index of bowler whose turn it currently is
     */
    public int getCurrentTurn(){
        return currentTurn;
    }

    /**
     * @return maximum amount of bowlers allowed in game
     */
    public int getLimit(){
        return bowlerLimit;
    }

    /**
     * @return frame the game is currently in
     */
    public int getFrame(){
        return frame;
    }

    /**
     * @param frame number of frame to set current session's frame to
     */
    public void setFrame(int frame){
        this.frame = frame;
    }

    /**
     * @param tf true if game is starting, false if game is ending
     */
    public void setActive(boolean tf){
        this.active = tf;
        setChanged();
        notifyObservers(tf);
    }

    /**
     * @return true if a game session is currently running, false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Calculates the score of the current bowler rolling, depending on how many new pins have been knocked down
     * @param update boolean[] of new pin state
     */
    public void calculateScore(boolean[] update){
        int score = 0;
        for(int i=0; i<10; i++){
            if(!update[i] && prevState[i]) score++;
        }
        this.prevState = update.clone();
        bowlers[currentTurn].addScore(frame, roll, score);
    }

    /**
     * Moves the game session to the next roll, taking care to change frame # and roll #. Ends game if necessary.
     */
    public void next(){
        if(frame<10){ //two rolls per frames 1-9
            if(roll == 2) {
                bowlers[currentTurn].setActive(false);
                roll = 1;
                if(currentTurn == (numOfBowlers-1)){
                    currentTurn=0;
                    frame++;
                } else currentTurn++;
            } else roll++;
        } else { //three rolls in the last frame
            if (roll == 3) {
                bowlers[currentTurn].setActive(false);
                roll = 1;
                if(currentTurn == (numOfBowlers-1)) { //end condition
                    setActive(false);
                } else currentTurn++;
            } else roll++;
        }
    }

    /**
     * Updates the leaderboard, to be used after a bowler's score changes
     */
    public void updateLeaderboard(){
        leaderboard.clear();
        for(Bowler bowler:bowlers){
            if(bowler!=null) leaderboard.add(bowler);
        }
    }

    /**
     * @param bowler Bowler to include in current game session
     */
    public void addBowler(Bowler bowler){
        if(bowlers[0]==null){
            bowlers[0] = bowler;
            bowlers[0].setActive(true);
        } else{
            bowlers[numOfBowlers] = bowler;
        }
        numOfBowlers++;
        setChanged();
        notifyObservers();
    }

    /**
     * Resets game parameters to be ready for a new session
     */
    public void reset(){
        this.numOfBowlers=0;
        this.frame=1;
        this.roll=1;
        this.currentTurn=0;
        this.bowlers = new Bowler[bowlerLimit];
        setChanged();
        notifyObservers();
    }

    public int getRoll(){
        return roll;
    }
}
