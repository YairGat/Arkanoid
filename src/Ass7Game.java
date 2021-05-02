import biuoop.GUI;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Ass6Game is a main of our game, in this class the game is starting to flow.
 */
public class Ass7Game {
    public static void main(String[] args) throws IOException {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner);
        gameFlow.play();
    }
}
