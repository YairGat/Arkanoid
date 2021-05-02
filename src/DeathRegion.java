import java.awt.Color;
import java.util.ArrayList;

/**
 * DeathRegion represents the bottom part of the screen amd used to hit notifer.
 */

public class DeathRegion extends Block implements HitNotifier {


    private ArrayList<HitListener> hitListeners;
    private Rectangle block;
    private Color color;

    /**
     * Constructor of Block.
     * @param block the shape of the block.
     * @param color the color of the block
     */
    public DeathRegion(Rectangle block, Color color) {
        super(block, color);
    }

    @Override
    public void timePassed() {
        super.timePassed();
    }
}
