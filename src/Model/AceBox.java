package Model;

import java.util.ArrayList;

public class AceBox extends ScoringBox {

    public AceBox() {
        this.maxValue=5;
        this.isOpen = true;
        this.potentialScore = null;
        this.finalScore = 0;
    }


    @Override
    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score of the Aces
     * row of the score sheet
     *
     * Should only calculate a maximum of five,
     * and should only add up to the value of the
     * aces from the dice passed to the method.
     *
     * The array of integers passed should represent
     * the total number of dice at that face value.
     *
     * For instance, totals[0] should give the total
     * number of dice with face value one on the board
     * currently.
     *
     * @param totals Requires the array to hold
     *               the totals of the face value
     *               of all die on the board.
     *               Each element should hold the
     *               value of a different face
     *               value.
     */
    protected void calcPotential(int[] totals) throws InvalidTotalException {

        // reset potential to 0, incase it
        // was calculated on the last turn
        this.potentialScore = 0;

        if (totals.length != 6) {
            throw new InvalidTotalException("Invalid Dice Total from OnesBox", totals);
        }

        this.potentialScore = 1 * totals[0];

        if (this.potentialScore > maxValue) {
            throw new InvalidTotalException("Potential Value exceeds Max Value in Ones Box", totals);
        }
    }
}
