package Model;

public class FullHouse extends ScoringBox{

    public FullHouse () {
        this.maxValue = 25;
        this.isOpen = true;
        this.finalScore = 0;
        this.potentialScore = null;
    }

    @Override
    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score of the Full
     * House box / row on the score sheet.  Looks
     * for a pair and three of a kind to exist on
     * the face values of the dice.  In the event
     * that there is both a pair and three of a
     * kind, the value of the Full House potential
     * is 25, and only 25.  No totaling or adding
     * is performed on this box.
     *
     * e.g.  Dice on the board show:
     *
     * 2, 2, 5, 5, 5
     *
     * Full House is triggered, and value of score
     * is 25 points.
     *
     * Dice on the board show:
     *
     * 2, 2, 2, 4, 5
     *
     * Full house is not triggered, and the value
     * of the score is 0 points.
     *
     *
     * @param totals Requires an int array where
     *               each element holds the total
     *               number of die with a specific
     *               face value.
     *
     *               i.e. totals[0] represents the
     *               total number of ONES shown on
     *               dice on the board.
     *
     * @throws InvalidTotalException Throws an invalid
     * total exception in the event the wrong number of totals
     * are passed.
     */
    protected void calcPotential(int[] totals) throws InvalidTotalException {

        // reset potential to 0, incase it
        // was calculated on the last turn
        this.potentialScore = 0;

        if (totals.length != 6) {
            throw new InvalidTotalException("Invalid Dice Totals in FullHouse Box", totals);
        }

        boolean pairFound = false,  trioFound = false;

        for (int i = 0; i < totals.length; i++) {
            if (totals[i] == 3) {
                trioFound = true;
            } else if (totals[i] == 2) {
                pairFound = true;
            }
        }

        if (pairFound && trioFound) {
            this.potentialScore = this.maxValue;
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
        this.potentialScore = 25;
    }
}
