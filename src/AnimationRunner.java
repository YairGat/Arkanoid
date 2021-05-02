import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.security.Guard;

/**
 * AnimationRunner class contains function for run animations.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * Animation runner constructor.
     * @param gui the screnn.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
    }

    /**
     * @return the gui.
     */
    public GUI getGui() {
        return gui;
    }
    /**
     * @return the number of frames per second.
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    /**
     * run the animation.
     * @param animation which ran.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / getFramesPerSecond();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
