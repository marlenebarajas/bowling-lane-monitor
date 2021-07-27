package BowlingScoreboard;

import javax.swing.*;
import java.awt.*;

public class SessionControlsView extends JPanel {
    SessionController controller;
    Component startGame;
    Component endGame;

    public SessionControlsView(SessionController controller){
        this.controller = controller;
        this.startGame = startGameBtn();
        this.endGame = endGameBtn();
        render();
    }

    private void render(){
        add(startGame);
    }

    public void setStart(){
        removeAll();
        add(endGame);
        revalidate();
        repaint();
    }

    public void setEnd(){
        removeAll();
        add(startGame);
        revalidate();
        repaint();
    }

    private JButton startGameBtn(){
        JButton button = new JButton("Start Game");
        button.addActionListener(e -> controller.start());
        return button;
    }

    private JButton endGameBtn(){
        JButton button = new JButton("End Game");
        button.addActionListener(e -> controller.end());
        return button;
    }
}
