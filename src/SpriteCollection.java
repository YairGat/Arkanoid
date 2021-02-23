import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Yair Gat <ID:324184036,User:gatyair>
 * Ball class includes fields and methods that reprsent balls.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * @return sprites size.
     */
    public int getSize() {
        return sprites.size();
    }

    /**
     * @param sprites list of sprities.
     */
    public SpriteCollection(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * build the sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * gets sprite and add it to the list.
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     *      call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        try {
            for (Sprite s : this.sprites) {
                try {
                    s.timePassed();
                } catch (Exception exp){
                    int yair = 1;
                }
            }
        } catch (Exception exp) {
            int yair2 = 1;
        }

    }

    /**
     *      call drawOn(d) on all sprites.
     * @param d surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }

}