package Model;

public class SixBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        SixesBox testBox = new SixesBox();

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