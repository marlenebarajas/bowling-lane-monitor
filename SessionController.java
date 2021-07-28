package BowlingScoreboard;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SessionController {
    BowlingSession session;

    public SessionController(BowlingSession session){
        this.session = session;
    }

    private void setup(){

    }

    public void start(){
        session.setFrame(1);
        session.setActive(true);
    }

    public void simulate(){
        if(session.getBowlers()[0]==null){
            createError("Cannot run simulation without any bowlers. Please add bowlers and try again.");
            return;
        }
        session.setFrame(1);
        session.setActive(true);

        while(session.isActive()){
            session.setState(randomRoll());
        }
    }

    /**
     * Creates a random state change
     * @return a random, valid boolean[] state change
     */
    private boolean[] randomRoll(){
        boolean[] newState = session.getState();
        boolean[] currentState = session.getState();
        Random rndm = new Random();
        int changes = rndm.nextInt(10); //0-9
        int idx = rndm.nextInt(10);
        for(int i=0;i<changes;i++){
            if(currentState[idx]){
                newState[idx] = false;
            }
            if(idx==9) idx=0;
            else idx++;
        }
        return newState;
    }

    public void end(){
        session.finish(); //maybe do here?
        session.reset();
    }

    /**
     * Adds bowlers to Bowling Session
     * @param name String, Bowler's name
     */
    public void createBowler(String name){
        Bowler newBowler = new Bowler(name);
        session.addBowler(newBowler);
    }

    /**
     *
     */
    private void createError(String msg){
        JFrame error = new JFrame();
        error.setPreferredSize(new Dimension(500,200));
        error.setLayout(new FlowLayout());
        error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        error.add(new JLabel(msg));
        error.pack();
        error.setVisible(true);
    }
}
