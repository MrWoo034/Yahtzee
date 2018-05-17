package Model;

import java.util.ArrayList;

public class ThreeOfAKindBox extends ScoringBox{

    public ThreeOfAKindBox () {
        this.maxValue = 30;
        this.isOpen = true;
        this.potentialScore = 0;
        this.finalScore = 0;
    }

    @Override
    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score for
     * the three of a kind box.  The maximum
     * allowable score is 30.  The score is
     * calculated by looking for three similar
     * die.  If three die with the same face
     * value exist in the array, the cumulative
     * value of all dice is added as the potential.
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
            throw new InvalidTotalException("Invalid Dice Totals in ThreeOfAKind", totals);
        }

        for (int i =0; i < 6; i++) {
            // if the total dice is greater than
            // or equal to three, we have three
            // of a kind.  Iterate through the array
            // and calculate the potential score

            if (totals[i] >= 3) {

                for (int j = 0; j < 6; j++) {
                    this.potentialScore += ((j+1) * totals[j]); // j is offset by 1.  Multiple of total dice
                }

                // break the for loop, as our
                // potential was calculated
                break;
            }
        }

        if (this.potentialScore == null) {
            this.potentialScore = 0;
        }

        if (this.potentialScore > maxValue) {
            throw new InvalidTotalException("Potential Score exceeds Max Value in ThreeOfAKind", totals);
        }
    }
}
