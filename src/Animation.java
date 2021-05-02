import biuoop.DrawSurface;

/**
 * Animation interface has all function that any animation in the program needs.
 */
public interface Animation {
    /**
     * @param d do one frame on d surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return if the animation should stop.
     */
    boolean shouldStop();
}
