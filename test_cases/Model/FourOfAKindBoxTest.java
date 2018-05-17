package Model;

public class FourOfAKindBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        FourOfAKindBox testBox = new FourOfAKindBox();

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