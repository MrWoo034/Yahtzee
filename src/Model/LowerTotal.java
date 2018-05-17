package Model;

import org.omg.CORBA.DynAnyPackage.Invalid;

public class LowerTotal extends ScoringBox{
    public LowerTotal() {
        this.maxValue = 30 + 30 + 25 + 30 + 40 + 50 + 30 + 300;
        this.isOpen = true;
        this.finalScore = 0;
        this.potentialScore = 0;
    }

    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential of the lower total bow
     * on the score sheet.  Requires the total of all
     * scoring categories from the lower half of the
     * score sheet.
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

        if (totals.length != 10) {
            throw new InvalidTotalException("Invalid dice total passed to Lower Total box", totals);
        }

        for (int i = 0; i < totals.length; i++) {
            this.potentialScore += totals[i];
        }

        if (this.potentialScore > this.maxValue) {
            throw new InvalidTotalException("Potential Score exceeds Max in Lower Total", totals);
        }
    }
}
