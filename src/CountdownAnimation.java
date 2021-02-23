import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 *  for numOfSeconds seconds, and on top of them it will show
 *  a countdown from countFrom back to 1, where each number will
 *  appear on the screen for (numOfSeconds / countFrom) seconds, before
 *  it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    double numOfSecond;
    int countFrom;
    SpriteCollection gameScreen;
    boolean stop;
    private GUI gui;

    /**
     * Constructor.
     * @param numOfSeconds the num of second of each frame.
     * @param countFrom from which number the counter start.
     * @param gameScreen sprite collection
     * @param gui the screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, GUI gui) {
        this.numOfSecond = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.gui = gui;

    }

    /**
     *
     * @param d do one frame on d surface.
     */
    public void doOneFrame(DrawSurface d) {
         Sleeper sleeper = new Sleeper();
         long startTime = System.currentTimeMillis();
         long usedTime = System.currentTimeMillis() - startTime;
        for (int i = countFrom; i > 0; --i) {
            d = this.gui.getDrawSurface();
            this.gameScreen.drawAllOn(d);
            usedTime = System.currentTimeMillis() - startTime;
            d.setColor(Color.gray);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(i), 50);
            gui.show(d);
            if (usedTime < numOfSecond / countFrom) {
            sleeper.sleepFor((long) (1000 * numOfSecond / countFrom));
            }
            startTime = System.currentTimeMillis();
        }
        this.stop = true;
    }

    /**
     * @return if the game should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}