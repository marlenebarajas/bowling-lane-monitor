package BowlingScoreboard;

import java.util.Observable;
import java.util.Observer;

public class BowlingSession implements Observer {
    private PinState pins;
    private boolean active; //is a game currently running?

    //MAYBE ITS OWN MODEL:
    private String[] bowlers;
    private int numOfBowlers;
    private int frame;
    private int playerTurn;

    public BowlingSession(){
        pins = PinState.getInstance();
        //render appropriate frames
    }

    public void simulateGame(){
        //we call PinState.roll() for the hardware;
    }

    public void runGame(){
        //in a real game, hardware calls PinState.roll() and we wait for update

    }

    public static void main(String[] args){
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
