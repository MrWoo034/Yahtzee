package Model;

public class ChanceBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        ChanceBox testBox = new ChanceBox();

        try {
            testBox.calcPotential(totals);
        } catch (InvalidTotalException e) {
            e.printStackTrace();
        }

        if (assertValue == testBox.getPotentialScore().intValue()){
            return true;
        } else {
            return false;
        }
    }
}