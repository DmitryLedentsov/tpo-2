package tpo.lab2.calculator.trigonometric;

import tpo.lab2.calculator.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cot extends Calculator {
    private Cos cos;
    private Sin sin;

    public Cot(double accuracy) {
        super(accuracy);
        this.cos = new Cos(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        this.sin = new Sin(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
    }

    public Cot(BigDecimal accuracy) {
        super(accuracy);
        this.cos = new Cos(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        this.sin = new Sin(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
    }

    @Override
    public BigDecimal calculate(BigDecimal x) {
        final BigDecimal cosValue = cos.calculate(x);
        final BigDecimal sinValue = sin.calculate(x);
        if (sinValue.compareTo(BigDecimal.ZERO) == 0)
            throw new ArithmeticException("X value is not valid for function Cot");
        return cosValue.divide(sinValue, Calculator.TRIGONOMETRIC_CALC_ACCURACY.scale(), RoundingMode.HALF_EVEN).setScale(getAccuracy().scale(), RoundingMode.HALF_EVEN);
    }
}
