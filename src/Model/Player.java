package Model;

import java.io.Serializable;

public class Player implements Serializable {
    private int id;
    private String name;
    private boolean isFirst;
    private ScoreSheet scoreSheet;

    /**
     * Requirement 3.5.1.1
     *
     * Allows the game to create new players
     * with a custom name.  This is the model
     * component and backend interaction.
     *
     * @param name The name the user entered
     *             for there chosen player name
     */

    public Player (int id, String name) {
        this.id = id;
        this.name = name;
        scoreSheet = new ScoreSheet();
    }

    protected Integer getGrandTotal() {
        return this.scoreSheet.getTallied()[ScoringCategories.GRAND_TOTAL.getValue()];
    }

    protected void calcPotentialScores(int[] totals) {
        this.scoreSheet.calcPotentialScores(totals);
    }

    protected boolean isFirst() {
        return this.isFirst;
    }

    protected void isFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    protected String getName () {
        return this.name;
    }

    /**
     * Requirement 3.8.2
     *
     * Calls the underlying ScoreSheet setScore
     * method.  Allows the player object to actually
     * commit a score to the ScoreSheet at the
     * specified index.
     *
     * @param index The index or row of the score sheet
     *              the player wants to record.  Can be
     *              passed as a ScoringCategory.
     */
    protected void setScore (int index) {
        this.scoreSheet.setScore(index);
    }

    protected int getId() {
        return this.id;
    }

    protected Integer[] getTallied() {
        return this.scoreSheet.getTallied();
    }

    protected Integer[] getPotentials() {
        return this.scoreSheet.getPotential();
    }

    /**
     * Returns the count of the rows that have
     * been set on the upper section of the
     * score sheet.  0 indicates all boxes are
     * OPEN, 6 indicates all boxes have been
     * scored.
     *
     * @return
     */
    protected int getOpenUpper() {
        return this.scoreSheet.getOpenUpperCount();
    }

    /**
     * Returns the count of the rows that have
     * been set on the lower section of the
     * score sheet.  0 indicates that all boxes
     * on the lower portion are still open for
     * scoring.  7 indicates that all boxes
     * have been scored.
     *
     * @return
     */

    protected int getOpenLower() {
        return this.scoreSheet.getOpenLowerCount();
    }
}
