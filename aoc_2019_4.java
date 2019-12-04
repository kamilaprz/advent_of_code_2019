import java.util.ArrayList;
import java.util.List;

class Scratch {
    static final int INPUT_LOW = 128392;
    static final int INPUT_HIGH = 643281;

    public static void main(String[] args) {
        List<Integer> matchingPasswords = new ArrayList<>();

        assert !matchesCriteria(111111);
        assert !matchesCriteria(643281);
        assert matchesCriteria(234556);
        assert !matchesCriteria(223450);
        assert !matchesCriteria(123789);
        assert !matchesCriteria(123444);
        assert matchesCriteria(111122);
        assert matchesCriteria(222233);

        for (int temp = INPUT_LOW; temp <= INPUT_HIGH; temp++) {
            if (matchesCriteria(temp)) {
                matchingPasswords.add(temp);
            }
        }

        System.out.println(matchingPasswords.size());
    }

    private static boolean matchesCriteria(int value) {
        int[] intTab = String.valueOf(value).chars().map(Character::getNumericValue).toArray();

        boolean hasTwoAdjacentDigits = false;
        int matchingDigit = -1;
        for (int i = 0; i < 5; i++) {
            if (intTab[i] > intTab[i + 1]) {
                return false;
            }
            if (intTab[i] == intTab[i + 1]) {
                matchingDigit = intTab[i];
                hasTwoAdjacentDigits = true;
            }
        }
        return counter(intTab, matchingDigit) == 2 && hasTwoAdjacentDigits;
    }

    private static int counter(int[] arr, int digit) {
        int count = 0;
        for (int value : arr) {
            if (value == digit) {
                count++;
            }
        }

        return count;
    }
}