import java.util.ArrayList;
import java.util.List;
/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 * Ball class includes fields and methods that reprsent balls.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upperLeft point.
     * @param width the rectangle's width.
     * @param height the rectangle's height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * @param upperLeftt set it to be the upper left of the rec.
     */
    public void setUpperLeft(Point upperLeftt) {
        this.upperLeft = upperLeftt;
    }

    /**
     * @return the left side of the rectangle.
     */
    public Line getSideLeft() {
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Line sideLeft = new Line(lowerLeft, upperLeft);
        return sideLeft;
    }

    /**
     * @return right side of the rectangle.
     */
    public Line getSideRight() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerRight = new Point(upperRight.getX(), upperRight.getY() + this.height);
        Line sideRight = new Line(lowerRight, upperRight);
        return sideRight;
    }

    /**
     * @return lower side of the rectangle.
     */
    public Line getLowerSide() {
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerRight = new Point(upperRight.getX(), upperRight.getY() + this.height);
        Line side2 = new Line(lowerLeft, lowerRight);
        return side2;
    }

    /**
     * @return upper side of the rectangle.
     */
    public Line getUpperSide() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Line side4 = new Line(upperRight, upperLeft);
        return side4;
    }

    /**
     *      Return a (possibly empty) List of intersection points.
     *      with the specified line.
     * @param line checked line
     * @return list of intersections.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<Point>();
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerRight = new Point(upperRight.getX(), upperRight.getY() + this.height);
        Line side1 = new Line(lowerLeft, upperLeft);
        Line side2 = new Line(lowerLeft, lowerRight);
        Line side3 = new Line(lowerRight, upperRight);
        Line side4 = new Line(upperRight, upperLeft);
        if (side1.isIntersecting(line)) {
            Point side1Intersection = side1.intersectionWith(line);
            intersections.add(side1.intersectionWith(line));
        }
        if (side2.isIntersecting(line)) {
            Point side2Intersection = side2.intersectionWith(line);
            intersections.add(side2.intersectionWith(line));
        }
        if (side3.isIntersecting(line)) {
            Point side3Intersection = side3.intersectionWith(line);
            intersections.add(side3.intersectionWith(line));
        }
        if (side4.isIntersecting(line)) {
            Point side4Intersection = side4.intersectionWith(line);
            intersections.add(side4.intersectionWith(line));
        }
        return intersections;

    }

    /**
     * @return lower left point.
     */
    public Point getLowerLeft() {
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return lowerLeft;
    }

    /**
     * @return upper right point.
     */
    public Point getUpperRight() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return upperRight;
    }

    /**
     * @return lower right point.
     */
    public Point getLowerRight() {
        Point lowerRight = new Point(getUpperRight().getX(), getUpperRight().getX() + this.height);
        return lowerRight;
    }

    /**
     * @return Return the width and height of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     *Returns the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}