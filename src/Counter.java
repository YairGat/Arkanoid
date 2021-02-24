/**
 * Counter class.
 */
public class Counter {

    private int number;

    /**
     * Counter constructor.
     */
    public Counter() {
        this.number = 0;
    }

    /**
     * Counter constructor.
     * @param number intliazes an number
     */
    public Counter(int number){
        this.number = number;
    }

    /**
     * @param number set counter equals to number.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the value of counter.
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number   add number to current count.
     */
    public void increase(int number) {
        setNumber(getNumber() + number);
    }

    /**
     * @param number subtract number from current count.
     */
    public void decrease(int number) {
        setNumber(getNumber() - number);
    }
    /**
     * @return  get current count.
     */
    public int getValue() {
        return getNumber();
    }
}
