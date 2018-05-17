package Model;

public class ChanceBox extends ScoringBox{

    public ChanceBox() {
        this.maxValue = 30;
        this.isOpen = true;
        this.potentialScore = 0;
        this.finalScore = 0;
    }

    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score for the chance box.
     * Chance is a cumulative score of all dice on the
     * board.
     *
     * @param totals Requires an integer array that gives
     *               certain totals.  Scoring or combo
     *               boxes require the array to give a
     *               count of the number of dice at a certain
     *               face value, where totals[0] would be
     *               the number of Ones, totals[1] would be
     *               the number of Twos, and so forth.
     *
     *               Total boxes require the array to give
     *               the current totals of the boxes to be
     *               summed for the total.
     *               e.g. Lower Total would require totals[0]
     *               to contain the value of the 3 Of A Kind
     *               row, totals[1] the value of the 4 Of A
     *               Kind row, and so forth.
     *
     *               See individual boxes for more details.
     *
     * @throws InvalidTotalException
     */
    @Override
    protected void calcPotential(int[] totals) throws InvalidTotalException {
        // reset potential to 0, incase it
        // was calculated on the last turn
        this.potentialScore = 0;

        if (totals.length != 6) {
            throw new InvalidTotalException("Invalid dice total in Chance Box", totals);
        }

        for (int i = 0; i < totals.length; i++){
            this.potentialScore += ((i+1) * totals[i]);
        }

        if (this.potentialScore > this.maxValue) {
            throw new InvalidTotalException("Potential Score exceeds Max Value in Chance Box", totals);
        }
    }
}
