package BowlingScoreboard.models;

import java.util.Observable;

public class Bowler extends Observable {
    private String name;
    private int score = 0;
    int[] scores = new int[21];
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

    public int getScore(){
        return score;
    }

    /**
     * Finds the score of a specific frame and roll
     * @param frame 1-10
     * @param roll 1-3
     * @return points scored
     */
    public int getScore(int frame, int roll){
        int idx = (frame-1)*2 + (roll-1);
        return scores[idx];
    }

    public int[] getScores(){
        return scores;
    }

    public void addScore(int frame, int roll, int score){
        int idx = (frame-1)*2 + (roll-1);
        scores[idx] = score;
        this.score += score;
        setChanged();
        notifyObservers();
    }


    public void setActive(boolean tf){
        this.active = tf;
        setChanged();
        notifyObservers();
    }

    /**
     * Specifies whether Bowler is still actively participating in the bowling game
     * @return True if Bowler is still in bowling session, False otherwise
     */
    public boolean isActive(){
        return active;
    }
}
