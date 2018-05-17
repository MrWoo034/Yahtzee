package Model;

import java.util.ArrayList;

public class FivesBox extends ScoringBox{

    public FivesBox() {
        this.maxValue = 25;
        this.isOpen = true;
        this.finalScore = 0;
        this.potentialScore = null;
    }

    @Override
    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score of the
     * fives box.  Maximum score allowable is
     * 25.  Calculates the cumulative total of
     * all dice with face value of five.
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
            throw new InvalidTotalException("Invalid Dice Totals in FivesBox", totals);
        }

        this.potentialScore = 5 * totals[4];

        if (this.potentialScore > this.maxValue) {
            throw new InvalidTotalException("Potential Score exceeds Max Value in FivesBox", totals);
        }
    }
}
