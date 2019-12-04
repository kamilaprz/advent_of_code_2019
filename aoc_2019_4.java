import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Scratch {
    static final int INPUT_LOW = 128392;
    static final int INPUT_HIGH = 643281;

    public static void main(String[] args) {

        assert matchesCriteria(234556);
        assert matchesCriteria(111122);
        assert matchesCriteria(222233);
        assert matchesCriteria(122233);
        assert !matchesCriteria(111111);
        assert !matchesCriteria(643281);
        assert !matchesCriteria(223450);
        assert !matchesCriteria(123789);
        assert !matchesCriteria(123444);
        assert matchesCriteria(122333);
        assert !matchesCriteria(110002);

        System.out.println(IntStream.range(INPUT_LOW, INPUT_HIGH).filter(Scratch::matchesCriteria).count());
    }

    private static boolean matchesCriteria(int value) {
        int[] intTab = String.valueOf(value).chars().map(Character::getNumericValue).toArray();

        for (int i = 0; i < 5; i++) {
            if (intTab[i] > intTab[i + 1]) {
                return false;
            }
        }
        return hasMatchingDoubles(intTab);
    }

    private static boolean hasMatchingDoubles(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(s -> s))
                .values()
                .stream()
                .anyMatch(v -> v.size() == 2);
    }
}