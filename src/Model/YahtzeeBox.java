package Model;

public class YahtzeeBox extends ScoringBox{

    public YahtzeeBox() {
        this.maxValue = 50;
        this.isOpen = true;
        this.potentialScore = null;
        this.finalScore = 0;
    }

    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score of the Yahtzee Box.
     *
     * Looks for five of a kind among the total count of dice
     * presented to the function.  If five are found, then
     * the potential score is 50.
     *
     * @param totals Requires an integer array that gives
     *               the total count of dice for a particular
     *               face value..
     *
     * @throws InvalidTotalException In the event the wrong totals
     * array is passed (i.e. wrong length) then a new exception is
     * thrown.
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
        }
    }
}
