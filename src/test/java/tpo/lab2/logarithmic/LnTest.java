package tpo.lab2.logarithmic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tpo.lab2.calculator.logarithmic.Ln;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LnTest {
    private static final BigDecimal ACCURACY = BigDecimal.valueOf(0.00000001);
    private final Ln ln = new Ln(ACCURACY);

    @Test
    public void zeroTest() {
        assertThrows(ArithmeticException.class, () -> ln.calculate(BigDecimal.ZERO));
    }

    @Test
    public void oneTest() {
        assertEquals(ZERO.setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                ln.calculate(ONE));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -2, -2.5, -10, -20, -10E2})
    public void negativeTest(double x) {
        assertThrows(ArithmeticException.class, () -> ln.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {2, 2.5, 3, 10, 20, 10E2})
    public void positiveTest(double x) {
        assertEquals(Math.log(x),
                ln.calculate(BigDecimal.valueOf(x)).doubleValue(),
                ACCURACY.doubleValue());
    }
}
