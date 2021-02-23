/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 */
public class CollisionInfo {
    private Point point;
    private Collidable collidable;

    /**
     * constuctor of collison info.
     * @param point collision point
     * @param collidable the object that collidavle.
     */
    public CollisionInfo(Point point, Collidable collidable) {
        this.point = point;
        this.collidable = collidable;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
            return this.collidable;
    }
}