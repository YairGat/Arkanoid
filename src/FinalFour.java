import org.w3c.dom.css.Rect;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Class that store information about levels.
 */
public class FinalFour implements LevelInformation {
    /**
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 3;
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
        return 100;
    }
    /**
     * @return the level name will be displayed at the top of the screen.
     */
    @Override
    public String levelName() {
        return "Final Four";
    }
    /**
     * @return Returns a sprite with the background of the level.
     */
    @Override
    public SpriteCollection getBackground() {
        SpriteCollection spriteCollection = new SpriteCollection();
        spriteCollection.addSprite(new Block(new Rectangle(new Point(0, 0),800, 600),Color.blue));
        Ball ball = new Ball(60, 450, 15, Color.lightGray);
        spriteCollection.addSprite(ball);
        Ball ball4 = new Ball(70, 460, 28, Color.lightGray);
        spriteCollection.addSprite(ball4);
        Ball ball1 = new Ball(80, 430, 20, Color.gray);
        spriteCollection.addSprite(ball1);
        Ball ball2 = new Ball(100, 420, 28, Color.gray);
        spriteCollection.addSprite(ball2);
        Ball ball3 = new Ball(120, 450, 28, Color.gray);
        spriteCollection.addSprite(ball3);
//        for (int i = 0; i < 8; i++) {
//            spriteCollection.addSprite(new Line(new Point(70, 450), new Point(50,600)));
//
//        }
        return spriteCollection;
    }

    /**
     * @return The Blocks that make up this level, each block contains
     *      its size, color and location.
     */
    @Override
    public ArrayList<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<Block>();
        Color color = Color.black;
        for (int i = 0; i < 15; i++) {
            blocks.add(new Block(new Rectangle(new Point(740 - 51*i - 5, 120),51, 30), Color.gray));
            blocks.add(new Block(new Rectangle(new Point(740 - 51*i - 5, 150),51, 30), Color.red));
            blocks.add(new Block(new Rectangle(new Point(740 - 51*i - 5, 180),51, 30), Color.yellow));
            blocks.add(new Block(new Rectangle(new Point(740 - 51*i - 5, 210),51, 30), Color.green));
            blocks.add(new Block(new Rectangle(new Point(740 - 51*i - 5, 240),51, 30), Color.white));
            blocks.add(new Block(new Rectangle(new Point(740 - 51*i - 5, 270),51, 30), Color.pink));
            blocks.add(new Block(new Rectangle(new Point(740 - 51*i - 5, 300),51, 30), Color.cyan));
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
        return 0;
    }
    /**
     * @return List of all the ball in the game.
     */
    @Override
    public ArrayList<Ball> allBalls() {
        ArrayList<Ball> balls = new ArrayList<>();
        Ball ball = new Ball(300, 380, 5, Color.white);
        ball.setVelocity(-2, -3);
        balls.add(ball);
        Ball ball2 = new Ball(350, 350, 5, Color.white);
        ball2.setVelocity(2, -3);
        balls.add(ball2);
        Ball ball3 = new Ball(400, 380, 5, Color.white);
        ball3.setVelocity(-2, -2);
        balls.add(ball3);
        return balls;
    }
}
