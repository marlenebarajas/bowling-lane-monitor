package BowlingScoreboard.views;

import javax.swing.*;

/**
 * Creates a bowling scorecard using a bowler's scores
 */
public class FramesView extends JPanel {
    FrameView[] frames = new FrameView[11];
    int[] scores;

    /**
     * @param scores int[31] where frame total is followed by roll scores
     *               ex. scores[0] = total score for frame 1
     *               scores[1] = first roll's score
     *               scores[2] = second roll's score
     */
    public FramesView(int[] scores){
        this.scores = scores;
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
        //first 9 frames
        for(int i=0;i<9;i++){
            int idx = (i*3); // index first value of this frame
            frames[i] = new FrameView(scores[idx], scores[idx+1], scores[idx+2]);
        }
        //tenth frame has three rolls
        frames[9] = new FrameView(scores[27], scores[28], scores[29], scores[30]);

        //total scores
        frames[10] = new FrameView(scores[27]);
    }
}
