package Model;

public class GrandTotal extends ScoringBox{


    public GrandTotal() {
        this.maxValue = 30 + 30 + 25 + 30 + 40 + 50 + 300 + 35 + 30 + 25 + 20 + 15 + 10 + 5;
        this.isOpen = true;
        this.potentialScore = 0;
        this.finalScore = 0;
    }




    @Override
    protected void calcPotential(int[] totals) throws InvalidTotalException {

        // reset potential to 0, incase it
        // was calculated on the last turn
        this.potentialScore = 0;

        if (totals.length != 2) {
            throw new InvalidTotalException("Invalid Dice totals in Grand Total box", totals);
        }

        for (int i = 0; i < totals.length; i++) {
            this.potentialScore += totals[i];
        }

        if (this.potentialScore > this.maxValue) {
            throw new InvalidTotalException("Potential Score exceeds max in grand total box", totals);
        }
    }
}
