package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ScoreSheet implements Serializable {

    protected ArrayList<ScoringBox> rows;
    private Integer[] tallied;
    private Integer[] potential;
    private final ScoringCategories[] scoringCategories = ScoringCategories.values();
    private boolean yahtzeeCalled;
    private int openUpperCount;
    private int openLowerCount;

    public ScoreSheet() {

        this.initializeScoreSheet();
        this.tallied = new Integer[scoringCategories.length];
        this.potential = new Integer[scoringCategories.length];
        openUpperCount = 0;
        openLowerCount = 0;

    }

    /**
     * Requirement 3.8.2.1
     * <p>
     * Calculates the potential scores for all rows
     * on the current score sheet based upon the dice
     * showing on the board currently.
     *
     * @param totals An array where each element is the
     *               total number of dice at that face
     *               value, where element [0] represents
     *               face value 1, and element [5]
     *               represents face value 6.
     */

    protected void calcPotentialScores(int[] totals) {

        int[] faceValueCount = totals;
        int[] upperScoringBoxes = {0, 0, 0, 0, 0, 0};
        int[] lowerScoringBoxes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] upperScoringTotals = {0, 0};
        int[] scoringTotals = {0, 0};
        boolean maxBonus = false;

        for (int i = 0; i < rows.size(); i++) {
            ScoringBox currentBox = rows.get(i);

            try {
                switch (scoringCategories[i]) {

                    // FIRST ARE CASES THAT REQUIRE THE
                    // FACE VALUE OF THE DIE

                    case ACES: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            upperScoringBoxes[0] = currentBox.getFinalScore();
                        }
                        break;
                    }
                    case TWOS: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            upperScoringBoxes[1] = currentBox.getFinalScore();
                        }
                        break;
                    }
                    case THREES: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            upperScoringBoxes[2] = currentBox.getFinalScore();
                        }
                        break;
                    }
                    case FOURS: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            upperScoringBoxes[3] = currentBox.getFinalScore();
                        }
                        break;
                    }
                    case FIVES: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            upperScoringBoxes[4] = currentBox.getFinalScore();
                        }
                        break;
                    }
                    case SIXES: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            upperScoringBoxes[5] = currentBox.getFinalScore();
                        }
                        break;
                    }

                    case THREE_OF_A_KIND: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            lowerScoringBoxes[0] = currentBox.getFinalScore();
                        }
                        break;
                    }

                    case FOUR_OF_A_KIND: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            lowerScoringBoxes[1] = currentBox.getFinalScore();
                        }
                        break;
                    }

                    case SM_STRAIGHT: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            lowerScoringBoxes[2] = currentBox.getFinalScore();
                        }
                        break;
                    }

                    case LG_STRAIGHT: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            lowerScoringBoxes[3] = currentBox.getFinalScore();
                        }
                        break;
                    }

                    case FULL_HOUSE: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            lowerScoringBoxes[4] = currentBox.getFinalScore();
                        }
                        break;
                    }

                    case CHANCE: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            lowerScoringBoxes[9] = currentBox.getFinalScore();
                        }
                        break;
                    }

                    case YAHTZEE: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(faceValueCount);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            lowerScoringBoxes[5] = currentBox.getFinalScore();
                            yahtzeeCalled = true;
                        }
                        break;
                    }

                    case BONUS_YAHTZEE_ONE: {
                        if (yahtzeeCalled) {
                            if (currentBox.isOpen()) {
                                currentBox.calcPotential(faceValueCount);
                                potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                                if (currentBox.getPotentialScore().intValue() > 0) {
                                    maxBonus = true;
                                }
                            } else {
                                lowerScoringBoxes[6] = currentBox.getFinalScore();
                            }
                            break;
                        }
                        break;
                    }
                    case BONUS_YAHTZEE_TWO: {
                        if (yahtzeeCalled) {
                            if (currentBox.isOpen()) {
                                currentBox.calcPotential(faceValueCount);
                                potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                                if (currentBox.getPotentialScore().intValue() > 0) {
                                    maxBonus = true;
                                }
                            } else {
                                lowerScoringBoxes[7] = currentBox.getFinalScore();
                            }
                            break;
                        }
                        break;
                    }
                    case BONUS_YAHTZEE_THREE: {
                        if (yahtzeeCalled) {
                            if (currentBox.isOpen()) {
                                currentBox.calcPotential(faceValueCount);
                                potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                                if (currentBox.getPotentialScore().intValue() > 0) {
                                    maxBonus = true;
                                }
                            } else {
                                lowerScoringBoxes[8] = currentBox.getFinalScore();
                            }
                            break;
                        }
                        break;
                    }

                    // CALCULATING POTENTIAL OF TOTAL TYPE BOXES, WHICH SHOULD
                    // HAVE BEEN POPULATED ABOVE
                    case UPPER_TOTAL: {
                        if (currentBox.isOpen()) {
                            currentBox.calcPotential(upperScoringBoxes);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        } else {
                            upperScoringTotals[0] = currentBox.getFinalScore();
                        }
                        break;
                    }

                    case UPPER_BONUS: {

                        if (currentBox.isOpen()) {
                            int[] upperTotal = {potential[ScoringCategories.UPPER_TOTAL.getValue()].intValue()};
                            currentBox.calcPotential(upperTotal);
                            potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();

                            if (currentBox.getPotentialScore() > 0) {
                                currentBox.setScore();
                                upperScoringTotals[1] = currentBox.getFinalScore();
                            }

                        } else {
                            upperScoringTotals[1] = currentBox.getFinalScore();
                        }
                        break;
                    }

                    case UPPER_TOTAL_W_BONUS: {
                        currentBox.calcPotential(upperScoringTotals);
                        potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        scoringTotals[0] = currentBox.getFinalScore();
                        break;
                    }

                    case LOWER_TOTAL: {
                        currentBox.calcPotential(lowerScoringBoxes);
                        potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        scoringTotals[1] = currentBox.getFinalScore();
                        break;
                    }

                    case GRAND_TOTAL: {
                        currentBox.calcPotential(scoringTotals);
                        potential[scoringCategories[i].getValue()] = currentBox.getPotentialScore();
                        break;
                    }


                }
            } catch (InvalidTotalException invalid) {
                System.out.println(invalid.getMessage() + "\n\n********************\n\n ****************\n\n");
                //invalid.printStackTrace();
            }
        }

        if (maxBonus) {
            bonusPotential();
        }

    }

    /**
     * Requirement 3.8.2
     * <p>
     * Allows the model to record a score in a box
     * as specified by the player.  Requires the
     * scoring row / box that the player selected
     * on the GUI to record the score.
     *
     * @param index The index of the row / box that
     *              needs to be scored.
     */

    protected void setScore(int index) {

        rows.get(scoringCategories[index].getValue()).setScore();

        if (index < tallied.length) {
            tallied[index] = rows.get(scoringCategories[index].getValue()).getFinalScore();
        }

        if (index >= ScoringCategories.ACES.getValue() && index <= ScoringCategories.SIXES.getValue()) {
            openUpperCount++;
        } else if (index >= ScoringCategories.THREE_OF_A_KIND.getValue() && index <= ScoringCategories.CHANCE.getValue()) {
            openLowerCount++;
        }

        switch (scoringCategories[index]) {
            case YAHTZEE:
                yahtzeeCalled = true;
        }
    }

    /**
     * Returns an array list of Integers that
     * represents the potential scores for all
     * rows on the score sheet for the current
     * player.
     *
     * @return
     */


    protected Integer[] getPotential() {
        return this.potential;
    }


    /**
     * Returns an array list of integers that
     * represents the scores that have been
     * tallied or entered on the score sheet.
     *
     * @return
     */
    protected Integer[] getTallied() {
        return this.tallied;
    }


    private void initializeScoreSheet() {

        rows = new ArrayList<ScoringBox>();

        for (ScoringCategories scoreCat : ScoringCategories.values()) {


            switch (scoreCat) {
                case ACES: {
                    rows.add(scoreCat.getValue(), new AceBox());
                    break;
                }

                case TWOS: {
                    rows.add(scoreCat.getValue(), new TwosBox());
                    break;
                }
                case THREES: {
                    rows.add(scoreCat.getValue(), new ThreesBox());
                    break;
                }

                case FOURS: {
                    rows.add(scoreCat.getValue(), new FoursBox());
                    break;
                }
                case FIVES: {
                    rows.add(scoreCat.getValue(), new FivesBox());
                    break;
                }

                case SIXES: {
                    rows.add(scoreCat.getValue(), new SixesBox());
                    break;
                }
                case UPPER_TOTAL: {
                    rows.add(scoreCat.getValue(), new UpperTotal());
                    break;
                }

                case UPPER_BONUS: {
                    rows.add(scoreCat.getValue(), new UpperBonus());
                    break;
                }
                case UPPER_TOTAL_W_BONUS: {
                    rows.add(scoreCat.getValue(), new UpperTotalTotal());
                    break;
                }

                case THREE_OF_A_KIND: {
                    rows.add(scoreCat.getValue(), new ThreeOfAKindBox());
                    break;
                }
                case FOUR_OF_A_KIND: {
                    rows.add(scoreCat.getValue(), new FourOfAKindBox());
                    break;
                }

                case FULL_HOUSE: {
                    rows.add(scoreCat.getValue(), new FullHouse());
                    break;
                }
                case SM_STRAIGHT: {
                    rows.add(scoreCat.getValue(), new SmStrghtBox());
                    break;
                }

                case LG_STRAIGHT: {
                    rows.add(scoreCat.getValue(), new LgStrghtBox());
                    break;
                }

                case YAHTZEE: {
                    rows.add(scoreCat.getValue(), new YahtzeeBox());
                    break;
                }
                case BONUS_YAHTZEE_ONE: {
                    rows.add(scoreCat.getValue(), new BonusYahtzeeBox());
                    break;
                }

                case BONUS_YAHTZEE_TWO: {
                    rows.add(scoreCat.getValue(), new BonusYahtzeeBox());
                    break;
                }
                case BONUS_YAHTZEE_THREE: {
                    rows.add(scoreCat.getValue(), new BonusYahtzeeBox());
                    break;
                }

                case CHANCE: {
                    rows.add(scoreCat.getValue(), new ChanceBox());
                    break;
                }
                case LOWER_TOTAL: {
                    rows.add(scoreCat.getValue(), new LowerTotal());
                    break;
                }

                case GRAND_TOTAL: {
                    rows.add(scoreCat.getValue(), new GrandTotal());
                    break;
                }
            }
        }
    }

    /**
     * Overrides the potential to be maximum
     * when a bonus Yahtzee has been scored for
     * the Sm Straight, Lg Straight, and Full House
     * boxes.  For details, see User Manual
     */

    private void bonusPotential() {

        ScoringBox temp;
        temp = rows.get(ScoringCategories.SM_STRAIGHT.getValue());
        ((SmStrghtBox) temp).bonusPotential();
        potential[ScoringCategories.SM_STRAIGHT.getValue()] = temp.getPotentialScore();

        temp = rows.get(ScoringCategories.LG_STRAIGHT.getValue());
        ((LgStrghtBox) temp).bonusPotential();
        potential[ScoringCategories.LG_STRAIGHT.getValue()] = temp.getPotentialScore();

        temp = rows.get(ScoringCategories.FULL_HOUSE.getValue());
        ((FullHouse) temp).bonusPotential();
        potential[ScoringCategories.FULL_HOUSE.getValue()] = temp.getPotentialScore();

    }

    /**
     * Helper methods for determining when
     * to show / set the final scores on the
     * score sheet.
     *
     * @return
     */

    protected int getOpenUpperCount() {
        return this.openUpperCount;
    }

    protected int getOpenLowerCount() {
        return this.openLowerCount;
    }


}
