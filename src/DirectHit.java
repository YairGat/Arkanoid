import biuoop.DrawSurface;

import javax.swing.text.html.HTMLDocument;
import java.awt.Color;
import java.time.temporal.ValueRange;
import java.util.ArrayList;

/**
 *  DirectHit contains methods and field that represents the DirectHit level.
 */
public class DirectHit implements LevelInformation {
    private int numberOfBalls;
    private ArrayList<Velocity> initialBallVelocity;
    private int PaddleSpeed;
    String levelName;
    private String getBackGround;
    private ArrayList<Block> blocks;
    private int numberOfBlocksToRemove;

    public DirectHit() {

    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public ArrayList<Velocity> initialBallVelocities() {
        Velocity ball1 = new Velocity(2.0,-4);
        ArrayList<Velocity> ballsVelocitySpeed = new ArrayList<Velocity>();
        ballsVelocitySpeed.add(ball1);
        return ballsVelocitySpeed;
    }
    @Override
    public ArrayList<Ball> allBalls() {
        Ball ball = new Ball(400, 450, 5, Color.white);
        ArrayList<Velocity> velocities = initialBallVelocities();
        ball.setVelocity(velocities.get(0));
        ArrayList<Ball> balls = new ArrayList<Ball>();
        balls.add(ball);
        return balls;
    }
    /**
     * @return paddle speed'  unused in my code.
     */
    @Override
    public int paddleSpeed() {
        return 4;
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
        return "Direct Hit";
    }
    /**
     * @return Returns a sprite with the background of the level.
     */
    @Override
    public SpriteCollection getBackground() {
        SpriteCollection spriteCollection = new SpriteCollection();
        spriteCollection.addSprite(new Block(new Rectangle(new Point(0, 0),800, 600), Color.black));
        spriteCollection.addSprite(new Line(250, 150, 550, 150));
        spriteCollection.addSprite(new Line(400, 0, 400, 300));
        spriteCollection.addSprite(new Ball(new Point(400, 150), 50, Color.blue));
        spriteCollection.addSprite(new Ball(new Point(400, 150), 70, Color.blue));
        spriteCollection.addSprite(new Ball(new Point(400, 150), 90, Color.blue));
        spriteCollection.addSprite(new Ball(new Point(400, 150), 110, Color.blue));
        return spriteCollection;

    }
    /**
     * @return Number of blocks that should be removed.
     *      before the level is considered to be "cleared".
     *      This number should be <= blocks.size();
     */
    @Override
    public ArrayList<Block> blocks() {
        Block block = new Block(new Rectangle(new Point(380, 135), 40, 40), Color.red);
        ArrayList<Block> blocks = new ArrayList<Block>();
        blocks.add(block);
        return blocks;
    }
    /**
     * @return List of all the ball in the game.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
