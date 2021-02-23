import biuoop.DrawSurface;
/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 * Ball class includes fields and methods that reprsent balls.
 */
public interface Sprite {
     /**
     *draw the sprite to the screen.
     * @param d surface.
     */
    void drawOn(DrawSurface d);

    /**
     *notify the sprite that time has passed.
     */
    void timePassed();
}