package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private ArrayList<Dice> scoringDice;
    private ArrayList<Player> players;
    private int currentRound, currentPlayer, currentRoll, numPlayers, firstPlayer;

    public Game(int numPlayers, String[] names) {
        this.numPlayers = numPlayers;

        this.players = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {
            this.players.add(i, new Player(i, names[i]));
        }

        this.currentPlayer = 0;
        this.currentRoll = 1;
        this.currentRound = 1;

        // TEMP

        this.firstPlayer = 0;

    }

    /**
     * Deprecated -- Requirement removed
     * @return
     */

    public Integer rollSingleDie() {
        Dice singleDie = new Dice();

        return singleDie.getCurrentValue();
    }

    /**
     * Deprecated -- Requirement removed
     * @param id
     */
    protected void setFirstPlayer(int id) {
        this.players.get(id).isFirst(true);
        this.currentPlayer = id;
        this.firstPlayer = id;
    }

    /**
     * Requirement 3.2
     * <p>
     * Allows the user to roll the dice, taking a turn.
     *
     * @param player     The player taking the current
     *                   turn.
     * @param diceToRoll The dice the player is trying
     *                   to roll (all five dice, with
     *                   their keeper status set to true /
     *                   false).
     * @return
     */

    public TurnInfo takeTurn(int player, ArrayList<Dice> diceToRoll) {


        scoringDice = diceToRoll;


        // if the dice have not been initialized or
        // null was passed (first roll of a turn)
        // we make a new ArrayList with new dice.
        if (scoringDice == null) {
            scoringDice = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                scoringDice.add(new Dice());
            }
        }


        int[] totals = {0, 0, 0, 0, 0, 0};  // represents counting of number of die at a face value, [0] = ones, [5] = sixes.


        // Start by checking keeper status of all five dice.

        for (int i = 0; i < scoringDice.size(); i++) {
            // if dice was a keeper, add it's value to the totals array.
            // else we reroll, then add the new value to the totals array.

            if (this.scoringDice.get(i).getKeeper()) {
                totals = countDice(totals, this.scoringDice.get(i).getCurrentValue());
            } else {
                this.scoringDice.get(i).reroll();
                totals = countDice(totals, this.scoringDice.get(i).getCurrentValue());
            }
        }

        // calculate this rounds potential scores based on the total count of dice.

        Player temp = this.players.get(player);
        temp.calcPotentialScores(totals);

        this.currentRoll++;

        return new TurnInfo(temp.getPotentials(), temp.getTallied(), temp.getId(), scoringDice, this.currentRoll,
                this.currentRound, temp.getOpenUpper(), temp.getOpenLower());

    }

    /**
     * Helper method.  Counts dice to determine
     * the number of face values available.  Used
     * to help assist in the counting of scores
     * for the Yahtzee board.
     *
     * @param totals
     * @param faceValue
     * @return
     */

    private int[] countDice(int[] totals, int faceValue) {
        switch (faceValue) {
            case 1: {
                totals[0]++;
                break;
            }
            case 2: {
                totals[1]++;
                break;
            }
            case 3: {
                totals[2]++;
                break;
            }
            case 4: {
                totals[3]++;
                break;
            }
            case 5: {
                totals[4]++;
                break;
            }
            case 6: {
                totals[5]++;
                break;
            }
            default:
                break;
        }

        return totals;
    }

    /**
     * 3.8.2.2
     * <p>
     * Sets the final score for a box.
     *
     * @param scoreCat
     * @param player
     * @return
     */

    public TurnInfo setScore(int scoreCat, int player) {
        Player temp = this.players.get(player);
        temp.setScore(scoreCat);
        // reset the rolls, increment the player
        // and if required, increment the round.
        this.currentRoll = 1;
        this.currentPlayer++;

        if (this.currentPlayer > (numPlayers - 1)) {

            this.currentPlayer = 0;

            if (this.currentPlayer == firstPlayer) {
                this.currentRound++;
            }
        }

        // Clear Keeper Status on all Dice;

        for (Dice die : scoringDice) {
            die.setKeeper(false);
        }

        return new TurnInfo(null, temp.getTallied(), this.currentPlayer, this.scoringDice, this.currentRoll, this.currentRound,
                temp.getOpenUpper(), temp.getOpenLower());
    }

    /**
     * Requirement 3.x.x.x
     * <p>
     * Loads the game state into an array of TurnInfo
     * objects.  From here, each users last tallied scores
     * can be gathered, and the current round, roll, and
     * scoring dice can be attained.
     *
     * @return
     */

    public TurnInfo[] loadGame() {

        TurnInfo[] theTurns = new TurnInfo[players.size()];
        for (int i = 0; i < players.size(); i++) {
            Player temp = this.players.get(i);
            theTurns[i] = new TurnInfo(temp.getPotentials(), temp.getTallied(), this.currentPlayer,
                    this.scoringDice, this.currentRoll, this.currentRound, temp.getOpenUpper(), temp.getOpenLower());
        }

        return theTurns;
    }

    /**
     * Returns an array of Strings that are the
     * names of the players.  These allow the GUI
     * to update if custom names were entered
     * when the game was saved.
     *
     * @return
     */

    public String[] getNames() {
        String[] names = new String[players.size()];

        for (int i = 0; i < players.size(); i++) {
            names[i] = players.get(i).getName();
        }

        return names;
    }

    /**
     * Sets the total scores for the upper
     * totals columns (Total, Bonus, Total W/
     * Bonus).
     *
     * @param player
     */


    public void setUpperTotalScore(int player) {
        Player temp = this.players.get(player);
        int[] totals = {0, 0, 0, 0, 0, 0};  // represents counting of number of die at a face value, [0] = ones, [5] = sixes.

        for (int i = 0; i < scoringDice.size(); i++) {

            totals = countDice(totals, this.scoringDice.get(i).getCurrentValue());

        }

        temp.calcPotentialScores(totals);
        temp.setScore(ScoringCategories.UPPER_TOTAL.getValue());
        temp.calcPotentialScores(totals);
        temp.setScore(ScoringCategories.UPPER_BONUS.getValue());
        temp.calcPotentialScores(totals);
        temp.setScore(ScoringCategories.UPPER_TOTAL_W_BONUS.getValue());

    }

    /**
     * Sets the total for the lower total
     * box (Lower Total)
     *
     * @param player
     */
    public void setLowerTotalScore(int player) {
        Player temp = this.players.get(player);
        int[] totals = {0, 0, 0, 0, 0, 0};  // represents counting of number of die at a face value, [0] = ones, [5] = sixes.

        for (int i = 0; i < scoringDice.size(); i++) {

            totals = countDice(totals, this.scoringDice.get(i).getCurrentValue());

        }

        temp.calcPotentialScores(totals);

        temp.setScore(ScoringCategories.LOWER_TOTAL.getValue());

    }

    /**
     * Sets the grand total score for the player,
     * indicating the end of the game.
     *
     * @param player
     */

    public void setGrandTotalScore (int player) {
        Player temp = this.players.get(player);
        int[] totals = {0, 0, 0, 0, 0, 0};  // represents counting of number of die at a face value, [0] = ones, [5] = sixes.

        for (int i = 0; i < scoringDice.size(); i++) {

            totals = countDice(totals, this.scoringDice.get(i).getCurrentValue());

        }

        temp.calcPotentialScores(totals);

        temp.setScore(ScoringCategories.GRAND_TOTAL.getValue());
    }

    /**
     * Sets the value of a bonus yahtzee
     * box for the current player.
     *
     * Requires the
     * @param bonusIndex The index (BONUS_YAHTZEE_ONE, _TWO,
     *                   _THREE) to be set.
     */

    public void setBonusScore(int bonusIndex) {
        Player temp = this.players.get(this.currentPlayer);
        temp.setScore(bonusIndex);
    }

    /**
     * Requirement 3.x.x.x
     *
     * Sets the name of the player who won the game.
     *
     * In the even of a tie, more than one name will
     * be in the array.  Therefor, the GUI should check
     * size of the array returned for determining a tie.
     *
     * @return
     */

    public String[] getWinner() {

        // holds the high score for comparison
        int highScore = 0;

        // Used to hold more than one name
        // in the event of a tie.
        ArrayList<String> winners = new ArrayList<>();

        // name of the current winner
        String winner;

        // temp player
        Player currPlayer;

        for (int i = 0; i < numPlayers; i++) {

            currPlayer = players.get(i);
            int playerScore = currPlayer.getGrandTotal().intValue();

            // first check if greater than high score,
            // if so, winner = player.
            // clear the ArrayList in the event lower
            // ties were recorded.
            if (playerScore > highScore) {
                winner = currPlayer.getName();
                highScore = playerScore;
                winners.clear();
                winners.add(new String(winner.toString()));
            }
            // else if the playerScore == highScore
            // must be a tie.  Add the current winner name (set on prior loop)
            // to the ArrayList, reset the winners name
            // to the current player, and add the second
            // name to the array list.
            else if (playerScore == highScore) {
                winner = currPlayer.getName();
                winners.add(new String(winner.toString()));
            }

        }

        String[] theRealWinner = new String[winners.size()];

        for (int i = 0; i < winners.size(); i++) {
            theRealWinner[i] = winners.get(i);
        }

        return theRealWinner;

    }
}
