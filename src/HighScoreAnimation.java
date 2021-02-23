import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HighScoreAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String score;
    private int check;

    public HighScoreAnimation() throws FileNotFoundException {
        this.stop = false;
        this.score = getHighestScore();
        this.check = 0;
    }
    public String getHighestScore() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("highscore")));
        String highest;
        try {
           highest = bufferedReader.readLine().toString();
           check = 1;
        } catch (IOException e) {
           highest = "There is no score yet";
        }
        return highest;
    }

    public void doOneFrame(DrawSurface d) {
        if (this.check == 1) {
            d.drawText(10, d.getHeight() / 2, "The highest score so far is: " + this.score, 32);
        }
        else {
            d.drawText(10, d.getHeight() / 2, this.score, 32);
        }
    }

    public boolean shouldStop() { return this.stop; }


}
