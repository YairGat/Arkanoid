import java.awt.List;
import java.util.ArrayList;
/**
 * Class that store information about levels.
 */
public interface LevelInformation {
    /**
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball
     *      Note that initialBallVelocities().size() == numberOfBalls()
     */
    ArrayList<Velocity> initialBallVelocities();
    /**
     * @return paddle speed'  unused in my code.
     */
    int paddleSpeed();

    /**
     * @return paddle width.
     */
    int paddleWidth();
    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return Returns a sprite with the background of the level.
     */
    SpriteCollection getBackground();

    /**
     * @return The Blocks that make up this level, each block contains
     *      its size, color and location.
     */
    ArrayList<Block> blocks();

    /**
     * @return Number of blocks that should be removed.
     *      before the level is considered to be "cleared".
     *      This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();

    /**
     * @return List of all the ball in the game.
     */
    public ArrayList<Ball> allBalls();
}