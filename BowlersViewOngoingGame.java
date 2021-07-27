package BowlingScoreboard;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class BowlersViewOngoingGame extends JPanel{
    Component removeBowler;
    Bowler[] bowlers; int numOfBowlers;

    public BowlersViewOngoingGame(Bowler[] bowlers){
        this.bowlers = bowlers;
        removeBowler = removeBowlerBtn();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initialize();
    }

    private void initialize(){
        for(int i=0;i<bowlers.length;i++){
            if(bowlers[i]!=null){
                BowlerView addingBowler = new BowlerView(i, bowlers[i]);
                //bowlers[i].setObserver(addingBowler);
                add(addingBowler);
            }
        }
        add(removeBowler);
    }

    private JButton removeBowlerBtn(){
        JButton button = new JButton("Remove Bowler");
        button.addActionListener(e -> removePlayer());
        return button;
    }

    private JPanel createBowlerLabel(Bowler bowler){
        JPanel bowlerInfo = new JPanel();
        bowlerInfo.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), bowler.getName()));
        return bowlerInfo;
    }

    private void removePlayer(){

    }

}
