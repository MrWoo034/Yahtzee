package Model;

public class LgStrghtBox extends ScoringBox {

    public LgStrghtBox() {
        this.maxValue = 40;
        this.isOpen = true;
        this.finalScore = 0;
        this.potentialScore = null;
    }

    @Override
    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score for the
     * large straight box.  This scoring box
     * has a maximum value of 40, and only 40.
     *
     * The score only tabulates at the maximum
     * if the array contains a continuous counting
     * combination of dice in order.
     *
     * E.g.
     *
     * 1, 2, 3, 4, 5
     * 2, 3, 4, 5, 6
     *
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

        if (totals.length != 6) {
            throw new InvalidTotalException("Invalid Dice Totals in LgStrghtBox", totals);
        }

        int consecutiveDice = 0;

        for (int i = 0; i < totals.length; i++) {

            if (consecutiveDice == 5) {
                break;
            }

            if (totals[i] > 0) {
                consecutiveDice++;
            } else {
                consecutiveDice = 0;
            }

            // if there is no straight,
            // we can break early from
            // our pattern.

            if (consecutiveDice < (i -1))
                break;

        }

        if (consecutiveDice == 5) {
            this.potentialScore = 40;
        } else {
            this.potentialScore = 0;
        }

    }

    /**
     * Requirement 3.8.2.1.bonus
     *
     * Allows the potential set by the totals
     * to be overridden in the event that a bonus
     * Yahtzee is recorded.  This deviates slightly
     * from the original Yahtzee rules as described
     * per the User Manual.
     *
     */

    protected void bonusPotential() {
        this.potentialScore = 40;
    }
}
