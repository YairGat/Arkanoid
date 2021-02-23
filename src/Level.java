import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Level implements LevelInformation{

    private List<Block> blocks;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private String levelName;
    private int paddleWidth;
    private int blockStartX;
    private int blockStarty;
    private Map<String,String> levelInfo;

    public Level(BufferedReader bufferedReader) {
        this.levelInfo = getLevelInfo(bufferedReader);
        this.levelName = this.levelInfo.get("level_name");
        this.paddleSpeed = Integer.parseInt(this.levelInfo.get("paddle_speed"));
        this.paddleWidth = Integer.parseInt(this.levelInfo.get("paddle_width"));
        this.blockStarty = Integer.parseInt(this.levelInfo.get("blocks_start_y"));
        this.blockStartX = (Integer.parseInt(this.levelInfo.get("blocks_start_x")));
        this.velocities = setVelocity(this.levelInfo.get("ball_velocities"));


    }
    public List<Velocity> setVelocity(String text){
        String[] velocity = text.split(" ");
        List<Velocity> velocityList = new ArrayList<>();
        Velocity v;
        for (String string: velocity) {
            v = Velocity.fromAngleAndSpeed(Integer.parseInt(string.split(",")[0]), Integer.parseInt(string.split(",")[1]));
            velocityList.add(v);
        }
        return velocityList;
    }

    public Map<String, String> getLevelInfo(BufferedReader bufferedReader) {
        Map<String, String> levelInfo = new TreeMap<>();
        List<List<Character>> blocks = null;
        try {
            String[] parts;
            for (String line = bufferedReader.readLine(); !line.equals("END_LEVEL"); line = bufferedReader.readLine()) {
                if (line.equals("START_BLOCKS")) {
                    blocks = getBlocks(bufferedReader);
                }
                else {
                    parts = line.split(":");
                    levelInfo.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levelInfo;
    }
    private static List<List<Character>> getBlocks(BufferedReader is) {
        List<List<Character>> blocks2 = new LinkedList<>();
        try {
            for (String line = is.readLine(); !line.equals("END_BLOCKS"); line = is.readLine()) {
                List<Character> l = new LinkedList<>();
                for (Character ch: line.toCharArray()) {
                    l.add(ch);
                }
                blocks2.add(l);
            }
        } catch (IOException e) {
            System.out.println("failed to read from file");
            System.exit(1);
        }
        return blocks2;
    }

    /**
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.velocities.size();
    }

    /**
     * @return The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     */
    @Override
    public ArrayList<Velocity> initialBallVelocities() {
        return (ArrayList<Velocity>) this.velocities;
    }

    /**
     * @return paddle speed'  unused in my code.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return paddle width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return Returns a sprite with the background of the level.
     */
    @Override
    public SpriteCollection getBackground() {
        return null;
    }

    /**
     * @return The Blocks that make up this level, each block contains
     * its size, color and location.
     */
    @Override
    public ArrayList<Block> blocks() {
        return (ArrayList<Block>) this.blocks;
    }

    /**
     * @return Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }

    /**
     * @return List of all the ball in the game.
     */
    @Override
    public ArrayList<Ball> allBalls() {
        return null;
    }
}
