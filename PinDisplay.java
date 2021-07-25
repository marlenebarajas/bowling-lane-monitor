package BowlingScoreboard;

import java.util.Observable;
import java.util.Observer;

public class PinDisplay implements Observer {
    //visual representation of pins that updates with pinstate changes

    public PinDisplay(){
        //render();
    }

    @Override
    public void update(Observable o, Object arg) {
        //render with new PinState.pins
    }
}
