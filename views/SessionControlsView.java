package BowlingScoreboard.views;

import BowlingScoreboard.controllers.SessionController;

import javax.swing.*;
import java.awt.*;

public class SessionControlsView extends JPanel {
    SessionController controller;
    Component startGame;
    Component endGame;
    Component simulate;

    public SessionControlsView(SessionController controller){
        this.controller = controller;
        this.startGame = startGameBtn();
        this.endGame = endGameBtn();
        this.simulate = simulateGameBtn();
        render();
    }

    private void render(){
        add(simulate);
        add(startGame);
    }

    public void showEnd(){
        removeAll();
        add(endGame);
        revalidate();
        repaint();
    }

    public void showStart(){
        removeAll();
        add(simulate);
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

    private JButton simulateGameBtn(){
        JButton button = new JButton("Simulate Game");
        button.addActionListener(e -> controller.simulate());
        return button;
    }
}
