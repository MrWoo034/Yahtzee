package Model;


public class SixesBox extends ScoringBox {

    public SixesBox() {
        this.maxValue = 30;
        this.isOpen = true;
        this.finalScore = 0;
        this.potentialScore = null;
    }

    @Override
    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score of sixes box
     * on the score sheet.  Maximum score value is
     * 30.  Calculates the cumulative score of all die
     * with a face value of six.
     *
     * @param totals Requires the array to hold
     *               the totals of the face value
     *               of all die on the board.
     *               Each element should hold the
     *               value of a different face
     *               value.
     *
     * @throws InvalidTotalException
     */
    protected void calcPotential(int[] totals) throws InvalidTotalException {

        // reset potential to 0, incase it
        // was calculated on the last turn
        this.potentialScore = 0;

        if (totals.length < 6) {
            throw new InvalidTotalException("Invalid Dice Totals in SixesBox", totals);
        }

        this.potentialScore = 6 * totals[5];

        if (this.potentialScore > maxValue ) {
            throw new InvalidTotalException("Potential Score exceeds Max Value in Sixes Box", totals);
        }
    }
}
