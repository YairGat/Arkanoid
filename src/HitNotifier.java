/**
 * HitNotifer interface represnts
 */
public interface HitNotifier {


    /**
     *  Add hl as a listener to hit events.
     * @param hl the add listner
     */
    void addHitListener(HitListener hl);

    /**
     *  Remove hl from the list of listeners to hit events.
     * @param hl the removed listener
     */
    void removeHitListener(HitListener hl);
}