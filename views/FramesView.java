package BowlingScoreboard.views;

import javax.swing.*;

/**
 * Creates a bowling scorecard using a bowler's scores
 */
public class FramesView extends JPanel {
    FrameView[] frames = new FrameView[11];
    int[] scores; int totalScore;

    /**
     * @param scores scores[20] corresponding to scores reached in every roll
     */
    public FramesView(int[] scores, int total){
        this.scores = scores;
        this.totalScore = total;
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        render();
    }

    private void render(){
        removeAll();
        createFrames();
        for(FrameView frame:frames){
            add(frame);
        }
        revalidate();
        repaint();
    }

    /**
     * Creates FrameView for each frame in scorecard and populates FrameView[]
     */
    private void createFrames(){
        for(int i=0; i<9; i++){
            int firstRoll = scores[i*2];
            int secondRoll = scores[(i*2)+1];
            frames[i] = new FrameView(firstRoll, secondRoll);
        }
        //tenth frame has three rolls
        int firstRoll = scores[18]; int secondRoll = scores[19]; int thirdRoll = scores[20];
        frames[9] = new FrameView(firstRoll, secondRoll, thirdRoll);
        //total scores
        frames[10] = new FrameView(totalScore);
    }
}
