import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    static MathService mathService;

    @BeforeAll
    static void init() {
        mathService = new MathService();
    }

    @ParameterizedTest
    @CsvSource(value = {"2, 5, -7, 81", "0, -7, 6, 49", "16,-8, 1, 0", "9, -6, 2, -36"})
    void testMethodGetD(int a, int b, int c, int res) {
        int dec = mathService.getD(a, b, c);
        Assertions.assertEquals(res, dec);
    }

    @ParameterizedTest
    @CsvSource(value = {"2, 5, -7, 1, -3.5", "1, -1, -6, 3.0, -2.0", "-1, -1, 56, -8, 7"})
    void testGetAnswerDecMoreZero(int a, int b, int c, double x1, double x2) throws NotFoundAnswerException {
        Pair pair = mathService.getAnswer(a, b, c);
        Assertions.assertEquals(x1, pair.first);
        Assertions.assertEquals(x2, pair.second);
    }

    @ParameterizedTest
    @CsvSource(value = {"3,-18, 27, 3.0", "4, 32, 64, -4.0", "16, -8, 1, 0.25"})
    void testGetAnswerDecEqualsZero(int a, int b, int c, double res) throws NotFoundAnswerException {
        Pair pair = mathService.getAnswer(a, b, c);
        Assertions.assertEquals(res, pair.first);
    }

    @ParameterizedTest
    @CsvSource(value = {"9, -6, 2", "1, 4, 29", "1, -1, 56"})
    void testGetAnswerDecLessZeroNegative(int a, int b, int c) {
        Throwable exc = assertThrows(NotFoundAnswerException.class,
                () -> mathService.getAnswer(a, b, c));
        Assertions.assertEquals("Корни не могут быть найдены", exc.getMessage());
    }
}
