import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;
/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 * Ball class includes fields and methods that reprsent balls.
 * Line is class that includes some fields and method that represent Line.
 */

public class Line implements Sprite {
    private Point start;
    private Point end;

    /**
     * Constructors.
     *
     * @param start where the line starts.
     * @param end   where the line ends.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Second constructor which gets 4 coordinates the 2 first represents,
     * the start point and the 2 second represents the end.
     *
     * @param x1 the start's x coordinate of the line
     * @param y1 the start's y coordinate of the line
     * @param x2 the end's x coordinate of the line
     * @param y2 the end's y coordinate of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

    }

    /**
     * @return the length of the line
     */

    public double length() {
        return this.start.distance(end);
    }

    /**
     * The middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        Point middle = new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
        return middle;
    }

    /**
     * @return the start point of the line.
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * @return the end point of the line.
     */
    public Point getEnd() {
        return this.end;
    }
    /**
     *
     * @param other other the checked line.
     * @return true if the line is vertical and false if isn't.
     */
    public boolean verticalLine(Line other) {
        if ((other.getEnd().getX() == other.getStart().getX())) {
            return true;
        }
        return false;
    }

    /**
     *Check if other is horizntal line.
     * @param other line
     * @return true if other is horizline and false otherwise
     */
    public boolean horizLine(Line other) {
        if ((other.getEnd().getY() == other.getStart().getY())) {
            return true;
        }
        return false;
    }

    /**
     * @param other vertical.
     * @param line horizntal.
     * @return intersenting between vertical line and horizntal line.
     */
    public Point isIntersectingVerticalAndHoriz(Line other, Line line) {
        if ((Math.min(line.getStart().getY(), line.getEnd().getY()) <= other.getStart().getY())
                && (other.getEnd().getY() <= Math.max(line.getStart().getY(), line.getEnd().getY()))) {
            if (line.getEnd().getX() <= Math.max(other.getStart().getX(), other.getEnd().getX())
                    && (line.getEnd().getX() >= Math.min(other.getStart().getX(), other.getEnd().getX()))) {
                return new Point(line.start.getX(), other.end.getY());
            }
        }
        if ((Math.min(other.getStart().getY(), other.getEnd().getY()) <= line.getStart().getY())
                && (line.getEnd().getY() <= Math.max(other.getStart().getY(), other.getEnd().getY()))) {
            if (other.getEnd().getX() <= Math.max(line.getStart().getX(), line.getEnd().getX())
                    && (other.getEnd().getX() >= Math.min(line.getStart().getX(), line.getEnd().getX()))) {
                return new Point(other.start.getX(), line.end.getY());
            }
        }
        return null;
    }

    /**
     * @param horiz horizntal line.
     * @param regular rgular line.
     * @return intersenting point between horizntal line and regular.
     */
    public Point intersectingHorizAndRegular(Line horiz, Line regular) {
        double maxX = Math.max(regular.getStart().getX(), regular.getEnd().getX());
        double maxY = Math.max(regular.getStart().getY(), regular.getEnd().getY());
        double minX = Math.min(regular.getStart().getX(), regular.getEnd().getX());
        double minY = Math.min(regular.getStart().getY(), regular.getEnd().getY());
        double maxXV = Math.max(horiz.getStart().getX(), horiz.getEnd().getX());
        double minXV = Math.min(horiz.getStart().getX(), horiz.getEnd().getX());
        if (maxY < horiz.getStart().getY() || minY > horiz.getStart().getY() || minX > maxXV || maxX < minXV) {
            return null;
        }

        double mRegular = (regular.end.getY() - regular.start.getY()) / (regular.end.getX() - regular.start.getX());
        double intersectionX = (horiz.getStart().getY() - regular.getStart().getY())
                / mRegular + regular.getStart().getX();
        if (!((intersectionX > minXV) && (intersectionX < maxXV))) {
            return null;
        }
        return new Point(intersectionX, horiz.getStart().getY());

    }

    /**
     * @param vertical line.
     * @param regular line.
     * @return intersecting point between vetical line regular line.
     */
    public Point intersectingVerticalAndRegular(Line vertical, Line regular) {
        double maxX = Math.max(regular.getStart().getX(), regular.getEnd().getX());
        double maxY = Math.max(regular.getStart().getY(), regular.getEnd().getY());
        double minX = Math.min(regular.getStart().getX(), regular.getEnd().getX());
        double minY = Math.min(regular.getStart().getY(), regular.getEnd().getY());
        double maxYV = Math.max(vertical.getStart().getY(), vertical.getEnd().getY());
        double minYV = Math.min(vertical.getStart().getY(), vertical.getEnd().getY());

        if (maxX < vertical.getEnd().getX() || (minX > vertical.getEnd().getX()) || maxYV < minY || minYV > maxY) {
            return null;
        }

        double mRegular = (regular.end.getY() - regular.start.getY())
                / (regular.end.getX() - regular.start.getX());
        double intersectionY = mRegular * (vertical.getEnd().getX()
                - regular.getEnd().getX()) + regular.getEnd().getY();
        if (!((intersectionY > minYV) && (intersectionY < maxYV))) {
            return null;
        }
        return new Point(vertical.getStart().getX(), intersectionY);
    }

