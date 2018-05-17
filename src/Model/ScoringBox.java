package Model;

import java.io.Serializable;

public abstract class ScoringBox implements Serializable{
    protected Integer maxValue;
    protected boolean isOpen;
    protected Integer potentialScore;
    protected Integer finalScore;

    /**
     * Requirement 3.8.2.1
     *
     * Calculates the potential score of the box
     * if the user were to record that score to
     * the score sheet now.  Implementation depends
     * upon the type or score box (or row) that
     * this box is on the score sheet.  See individual
     * boxes for more details.
     *
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
     * @throws InvalidTotalException Throws an invalid total
     * exception if the total of dice exceeds the max value or there
     * is an issue with the array passed to the calc method.
     */

    protected abstract void calcPotential(int[] totals) throws InvalidTotalException;

    /**
     * Requirement 3.8.2.1
     *
     * Returns the calculated potential score
     * for the current scoring box (row) on
     * the score sheet.
     *
     * @return Returns an Integer that is the
     * value of that would be recorded in the
     * score sheet if the user set that score
     * to memory right now.
     */

    protected Integer getPotentialScore() {
        return this.potentialScore;
    }

    protected void setPotentialScore( Integer potentialScore) {
        this.potentialScore = potentialScore;
    }

    protected Integer getFinalScore() {
        return this.finalScore;
    }

    protected boolean isOpen() {
        return this.isOpen;
    }

    /**
     * Requirement 3.8.2
     *
     * Records the current potential score
     * as the final score for this scoring
     * box (row) on the score sheet.
     *
     */

    protected void setScore() {
        this.finalScore = this.potentialScore;
        this.isOpen = false;
    }
}
