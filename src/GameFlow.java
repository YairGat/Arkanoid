import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.awt.image.BufferedImageFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Game flow class represents the flow of the game.
 */
public class GameFlow {

    private AnimationRunner ar;
    private KeyboardSensor ks;
    private int finalScore;

    /**
     * @param ar is run the animation.
     */
    public GameFlow(AnimationRunner ar) {
        this.ar = ar;
        this.ks = ks;
        this.finalScore = 0;
    }
    public void runMenu() {
        Menu<String> menu = new MenuAnimation<String>("Menu Title", this.ks, this.ar);
        // the parameters to addSelection are:
        // key to wait for, line to print, what to return
        menu.addSelection("a", "First Choice", "option a");
        menu.addSelection("b", "Second Choice", "option b");
        menu.addSelection("c", "Third Choice", "option c");
        ar.run(menu);
        // A menu with the selections is displayed.
        // Runs until user presses "a", "b"  or "c"

        String result = menu.getStatus();
        // result will contain "option a", "option b"
        // or "option c"
        System.out.println("You chose:" + result);
    }

    public AnimationRunner getAr() {
        return ar;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public KeyboardSensor getKs() {
        return ks;
    }
    public ArrayList<LevelInformation> runAllLevels() {
        ArrayList<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        levels.add(new FinalFour());
        return levels;
    }
    /**
     * @param levels a list that stores the levels.
     */
    public void runLevels(ArrayList<LevelInformation> levels) throws IOException {
        // ...
        int check = 0;
        int indexInLevelInfo = 0;
        GameLevel level;
        GameLevel lastLevel = null;
        int counter = 0;
        for (LevelInformation levelInfo : levels) {
            counter++;
            if (indexInLevelInfo == 0) {
                level = new GameLevel(levelInfo, this.ar);
                lastLevel = level;
            } else {
                level = new GameLevel(levelInfo, this.ar, lastLevel.getScoreTrackingListener());
            }
            level.initialize();
            while (level.getRemain().getNumber() > 0 && (level.getBallsRemoved().getNumber() != levelInfo.numberOfBalls())) {
                level.run();
                this.finalScore = this.finalScore + level.getScore().getNumber();
            }
            if ((level.getBallsRemoved().getNumber() == levelInfo.numberOfBalls())) {
                    break;
            }
            if (counter == levels.size()) {
                check = 1;
            }
            indexInLevelInfo++;
            lastLevel = level;
        }
            this.ks = ar.getGui().getKeyboardSensor();
            Animation a1 = new EndScreen(finalScore, check);
            Boolean rec = isItNewRecord();
            if (rec) {
                newRecord();
            }
            Animation a1k = new KeyPressStoppableAnimation(ar.getGui().getKeyboardSensor(), "space", a1);
            this.ar.run(a1k);
            Animation a2 = new HighScoreAnimation();
            Animation a2k = new KeyPressStoppableAnimation(ar.getGui().getKeyboardSensor(), "space", a2);
            this.ar.run(a2k);

        play();
    }
    public void newRecord() throws IOException {
        File file = new File("highscore");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        try {
            bufferedWriter.write("The highest score so far is: " + this.finalScore);
        } catch (IOException e) {
            System.out.println("can't write in this file!");
        }
        finally {
            bufferedWriter.close();
        }

    }
    public boolean isItNewRecord() throws IOException {
        File file = new File("highscore");
        if (file.createNewFile()) {
            return true;
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        try {
            line = bufferedReader.readLine();
            String[] score = line.split(": ");
            try {
                int rec = Integer.parseInt(score[1]);
                if (rec < this.finalScore) {
                    bufferedReader.close();
                    return true;
                }
            } catch (RuntimeException r) {
                System.out.println("INVAILD FORMAT OF THE FILE.");
            }
        } catch (IOException e) {
            System.out.println("Can't read data from file!");
        }
        bufferedReader.close();
        return false;
    }
    public void play() throws IOException {
        this.ks = getAr().getGui().getKeyboardSensor();
        Menu<Task<Void>> menu = new MenuAnimation<>("ARKNOID", this.ks, this.ar);
        menu.addSelection("s", "Start a new game", new Task<Void>() {
            @Override
            public Void run() throws IOException {
                runLevels(runAllLevels());
                return null;
            }
        });
        menu.addSelection("h", "see high scores", new Task<Void>() {
            @Override
            public Void run() throws IOException {
                Animation a2 = new HighScoreAnimation(); // also an Animation
                Animation a2k = new KeyPressStoppableAnimation(ar.getGui().getKeyboardSensor(), "space", a2);
                ar.run(a2k);
                play();
                return null;
            }
        });
        menu.addSelection("q", "quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        });
        while (true) {
            this.ar.run(menu);
            menu.getStatus().run();
        }
    }
}
