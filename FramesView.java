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
        for(int i=0; i<10; i++){
            JPanel frame = new JPanel();
            frame.setLayout(new BoxLayout(frame, BoxLayout.LINE_AXIS));
            frame.add(Box.createHorizontalGlue());
            frame.setPreferredSize(new Dimension(50, 50));
            frame.setMinimumSize(new Dimension(50, 50));
            frame.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.black));

            Box firstRoll = Box.createHorizontalBox();
            firstRoll.add(Box.createVerticalGlue());
            firstRoll.add(new JLabel(" "));
            firstRoll.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0,0,25,0),
                    BorderFactory.createMatteBorder(1,1,1,1, Color.black)
            ));
            firstRoll.setPreferredSize(new Dimension(20, 20));

            Box secondRoll = Box.createHorizontalBox();
            secondRoll.add(Box.createVerticalGlue());
            secondRoll.add(new JLabel(" "));
            secondRoll.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0,0,25,0),
                    BorderFactory.createMatteBorder(1,1,1,1, Color.black)
            ));
            secondRoll.setPreferredSize(new Dimension(20, 20));

            frame.add(firstRoll);
            frame.add(secondRoll);
            frames[i] = frame;
        }

    }

    /**
     *
     * @param frame (n-1)th frame in frames[], to be updated
     * @param firstScore
     * @param secondScore
     */
    private void setFrame(int frame, int firstScore, int secondScore){
        JPanel updatedFrame = new JPanel();
        updatedFrame.setLayout(new BoxLayout(updatedFrame, BoxLayout.LINE_AXIS));
        updatedFrame.add(Box.createHorizontalGlue());
        updatedFrame.setPreferredSize(new Dimension(50, 50));
        updatedFrame.setMinimumSize(new Dimension(50, 50));
        updatedFrame.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.black));

        Box firstRoll = Box.createHorizontalBox();
        firstRoll.add(Box.createVerticalGlue());
        if(firstScore!=0){
            firstRoll.add(new JLabel(" "+firstScore));
        } else{
            firstRoll.add(new JLabel(" "));
        }

        firstRoll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0,0,25,0),
                BorderFactory.createMatteBorder(1,1,1,1, Color.black)
        ));
        firstRoll.setPreferredSize(new Dimension(20, 20));

        Box secondRoll = Box.createHorizontalBox();
        secondRoll.add(Box.createVerticalGlue());
        if(secondScore!=0){
            secondRoll.add(new JLabel(" "+secondScore));
        } else{
            secondRoll.add(new JLabel(" "));
        }
        secondRoll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0,0,25,0),
                BorderFactory.createMatteBorder(1,1,1,1, Color.black)
        ));
        secondRoll.setPreferredSize(new Dimension(20, 20));

        updatedFrame.add(firstRoll);
        updatedFrame.add(secondRoll);
        frames[frame-1] = updatedFrame;
        render();
    }
}
