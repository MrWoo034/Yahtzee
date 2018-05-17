package Model;

public class FiveBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        FivesBox testBox = new FivesBox();

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