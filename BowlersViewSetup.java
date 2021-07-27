package BowlingScoreboard;

import javax.swing.*;
import java.awt.*;

public class BowlersViewSetup extends JPanel {
    JTextField nameInput; Component nameField; Component addBowler;
    Bowler[] bowlers; int numOfBowlers; int bowlerLimit;

    public BowlersViewSetup(Bowler[] bowlers, int limit){
        this.bowlerLimit = limit;
        this.bowlers = bowlers;
        numOfBowlers = 0;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        nameField = nameField();
        addBowler = addBowlerBtn();
        render();
    }

    private void render(){
        removeAll();
        for(int i=0; i<numOfBowlers;i++){
            if(bowlers[i]!=null){
                add(new BowlerView(i, bowlers[i]));
            }
        }
        if(numOfBowlers!=bowlerLimit){
            add(nameField);
            add(addBowler);
        }
        revalidate();
        repaint();
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

    private Component addBowlerBtn() {
        JButton button = new JButton("Add Bowler");
        button.addActionListener(e -> createBowler(nameInput.getText()));
        button.setPreferredSize(new Dimension(Short.MAX_VALUE, 40));
        button.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));

        Box box = Box.createHorizontalBox();
        box.add(button);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.add(Box.createHorizontalGlue());
        box.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return box;
    }

    /**
     * Adds bowlers to Bowling Session
     * @param name String, Bowler's name
     */
    private void createBowler(String name){
        Bowler newBowler = new Bowler(name);
        bowlers[numOfBowlers] = newBowler;
        ++numOfBowlers;
        render();
    }

}
