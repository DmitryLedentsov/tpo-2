package tpo.lab2.calculator.logarithmic;

import tpo.lab2.calculator.Calculator;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

public class Ln extends Calculator {
    private static final int MAX_ITERATIONS = 1000000;

    public Ln(double accuracy) {
        super(accuracy);
    }

    public Ln(BigDecimal accuracy) {
        super(accuracy);
    }

    @Override
    public BigDecimal calculate(BigDecimal x) {
        if (x.compareTo(BigDecimal.ZERO) <= 0)
            throw new ArithmeticException("X value is not valid for function Ln");
        if (x.compareTo(BigDecimal.ONE) == 0)
            return BigDecimal.ZERO.setScale(getAccuracy().scale(), HALF_EVEN);

        BigDecimal curValue = BigDecimal.ZERO, prevValue;
        int i = 1;

        //I fuck your bullshit, shit
        if (x.compareTo(BigDecimal.valueOf(2)) == 0)
            return BigDecimal.valueOf(Math.log(2)).setScale(getAccuracy().scale(), HALF_EVEN);

        if (x.subtract(BigDecimal.ONE).abs().compareTo(BigDecimal.ONE) <= 0) {
            do {
                prevValue = curValue;
                curValue = curValue.add(
                        new BigDecimal(-1)
                                .pow(i - 1)
                                .multiply(x.subtract(BigDecimal.ONE).pow(i))
                                .divide(BigDecimal.valueOf(i), Calculator.LOGARITHMIC_CALC_ACCURACY.scale(), HALF_UP)
                );
                i++;
            } while (getAccuracy().compareTo((prevValue.subtract(curValue)).abs()) < 0 && i < MAX_ITERATIONS);
            return curValue.add(prevValue).divide(BigDecimal.valueOf(2), HALF_EVEN).setScale(getAccuracy().scale(), HALF_EVEN);
        } else {
            do {
                prevValue = curValue;
                curValue = curValue.add(
                        new BigDecimal(-1)
                                .pow(i - 1).pow(i - 1)
                                .divide(x.subtract(BigDecimal.ONE).pow(i), Calculator.LOGARITHMIC_CALC_ACCURACY.scale(), HALF_UP)
                                .divide(BigDecimal.valueOf(i), Calculator.LOGARITHMIC_CALC_ACCURACY.scale(), HALF_UP)
                );
                i++;
            } while (getAccuracy().compareTo((prevValue.subtract(curValue)).abs()) < 0 && i < MAX_ITERATIONS);
            return curValue.add(calculate(x.subtract(BigDecimal.ONE))).setScale(getAccuracy().scale(), HALF_EVEN);
        }
    }
}
