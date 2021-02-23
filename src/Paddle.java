import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 * Ball class includes fields and methods that reprsent balls.
 */
public class Paddle implements Sprite, Collidable {
    private Block block;
    private GUI gui;
    private KeyboardSensor keyboard;
    private Velocity velocity;

    /**
     * Paddle constructor.
     * @param gui gui.
     */
    public Paddle(GUI gui, int width) {
        this.gui = gui;
        this.block = new Block(new Rectangle(new Point((double) (this.gui.getDrawSurface().getWidth()) / 2, 550), width, 20), Color.red);
        this.block.setColor(Color.yellow);
        this.keyboard = gui.getKeyboardSensor();
        this.velocity = new Velocity(5, 0);
    }

    /**
     * Set velocity as (dx,dy).
     * @param dx x's coordinate of velocity.
     * @param dy y's coordinate of velocity.ssss
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     *Move the padlle left.
     */
    public void moveLeft() {
        if (this.block.getBlock().getUpperLeft().getX() - 5 >= 0) {
            this.block.getBlock().setUpperLeft(new Point(this.block.getBlock().getUpperLeft().getX() - 5,
                    this.block.getBlock().getUpperLeft().getY()));
        }
    }

    /**
     * Move the padlle right.
     */
    public void moveRight() {
        if (this.block.getBlock().getUpperRight().getX() + 5 <= 800) {
            this.block.getBlock().setUpperLeft(new Point(this.block.getBlock().getUpperLeft().getX() + 5,
                    this.block.getBlock().getUpperLeft().getY()));
        }
    }

    // Sprite

    /**
     * Right and Left.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw the padlle.
     * @param d our surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle((int) this.block.getBlock().getUpperLeft().getX(),
                (int) this.block.getBlock().getUpperLeft().getY(), (int) this.block.getBlock().getWidth(),
                (int) this.block.getBlock().getHeight());
        d.setColor(this.block.getColor());
        d.fillRectangle((int) this.block.getBlock().getUpperLeft().getX(),
                (int) this.block.getBlock().getUpperLeft().getY(), (int) this.block.getBlock().getWidth(),
                (int) this.block.getBlock().getHeight());
    }

    /**
     * @return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    /**
     *
     * @param collisionPoint collision coordinates
     * @param currentVelocity current velocity of the ball
     * @return new velocity acording to the hit location.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity,Ball hitter) {
        double xCollision = collisionPoint.getX();
        double yCollision = collisionPoint.getY();
        Velocity newVelocity = null;
        double regionWidth = this.getCollisionRectangle().getWidth() / 5;
        Point point = new Point(this.block.getCollisionRectangle().getUpperLeft().getX() + regionWidth,
                this.block.getCollisionRectangle().getUpperLeft().getY());
        Line firstRegion = new Line(this.block.getCollisionRectangle().getUpperLeft(), point);
        Line secondRegion = new Line(firstRegion.getEnd(), new Point(firstRegion.getEnd().getX()
                + regionWidth, firstRegion.getEnd().getY()));
        Line thirdRegion = new Line(secondRegion.getEnd(), new Point(secondRegion.getEnd().getX()
                + regionWidth, secondRegion.getEnd().getY()));
        Line forthRegion = new Line(thirdRegion.getEnd(), new Point(thirdRegion.getEnd().getX()
                + regionWidth, thirdRegion.getEnd().getY()));
        Line fifthRegion = new Line(forthRegion.getEnd(), this.block.getCollisionRectangle().getUpperRight());
        if (firstRegion.isOnTheLine(collisionPoint)) {
            newVelocity = Velocity.fromAngleAndSpeed(210, currentVelocity.getVelocitySpeed());
        } else if (secondRegion.isOnTheLine(collisionPoint)) {

            newVelocity = Velocity.fromAngleAndSpeed(240, currentVelocity.getVelocitySpeed());
        } else if (thirdRegion.isOnTheLine(collisionPoint)) {
            newVelocity =  Velocity.fromAngleAndSpeed(270, currentVelocity.getVelocitySpeed());
        } else if (forthRegion.isOnTheLine(collisionPoint)) {
            newVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.getVelocitySpeed());
        } else if (fifthRegion.isOnTheLine(collisionPoint)) {

            newVelocity = Velocity.fromAngleAndSpeed(330, currentVelocity.getVelocitySpeed());
        } else if (this.block.getBlock().getSideLeft().isOnTheLine(collisionPoint)
                || this.block.getBlock().getSideRight().isOnTheLine(collisionPoint)) {
            newVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        } else if (this.block.getBlock().getLowerSide().isOnTheLine(collisionPoint)) {
            newVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        return newVelocity;


    }

    /**
     * Add this paddle to the game.
     * @param g our game enviornment.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}