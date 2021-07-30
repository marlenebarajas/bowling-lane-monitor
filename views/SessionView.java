package BowlingScoreboard.views;

import BowlingScoreboard.controllers.SessionController;
import BowlingScoreboard.models.Bowler;
import BowlingScoreboard.models.BowlingGame;
import BowlingScoreboard.models.PinState;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;

public class SessionView implements Observer {
    private SessionController controller;
    private JFrame frame;
    private PinView pins;
    private BowlersView bowlers;
    private SessionControlsView controls;
    private EndView end;
    private boolean showingEnd;

    private boolean active = false; //is this game active?

    public SessionView(SessionController controller){
        this.controller = controller;
        this.showingEnd = false;
        controller.linkModels(this);
        createAndShowGUI();
    }

    private void createAndShowGUI(){
        //Create and set up the window.
        frame = new JFrame("Bowling Session");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1200,800));
        frame.setPreferredSize(new Dimension(1200,800));

        //Create content for window.
        this.pins = new PinView();
        this.bowlers = new BowlersView(controller);
        this.controls = new SessionControlsView(controller);

        //Add content to the window.
        frame.add(pins, BorderLayout.LINE_START);
        frame.add(bowlers, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.PAGE_END);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Exchanges PinView with EndView
     */
    private void endScreen(PriorityQueue leaderboard){
        frame.remove(pins);
        this.end = new EndView(leaderboard);
        frame.add(end, BorderLayout.LINE_START);
        this.showingEnd = true;
        frame.pack();
    }

    /**
     * Exchanges EndView with PinView
     */
    private void pinScreen(){
        frame.remove(end);
        frame.add(pins, BorderLayout.LINE_START);
        frame.pack();
    }

    /**
     * Updates display of the different components in this view
     * @param o PinState, BowlingGame, or Bowler
     * @param arg dependent on o, if:
     *            PinState - boolean[] of new pin state;
     *            Bowler - null;
     *            BowlingGame - null, true, or false
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof PinState){
            pins.render((boolean[]) arg);
        }
        if(o instanceof Bowler) bowlers.render();
        if(o instanceof BowlingGame){
            if(arg==null){ //continuing game
                bowlers.render();
                if(showingEnd) pinScreen();
                controls.showStart();
            }
            else if((boolean)arg){ //start game condition
                controller.linkBowlers(this);
                controls.showEnd();
            }
            else{ //end game condition
                endScreen(controller.leaderboard());
            }
        }
    }
}
