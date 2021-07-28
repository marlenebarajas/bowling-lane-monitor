package BowlingScoreboard.views;

import javax.swing.*;
import java.awt.*;

public class FrameView extends JPanel {
    Component firstRoll;
    Component secondRoll;
    Component thirdRoll;
    boolean hasThirdRoll;

    public FrameView(boolean tenth){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(Box.createHorizontalGlue());
        setPreferredSize(new Dimension(50, 50));
        setMinimumSize(new Dimension(50, 50));
        setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.black));

        this.hasThirdRoll = tenth;
        initialize();
        render();
    }

    /**
     * Adds 2 empty roll-score boxes to the frame; adds 3 if this.FrameView represents the 10th frame
     */
    private void initialize(){
        this.firstRoll = createRollBox(0);
        this.secondRoll = createRollBox(0);
        if(hasThirdRoll()) this.thirdRoll = createRollBox(0);
    }

    /**
     * Displays FrameView
     */
    private void render(){
        removeAll();
        add(Box.createHorizontalGlue());
        add(firstRoll);
        add(secondRoll);
        if(hasThirdRoll) add(thirdRoll);
        revalidate();
        repaint();
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
     * Updates the score of an empty roll box and renders to updated information
     */
    public void addRoll(int roll, int score){
        switch(roll){
            case 1:
                firstRoll = createRollBox(score);
                break;
            case 2:
                secondRoll = createRollBox(score);
                break;
            case 3:
                if(hasThirdRoll()) thirdRoll = createRollBox(score);
                break;
        }
        render();
    }

    /**
     * @return true if this.FrameView represents the 10th frame, false otherwise
     */
    public boolean hasThirdRoll(){
        return hasThirdRoll;
    }
}
