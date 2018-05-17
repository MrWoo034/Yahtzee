package Model;

public class ThreeOfAKindBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        ThreeOfAKindBox testBox = new ThreeOfAKindBox();

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