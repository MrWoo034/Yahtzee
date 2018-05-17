package Model;

public class UpperTotalTotal extends ScoringBox {

    public UpperTotalTotal() {
        this.maxValue = 35 + 30 + 25 + 20 + 15 + 10 + 5;
        this.isOpen = true;
        this.finalScore = 0;
        this.potentialScore = 0;
    }

    /**
     * Requirement 3.8.2.1
     *
     * Calculates the total total of the upper section of
     * the score sheet.  Is the total of the upper bonus
     * and upper total boxes.  Calculated after upper
     * total has been closed.
     *
     *
     * @param totals Requires only the total of the upper
     *               bonus and the upper total boxes to
     *               provide an accurate score.
     *
     * @throws InvalidTotalException
     */
    @Override
    protected void calcPotential(int[] totals) throws InvalidTotalException {
        // reset potential to 0, incase it
        // was calculated on the last turn
        this.potentialScore = 0;

        if (totals.length != 2) {
            throw new InvalidTotalException("Invalid total passed to UpperTotalTotal box", totals);
        }

        for (int i = 0; i < totals.length; i++) {
            this.potentialScore += totals[i];
        }
    }
}
