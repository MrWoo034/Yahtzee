package View;

import Model.Dice;

import java.util.ArrayList;
import java.util.EventObject;

public class RollEvent extends EventObject{

    private ArrayList<Dice> dice;

    /** Requirement 3.XX.XX
     *
     * Constructs a Roll Event.
     *
     * Takes as an input the dice that are shown
     * on the board initially and passes them up
     * to the model to be rolled.
     *
     * @param source The object on which the Event initially occurred.
     * @param dice The array of dice shown on the board.
     *             Should innclude all five dice, and the
     *             keeper status associated with each.
     *
     * @throws IllegalArgumentException if source is null.
     */
    public RollEvent(Object source, ArrayList<Dice> dice) {
        super(source);
        this.dice = dice;
    }

    public ArrayList<Dice> getDice() {
        return this.dice;
    }
}
