package tpo.lab2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tpo.lab2.calculator.logarithmic.Ln;
import tpo.lab2.calculator.logarithmic.Log10;
import tpo.lab2.calculator.logarithmic.Log2;
import tpo.lab2.calculator.logarithmic.Log3;
import tpo.lab2.calculator.logarithmic.Log5;
import tpo.lab2.calculator.system.FunctionsSystem;
import tpo.lab2.calculator.trigonometric.*;

import java.math.BigDecimal;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FunctionsSystemTest {
    private static final BigDecimal ACCURACY = BigDecimal.valueOf(0.00001);
    @Mock
    private Cos cos = new Cos(ACCURACY);
    @Mock
    private Cot cot = new Cot(ACCURACY);
    @Mock
    private Ln ln = new Ln(ACCURACY);
    @Mock
    private Log2 log2 = new Log2(ACCURACY);
    @Mock
    private Log3 log3 = new Log3(ACCURACY);
    @Mock
    private Log5 log5 = new Log5(ACCURACY);
    @Mock
    private Log10 log10 = new Log10(ACCURACY);
    @InjectMocks
    FunctionsSystem functionsSystem = new FunctionsSystem(ACCURACY);

    @Test
    void shouldCallTrigonometricMocks() {
        when(cos.calculate(eq(BigDecimal.valueOf(-1.488)))).thenReturn(BigDecimal.valueOf(Math.cos(-1.488)));
        //when(sin.calculate(eq(BigDecimal.valueOf(-1.488)))).thenReturn(BigDecimal.valueOf(Math.sin(-1.488)));
        //when(sec.calculate(eq(BigDecimal.valueOf(-1.488)))).thenReturn(BigDecimal.valueOf(1 / Math.cos(-1.488)));
        //when(tan.calculate(eq(BigDecimal.valueOf(-1.488)))).thenReturn(BigDecimal.valueOf(Math.sin(-1.488) / Math.cos(-1.488)));
        when(cot.calculate(eq(BigDecimal.valueOf(-1.488)))).thenReturn(BigDecimal.valueOf(Math.cos(-1.488) / Math.sin(-1.488)));
        functionsSystem.calculate(BigDecimal.valueOf(-1.488));
        verify(cos, atLeastOnce()).calculate(any(BigDecimal.class));
        //verify(sin, atLeastOnce()).calculate(any(BigDecimal.class));
        //verify(sec, atLeastOnce()).calculate(any(BigDecimal.class));
        //verify(tan, atLeastOnce()).calculate(any(BigDecimal.class));
        verify(cot, atLeastOnce()).calculate(any(BigDecimal.class));
    }

    @Test
    void shouldCallLogarithmicMocks() {
        when(ln.calculate(eq(BigDecimal.valueOf(2.28)))).thenReturn(BigDecimal.valueOf(Math.log(2.28)));
        when(log2.calculate(eq(BigDecimal.valueOf(2.28)))).thenReturn(BigDecimal.valueOf(Math.log(2.28) / Math.log(2)));
        when(log3.calculate(eq(BigDecimal.valueOf(2.28)))).thenReturn(BigDecimal.valueOf(Math.log(2.28) / Math.log(3)));
        when(log5.calculate(eq(BigDecimal.valueOf(2.28)))).thenReturn(BigDecimal.valueOf(Math.log(2.28) / Math.log(5)));
        when(log10.calculate(eq(BigDecimal.valueOf(2.28)))).thenReturn(BigDecimal.valueOf(Math.log(2.28) / Math.log(10)));
        functionsSystem.calculate(BigDecimal.valueOf(2.28));
        verify(ln, atLeastOnce()).calculate(any(BigDecimal.class));
        verify(log2, atLeastOnce()).calculate(any(BigDecimal.class));
        verify(log3, atLeastOnce()).calculate(any(BigDecimal.class));
        verify(log5, atLeastOnce()).calculate(any(BigDecimal.class));
        verify(log10, atLeastOnce()).calculate(any(BigDecimal.class));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.81800544173759694730, -2.4938085790642309486,
            -7.1011907489171834243, -8.7769938862438174256, -15.060179193423403903})
    public void checkTrigonometricRoots(double x) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        //when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        //when(sec.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(1 / Math.cos(x)));
        //when(tan.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x) / Math.cos(x)));
        when(cot.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x) / Math.sin(x)));
        assertEquals(0, functionsSystem.calculate(BigDecimal.valueOf(x)).doubleValue(), ACCURACY.doubleValue());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -PI, -2 * PI})
    public void checkTrigonometricGaps(double x) {
        //when(sec.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.ONE);
        when(cot.calculate(eq(BigDecimal.valueOf(x)))).thenThrow(ArithmeticException.class);
        assertThrows(IllegalArgumentException.class, () -> functionsSystem.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest
    @CsvSource({
            "-3.14148, 8876.7696814811",
            "-5.56785, -87205.856570384",
            "-6.99851, -40517.506750832",
            "-13.28169, -17555.271547785"

    })
    public void checkTrigonometricBigYValues(double x, double y) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        //when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        //when(sec.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(1 / Math.cos(x)));
       // when(tan.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x) / Math.cos(x)));
        when(cot.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x) / Math.sin(x)));
        assertEquals(y, functionsSystem.calculate(BigDecimal.valueOf(x)).doubleValue(), ACCURACY.doubleValue());
    }

    @ParameterizedTest
    @CsvSource({
            "-1.652228, -0.999954",
            "-4, -0.707262",
            "-5.8932, 3.665228",
            "-8, -0.999505"

    })
    public void checkTrigonometricCommonValues(double x, double y) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        //when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        //when(sec.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(1 / Math.cos(x)));
        //when(tan.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x) / Math.cos(x)));
        when(cot.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x) / Math.sin(x)));
        assertEquals(y, functionsSystem.calculate(BigDecimal.valueOf(x)).doubleValue(), ACCURACY.doubleValue());
    }


    @Test
    public void checkLogarithmicGaps() {
        when(ln.calculate(eq(BigDecimal.ONE))).thenReturn(BigDecimal.ZERO);
        when(log2.calculate(eq(BigDecimal.ONE))).thenReturn(BigDecimal.ZERO);
        assertThrows(IllegalArgumentException.class, () -> functionsSystem.calculate(BigDecimal.ONE));
    }

    @ParameterizedTest
    @CsvSource({
            "0.99, -880368.70267386660",
            "1.6, -3.03568"
    })
    public void checkLogarithmicBigYValues(double x, double y) {
        when(ln.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x)));
        when(log2.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x) / Math.log(2)));
        when(log3.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x) / Math.log(3)));
        when(log5.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x) / Math.log(5)));
        when(log10.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x) / Math.log(10)));
        assertEquals(y, functionsSystem.calculate(BigDecimal.valueOf(x)).doubleValue(), ACCURACY.doubleValue());
    }

    @ParameterizedTest
    @CsvSource({
            "0.5, -3.160696",
            "3, 0.975025",
            "15, 0.167124",
            "25, 0.129539",
            "35, 0.112905"

    })
    public void checkLogarithmicCommonValues(double x, double y) {
        when(ln.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x)));
        when(log2.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x) / Math.log(2)));
        when(log3.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x) / Math.log(3)));
        when(log5.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x) / Math.log(5)));
        when(log10.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.log(x) / Math.log(10)));
        assertEquals(y, functionsSystem.calculate(BigDecimal.valueOf(x)).doubleValue(), ACCURACY.doubleValue());
    }

}
