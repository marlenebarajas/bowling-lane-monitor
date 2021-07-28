package BowlingScoreboard.views;

import BowlingScoreboard.views.FrameView;

import javax.swing.*;

public class FramesView extends JPanel {
    FrameView[] frames = new FrameView[10];

    public FramesView(){
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        initialize();
        render();
    }

    /**
     * Displays frame scorecard
     */
    private void render(){
        removeAll();
        for(FrameView frame:frames){
            add(frame);
        }
        revalidate();
        repaint();
    }

    /**
     * Initializes 10 empty frame on the scorecard
     */
    private void initialize(){
        for(int i=0; i<9; i++){
            FrameView frame = new FrameView(false);
            frames[i] = frame;
        }
        //tenth frame has three rolls
        FrameView frame = new FrameView(true);
        add(frame);
        frames[9] = frame;
    }

    /**
     * Updates the display frame score on the scorecard
     * @param frame (n-1)th frame in frames[], to be updated
     * @param roll int 1, 2, or 3
     * @param score int point value of most recent roll
     */
    public void setFrame(int frame, int roll, int score){
        frames[frame-1].addRoll(roll, score);
        render();
    }
}
