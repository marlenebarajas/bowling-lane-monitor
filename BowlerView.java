package BowlingScoreboard;

import javax.swing.*;
import java.awt.*;

public class BowlerView extends JPanel {
    public BowlerView(int idx, Bowler bowler){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(createBowlerLabel(idx, bowler));
        add(new FramesView());
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
