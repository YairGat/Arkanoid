/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 * Ball class includes fields and methods that reprsent balls.
 */
public interface Collidable {
    /**
     *@return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with.
     *      a given velocity.
     *      The return is the new velocity expected after the hit (based on
     *      the force the object inflicted on us).
     *
     * @param collisionPoint collision coordinates
     * @param currentVelocity current velocity of the ball
     * @return new velocity acording to the hit location
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity,Ball hitter);
}
