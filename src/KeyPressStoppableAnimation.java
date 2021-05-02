import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * KeyPressStoppableAnimation 
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor sensor
     * @param key key
     * @param animation animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * @param d do one frame on d surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
         if (this.sensor.isPressed(key) && this.isAlreadyPressed) { }
         else if (this.sensor.isPressed(key))  { this.stop = true; }
         else {
             this.isAlreadyPressed = false;
         }
    }
    /**
     * @return if the animation should stop.
     */
    @Override
    public boolean shouldStop() { return this.stop; }
}
