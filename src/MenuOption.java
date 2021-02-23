/**
 * represent an option in the menu.
 * @param <T> - returned value type
 */
public class MenuOption<T> {
    private String key;
    private String optionName;
    private T returnValue;

    /**
     * constructor.
     * @param key - key need to press
     * @param optionName - the option name
     * @param returnValue - the returned value of the menu
     */
    public MenuOption(String key, String optionName, T returnValue) {
        this.key = key;
        this.optionName = optionName;
        this.returnValue = returnValue;
    }

    /**
     * return the key.
     * @return - key
     */
    public String getKey() {
        return key;
    }

    /**
     * return the return value.
     * @return return value
     */
    public T getReturnValue() {
        return returnValue;
    }

    @Override
    public String toString() {
        return "Press '" + this.key + "' to " + this.optionName;
    }
}
