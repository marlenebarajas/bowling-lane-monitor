package BowlingScoreboard;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Renders list of bowlers in current bowling session.
 * Two different versions, for when the game is ongoing and otherwise.
 */
public class BowlersView extends JPanel {
    JButton startGame;
    JButton endGame;
    int bowlerLimit;

    public BowlersView(Bowler[] bowlers, int limit){
        bowlerLimit = limit;
        startGame = startGameBtn(bowlers);
        endGame = endGameBtn();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Bowlers"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10))
        );
        render(bowlers, true);
    }

    private void render(Bowler[] bowlers, boolean editable){
        removeAll();
        if(editable){
            add(new BowlersViewSetup(bowlers, bowlerLimit), BorderLayout.PAGE_START);
            add(startGame, BorderLayout.PAGE_END);
        } else{
            add(new BowlersViewOngoingGame(bowlers), BorderLayout.PAGE_START);
            add(endGame, BorderLayout.PAGE_END);
        }
        revalidate();
        repaint();
    }

    private JButton startGameBtn(Bowler[] bowlers){
        JButton button = new JButton("Start Game");
        button.addActionListener(e -> render(bowlers,false));
        return button;
    }

    private JButton endGameBtn(){
        JButton button = new JButton("End Game");
        return button;
    }

    /**
     * Locks the ability to add new bowlers.
     */
    private void lockBowlers(){

    }
}
