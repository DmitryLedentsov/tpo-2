package tpo.lab2.calculator.trigonometric;

import tpo.lab2.calculator.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sec extends Calculator {
    private Cos cos;

    public Sec(double accuracy) {
        super(accuracy);
        this.cos = new Cos(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
    }

    public Sec(BigDecimal accuracy) {
        super(accuracy);
        this.cos = new Cos(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
    }

    @Override
    public BigDecimal calculate(BigDecimal x) {
        final BigDecimal cosValue = cos.calculate(x);
        if (cosValue.setScale(getAccuracy().scale(), RoundingMode.HALF_EVEN).doubleValue() == 0)
            throw new ArithmeticException("X value is not valid for function Sec");
        return BigDecimal.ONE.divide(cosValue, Calculator.TRIGONOMETRIC_CALC_ACCURACY.scale(), RoundingMode.HALF_EVEN).setScale(getAccuracy().scale(), RoundingMode.HALF_EVEN);
    }
}
