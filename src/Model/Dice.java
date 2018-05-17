package Model;

import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable{
    static final Integer MAX_VALUE = 6;
    static final Integer MIN_VALUE = 1;
    private boolean isKeeper;
    private Integer currentValue;
    private static Random rand = new Random(System.currentTimeMillis());

    /**
     * Generates a new dice object, setting the
     * currentValue to a new random Integer between
     * 1 and 6 (to simulate a six sided die).  The
     * new die is seeded off the current system time
     * in milliseconds to help stimulate randomness.
     */

    public Dice() {
        currentValue = rand.nextInt(MAX_VALUE - MIN_VALUE)+MIN_VALUE;

    }

    public boolean getKeeper() {
        return this.isKeeper;
    }

    public void setKeeper(boolean isKeeper) {
        this.isKeeper = isKeeper;
    }

    public Integer getCurrentValue() {
        return this.currentValue;
    }

    protected void reroll() {
        currentValue = rand.nextInt(MAX_VALUE)+MIN_VALUE;
    }

}
