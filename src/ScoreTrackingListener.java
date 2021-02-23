public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public Counter getCurrentScore() {
        return currentScore;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        getCurrentScore().increase(5);
    }
}