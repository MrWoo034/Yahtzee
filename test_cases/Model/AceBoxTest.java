package Model;

public class AceBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        AceBox aceBox = new AceBox();

        try {
            aceBox.calcPotential(totals);
        } catch (InvalidTotalException e) {
            e.printStackTrace();
        }

        if (assertValue == aceBox.getPotentialScore().intValue()){
            return true;
        } else {
            return false;
        }
    }
}