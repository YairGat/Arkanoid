import biuoop.GUI;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Ass6Game uses as a main for our game and start the game.
 */
public class Ass7Game {
    public static void main(String[] args) throws IOException {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner);
        gameFlow.play();
    }
}
