import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;


public class MenuAnimation<T> implements Menu<T> {
    private List<MenuOption<T>> options;
    private T currentStatus;
    private String title;
    private Sprite background = null;
    private boolean stop = false;
    private boolean isAlreadyPressed = true;
    private KeyboardSensor ks;
    private AnimationRunner ar;

    /**
     * constructor.
     * @param title - menu title
     * @param keyboard - the keyboard
     * @param runner - runs the sub menu
     */
    public MenuAnimation(String title, KeyboardSensor keyboard, AnimationRunner runner) {
        this.title = title;
        this.options = new LinkedList<>();
        this.currentStatus = null;
        this.ks = keyboard;
        this.ar = runner;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.stop = false;
        boolean oneIsPressed = false;

        for (MenuOption<T> o: this.options) {
            if (this.ks.isPressed(o.getKey())) {
                this.stop = !this.isAlreadyPressed;
                oneIsPressed = true;
                this.currentStatus = o.getReturnValue();
                break;
            }
        }
        if (oneIsPressed && !isAlreadyPressed) {
            isAlreadyPressed = true;
            return;
        }
        this.isAlreadyPressed = oneIsPressed;
        if (this.background != null) {
            this.background.drawOn(d);
            this.background.timePassed();
        }
        d.setColor(Color.black);
        d.drawText(260, 220, this.title + ":", 20);
        for (int i = 0; i < this.options.size(); i++) {
            d.drawText(260, (30 *  i) + 250, this.options.get(i).toString(), 20);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public T getStatus() {
        return this.currentStatus;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.options.add(new MenuOption<>(key, message, returnVal));
    }


}
