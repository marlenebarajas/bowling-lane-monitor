package BowlingScoreboard;

import javax.swing.*;
import java.awt.*;

public class FramesView extends JPanel {
    JPanel[] frames = new JPanel[10];

    public FramesView(){
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        initialize();
        render();
    }

    private void render(){
        removeAll();
        for(JPanel frame:frames){
            add(frame);
        }
        revalidate();
        repaint();
    }

    private void initialize(){
        for(int i=0; i<9; i++){
            JPanel frame = createFrame();
            addRollBox(frame, 0);
            addRollBox(frame, 0);
            frames[i] = frame;
        }
        //tenth frame has three rolls
        JPanel frame = createFrame();
        addRollBox(frame, 0);
        addRollBox(frame, 0);
        addRollBox(frame, 0);
        frames[9] = frame;
    }

    private JPanel createFrame(){
        JPanel frame = new JPanel();
        frame.setLayout(new BoxLayout(frame, BoxLayout.LINE_AXIS));
        frame.add(Box.createHorizontalGlue());
        frame.setPreferredSize(new Dimension(50, 50));
        frame.setMinimumSize(new Dimension(50, 50));
        frame.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.black));

        return frame;
    }

    private void addRollBox(JPanel frame, int score){
        Box roll = Box.createHorizontalBox();
        roll.add(Box.createVerticalGlue());
        if(score==0) roll.add(new JLabel(" "));
        else roll.add(new JLabel(" "+score));
        roll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0,0,25,0),
                BorderFactory.createMatteBorder(1,1,1,1, Color.black)
        ));
        roll.setPreferredSize(new Dimension(20, 20));
        frame.add(roll);
    }

    /**
     *
     * @param frame (n-1)th frame in frames[], to be updated
     * @param roll int 1, 2, or 3
     * @param score int point value of most recent roll
     */
    public void setFrame(int frame, int roll, int score){
        JPanel updatedFrame = createFrame();
        if(frame!=10){
            if(roll==1){
                addRollBox(updatedFrame, score);
                addRollBox(updatedFrame, 0);
            } else{
                addRollBox(updatedFrame, 0);
                addRollBox(updatedFrame, score);
            }
            frames[frame-1] = updatedFrame;
        }else{
            if(roll==1){
                addRollBox(updatedFrame, score);
                addRollBox(updatedFrame, 0);
                addRollBox(updatedFrame, 0);
            }else if(roll==2){
                addRollBox(updatedFrame, 0);
                addRollBox(updatedFrame, score);
                addRollBox(updatedFrame, 0);
            }else{
                addRollBox(updatedFrame, 0);
                addRollBox(updatedFrame, 0);
                addRollBox(updatedFrame, score);
            }
            frames[9] = updatedFrame;
        }
        render();
    }
}
