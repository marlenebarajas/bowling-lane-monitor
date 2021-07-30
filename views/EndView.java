package BowlingScoreboard.views;

import BowlingScoreboard.models.Bowler;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.util.PriorityQueue;

/**
 * Renders final placements of bowlers in the last game session.
 */
public class EndView extends JPanel {
    PriorityQueue<Bowler> leaderboard;

    public EndView(PriorityQueue<Bowler> leaderboard){
        this.leaderboard = leaderboard;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                "Leaderboard");
        title.setTitleJustification(TitledBorder.CENTER);
        setBorder(title);

        render();
    }

    public void render(){
        removeAll();

        JLabel display = new JLabel(createScoreString());
        display.setVerticalAlignment(SwingConstants.CENTER);
        display.setHorizontalAlignment(SwingConstants.CENTER);
        add(display);

        revalidate();
        repaint();
    }

    public String createScoreString(){
        String html = "<html><font size=+3>\n";
        String result = html;
        int place = 1;
        while(!leaderboard.isEmpty()){
            Bowler bowler = leaderboard.poll();
            if(place==1){
                result = result+"<p style=\"color: green\">"+place+"  "+bowler.getScore()+"  "+bowler.getName()+"</p>\n";
            } else{
                result = result+"<p>"+place+"  "+bowler.getScore()+"  "+bowler.getName()+"</p>\n";
            }
            place++;
        }
        return result;
    }
}
