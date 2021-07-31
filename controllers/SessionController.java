package BowlingScoreboard.controllers;

import BowlingScoreboard.models.Bowler;
import BowlingScoreboard.models.BowlingGame;
import BowlingScoreboard.models.PinState;

import java.util.Observer;
import java.util.PriorityQueue;

public class SessionController {
    BowlingGame session;
    PinState state;

    public SessionController(PinState state, BowlingGame session){
        this.session = session;
        this.state = state;
        state.setController(this);
    }

    //-- SessionView Setup --//

    public void linkModels(Object o){
        session.addObserver((Observer) o);
        state.addObserver((Observer) o);
    }

    public void linkBowlers(Object o){
        for(Bowler bowler:session.getBowlers()){
            if(bowler!=null) bowler.addObserver((Observer) o);
        }
    }

    //-- BowlingGame Info --//

    public boolean isGameActive(){
        return session.isActive();
    }

    public int getNumOfBowlers(){
        return session.getNumOfBowlers();
    }

    public Bowler[] getBowlers(){
        return session.getBowlers();
    }

    public int getBowlerLimit(){
        return session.getLimit();
    }

    //-- BowlingGame Controls--//

    /**
     * Creates and adds bowler to the game session
     * @param name String
     */
    public void createBowler(String name){
        Bowler bowler = new Bowler(name);
        session.addBowler(bowler);
    }

    /**
     * Starts a bowling game with added bowlers
     */
    public void start(){
        session.setFrame(1);
        session.setActive(true);

        int turn = 0;
        while(session.isActive()){
            if(session.getRoll()==1){
                state.reset();
            }
            //wait for roll, then...
            session.calculateScore(state.getState());
            session.updateLeaderboard();
        }
    }

    /**
     * Simulates a game between current bowlers without the use of hardware input
     */
    public void simulate(){
        session.setFrame(1);
        session.setActive(true);

        while(session.isActive()){
            if(session.getRoll()==1){
                state.reset();
            }
            state.runSimulated();
            session.calculateScore(state.getState());
            session.updateLeaderboard();
            //session.next();
        }
    }

    /**
     * Prematurely ends bowling game or resets the end screen to start a new game
     */
    public void end(){
        if(session.isActive()) session.setActive(false);
        session.reset();
    }

    /**
     * @return PriorityQueue<Bowler> in decreasing order by score
     */
    public PriorityQueue<Bowler> leaderboard(){
        return session.getLeaderboard();
    }

}
