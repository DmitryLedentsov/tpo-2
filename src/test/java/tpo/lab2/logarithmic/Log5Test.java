package tpo.lab2.logarithmic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tpo.lab2.calculator.logarithmic.Ln;
import tpo.lab2.calculator.logarithmic.Log5;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Log5Test {
    private static final BigDecimal ACCURACY = BigDecimal.valueOf(0.00000001);
    @Mock
    private Ln ln = new Ln(ACCURACY);
    @InjectMocks
    private Log5 Log5 = new Log5(ACCURACY);


    @Test
    void shouldCallLnFunction() {
        when(ln.calculate(any())).thenReturn(BigDecimal.valueOf(1.48));
        Log5.calculate(new BigDecimal(8));
        verify(ln, atLeastOnce()).calculate(any(BigDecimal.class));
    }

    @Test
    public void zeroTest() {
        when(ln.calculate(eq(ZERO))).thenThrow(ArithmeticException.class);
        assertThrows(ArithmeticException.class, () -> Log5.calculate(BigDecimal.ZERO));
    }

    @Test
    public void oneTest() {
        when(ln.calculate(eq(ONE))).thenReturn(ZERO);
        when(ln.calculate(eq(BigDecimal.valueOf(5)))).thenReturn(BigDecimal.valueOf(Math.log(5)));
        assertEquals(ZERO.setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                Log5.calculate(ONE));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -2, -2.5, -10, -20, -10E2})
    public void negativeTest(double x) {
        when(ln.calculate(eq(BigDecimal.valueOf(x)))).thenThrow(ArithmeticException.class);
        assertThrows(ArithmeticException.class, () -> Log5.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {2, 2.5, 3, 10, 20, 10E2})
    public void positiveTest(double x) {
        when(ln.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x)));
        when(ln.calculate(eq(BigDecimal.valueOf(5)))).thenReturn(BigDecimal.valueOf(Math.log(5)));
        assertEquals(Math.log(x) / Math.log(5),
                Log5.calculate(BigDecimal.valueOf(x)).doubleValue(),
                ACCURACY.doubleValue());
    }
}
