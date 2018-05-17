import Model.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    // UPPER CATEGORIES

    static FileReader ace_results;
    static FileReader testCases;
    static String ace_test_results = "ace_test_results.txt";

    static FileReader two_results;
    static String two_test_results = "two_test_results.txt";

    static FileReader three_results;
    static String three_test_results = "three_test_results.txt";

    static FileReader four_results;
    static String four_test_results = "four_test_results.txt";

    static FileReader five_results;
    static String five_test_results = "five_test_results.txt";

    static FileReader six_results;
    static String six_test_results = "six_test_results.txt";

    // LOWER CATEGORIES

    static FileReader sm_straight_results;
    static String sm_straight_test_results = "sm_straight_test_results.txt";

    static FileReader lg_straight_results;
    static String lg_straight_test_results = "lg_straight_test_results.txt";

    static FileReader three_of_a_kind_results;
    static String three_of_a_kind_test_results = "three_of_a_kind_test_results.txt";

    static FileReader four_of_a_kind_results;
    static String four_of_a_kind_test_results = "four_of_a_kind_test_results.txt";

    static FileReader full_house_results;
    static String full_house_test_results = "full_house_test_results.txt";

    static FileReader yahtzee_results;
    static String yahtzee_test_results = "yahtzee_test_results.txt";

    static FileReader chance_results;
    static String chance_test_results = "chance_test_results.txt";

    static AceBoxTest testAce = new AceBoxTest();
    static TwoBoxTest testTwos = new TwoBoxTest();
    static ThreeBoxTest testThrees = new ThreeBoxTest();
    static FourBoxTest testFours = new FourBoxTest();
    static FiveBoxTest testFives = new FiveBoxTest();
    static SixBoxTest testSixes = new SixBoxTest();
    static ThreeOfAKindBoxTest testThreeOAK = new ThreeOfAKindBoxTest();
    static FourOfAKindBoxTest testFourOAK = new FourOfAKindBoxTest();
    static SmStraightBoxTest testSmStraight = new SmStraightBoxTest();
    static LgStraightBoxTest testLgStraight = new LgStraightBoxTest();
    static FullHouseBoxTest testFullHouse = new FullHouseBoxTest();
    static YahtzeeBoxTest testYahtzee = new YahtzeeBoxTest();
    static ChanceBoxTest testChance = new ChanceBoxTest();


    static Scanner aceCase, twoCase, threeCase, fourCase, fiveCase, sixCase,
                    threeKindCase, fourKindCase, smStraightCase, lgStraightCase,
                    yahtzeeCase, chanceCase, fullHouseCase;

    public static void main(String[] args) {

        try {
            // EXPECTED RESULTS FROM EACH TYPE
            ace_results = new FileReader("ace_results.txt");
            two_results = new FileReader("two_results.txt");
            three_results = new FileReader("three_results.txt");
            four_results = new FileReader("four_results.txt");
            five_results = new FileReader("five_Results.txt");
            six_results = new FileReader("six_results.txt");
            three_of_a_kind_results = new FileReader("three_of_kind_Results.txt");
            four_of_a_kind_results = new FileReader("four_of_kind_Results.txt");
            sm_straight_results = new FileReader("sm_straight_Results.txt");
            lg_straight_results = new FileReader("lg_straight_results.txt");
            full_house_results = new FileReader("full_house.txt");
            yahtzee_results = new FileReader("yahtzee_results.txt");
            chance_results = new FileReader("chance_results.txt");
            // BASE CASES / COLLECTIONS
            testCases = new FileReader("testCases.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Setup file scanners

        Scanner scanner = new Scanner(testCases);
        aceCase = new Scanner(ace_results);
        twoCase = new Scanner(two_results);
        threeCase = new Scanner(three_results);
        fourCase = new Scanner(four_results);
        fiveCase = new Scanner(five_results);
        sixCase = new Scanner(six_results);
        threeKindCase = new Scanner(three_of_a_kind_results);
        fourKindCase = new Scanner(four_of_a_kind_results);
        smStraightCase = new Scanner(sm_straight_results);
        lgStraightCase = new Scanner(lg_straight_results);
        fullHouseCase = new Scanner(full_house_results);
        yahtzeeCase = new Scanner(yahtzee_results);
        chanceCase = new Scanner(chance_results);


        boolean passed = false;

        int testNumber = 0;

        /*
        These writers write to individual files (using the above _test_results as the
        file names for each type.  Check the contents of the written file to determine
        the value of each test case and whether that test passed or failed.
         */
        PrintWriter ace_writer = null, two_writer = null, three_writer = null, four_writer = null,
                five_writer = null, six_writer = null, three_kind_writer = null, four_kind_writer = null,
                full_house_writer = null, sm_straight_writer = null, lg_straight_writer = null,
                yahtzee_writer = null, chance_writer = null;
        try {
            ace_writer = new PrintWriter(ace_test_results, "UTF-8");
            two_writer = new PrintWriter(two_test_results, "UTF-8");
            three_writer = new PrintWriter(three_test_results, "UTF-8");
            four_writer = new PrintWriter(four_test_results, "UTF-8");
            five_writer = new PrintWriter(five_test_results, "UTF-8");
            six_writer = new PrintWriter(six_test_results, "UTF-8");
            three_kind_writer = new PrintWriter(three_of_a_kind_test_results, "UTF-8");
            four_kind_writer = new PrintWriter(four_of_a_kind_test_results, "UTF-8");
            full_house_writer = new PrintWriter(full_house_test_results, "UTF-8");
            sm_straight_writer = new PrintWriter(sm_straight_test_results, "UTF-8");
            lg_straight_writer = new PrintWriter(lg_straight_test_results, "UTF-8");
            yahtzee_writer = new PrintWriter(yahtzee_test_results, "UTF-8");
            chance_writer = new PrintWriter(chance_test_results, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            // read the current line into a temp string.
            String str = scanner.nextLine();
            //System.out.println("Line read: " + str);

            // map the string into an array of ints that is the test
            // case of totals to use for testing.

            int[] arr = splitString(str);

            //System.out.println(Arrays.toString(arr));

            /*
            *************
            * ************
            * * ***********
            * ACES
             */

            // get the current test case for this class

            int aceValue = -1;

            if (aceCase.hasNextInt()) {
                aceValue = aceCase.nextInt();
            }

            // check to see if assertion is true

            passed = testAces(arr, aceValue);

            // write pass or failed to the file

            if (!passed) {
                ace_writer.println("Failed on Case: " + testNumber++ + " with values: {" + arr[0] + ", " +
                        arr[1] + ", " + arr[2] + ", " + arr[3] + ", " +
                        arr[4] + ", " + arr[5] + "} and assertion value: "
                        + aceValue);
            } else {
                ace_writer.println("Passed on Aces Case: " + testNumber++ + " with values: {" + arr[0] + ", " +
                        arr[1] + ", " + arr[2] + ", " + arr[3] + ", " +
                        arr[4] + ", " + arr[5] + "} and assertion value: "
                        + aceValue);
            }

            /*
             *************
             * ************
             * * ***********
             * TWOS
             */

            // get the current test case for this class

            int twoValue = -1;

            if (twoCase.hasNextInt()) {
                twoValue = twoCase.nextInt();
            }

            // check to see if assertion is true

            passed = testTwos.calcPotential(arr, twoValue);

            // write pass or failed to the file

            if (!passed) {
                two_writer.println("Failed on Case: " + testNumber + " with values: {" + arr[0] + ", " +
                        arr[1] + ", " + arr[2] + ", " + arr[3] + ", " +
                        arr[4] + ", " + arr[5] + "} and assertion value: "
                        + aceValue);
            } else {
                two_writer.println("Passed on Case: " + testNumber + " with values: {" + arr[0] + ", " +
                        arr[1] + ", " + arr[2] + ", " + arr[3] + ", " +
                        arr[4] + ", " + arr[5] + "} and assertion value: "
                        + aceValue);
            }

            /*
             *************
             * ************
             * * ***********
             * THREES
             */

            // get the current test case for this class

            int threeValue = -1;

            if (threeCase.hasNextInt()) {
                threeValue = threeCase.nextInt();
            }

            // check to see if assertion is true

            passed = testThrees.calcPotential(arr, threeValue);

            // write pass or failed to the file

            if (!passed) {
                three_writer.println("Failed on Case: " + testNumber + " with values: {" + arr[0] + ", " +
                        arr[1] + ", " + arr[2] + ", " + arr[3] + ", " +
                        arr[4] + ", " + arr[5] + "} and assertion value: "
                        + threeValue);
            } else {
                three_writer.println("Passed on Case: " + testNumber + " with values: {" + arr[0] + ", " +
                        arr[1] + ", " + arr[2] + ", " + arr[3] + ", " +
                        arr[4] + ", " + arr[5] + "} and assertion value: "
                        + threeValue);
            }

            /*
             *************
             * ************
             * * ***********
             * FOURS
             */

            // get the current test case for this class

            int fourValue = -1;

            if (fourCase.hasNextInt()) {
                fourValue = fourCase.nextInt();
            }

            // check to see if assertion is true

            passed = testFours.calcPotential(arr, fourValue);

            // write pass or failed to the file

            if (!passed) {
                four_writer.println("Failed on Case: " + testNumber + " with values: {" + arr[0] + ", " +
                        arr[1] + ", " + arr[2] + ", " + arr[3] + ", " +
                        arr[4] + ", " + arr[5] + "} and assertion value: "
                        + fourValue);
            } else {
                four_writer.println("Passed on Case: " + testNumber + " with values: {" + arr[0] + ", " +
                        arr[1] + ", " + arr[2] + ", " + arr[3] + ", " +
                        arr[4] + ", " + arr[5] + "} and assertion value: "
                        + fourValue);
            }

            /*
             *************
             * ************
             * * ***********
             * THREES
             */

            // get the current test case for this class

            int fiveValue = -1;

            if (fiveCase.hasNextInt()) {
                fiveValue = fiveCase.nextInt();
            }

            // check to see if assertion is true

            writeToFile(five_writer, testFives.calcPotential(arr, fiveValue),
                        arr, testNumber, fiveValue);

            /*
             *************
             * ************
             * * ***********
             * SIXES
             */

            // get the current test case for this class

            int sixValue = -1;

            if (sixCase.hasNextInt()) {
                sixValue = sixCase.nextInt();
            }

            // check to see if assertion is true

            writeToFile(six_writer, testSixes.calcPotential(arr,sixValue),
                        arr, testNumber, sixValue);

            /*
             *************
             * ************
             * * ***********
             * THREE OF A KIND
             */

            // get the current test case for this class

            int threeOakValue = -1;

            if (threeKindCase.hasNextInt()) {
                threeOakValue = threeKindCase.nextInt();
            }

            // check to see if assertion is true

            writeToFile(three_kind_writer, testThreeOAK.calcPotential(arr,threeOakValue),
                    arr, testNumber, threeOakValue);
            /*
             *************
             * ************
             * * ***********
             * FOUR OF A KIND
             */

            // get the current test case for this class

            int fourOakValue = -1;

            if (fourKindCase.hasNextInt()) {
                fourOakValue = fourKindCase.nextInt();
            }

            // check to see if assertion is true

            writeToFile(four_kind_writer, testFourOAK.calcPotential(arr,fourOakValue),
                    arr, testNumber, fourOakValue);
            /*
             *************
             * ************
             * * ***********
             * FULL HOUSE
             */

            // get the current test case for this class

            int fhValue = -1;

            if (fullHouseCase.hasNextInt()) {
                fhValue = fullHouseCase.nextInt();
            }

            // check to see if assertion is true

            writeToFile(full_house_writer, testFullHouse.calcPotential(arr,fhValue),
                    arr, testNumber, fhValue);
            /*
             *************
             * ************
             * * ***********
             * SMALL STRAIGHT
             */

            // get the current test case for this class

            int smStraightValue = -1;

            if (smStraightCase.hasNextInt()) {
                smStraightValue = smStraightCase.nextInt();
            }

            // check to see if assertion is true

            writeToFile(sm_straight_writer, testSmStraight.calcPotential(arr,smStraightValue),
                    arr, testNumber, smStraightValue);

            /*
             *************
             * ************
             * * ***********
             * LARGE STRAIGHT
             */

            // get the current test case for this class

            int lgStraightValue = -1;

            if (lgStraightCase.hasNextInt()) {
                lgStraightValue = lgStraightCase.nextInt();
            }

            // check to see if assertion is true

            writeToFile(lg_straight_writer, testLgStraight.calcPotential(arr,lgStraightValue),
                    arr, testNumber, lgStraightValue);
            /*
             *************
             * ************
             * * ***********
             * YAHTZEE
             */

            // get the current test case for this class

            int yahtzeeValue = -1;

            if (yahtzeeCase.hasNextInt()) {
                yahtzeeValue = yahtzeeCase.nextInt();
            }

            // check to see if assertion is true

            writeToFile(yahtzee_writer, testYahtzee.calcPotential(arr,yahtzeeValue),
                    arr, testNumber, yahtzeeValue);
            /*
             *************
             * ************
             * * ***********
             * CHANCE
             */

            // get the current test case for this class

            int chanceValue = -1;

            if (chanceCase.hasNextInt()) {
                chanceValue = chanceCase.nextInt();
            }

            // check to see if assertion is true

            writeToFile(chance_writer, testChance.calcPotential(arr,chanceValue),
                    arr, testNumber, chanceValue);

        }

        ace_writer.close();
        two_writer.close();
        three_writer.close();
        four_writer.close();
        five_writer.close();
        six_writer.close();
        three_kind_writer.close();
        four_kind_writer.close();
        sm_straight_writer.close();
        lg_straight_writer.close();
        full_house_writer.close();
        yahtzee_writer.close();
        chance_writer.close();

        aceCase.close();
        twoCase.close();
        threeCase.close();
        fourCase.close();
        fiveCase.close();
        sixCase.close();
        threeKindCase.close();
        fourKindCase.close();
        smStraightCase.close();
        lgStraightCase.close();
        fullHouseCase.close();
        yahtzeeCase.close();
        chanceCase.close();

        try {
            ace_results.close();
            two_results.close();
            three_results.close();
            four_results.close();
            five_results.close();
            six_results.close();
            sm_straight_results.close();
            lg_straight_results.close();
            full_house_results.close();
            three_of_a_kind_results.close();
            four_of_a_kind_results.close();
            yahtzee_results.close();
            chance_results.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writeToFile(PrintWriter theWriter, boolean passed, int[] arr, int testNumber, int assertsValue) {
        if (!passed) {
            theWriter.println("Failed on Case: " + testNumber + " with values: {" + arr[0] + ", " +
                    arr[1] + ", " + arr[2] + ", " + arr[3] + ", " +
                    arr[4] + ", " + arr[5] + "} and assertion value: "
                    + assertsValue);
        } else {
            theWriter.println("Passed on Case: " + testNumber + " with values: {" + arr[0] + ", " +
                    arr[1] + ", " + arr[2] + ", " + arr[3] + ", " +
                    arr[4] + ", " + arr[5] + "} and assertion value: "
                    + assertsValue);
        }
    }


    public static boolean testAces(int[] totals, int assertValue) {
        return testAce.calcPotential(totals, assertValue);
    }

    public static int[] splitString(String str) {
        return Arrays.stream(str.substring(1, str.length() - 1).split(","))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();
    }
}
