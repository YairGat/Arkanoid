import java.awt.Color;
import java.util.ArrayList;
/**
 * Class that store information about levels.
 */
public class Green3 implements LevelInformation {
    /**
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }
    /**
     * @return The initial velocity of each ball
     *      Note that initialBallVelocities().size() == numberOfBalls()
     */
    @Override
    public ArrayList<Velocity> initialBallVelocities() {
        return null;
    }
    /**
     * @return paddle speed'  unused in my code.
     */
    @Override
    public int paddleSpeed() {
        return 0;
    }
    /**
     * @return paddle width.
     */
    @Override
    public int paddleWidth() {
        return 90;
    }
    /**
     * @return the level name will be displayed at the top of the screen.
     */
    @Override
    public String levelName() {
        return "Green 3";
    }
    /**
     * @return Returns a sprite with the background of the level.
     */
    @Override
    public SpriteCollection getBackground() {
        SpriteCollection spriteCollection = new SpriteCollection();
        spriteCollection.addSprite(new Block(new Rectangle(new Point(0,0), 800, 600), Color.green));
        return spriteCollection;
    }
    /**
     * @return The Blocks that make up this level, each block contains
     *      its size, color and location.
     */
    @Override
    public ArrayList<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        int width = 800;
        int height = 600;
        Block b1;
        for (int i = 0; i < 10; i++) {
            b1 = new Block(new Rectangle(new Point(width - 73 - i * 50, 100), 50, 20), Color.gray);
            blocks.add(b1);
        }
        for (int i = 0; i < 9; i++) {
            b1 = new Block(new Rectangle(new Point(width - 73 - i  *  50, 120), 50, 20), Color.red);
            blocks.add(b1);
        }
        for (int i = 0; i < 8; i++) {
            b1 = new Block(new Rectangle(new Point(width - 73 - i * 50, 140), 50, 20), Color.yellow);
            blocks.add(b1);
        }
        for (int i = 0; i < 7; i++) {
            b1 = new Block(new Rectangle(new Point(width - 73 - i * 50, 160), 50, 20), Color.BLUE);
            blocks.add(b1);
        }
        for (int i = 0; i < 6; i++) {
            b1 = new Block(new Rectangle(new Point(width - 73 - i * 50, 180), 50, 20), Color.WHITE);
            blocks.add(b1);
        }

        return blocks;
    }
    /**
     * @return Number of blocks that should be removed.
     *      before the level is considered to be "cleared".
     *      This number should be <= blocks.size();
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 57;
    }
    /**
     * @return List of all the ball in the game.
     */
    @Override
    public ArrayList<Ball> allBalls() {
        ArrayList<Ball> balls = new ArrayList<>();
        Ball ball = new Ball(300, 350, 5, Color.white);
        ball.setVelocity(2, -3);
        balls.add(ball);
        Ball ball2 = new Ball(350, 350, 5, Color.white);
        balls.add(ball2);
        ball2.setVelocity(3, -2);
        return balls;
    }
}
