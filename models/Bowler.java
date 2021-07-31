package BowlingScoreboard.models;

import java.util.Observable;

public class Bowler extends Observable {
    private String name;
    int[] scores = new int[21];
    int[] fixedScores = new int[31];
    private boolean thirdRoll = false;
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
     * @return total score so far
     */
    public int getScore(){
        return fixedScores[27];
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
        return fixedScores;
    }

    public void addScore(int frame, int roll, int score){
        int idx = (frame-1)*2 + (roll-1);
        scores[idx] = score;
        fixScores();
        setChanged();
        notifyObservers();
    }

    /**
     * Bowling-ify the raw scores of each roll
     */
    private void fixScores(){
        int runningTotal = 0;

        //first 9 frames
        for(int i=0;i<9;i++){
            int total = 0; //this frame's total score
            int idx = i*2; //idx of first roll
            int firstRoll = scores[idx];
            int secondRoll = scores[idx+1];

            if(firstRoll==10){ // strike condition
                if(scores[idx+2]==10 && scores[idx+4]==10){
                    total = 30;
                } else if(scores[idx+2]==10){
                    total = 20 + scores[idx+4] + scores[idx+5];
                } else{
                    total += firstRoll+scores[idx+2]+scores[idx+3];
                }
            } else if((firstRoll+secondRoll)==10){ // spare condition
                total = 10 + scores[idx+2];
            } else {
                total += firstRoll+secondRoll;
            }
            runningTotal+=total;
            fixedScores[i*3] = runningTotal;
            fixedScores[(i*3)+1] = firstRoll;
            fixedScores[(i*3)+2] = secondRoll;
        }
        //last frame
        int firstRoll = scores[18]; int secondRoll = scores[19]; int thirdRoll = scores[20];
        fixedScores[27] = runningTotal+firstRoll+secondRoll+thirdRoll;
        fixedScores[28] = firstRoll;
        fixedScores[29] = secondRoll;
        fixedScores[30] = thirdRoll;
        if(firstRoll==10 || (firstRoll+secondRoll)==10) this.thirdRoll=true;
    }

    public boolean hasThirdRoll(){
        return thirdRoll;
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
