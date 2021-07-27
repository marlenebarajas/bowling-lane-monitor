package BowlingScoreboard;

public class SessionController {
    BowlingSession session;

    public SessionController(BowlingSession session){
        this.session = session;
    }

    private void setup(){

    }

    public void start(){
        session.setFrame(1);
        session.setActive(true);
    }

    public void end(){
        session.finish();
        session.reset();
        session.setActive(false);
    }

    /**
     * Adds bowlers to Bowling Session
     * @param name String, Bowler's name
     */
    public void createBowler(String name){
        Bowler newBowler = new Bowler(name);
        session.addBowler(newBowler);
    }
}
