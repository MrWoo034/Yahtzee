package Model;

public class BonusYahtzeeBox extends ScoringBox {

    public BonusYahtzeeBox() {
        this.maxValue = 100;
        this.isOpen = true;
        this.potentialScore = null;
        this.finalScore = 0;
    }

    /**
     * Requirement 3.8.2.1
     *
     * Calculates the total of a bonus yahtzee, if
     * applicable.  A yahtzee must have already been
     * scored for a bonus to be used.  If there is
     * a bonus Yahtzee, the first Yahtzee must have
     * been captured.
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
            throw new InvalidTotalException("Invalid Dice Totals in Yahtzee Box", totals);
        }

        boolean fiveFound = false;

        for (int i = 0 ; i < totals.length; i++) {
            if (totals[i] == 5) {
                fiveFound = true;
                break;
            }
        }

        if (fiveFound) {
            this.potentialScore = this.maxValue;
        } else {
            this.potentialScore = 0;
        }
    }
}
