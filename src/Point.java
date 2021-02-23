/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 * Point class includes fields and methods that represent Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Point constructor.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other point
     * @return the distance of this point to the other point.
     */

    public double distance(Point other) {
        return Math.sqrt(Math.pow(other.getX() - this.getX(), 2) + Math.pow(other.getY() - this.getY(), 2));

    }

    /**
     * @param other point
     * @return true is the points are equal, false otherwise.
     */

    public boolean equalsPoints(Point other) {
        if (other == null) {

            return false;
        }

        return (other.getX() == this.getX()) && (other.getY() == this.getY());
    }

    /**
     * @return the point's x coordinate.
     */
    public double getX() {
        return this.x;
    }
    /**
     * @return the point's y coordinate.
     */
    public double getY() {
        return this.y;
    }


}