package Model;

import java.util.ArrayList;

public class ThreesBox extends ScoringBox{

    public ThreesBox() {
        this.maxValue = 15;
        this.isOpen = true;
        this.finalScore = 0;
        this.potentialScore = null;
    }

    @Override
    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score for the
     * threes box on the score sheet.  Maximum
     * allowable score is 15.  Should calculate
     * the cumulative value of all dice with a
     * face value of 3 on them.
     *
     * @param totals Requires the array to hold
     *               the totals of the face value
     *               of all die on the board.
     *               Each element should hold the
     *               value of a different face
     *               value.
     */
    protected void calcPotential(int[] totals) throws InvalidTotalException{

        // reset potential to 0, incase it
        // was calculated on the last turn
        this.potentialScore = 0;

        if (totals.length != 6) {
            throw new InvalidTotalException("Invalid Dice Totals in ThreesBox", totals);
        }
        this.potentialScore = 3 * totals[2];

        if (this.potentialScore > this.maxValue) {
            throw new InvalidTotalException("Potential Score exceeds Max Value in ThreesBox", totals);
        }
    }
}
