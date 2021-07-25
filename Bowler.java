package BowlingScoreboard;

import java.util.Observable;

public class Bowler extends Observable {
    private String name;
    private int score = 0;
    private boolean active = true;

    public Bowler(String name){
        this.name = name;
    }

    private void setName(String name){
        this.name = name;
    }

    private String getName(){
        return name;
    }

    private void setScore(int score){
        this.score = score;
        setChanged();
        notifyObservers();
    }
    private int getScore(){
        return score;
    }

    private void deactivate(){
        this.active = false;
    }

    /**
     * Specifies whether Bowler is still actively participating in the bowling game.
     * @return True if Bowler is still in bowling session, False otherwise
     */
    private boolean isActive(){
        return active;
    }
}
