package BowlingScoreboard.models;

import java.util.Observable;

public class Bowler extends Observable {
    private String name;
    private int score = 0;
    private boolean active = false;

    public Bowler(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    /**
     * Updates BowlerView to correctly display frame information
     * @param score
     */
    public void addRoll(int frame, int roll, int score){
        this.score += score;
        setChanged();
        notifyObservers(new int[]{frame, roll, score});
    }
    public int getScore(){
        return score;
    }

    public void setActive(boolean tf){
        this.active = tf;
    }

    /**
     * Specifies whether Bowler is still actively participating in the bowling game.
     * @return True if Bowler is still in bowling session, False otherwise
     */
    public boolean isActive(){
        return active;
    }
}
