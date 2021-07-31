package BowlingScoreboard.views;

import javax.swing.*;
import java.awt.*;

public class FrameView extends JPanel {

    /**
     * Constructor for a frame with only the total score
     * @param total Bowler's total score
     */
    public FrameView(int total){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(Box.createHorizontalGlue());
        setPreferredSize(new Dimension(50, 50));
        setMinimumSize(new Dimension(50, 50));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2,2,2,2, Color.black),
                BorderFactory.createEmptyBorder(20,0,15,0)
                ));

        add(new JLabel(String.valueOf(total)), BorderLayout.PAGE_END);
        add(Box.createHorizontalGlue());
    }

    /**
     * Constructor with only two roll-score spaces, for frames 1-9
     * @param total score obtained in this frame
     * @param first score obtained for first roll
     * @param second score obtained for second roll
     */
    public FrameView(int total, int first, int second){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(Box.createHorizontalGlue());
        setPreferredSize(new Dimension(50, 50));
        setMinimumSize(new Dimension(50, 50));
        setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.black));

        if(total==0) add(new JLabel(" "), BorderLayout.PAGE_END);
        else add(new JLabel(total+" "), BorderLayout.PAGE_END);

        if(first==10){// strike condition
            add(createRollBox("X"), BorderLayout.LINE_END);
            add(createRollBox(second), BorderLayout.LINE_END);
        } else if((first+second)==10){// spare condition
            add(createRollBox(first), BorderLayout.LINE_END);
            add(createRollBox("/"), BorderLayout.LINE_END);
        }
        else{
            add(createRollBox(first), BorderLayout.LINE_END);
            add(createRollBox(second), BorderLayout.LINE_END);
        }
    }

    /**
     * Constructor with only three roll-score spaces, for frame 10
     * @param total score obtained in this frame
     * @param first score obtained for first roll
     * @param second score obtained for second roll
     * @param third score obtained for third roll
     */
    public FrameView(int total, int first, int second, int third){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(Box.createHorizontalGlue());
        setPreferredSize(new Dimension(70, 50));
        setMinimumSize(new Dimension(70, 50));
        setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.black));

        if(total==0) add(new JLabel(" "), BorderLayout.PAGE_END);
        else add(new JLabel(total+" "), BorderLayout.PAGE_END);

        // check strike/spare conditions
        if(first==10) add(createRollBox("X"), BorderLayout.LINE_END);
        else add(createRollBox(first));

        if(second==10){
            if((first+second)==10) add(createRollBox("/"), BorderLayout.LINE_END);
            else add(createRollBox("X"), BorderLayout.LINE_END);
        } else if((first+second)==10){
            add(createRollBox(first), BorderLayout.LINE_END);
            add(createRollBox("/"), BorderLayout.LINE_END);
        } else{
            add(createRollBox(second), BorderLayout.LINE_END);
        }

        if(third==10){
            add(createRollBox("X"), BorderLayout.LINE_END);
        } else{
            add(createRollBox(third), BorderLayout.LINE_END);
        }
    }

    /**
     * Creates a Box Component that will display the score received from a roll
     * @param score points received for this roll
     * @return Box with appropriate score information
     */
    private Component createRollBox(int score){
        Box roll = Box.createHorizontalBox();
        roll.add(Box.createVerticalGlue());
        if(score==0) roll.add(new JLabel(" "));
        else roll.add(new JLabel(" "+score));
        roll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0,0,25,0),
                BorderFactory.createMatteBorder(1,1,1,1, Color.black)
        ));
        roll.setPreferredSize(new Dimension(20, 20));
        return roll;
    }

    /**
     * Creates a Box Component that will display the score received from a roll
     * @param s "/" or "X"
     * @return Box with appropriate score information
     */
    private Component createRollBox(String s){
        Box roll = Box.createHorizontalBox();
        roll.add(Box.createVerticalGlue());
        roll.add(new JLabel(" "+s));
        roll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0,0,25,0),
                BorderFactory.createMatteBorder(1,1,1,1, Color.black)
        ));
        roll.setPreferredSize(new Dimension(20, 20));
        return roll;
    }
}
