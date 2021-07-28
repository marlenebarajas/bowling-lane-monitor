package BowlingScoreboard.views;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PinView extends JPanel {
    boolean[] pinState;

    public PinView(boolean[] pinState){
        this.pinState = pinState;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                "Pins");
        title.setTitleJustification(TitledBorder.CENTER);
        setBorder(title);

        render(pinState);
    }

    public void render(boolean[] update){
        removeAll();

        String pinRows = pinRows(update);
        JLabel display = new JLabel(pinRows);
        display.setVerticalAlignment(SwingConstants.CENTER);
        display.setHorizontalAlignment(SwingConstants.CENTER);
        add(display);

        revalidate();
        repaint();
    }

    /**
     * Creates the pin display
     * @param state dictates what pins are shown as hit
     * @return String in the correct format
     */
    private String pinRows(boolean[] state){
        String formatting = "<html><font size=+3>\n";
        String fourthRow = "<p>O---O---O---O</p>\n";//7 8 9 10
        String thirdRow =  "<p>--O---O---O</p>\n"; //4 5 6
        String secondRow = "<p>----O----O</p>\n"; //2 3
        String firstRow= "<p>-------O</p></font>\n"; //1

        for(int i=0; i<10;i++){
            int location = getPinLocation(i);
            if(i>5){
                if(state[i]) fourthRow = fourthRow.substring(0,location)+'O'+fourthRow.substring(location+1);
                else fourthRow = fourthRow.substring(0,location)+'Ø'+fourthRow.substring(location+1);
            }
            else if(i>2){
                if(state[i]) thirdRow = thirdRow.substring(0,location)+'O'+thirdRow.substring(location+1);
                else thirdRow = thirdRow.substring(0,location)+'Ø'+thirdRow.substring(location+1);
            }
            else if(i>0){
                if(state[i]) secondRow = secondRow.substring(0,location)+'O'+secondRow.substring(location+1);
                else secondRow = secondRow.substring(0,location)+'Ø'+secondRow.substring(location+1);
            }
            else{
                if(state[i]) firstRow = firstRow.substring(0,location)+'O'+firstRow.substring(location+1);
                else firstRow = firstRow.substring(0,location)+'Ø'+firstRow.substring(location+1);
            }
        }
        return formatting+fourthRow+thirdRow+secondRow+firstRow;
    }

    /**
     * @param pin one of ten pins
     * @return location of the character representing given pin
     */
    private int getPinLocation(int pin){
        switch(pin){
            case 0:
                return 10;
            case 1:
            case 7:
                return 7;
            case 2:
                return 12;
            case 3:
                return 5;
            case 4:
                return 9;
            case 5:
                return 13;
            case 6:
                return 3;
            case 8:
                return 11;
            case 9:
                return 15;
            default:
                throw new IllegalStateException("Unexpected value: " + pin);
        }
    }
}
