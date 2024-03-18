package tpo.lab2.calculator.trigonometric;

import tpo.lab2.calculator.Calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static java.lang.Math.PI;
import static java.math.RoundingMode.HALF_EVEN;

public class Sin extends Calculator {

    public Sin(double accuracy) {
        super(accuracy);
    }

    public Sin(BigDecimal accuracy) {
        super(accuracy);
    }

    @Override
    public BigDecimal calculate(BigDecimal x) {
        BigDecimal sum = BigDecimal.ZERO, prev;
        int i = 0;
        if (x.remainder(BigDecimal.valueOf(PI)).compareTo(BigDecimal.ZERO) == 0)
            return BigDecimal.ZERO.setScale(getAccuracy().scale(), HALF_EVEN);
        //periodical function -> fuck big X
        x = x.remainder(BigDecimal.valueOf(2 * PI));
        do {
            prev = sum;
            sum = sum.add(
                    new BigDecimal(-1)
                            .pow(i)
                            .multiply(x.pow(2 * i + 1))
                            .divide(new BigDecimal(calculateFactorial(2 * i + 1)), Calculator.TRIGONOMETRIC_CALC_ACCURACY.scale(), RoundingMode.HALF_UP));
            i++;
        } while (getAccuracy().compareTo(prev.subtract(sum).abs()) < 0);
        return sum.setScale(getAccuracy().scale(), RoundingMode.HALF_EVEN);
    }

    private BigInteger calculateFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }
}
