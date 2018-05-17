package Model;

public class UpperTotal extends ScoringBox{

    public UpperTotal() {
        this.maxValue = 30 + 25 + 20 + 15 + 10 + 5;
        this.potentialScore = 0;
        this.finalScore = 0;
        this.isOpen = true;
    }

    @Override
    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score for the upper total
     * box.  This box should remain open until all boxes
     * from the upper total are closed / tallied.  At that
     * point its score should be set.
     *
     * @param totals Requires an array that represents the
     *               individual value of upper scoring
     *               boxes.  These elements should represent
     *               the Aces, Twos, Threes, Fours, Fives,
     *               and Sixes boxes only.  The bonus is calculated
     *               and implemented as a separate box, and the
     *               total plus bonus is yet another box.
     * @throws InvalidTotalException
     *
     */
    protected void calcPotential(int[] totals) throws InvalidTotalException {
        // reset potential to 0, incase it
        // was calculated on the last turn
        this.potentialScore = 0;

        if (totals.length != 6) {
            throw new InvalidTotalException("Invalid Dice Totals in UpperTotal Box", totals);
        }

        for (int i = 0; i < totals.length; i++) {
            this.potentialScore += totals[i];
        }
    }
}
