package BowlingScoreboard.views;

import BowlingScoreboard.models.Bowler;

import javax.swing.*;
import java.awt.*;

/**
 * Renders individual bowler information, including name and frame scores.
 */
public class BowlerView extends JPanel{
    Bowler bowler;
    Component name;
    FramesView frames;

    public BowlerView(int idx, Bowler bowler){
        this.bowler = bowler;
        name = createBowlerLabel(idx, bowler);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        render();
    }

    private void render(){
        removeAll();
        add(name);
        frames = new FramesView(bowler.getScores(), bowler.getScore());
        add(frames);
        revalidate();
        repaint();
    }

    private Component createBowlerLabel(int idx, Bowler bowler){
        JLabel name = new JLabel((idx+1)+" | "+bowler.getName());

        Box box = Box.createHorizontalBox();
        box.add(name);
        box.add(Box.createHorizontalGlue());
        box.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));

        return box;
    }

}
