package BowlingScoreboard.views;

import javax.swing.*;
import java.awt.*;

public class FrameView extends JPanel {

    /**
     * Constructor with only two roll-score spaces, for frames 1-9
     * @param total Bowler's total score
     */
    public FrameView(int total){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(Box.createHorizontalGlue());
        setPreferredSize(new Dimension(50, 50));
        setMinimumSize(new Dimension(50, 50));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2,2,2,2, Color.black),
                BorderFactory.createEmptyBorder(20,2,15,2)
                ));

        add(new JLabel(String.valueOf(total)), BorderLayout.PAGE_END);
        add(Box.createHorizontalGlue());
    }

    /**
     * Constructor with only two roll-score spaces, for frames 1-9
     * @param first score obtained for first roll
     * @param second score obtained for second roll
     */
    public FrameView(int first, int second){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(Box.createHorizontalGlue());
        setPreferredSize(new Dimension(50, 50));
        setMinimumSize(new Dimension(50, 50));
        setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.black));

        int total = first+second;
        add(new JLabel(String.valueOf(total)), BorderLayout.PAGE_END);
        add(Box.createHorizontalGlue());
        add(createRollBox(first));
        add(createRollBox(second));
    }

    /**
     * Constructor with only three roll-score spaces, for frame 10
     * @param first score obtained for first roll
     * @param second score obtained for second roll
     * @param third score obtained for third roll
     */
    public FrameView(int first, int second, int third){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(Box.createHorizontalGlue());
        setPreferredSize(new Dimension(50, 50));
        setMinimumSize(new Dimension(50, 50));
        setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.black));

        int total = first+second+third;
        add(new JLabel(String.valueOf(total)), BorderLayout.PAGE_END);
        add(Box.createHorizontalGlue());
        add(createRollBox(first));
        add(createRollBox(second));
        add(createRollBox(third));
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
}
