// Velocity specifies the change in position on the `x` and the `y` axes.

/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx is the velocity x coordinate.
     * @param dy is the velocity y coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dy = dy;
        this.dx = dx;

    }

    /**
     * Gets speed in the (angle,speed) shape and return dx,dy velocity.
     *
     * @param angle the angle of the velocity
     * @param speed the size of the speed
     * @return dx, dy Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * @return the velocity size of speed.
     */
    public double getVelocitySpeed() {
        return Math.sqrt((Math.pow(getDx(), 2) + Math.pow(getDy(), 2)));
    }

    /**
     *
     * @return dx- x coordinate of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     *
     * @return dy- y coordinate of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy).
     *
     * @param p set legal velocity to point
     * @return new point (x+dx,y+dy).
     */

    public Point applyToPoint(Point p) {
        Point newP = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newP;
    }
}
