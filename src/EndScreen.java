import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen implements Animation {
    private boolean stop;
    private int finalScore;
    private KeyboardSensor keyboard;
    private int isItWin;

    public EndScreen(int finalScore, int isItWin, KeyboardSensor keyboard) {
        this.stop = false;
        this.finalScore = finalScore;
        this.isItWin = isItWin;
        this.keyboard = keyboard;
    }

    public EndScreen(int finalScore, int isItWin) {
        this.stop = false;
        this.finalScore = finalScore;
        this.isItWin = isItWin;
    }

    public void doOneFrame(DrawSurface d) {
        if (isItWin != 1) {
            d.drawText(10, d.getHeight() / 2, ("Game Over! Your score is: " + this.finalScore), 32);
        }
        else {
            d.drawText(10, d.getHeight() / 2, ("You Win! Your score is: " + this.finalScore), 32);
        }
    }
    public boolean shouldStop() { return this.stop; }
}
