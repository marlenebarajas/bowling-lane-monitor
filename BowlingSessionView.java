package BowlingScoreboard;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BowlingSessionView extends JPanel implements Observer {
    private static final PinState pins = PinState.getInstance();;
    private static Bowler[] bowlers = new Bowler[8];

    //MAYBE ITS OWN MODEL:
    private int numOfBowlers;
    private int frame;
    private int playerTurn;

    public BowlingSessionView(){
        //render appropriate frames
    }

    public void simulateGame(){
        //we call PinState.roll() for the hardware;
    }

    public void runGame(){
        //in a real game, hardware calls PinState.roll() and we wait for update

    }
    private static void render() {

    }

    //button to StartGame lives here
    public static void main(String[] args){
        render();
        //Create panels that all change according to pinState
        //  pinState.addObserver(this);

        //create JButton that runs simulateGame() and another that runs runGame
        //  this rerenders part of the window appropriately
    }

    @Override
    public void update(Observable o, Object arg) {
        //called when PinState changes
    }
}
