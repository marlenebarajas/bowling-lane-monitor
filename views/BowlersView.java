package BowlingScoreboard.views;

import BowlingScoreboard.models.Bowler;
import BowlingScoreboard.controllers.SessionController;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Renders list of bowlers in current bowling session.
 * Two different versions, for when the game is ongoing and otherwise.
 */
public class BowlersView extends JPanel {
    SessionController controller;
    JTextField nameInput; Component nameField; Component addBowler;

    public BowlersView(SessionController controller){
        this.controller = controller;
        addBowler = addBowlerBtn();
        nameField = nameField();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Bowlers"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10))
        );
        render();
    }

    /**
     * Displays every BowlerView in views
     */
    public void render(){
        removeAll();
        add(bowlerList(!controller.isGameActive()),BorderLayout.PAGE_START);
        revalidate();
        repaint();
    }

    private JPanel bowlerList(boolean editable){
        JPanel showBowlers = new JPanel();
        showBowlers.setLayout(new BoxLayout(showBowlers, BoxLayout.PAGE_AXIS));

        Bowler[] bowlers = controller.getBowlers();
        int count = 0;
        for(int i=0; i<bowlers.length;i++){
            if(bowlers[i]!=null){
                count++;
                BowlerView view = new BowlerView(i, bowlers[i]);
                if(bowlers[i].isActive()) view.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.blue));
                showBowlers.add(view);
            } else break;
        }
        if(count!=controller.getBowlerLimit() && editable){
            showBowlers.add(nameInput);
            showBowlers.add(addBowler);
        }
        return showBowlers;
    }

    private Component addBowlerBtn() {
        JButton button = new JButton("Add Bowler");
        button.addActionListener(e -> controller.createBowler(nameInput.getText()));
        button.setPreferredSize(new Dimension(Short.MAX_VALUE, 40));
        button.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));

        Box box = Box.createHorizontalBox();
        box.add(button);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.add(Box.createHorizontalGlue());
        box.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return box;
    }

    private Component nameField(){
        nameInput = new JTextField("Enter Name");
        nameInput.setPreferredSize(new Dimension(Short.MAX_VALUE,40));
        nameInput.setMinimumSize(new Dimension(400, 40));
        nameInput.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));

        Box box = Box.createHorizontalBox();
        box.add(nameInput);
        box.add(Box.createHorizontalGlue());
        box.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return box;
    }
}
