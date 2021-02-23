import biuoop.DrawSurface;
import java.awt.Color;

/**
 * ScoreIndicator is class that use for the representation of the score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Block block;
    private String levelName;
    /**
     * Consturctor of ScoreIndicator.
     * @param score score
     * @param block block
     */
    public ScoreIndicator(Counter score, Block block, String levelName) {
        this.score = score;
        this.block = block;
        this.levelName = levelName;
    }
    /**
     * draw the ball object.
     * @param surface our surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.white);
        surface.drawRectangle((int) this.block.getBlock().getUpperLeft().getX(), (int) this.block.getBlock().getUpperLeft().getY(),
                (int) this.block.getBlock().getWidth(), (int) this.block.getBlock().getHeight());
        surface.fillRectangle((int) this.block.getBlock().getUpperLeft().getX(), (int) this.block.getBlock().getUpperLeft().getY(),
                (int) this.block.getBlock().getWidth(), (int) this.block.getBlock().getHeight());
        surface.setColor(Color.BLACK);
        Point middle = this.block.getBlock().getLowerSide().middle();
        String string = "Score: " + String.valueOf(this.score.getNumber());
        surface.drawText((int) middle.getX() - (int) (string.length() / 2) - 60, (int)middle.getY() - 3, string, 20);
        String nameLevel = "Level Name: " + this.levelName;
        surface.drawText((int) middle.getX() - (int) (string.length() / 2) + 100, (int)middle.getY() - 3, nameLevel, 20);
    }

    /**
     * Do nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * get game and join the block to him.
     * @param game game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
