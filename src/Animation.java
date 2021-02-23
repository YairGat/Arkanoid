import biuoop.DrawSurface;

/**
 * Animation interface used the basic for any type of animation.
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