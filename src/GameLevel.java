import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 * Ball class includes fields and methods that reprsent balls.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter removed;
    private Counter ballsRemoved;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    LevelInformation levelInformation;
    AnimationRunner ar;
    KeyboardSensor ks;
    /**
     * Constuctor of Game object.
     * @param sprites Sprite collection.
     * @param environment game environment of the game
     */
    public GameLevel(SpriteCollection sprites, GameEnvironment environment, LevelInformation levelInformation) {
        this.sprites = sprites;
        this.environment = environment;
        this.gui = new GUI("title", 800, 600);
    }

    /**
     * Constuctor of Game object to the case of no values accepted.
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = ar.getGui();
        this.removed = new Counter();
        this.ballsRemoved = new Counter();
        this.blockRemover = new BlockRemover(this, getRemoved());
        this.ballRemover = new BallRemover(this, getBallsRemoved());
        this.score = new Counter();
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.runner = new AnimationRunner(this.gui);
        this.running = true;
        this.keyboard = gui.getKeyboardSensor();
        this.levelInformation = levelInformation;
        this.ar = ar;
        this.ks = ks;
    }
    /**
     * Constuctor of Game object to the case of no values accepted.
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, ScoreTrackingListener scoreTrackingListener) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = ar.getGui();
        this.removed = new Counter();
        this.ballsRemoved = new Counter();
        this.blockRemover = new BlockRemover(this, getRemoved());
        this.ballRemover = new BallRemover(this, getBallsRemoved());
        this.score = scoreTrackingListener.getCurrentScore();
        this.scoreTrackingListener = scoreTrackingListener;
        this.runner = new AnimationRunner(this.gui);
        this.running = true;
        this.keyboard = gui.getKeyboardSensor();
        this.levelInformation = levelInformation;
        this.ar = ar;
        this.ks = ks;
    }
    /**
     * @return the rest of the block that are still on the screen.
     */
    public Counter getRemain() {
        //5 because 4 frames and 1 pedal.
        return new Counter(this.environment.getCollidables().size() - 5);
    }
//    public ScoreTrackingListener getScoreTrackingListener() {
//        return this.scoreTrackingListener;
//    }
    /**
     * @return the current score.
     */
    public Counter getScore() {
        return score;
    }

    /**
     * @return count of removed balls.
     */
    public Counter getBallsRemoved() {
        return ballsRemoved;
    }

    /**
     * @return get the count of removed balls.
     */
    public Counter getRemoved() {
        return removed;
    }

    /**
     * Get action of game environment.
     * @return game environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Get collidable and add it to game environment.
     * @param c collidable that we gonna add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Get sprite and add it to the sprites collection of the game.
     * @param s the sprite that we gonna add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * Build the frame.
     * @param ballListener the ball lisnter of the blocks
     */
    public void buildFrame(HitListener ballListener) {
        Block b1 = new Block(new Rectangle(new Point(0, 30), 20, 600), Color.gray);
        Block b2 = new Block(new Rectangle(new Point(0, 30), 800, 20), Color.gray);
        Block b3 = new Block(new Rectangle(new Point(780, 30), 20, 600), Color.gray);
        DeathRegion deathRegion = new DeathRegion(new Rectangle(new Point(0, 580), 800, 20), Color.gray);
        deathRegion.addHitListener(ballListener);
        Block[] blocks = {b1, b3, deathRegion, b2};
        for (Block block:blocks) {
            block.addToGame(this);
        }
    }

    /**
     * Add ball's to our game.
     */
    public void addBalls() {
        Ball ball = new Ball(150, 350, 5, Color.blue);
        ball.setVelocity(3, 5);
        ball.addToGame(this);
        ball.setVelocity(-5, -2);
        Ball ball2 = new Ball(100, 300, 5, Color.black);
        ball2.addToGame(this);
        ball2.setVelocity(5, -2);
        Ball ball3 = new Ball(200, 300, 5, Color.darkGray);
        ball3.addToGame(this);
        ball3.setVelocity(5, 2);
    }


    public void buildLevel(HitListener hitListener) {
        this.sprites.getSprites().addAll(this.levelInformation.getBackground().getSprites());
        for (Ball ball: this.levelInformation.allBalls()) {
            ball.addToGame(this);
        }
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(hitListener);
            block.addHitListener(this.scoreTrackingListener);
        }

    }
    /**
     *Initialize a new game: create the Blocks and Ball (and Paddle)
     *and add them to the game.
     */
    public void initialize() {

            buildLevel(this.blockRemover);
            Paddle paddle = new Paddle(this.gui, this.levelInformation.paddleWidth());
            paddle.addToGame(this);
            buildFrame(this.ballRemover);
            Block scoreBoard = new Block(new Rectangle(new Point(0, 0), 800, 30), Color.white);
            ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreTrackingListener.getCurrentScore(), scoreBoard, this.levelInformation.levelName());
            scoreIndicator.addToGame(this);
    }

    /**
     * Remove colidable c from the colidables list.
      * @param c the removed collidable.
     */
    public void removeCollidable(Collidable c) {

            getEnvironment().getCollidables().remove(c);

    }

    /**
     * Remove spirite c from the spirites list.
     * @param s the removed spiritw.
     */
    public void removeSprite(Sprite s) {

        this.sprites.getSprites().remove(s);
    }

    /**
     * @return the ScoreTracking Listener.
     */
    public ScoreTrackingListener getScoreTrackingListener() {
        return scoreTrackingListener;
    }

    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        d.setColor(Color.blue);
        this.sprites.notifyAllTimePassed();
        if (getRemain().getNumber() == 0) {
            getScoreTrackingListener().getCurrentScore().increase(100);
            System.out.println(getScoreTrackingListener().getCurrentScore().getNumber());
//            System.out.println("The game is over");
//            gui.close();
            this.running = false;
        }
        if (this.getBallsRemoved().getNumber() == levelInformation.numberOfBalls()) {
            System.out.println(getScoreTrackingListener().getCurrentScore().getNumber());
//            System.out.println("The game is over");
//            gui.close();
            this.running = false;
        }
        this.keyboard = gui.getKeyboardSensor();
        if (this.keyboard.isPressed("p")) {
            Animation a2 = new PauseScreen(); // also an Animation
            Animation a2k = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "space", a2);
            ar.run(a2k);
        }
    }
    public boolean shouldStop() {
       return !this.running;
    }
    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(5, 3, this.sprites, this.gui)); // countdown before turn starts.
        this.runner.run(this);
    }
}