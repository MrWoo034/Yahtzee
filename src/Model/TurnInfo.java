package Model;

import java.util.ArrayList;

public class TurnInfo {
    private Integer[] potentialScores;
    private Integer[] talliedScores;
    private int playerId;
    private ArrayList<Dice> dice;
    private int currentRoll, currentRound, openUpperTotals, openLowerTotals;

    public TurnInfo(Integer[] potentialScores, Integer[] talliedScores, int playerId,
                    ArrayList<Dice> dice, int currentRoll, int currentRound, int openUpperTotals, int openLowerTotals) {
        this.potentialScores = potentialScores;
        this.talliedScores = talliedScores;
        this.playerId = playerId;
        this.dice = dice;
        this.currentRoll = currentRoll;
        this.currentRound = currentRound;
        this.openUpperTotals = openUpperTotals;
        this.openLowerTotals = openLowerTotals;
    }

    public Integer[] getPotentialScores() {
        return potentialScores;
    }

    public Integer[] getTalliedScores() {
        return talliedScores;
    }

    public int getPlayerId() {
        return playerId;
    }

    public ArrayList<Dice> getDice() {
        return dice;
    }

    public int getCurrentRoll() {
        return currentRoll;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getOpenUpperTotals() {
        return this.openUpperTotals;
    }

    public int getOpenLowerTotals() {
        return this.openLowerTotals;
    }
}
