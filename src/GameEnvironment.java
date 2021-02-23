import java.util.ArrayList;
import java.util.List;
/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 * Ball class includes fields and methods that reprsent balls.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Constuctor for GameEnvironment.
     * @param collidables List of collidables.
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Constuctor for GameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Gets the collidable's list.
     * @return list of collidable.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * set a colldiable as "colidables".
     * @param collidabless list of colidables.
     */
    public void setCollidables(List<Collidable> collidabless) {
        this.collidables = collidabless;
    }

    /**
     *   add the given collidable to the environment.
     * @param c the collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     *      Assume an object moving from line.start() to line.end().
     *      If this object will not collide with any of the collidables
     *      in this collection, return null. Else, return the information
     *      about the closest collision that is going to occur.
     * @param trajectory the ball's trajectory.
     * @return closest intersection point.
     */

    public CollisionInfo getClosestCollision(Line trajectory) {

        ArrayList<CollisionInfo> cI = new ArrayList<CollisionInfo>();
        CollisionInfo collisionInfo = null;
        for (Collidable c : this.collidables) {
            if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) != null) {
                collisionInfo = new CollisionInfo(trajectory.closestIntersectionToStartOfLine(
                        c.getCollisionRectangle()), c);
                cI.add(collisionInfo);
            }
        }
        CollisionInfo min = collisionInfo;
        for (CollisionInfo c : cI) {
            if (c.collisionPoint().distance(trajectory.getStart())
                    <= min.collisionPoint().distance(trajectory.getStart())) {
                min = c;
            }
        }

        return min;
    }
}
