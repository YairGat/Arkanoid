/**
 * BallRemover class used for ball that gets out from the screen.
 */
public class BallRemover implements HitListener  {

    private GameLevel game;
    private Counter removedBall;

    /**
     * Constuctor.
     * @param game game
     * @param removedBlocks removed block
     */
    public BallRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.removedBall = removedBlocks;
    }

    /**
     * @return the removed ball.
     */
    public Counter getRemovedBall() {
        return removedBall;
    }

    /**
     *      Blocks that are hit should be removed
     *      from the game. Remember to remove this listener from the block
     *      that is being removed from the game.
     * @param beingHit the beingHit object is hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.removedBall.increase(1);
        hitter.removeFromGame(game);
        hitter.removeHitListener(this);

    }
}
