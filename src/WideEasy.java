import java.awt.Color;
import java.util.ArrayList;
/**
 * WideEasy contains methods and field that represents the Wide Easy level.
 */
public class WideEasy implements  LevelInformation {
    /**
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * @return The initial velocity of each ball
     *      Note that initialBallVelocities().size() == numberOfBalls()
     */
    @Override
    public ArrayList<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocities.add(new Velocity(4,-2));
        }
        return velocities;
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
        return 300;
    }
    /**
     * @return the level name will be displayed at the top of the screen.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }
    /**
     * @return Returns a sprite with the background of the level.
     */
    @Override
    public SpriteCollection getBackground() {
        SpriteCollection spriteCollection = new SpriteCollection();
        spriteCollection.addSprite(new Block(new Rectangle(new Point(0, 0), 800, 600), Color.white));
        //        spriteCollection.addSprite(new Ball(230, 230,40, Color.yellow));
//        spriteCollection.addSprite(new Ball(230, 230,80, Color.ORANGE));
//        for (int i = 0; i < 300 ; i++){
//           spriteCollection.addSprite(new Ball(new Point(120,120), 60 + i/100, Color.yellow));
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
            if (i < 2) {
                color = Color.CYAN;
            }
            else if (i < 4) {
                color = Color.PINK;
            }
            else if (i < 6) {
                color = Color.blue;
            }
            else if (i < 9) {
                color = Color.green;
            }
            else if (i < 11) {
                color = Color.yellow;
            }
            else if (i < 13) {
                color = Color.orange;

            }
            else {
                color = Color.red;

            }
            blocks.add(new Block(new Rectangle(new Point(740 - 51*i - 5, 200),51, 30), color));

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
        return 15;
    }
    /**
     * @return List of all the ball in the game.
     */
    @Override
    public ArrayList<Ball> allBalls() {
        ArrayList<Ball> balls = new ArrayList<Ball>();
        Ball ball;
        Ball ball1;
        for (int i = 0; i < 5; i++) {
            ball = new Ball(250 + 20 * i , 350 - 20 * i, 5, Color.gray);
            ball.setVelocity(2,-3);
            balls.add(ball);
            ball1 = new Ball(400 + 20 * i, 270 + 20 * i, 5 , Color.gray);
            balls.add(ball1);
            ball1.setVelocity(2, -3);
        }
        return balls;
    }
}
