package BowlingScoreboard;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PinView extends JPanel implements Observer {
    boolean[] pinState;
    JLabel display;
    //visual representation of pins that updates with pinstate changes

    public PinView(boolean[] pinState){
        this.pinState = pinState;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                "Pins");
        title.setTitleJustification(TitledBorder.CENTER);
        setBorder(title);

//      setPreferredSize(new Dimension(400, 200));
//      setMinimumSize(new Dimension(400, 200));

        String pinRows = "<html><font size=+3>\n" +
                "<p>O---O---O---O</p>\n" +
                "<p>--Ø---Ø---Ø</p>\n" +
                "<p>----O----O</p>\n" +
                "<p>-------O</p></font>\n";

        display = new JLabel(pinRows);
        display.setVerticalAlignment(SwingConstants.CENTER);
        display.setHorizontalAlignment(SwingConstants.CENTER);

        add(display);
    }

    @Override
    public void update(Observable o, Object arg) {
        //render with new PinState.pins
    }
}
