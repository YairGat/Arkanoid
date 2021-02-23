import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private ArrayList<HitListener> hitListeners;
    private Rectangle block;
    private Color color;

    /**
     * Constructor of Block.
     *
     * @param block the shape of the block.
     * @param color the color of the block
     */
    public Block(Rectangle block, Color color) {
        this.block = block;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }
//    /**
//     * Constructor of Block.
//     * @param block the shape of the block.
//     * @param color the color of the block
//     */
//    public Block(Rectangle block, Color color, ArrayList<HitListener> hitListeners) {
//        this.block = block;
//        this.color = color;
//        this.hitListeners = hitListeners;
//    }

    /**
     * gets block's color.
     *
     * @return block's color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * gets color and define the block to be drawn in this color.
     *
     * @param colorr of the block.
     */
    public void setColor(Color colorr) {
        this.color = colorr;
    }


    /**
     * draw the ball object.
     *
     * @param surface our surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    /**
     * Do nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Return the block's shape.
     *
     * @return block's shape.
     */
    public Rectangle getBlock() {
        return this.block;
    }

    /**
     * Get block and set it as this.block attrubute.
     *
     * @param blockk block shape
     */
    public void setBlock(Rectangle blockk) {
        this.block = blockk;
    }

    /**
     * return the block.
     *
     * @return the block shape.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * The function locates the collision point and change the speed acording to the location.
     *
     * @param collisionPoint  collision point.
     * @param currentVelocity current velocity of the ball
     * @return new velocity acording to hit location.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = null;
        double dy = currentVelocity.getDy();
        double dx = currentVelocity.getDx();
        Line lowerSide = block.getLowerSide();
        Line upperSide = block.getUpperSide();
        Line leftSide = block.getSideLeft();
        Line rightSide = block.getSideRight();
        if (lowerSide.isOnTheLine(collisionPoint) || upperSide.isOnTheLine(collisionPoint)) {
            dy = -dy;
        }
        if (leftSide.isOnTheLine(collisionPoint) || rightSide.isOnTheLine(collisionPoint)) {

            dx = -dx;
        }
        newVelocity = new Velocity(dx, dy);
        return newVelocity;

    }

    /**
     * The function locates the collision point and change the speed acording to the location + ass5 addition.
     *
     * @param collisionPoint  collision point.
     * @param currentVelocity current velocity of the ball
     * @param hitter          the ball which hits the block
     * @return new velocity acording to hit location.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        Velocity newVelocity = null;
        double dy = currentVelocity.getDy();
        double dx = currentVelocity.getDx();
        Line lowerSide = block.getLowerSide();
        Line upperSide = block.getUpperSide();
        Line leftSide = block.getSideLeft();
        Line rightSide = block.getSideRight();
        this.notifyHit(hitter);
        if (lowerSide.isOnTheLine(collisionPoint) || upperSide.isOnTheLine(collisionPoint)) {
            dy = -dy;
        }
        if (leftSide.isOnTheLine(collisionPoint) || rightSide.isOnTheLine(collisionPoint)) {

            dx = -dx;
        }
        newVelocity = new Velocity(dx, dy);
        return newVelocity;

    }

    /**
     * get game and join the block to him.
     *
     * @param game game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * remove this block from the game.
     *
     * @param game the game we removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);

    }

    /**
     * @param hitter the hit ball.
     */
    protected void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * @param hl the add listner.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * @param hl the removed listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
