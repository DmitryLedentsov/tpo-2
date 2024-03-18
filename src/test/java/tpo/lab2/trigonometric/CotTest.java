package tpo.lab2.trigonometric;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tpo.lab2.calculator.trigonometric.Cos;
import tpo.lab2.calculator.trigonometric.Cot;
import tpo.lab2.calculator.trigonometric.Sin;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CotTest {
    private static final BigDecimal ACCURACY = BigDecimal.valueOf(0.000000001);
    @Mock
    private Cos cos = new Cos(ACCURACY);
    @Mock
    private Sin sin = new Sin(ACCURACY);
    @InjectMocks
    private Cot cot = new Cot(ACCURACY);

    @Test
    void shouldCallSinAndCosFunction() {
        when(sin.calculate(any())).thenReturn(BigDecimal.ONE);
        when(cos.calculate(any())).thenReturn(BigDecimal.ZERO);
        cot.calculate(BigDecimal.valueOf(PI / 2));
        verify(cos, atLeastOnce()).calculate(any(BigDecimal.class));
        verify(sin, atLeastOnce()).calculate(any(BigDecimal.class));
    }

    @ParameterizedTest(name = "{index}. Test Cot({0}) on null ")
    @ValueSource(doubles = {-2 * PI, -PI, 0, PI, 2 * PI})
    void checkGapValues(double x) {
        when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.ZERO);
        assertThrows(ArithmeticException.class, () -> cot.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test Cot({0}) in peaks ")
    @ValueSource(doubles = {
            (PI / 2), (3 * PI) / 2, (PI / 2) + 2 * PI, ((3 * PI) / 2) + (2 * PI),
            -(PI / 2), -(3 * PI) / 2, -(PI / 2) - 2 * PI, -((3 * PI) / 2) - (2 * PI)
    })
    void checkPeaks(double x) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        assertEquals(BigDecimal.valueOf(Math.cos(x) / Math.sin(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                cot.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test Cot({0}) in small derivative areas ")
    @ValueSource(doubles = {-2 * PI / 3, -PI / 3, PI / 3, 2 * PI / 3
    })
    void checkIntervalsWithSmoothDerivativeChange(double x) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        assertEquals(BigDecimal.valueOf(Math.cos(x) / Math.sin(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                cot.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test Cot({0}) in large derivative areas ")
    @ValueSource(doubles = {-7 * PI / 8, -PI / 8, PI / 8, 7 * PI / 8})
    void checkIntervalsWithSignificantDerivativeChange(double x) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        assertEquals(BigDecimal.valueOf(Math.cos(x) / Math.sin(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                cot.calculate(BigDecimal.valueOf(x)));
    }
}
