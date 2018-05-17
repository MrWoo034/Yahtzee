package Model;

public enum ScoringCategories {
    ACES(0),
    TWOS(1),
    THREES(2),
    FOURS(3),
    FIVES(4),
    SIXES(5),
    UPPER_TOTAL(6),
    UPPER_BONUS(7),
    UPPER_TOTAL_W_BONUS(8),
    THREE_OF_A_KIND(9),
    FOUR_OF_A_KIND(10),
    FULL_HOUSE(11),
    SM_STRAIGHT(12),
    LG_STRAIGHT(13),
    YAHTZEE(14),
    CHANCE(15),
    BONUS_YAHTZEE_ONE(16),
    BONUS_YAHTZEE_TWO(17),
    BONUS_YAHTZEE_THREE(18),
    LOWER_TOTAL(19),
    GRAND_TOTAL(20);

    private int value;

    ScoringCategories (int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
