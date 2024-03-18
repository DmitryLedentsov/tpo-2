package tpo.lab2.trigonometric;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tpo.lab2.calculator.Calculator;
import tpo.lab2.calculator.trigonometric.Cos;
import tpo.lab2.calculator.trigonometric.Sin;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.PI;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CosTest {
    private static final BigDecimal ACCURACY = BigDecimal.valueOf(0.000000001);
    @Mock
    private Sin sin = new Sin(ACCURACY);
    @InjectMocks
    private Cos cos = new Cos(ACCURACY);


    @Test
    void shouldCallSinFunction() {
        when(sin.calculate(any())).thenReturn(BigDecimal.valueOf(-1));
        cos.calculate(new BigDecimal(PI));
        verify(sin, atLeastOnce()).calculate(any(BigDecimal.class));
    }

    @ParameterizedTest(name = "{index}. Test cos({0}) on 0")
    @ValueSource(doubles = {(PI / 2), (PI / 2) + 2 * PI, (PI / 2) - 2 * PI,
            (3 * PI) / 2, ((3 * PI) / 2) + (2 * PI), ((3 * PI) / 2) - (2 * PI)})
    public void zeroTest(double x) {
        when(sin.calculate(any())).thenReturn(
                BigDecimal.valueOf(Math.sin(new BigDecimal(PI)
                        .divide(new BigDecimal(2), Calculator.TRIGONOMETRIC_CALC_ACCURACY.scale(), HALF_EVEN)
                        .subtract(BigDecimal.valueOf(x)).doubleValue())));
        assertEquals(ZERO.setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                cos.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test cos({0}) on 1")
    @ValueSource(doubles = {-2 * PI, 0, 2 * PI})
    public void oneTest(double x) {
        when(sin.calculate(any())).thenReturn(
                BigDecimal.valueOf(Math.sin(new BigDecimal(PI)
                        .divide(new BigDecimal(2), Calculator.TRIGONOMETRIC_CALC_ACCURACY.scale(), HALF_EVEN)
                        .subtract(BigDecimal.valueOf(x)).doubleValue())));
        assertEquals(ONE.setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                cos.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test cos({0}) on -1")
    @ValueSource(doubles = {-3 * PI, -PI, PI, 3 * PI})
    public void minusOneTest(double x) {
        when(sin.calculate(any())).thenReturn(
                BigDecimal.valueOf(Math.sin(new BigDecimal(PI)
                        .divide(new BigDecimal(2), Calculator.TRIGONOMETRIC_CALC_ACCURACY.scale(), HALF_EVEN)
                        .subtract(BigDecimal.valueOf(x)).doubleValue())));
        assertEquals(ZERO.subtract(ONE).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                cos.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test cos({0}) on increasing")
    @ValueSource(doubles = {PI / 3, -PI / 3, PI / 3 + 2 * PI, -PI / 3 + 2 * PI})
    public void functionIncreasing(double x) {
        when(sin.calculate(any())).thenReturn(
                BigDecimal.valueOf(Math.sin(new BigDecimal(PI)
                        .divide(new BigDecimal(2), Calculator.TRIGONOMETRIC_CALC_ACCURACY.scale(), HALF_EVEN)
                        .subtract(BigDecimal.valueOf(x)).doubleValue())));
        assertEquals(BigDecimal.valueOf(Math.cos(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                cos.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test cos({0}) on decreasing")
    @ValueSource(doubles = {2 * PI / 3, -2 * PI / 3, 2 * PI / 3 + 2 * PI, -2 * PI / 3 + 2 * PI})
    public void functionDecreasing(double x) {
        when(sin.calculate(any())).thenReturn(
                BigDecimal.valueOf(Math.sin(new BigDecimal(PI)
                        .divide(new BigDecimal(2), Calculator.TRIGONOMETRIC_CALC_ACCURACY.scale(), HALF_EVEN)
                        .subtract(BigDecimal.valueOf(x)).doubleValue())));
        assertEquals(BigDecimal.valueOf(Math.cos(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                cos.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test cos({0}) in big X values")
    @ValueSource(doubles = {-10e4, 10e4})
    void checkBigX(double x) {
        when(sin.calculate(any())).thenReturn(
                BigDecimal.valueOf(Math.sin(new BigDecimal(PI)
                        .divide(new BigDecimal(2), Calculator.TRIGONOMETRIC_CALC_ACCURACY.scale(), HALF_EVEN)
                        .subtract(BigDecimal.valueOf(x)).doubleValue())));
        assertEquals(BigDecimal.valueOf(Math.cos(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                cos.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test cos({0}) in small X values")
    @ValueSource(doubles = {-10e-4, 10e-4})
    void checkSmallX(double x) {
        when(sin.calculate(any())).thenReturn(
                BigDecimal.valueOf(Math.sin(new BigDecimal(PI)
                        .divide(new BigDecimal(2), Calculator.TRIGONOMETRIC_CALC_ACCURACY.scale(), HALF_EVEN)
                        .subtract(BigDecimal.valueOf(x)).doubleValue())));
        assertEquals(BigDecimal.valueOf(Math.cos(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                cos.calculate(BigDecimal.valueOf(x)));
    }
}
