package Model;

public class UpperBonus extends ScoringBox {

    public UpperBonus() {
        this.maxValue = 35;
        this.isOpen = true;
        this.finalScore = 0;
        this.potentialScore = null;
    }

    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score for the upper bonus
     * socring box.  This box is worth 35 points if the
     * cumulative total of upper scoring boxes is >= 63
     * points.
     *
     * @param totals Requires a single value for its param,
     *               the value of the UpperTotal box.
     *
     * @throws InvalidTotalException
     */
    @Override
    protected void calcPotential(int[] totals) throws InvalidTotalException {

        // reset potential to 0, incase it
        // was calculated on the last turn
        this.potentialScore = 0;

        if (totals.length > 1) {
            throw new InvalidTotalException("Invalid Dice Total passed to the UpperBonus Box", totals);
        }

        if (totals[0] >= 63) {
            this.potentialScore = this.maxValue;
            this.setScore();
        } else {
            this.potentialScore = 0;
        }
    }
}
