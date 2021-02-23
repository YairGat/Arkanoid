/**
 * HitListener is interface of all the listener class.
 */
public interface HitListener {

    /**
     *      This method is called whenever the beingHit object is hit.
     *      The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the beingHit object is hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}