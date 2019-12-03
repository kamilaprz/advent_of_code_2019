import java.util.Arrays;
import java.util.stream.IntStream;

class Scratch {
    static final int ADDING_OPERAND = 1;
    static final int MULTIPLY_OPERAND = 2;
    static final int HALT_CODE = 99;

    static final int OPERAND_INDEX = 0;
    static final int IN_1_INDEX = 1;
    static final int IN_2_INDEX = 2;
    static final int OUT_INDEX = 3;

    static final int FINAL_VALUE = 19690720;

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {2, 0, 0, 0, 99}, calculate(new int[] {1, 0, 0, 0, 99}));
        assert Arrays.equals(new int[] {2, 3, 0, 6, 99}, calculate(new int[] {2, 3, 0, 3, 99}));
        assert Arrays.equals(new int[] {2, 4, 4, 5, 99, 9801}, calculate(new int[] {2, 4, 4, 5, 99, 0}));
        assert Arrays
                .equals(new int[] {30, 1, 1, 4, 2, 5, 6, 0, 99}, calculate(new int[] {1, 1, 1, 4, 99, 5, 6, 0, 99}));

        int[] verbs = IntStream.range(0, 99).toArray();
        int[] nouns = IntStream.range(0, 99).toArray();
        int[] arr = null;

        outerloop:
        for (int i = 0; i < nouns.length; i++) {
            for (int j = 0; j < verbs.length; j++) {
                arr = calculate(
                        new int[] {1, nouns[i], verbs[j], 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 9, 1, 19, 1, 19, 5,
                                23, 1, 9, 23, 27, 2,
                                27, 6, 31, 1, 5, 31, 35, 2, 9, 35, 39, 2, 6, 39, 43, 2, 43, 13, 47, 2, 13, 47, 51, 1,
                                10, 51,
                                55, 1, 9, 55, 59, 1, 6, 59, 63, 2, 63, 9, 67, 1, 67, 6, 71, 1, 71, 13, 75, 1, 6, 75, 79,
                                1, 9,
                                79, 83, 2, 9, 83, 87, 1, 87, 6, 91, 1, 91, 13, 95, 2, 6, 95, 99, 1, 10, 99, 103, 2, 103,
                                9, 107,
                                1, 6, 107, 111, 1, 10, 111, 115, 2, 6, 115, 119, 1, 5, 119, 123, 1, 123, 13, 127, 1,
                                127, 5,
                                131, 1, 6, 131, 135, 2, 135, 13, 139, 1, 139, 2, 143, 1, 143, 10, 0, 99, 2, 0, 14, 0});
                System.out.println("Noun: " + nouns[i] + " Verb: " + verbs[j] + " Output: " + arr[0]);
                if (arr[0] == FINAL_VALUE) {
                    break outerloop;
                }
            }
        }
        System.out.println("Response for the task for " + arr[1] + " and " + arr[2] + " is " + (100 * arr[1] + arr[2]));
    }

    private static int[] calculate(int[] arr) {
        for (int i = 0; i < arr.length; ) {
            if (arr[OPERAND_INDEX + i] == HALT_CODE) {
                return arr;
            }

            int in1 = arr[arr[i + IN_1_INDEX]];
            int in2 = arr[arr[i + IN_2_INDEX]];

            switch (arr[i + OPERAND_INDEX]) {
                case ADDING_OPERAND:
                    arr[arr[i + OUT_INDEX]] = in1 + in2;
                    break;
                case MULTIPLY_OPERAND:
                    arr[arr[i + OUT_INDEX]] = in1 * in2;
                    break;
                default:
                    throw new RuntimeException("Unknown operand code: " + arr[OPERAND_INDEX]);
            }

            i = i + 4;
        }

        return arr;
    }
}