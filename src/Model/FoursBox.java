package Model;

import java.util.ArrayList;

public class FoursBox extends ScoringBox{

    public FoursBox() {
        this.maxValue = 20;
        this.isOpen = true;
        this.finalScore = 0;
        this.potentialScore = null;
    }

    @Override
    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score of the fours
     * box on the score sheet.  Maximum value to be
     * calculated is 20.  Should calculate the cumulative
     * score of all dice with face value of four.
     *
     * @param totals Requires the array to hold
     *               the totals of the face value
     *               of all die on the board.
     *               Each element should hold the
     *               value of a different face
     *               value.
     *
     */
    protected void calcPotential(int[] totals) throws InvalidTotalException {

        // reset potential to 0, incase it
        // was calculated on the last turn
        this.potentialScore = 0;

        if (totals.length < 6) {
            throw new InvalidTotalException("Invalid Dice Total from FourBox", totals);
        }

        this.potentialScore = 4 * totals[3];

        if (this.potentialScore > maxValue) {
            throw new InvalidTotalException("Total of Fours exceeds Max Value", totals);
        }
    }
}
