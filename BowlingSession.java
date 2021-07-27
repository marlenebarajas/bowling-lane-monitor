package BowlingScoreboard;

import javax.swing.*;
import java.awt.*;

public class BowlingSession {
    //session parameters
    private static BowlingSession session = null;
    private static PinState pins = PinState.getInstance();;
    private static Bowler[] bowlers;
    private static int bowlerLimit;

    //running session info
    private boolean active;
    private int numOfBowlers;
    private int frame;
    private int bowlerTurn;

    public BowlingSession(int limit){
        bowlerLimit = limit;
        bowlers = new Bowler[bowlerLimit];
        createAndShowGUI();
    }

    public static BowlingSession getInstance(int limit){
        if (session == null) {
            synchronized (BowlingSession.class) {
                if (session == null) {
                    session = new BowlingSession(limit);
                }
            }
        }
        return session;
    }

    private static void createAndShowGUI(){
        //Create and set up the window.
        JFrame frame = new JFrame("Bowling Session");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800,400));
        frame.setPreferredSize(new Dimension(800,400));
        //frame.setLayout(new GridLayout(0, 2));

        //Add content to the window.
        frame.add(new PinView(pins.state), BorderLayout.LINE_START);
        frame.add(new BowlersView(bowlers, bowlerLimit),BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
