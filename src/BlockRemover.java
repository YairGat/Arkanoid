/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count.
 *  of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter removedBlocks;

    /**
     * Consturctor.
     * @param game current game
     * @param removedBlocks the removed block
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
            this.game = game;
            this.removedBlocks = removedBlocks;
    }

    /**
     * @return the count of the removed block.
     */
    public Counter getRemovedBlocks() {
        return removedBlocks;
    }

    /**
     * @return the current game.
     */
    public GameLevel getGame() {
        return this.game;
    }
    /**
     *   Blocks that are hit should be removed.
     *      from the game. Remember to remove this listener from the block
     *      that is being removed from the game.
     * @param beingHit the beingHit object is hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(getGame());
        beingHit.removeHitListener(this);
        getRemovedBlocks().increase(1);
    }
}