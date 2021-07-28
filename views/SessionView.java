package BowlingScoreboard.views;

import BowlingScoreboard.models.BowlingSession;
import BowlingScoreboard.controllers.SessionController;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class SessionView implements Observer {
    private BowlingSession session;
    SessionController sessionController;
    private boolean active = false; //is this game active?

    JFrame frame;
    PinView pins;
    BowlersView bowlers;
    SessionControlsView controls;

    public SessionView(BowlingSession session){
        this.session = session;
        session.addObserver(this);
        sessionController = new SessionController(session);
        createAndShowGUI();
    }

    private void createAndShowGUI(){
        //Create and set up the window.
        frame = new JFrame("Bowling Session");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1200,800));
        frame.setPreferredSize(new Dimension(1200,800));

        //Create content for window.
        this.pins = new PinView(session.getState());

        this.bowlers = new BowlersView(sessionController, session.getBowlers(), session.getBowlerLimit());

        this.controls = new SessionControlsView(sessionController);
        //Add content to the window.
        frame.add(pins, BorderLayout.LINE_START);
        frame.add(bowlers, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.PAGE_END);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void reset(){
        frame.remove(bowlers);
        this.active = false;
        this.bowlers = new BowlersView(sessionController, session.getBowlers(), session.getBowlerLimit());
        frame.add(bowlers, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Called when BowlingSession starts, end, or updates the amount of bowlers
     * @param o BowlingSession
     * @param arg boolean, true when a game is starts, false otherwise
     */
    @Override
    public void update(Observable o, Object arg) {
        if((boolean) arg){
            this.active = true;
            bowlers.render(false);
            controls.showEnd();
        } else{
            if(active){ //game is JUST over!
                //show results
                //display button to start a new session that calls reset();
                reset();
                controls.showStart();
            } else bowlers.render(true);
        }

        //also update PinView here
        pins.render(session.getState());
    }
}