    /**
     * Checks if the line this and the other line intersect.
     * @param other the other line which we check intersecting with him
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (verticalLine(this)) {
            if (verticalLine(other)) {
                return false;
            }
            if (horizLine(other)) {
                if (isIntersectingVerticalAndHoriz(other, this) != null) {
                    return true;
                }
                return false;
            }
            if (intersectingVerticalAndRegular(this, other) != null) {
                return true;
            }
            return false;
        }
        if (horizLine(this)) {
            if (horizLine(other)) {
                return false;
            }
            if (verticalLine(other)) {
                if (isIntersectingVerticalAndHoriz(other, this) != null) {
                    return true;
                }
                return false;
            }
            if (intersectingHorizAndRegular(this, other) != null) {
                Point point = intersectingHorizAndRegular(this, other);
                return true;
            }
            return false;
        }
        if (horizLine(other) || verticalLine(other)) {
            if (intersectingVerticalAndRegular(other, this) != null) {
                return true;
            }
            return false;
        }
        double mOther = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
        double mThis = (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
        double xIntersection = (mThis * this.start.getX() - this.start.getY()
                + other.start.getY() - mOther * other.start.getX()) / (mThis - mOther);
        double yIntersection = mThis * xIntersection - mThis * this.start.getX() + this.start.getY();
        if ((xIntersection <= Math.max(this.start.getX(), this.end.getX())
                && (xIntersection >= Math.min(this.start.getX(), this.end.getX())
                && (xIntersection <= Math.max(other.start.getX(), other.end.getX())
                && (xIntersection >= Math.min(other.start.getX(), other.end.getX())))))) {
            return true;
        }
        return false;
    }

    /**
     * find intersection between two lines.
     *
     * @param other the other line which we find an intersecting point with him
     * @return Returns the intersection point if the lines intersect,
     * and null otherwise.
     */

    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            if (verticalLine(this)) {
                if (verticalLine(other)) {
                    return null;
                }
                if (horizLine(other)) {
                    if (isIntersectingVerticalAndHoriz(other, this) != null) {
                        return isIntersectingVerticalAndHoriz(other, this);
                    }
                    return null;
                }
                if (intersectingVerticalAndRegular(this, other) != null) {
                    return intersectingVerticalAndRegular(this, other);
                }
                return null;
            }
            if (horizLine(this)) {
                if (horizLine(other)) {
                    return null;
                }
                if (verticalLine(other)) {
                    if (isIntersectingVerticalAndHoriz(other, this) != null) {
                        return isIntersectingVerticalAndHoriz(other, this);
                    }
                    return null;
                }
                Point point = intersectingHorizAndRegular(this, other);
                if (intersectingHorizAndRegular(this, other) != null) {
                    return intersectingHorizAndRegular(this, other);
                }
                return null;
            }

            double mOther = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
            double mThis = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            double xIntersection = (mThis * this.start.getX() - this.start.getY()
                    + other.start.getY() - mOther * other.start.getX()) / (mThis - mOther);
            double yIntersection = mThis * xIntersection - mThis * this.start.getX() + this.start.getY();
            Point intersection = new Point(xIntersection, yIntersection);
            return intersection;
        }
        return null;
    }

    /**
     * check equality between two lines.
     *
     * @param other the other line which we check equality
     * @return return true if the lines are equal, false otherwise.
     */
    public boolean equalsLine(Line other) {
        if ((other.start == this.start) && (other.end == this.end)) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect rectangle
     * @return the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line line = new Line(this.start, this.end);
        List intersections = rect.intersectionPoints(line);
        if (intersections.size() == 0) {
            return null;
        }
        Point closest = (Point) intersections.get(0);
        if (closest == null) {
            return null;
        }
        double minDistance = this.start.distance((Point) intersections.get(0));
        for (int i = 1; i < intersections.size(); i++) { //Loop starts from 1 because the initialization of closest.
            if (this.start.distance((Point) intersections.get(i)) < minDistance) {
                closest = (Point) intersections.get(i);
                minDistance = this.start.distance((Point) intersections.get(i));
            }
        }
        return closest;
    }

    /**
     * checks if the point in this line.
     * @param point the checked point.
     * @return the checked line.
     */
    public boolean isOnTheLine(Point point) {
        if (horizLine(this)) {
            if ((Math.max(this.getStart().getX(), this.getEnd().getX()) >= (int) point.getX())
                    && (Math.min(this.getStart().getX(), this.getEnd().getX()) <= (int) point.getX()
                    && (point.getY() == this.getEnd().getY()))) {
                return true;
            }
        }
        if (verticalLine(this)) {
            if ((Math.max(this.getStart().getX(), this.getEnd().getX()) >= (int) point.getX())
                     && (Math.min(this.getStart().getX(), this.getEnd().getX())
                    <= (int) point.getX() && (point.getX() == this.getEnd().getX()))) {
                return true;
            }
        }
        double mThis = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        if (mThis == 0) {
            return false;
        }
        double left = point.getY() - this.getStart().getY();
        double right = mThis * (point.getX() - this.getStart().getX());
        return left == right;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.drawLine((int)this.start.getX(), (int)this.start.getY(), (int)this.end.getX(), (int)this.end.getY());
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}