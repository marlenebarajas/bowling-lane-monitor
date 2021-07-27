package BowlingScoreboard;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BowlerView extends JPanel implements Observer {
    FramesView frames = new FramesView();

    public BowlerView(int idx, Bowler bowler){
        bowler.addObserver(this);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(createBowlerLabel(idx, bowler));
        add(frames);
    }

    private Component createBowlerLabel(int idx, Bowler bowler){
        JLabel name = new JLabel((idx+1)+" | "+bowler.getName());

        Box box = Box.createHorizontalBox();
        box.add(name);
        box.add(Box.createHorizontalGlue());
        box.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));

        return box;
    }

    /**
     * Called when Bowler's score changes and frame information needs updating
     * @param o Bowler
     * @param arg int[3], where 0 = frame the roll was taken in, 1 = (first/second/third) roll, 2 = score received from the roll
     */
    @Override
    public void update(Observable o, Object arg) {
        int[] params = (int[]) arg;
        frames.setFrame(params[0],params[1],params[2]);
    }
}
