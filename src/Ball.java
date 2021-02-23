import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 * Ball class includes fields and methods that reprsent balls.
 */
public class Ball implements Sprite , HitNotifier {
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private int height, width;
    private GameEnvironment gameEnvironment;
    private ArrayList<HitListener> hitListeners;
    private int isItCircle;

    /**
     * Constructor.
     *
     * @param center ball's center point
     * @param r      ball's radius
     * @param color  ball's color
     * @param gameEnvironment our game environment
     * @param height height of screen
     * @param width the width of screen
     *
     */
    public Ball(Point center, int r, Color color, int width, int height, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.width = width;
        this.height = height;
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<>();
        this.isItCircle = 0;
    }
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.isItCircle = 1;
    }
    /**
     * Second constructor.
     *
     * @param i     x coordinate of center point
     * @param i1    y coordinate of center point
     * @param i2    radius of ball
     * @param green ball's color
     */
    public Ball(int i, int i1, int i2, Color green) {
        this.center = new Point(i, i1);
        this.r = i2;
        this.color = green;
        this.isItCircle = 0;
    }
    /**
     *  find the ball's trajectory.
     * @return the trajectory destination of the ball
     */
    public Line findTrajectory() {

        Point end = new Point((int) (this.center.getX() + this.velocity.getDx()),
                (int) (this.center.getY() + this.velocity.getDy()));
        Line line = new Line(this.center, end);
        return line;

    }

    /**
     * @return ball's game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }



    /**
     * gets game environment and set it as a atrtbiue of the ball.
     * @param gameEnvironmentt chane this.game environment to gameEnbironment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironmentt) {
        this.gameEnvironment = gameEnvironmentt;

    }

    /**
     * @return return the height of screen.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return width of the screen.
     */
    public int getWidth() {
        return width;
    }
    /**
     * Accessor.
     *
     * @return x
     */

    public int getX() {
        return (int) center.getX();
    }

    /**
     * Accessor.
     *
     * @return y
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Accessor.
     *
     * @return size-ball's radius.
     */

    public int getSize() {
        return this.r;
    }

    /**
     * Accessor.
     *
     * @return ball's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface our surface
     */
    public void drawOn(DrawSurface surface) {
        if (isItCircle == 1) {
            surface.setColor(this.color);
            surface.drawCircle(this.getX(), this.getY(), this.r);
        }
        else {
            surface.setColor(this.color);
            surface.fillCircle(this.getX(), this.getY(), this.r);
            surface.drawCircle(this.getX(), this.getY(), this.r);
        }
    }

    /**
     * time passed of ball.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Set velocity as v.
     *
     * @param v is the velocity of the ball.
     */

    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set velocity as (dx,dy) velocity.
     *
     * @param dx x coordinate of the velocity
     * @param dy y coordinate of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Accessor.
     *
     * @return this.velocity which means our velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move the ball and make he is on the screen.
     */
    public void moveOneStep() {
        CollisionInfo closestCollisionInfo = this.gameEnvironment.getClosestCollision(findTrajectory());
        Point point = null;
        if (closestCollisionInfo != null) {
            CollisionInfo closestCollisionInfo2 = this.gameEnvironment.getClosestCollision(findTrajectory());

            Rectangle closestCollision = closestCollisionInfo.collisionObject().getCollisionRectangle();
            Collidable block = (Collidable) closestCollisionInfo.collisionObject();
            Point collision = closestCollisionInfo.collisionPoint();
            this.center = new Point(collision.getX() - this.velocity.getDx() / 2,
                    collision.getY() - this.velocity.getDy() / 2);
            setVelocity(block.hit(collision, this.velocity,this));
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }

    }

    /**
     * gets game and add it to the game as sprite.
     * @param game given game.
     */
    public void addToGame(GameLevel game) {
        this.gameEnvironment = game.getEnvironment();
        game.addSprite(this);

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

    /**
     * @param game remove this ball from that game.
     */
    public void removeFromGame(GameLevel game){
        game.removeSprite(this);

    }
}