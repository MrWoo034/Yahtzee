package Model;

import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.ArrayList;

public class TwosBox extends ScoringBox{

    public TwosBox() {
        this.maxValue = 10;
        this.isOpen = true;
        this.finalScore = 0;
        this.potentialScore = null;
    }

    @Override
    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score for the
     * twos box on the score sheet.  Maximum
     * score is a 10, and the calculated total
     * should be the value of all dice with a
     * face value of two added together.
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
            throw new InvalidTotalException("Invalid Dice Total from Twos Box", totals);
        }

        this.potentialScore = 2 * totals[1];

        if (this.potentialScore > maxValue) {
            throw new InvalidTotalException("Potential Score exceeds Max Value in Twos Box", totals);
        }
    }
}
