package tpo.lab2.calculator.system;

import tpo.lab2.calculator.Calculator;
import tpo.lab2.calculator.logarithmic.Ln;
import tpo.lab2.calculator.logarithmic.Log10;
import tpo.lab2.calculator.logarithmic.Log2;
import tpo.lab2.calculator.logarithmic.Log3;
import tpo.lab2.calculator.logarithmic.Log5;
import tpo.lab2.calculator.trigonometric.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionsSystem extends Calculator {
    private Cos cos;
    private Cot cot;
    private Ln ln;
    private Log2 log2;
    private Log3 log3;
    private Log5 log5;
    private Log10 log10;
    


    public FunctionsSystem(double accuracy) {
        super(accuracy);
        this.cos = new Cos(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        new Sin(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        new Sec(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        new Tan(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        this.cot = new Cot(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        this.ln = new Ln(Calculator.LOGARITHMIC_CALC_ACCURACY);
        this.log2 = new Log2(Calculator.LOGARITHMIC_CALC_ACCURACY);
        this.log3 = new Log3(Calculator.LOGARITHMIC_CALC_ACCURACY);
        this.log5 = new Log5(Calculator.LOGARITHMIC_CALC_ACCURACY);
        this.log10 = new Log10(Calculator.LOGARITHMIC_CALC_ACCURACY);
    }

    public FunctionsSystem(BigDecimal accuracy) {
        super(accuracy);
        this.cos = new Cos(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        new Sin(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        new Sec(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        new Tan(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        this.cot = new Cot(Calculator.TRIGONOMETRIC_CALC_ACCURACY);
        this.ln = new Ln(Calculator.LOGARITHMIC_CALC_ACCURACY);
        this.log2 = new Log2(Calculator.LOGARITHMIC_CALC_ACCURACY);
        this.log3 = new Log3(Calculator.LOGARITHMIC_CALC_ACCURACY);
        this.log5 = new Log5(Calculator.LOGARITHMIC_CALC_ACCURACY);
        this.log10 = new Log10(Calculator.LOGARITHMIC_CALC_ACCURACY);
    }

    @Override
    public BigDecimal calculate(BigDecimal x) {
        try {
            if (x.compareTo(BigDecimal.ZERO) <= 0)
                return (
                        cot.calculate(x)
                                .add(cos.calculate(x)));
                        
            else
                return (
                        ln.calculate(x)
                                .add(log3.calculate(x))
                                .add(log10.calculate(x)))
                        .divide(ln.calculate(x).pow(2), Calculator.LOGARITHMIC_CALC_ACCURACY.scale(), RoundingMode.HALF_EVEN)
                        .subtract(log5.calculate(x))
                        .multiply(log5.calculate(x).subtract(ln.calculate(x)).pow(2).subtract(log2.calculate(x)))
                        .setScale(getAccuracy().scale(), RoundingMode.HALF_EVEN);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("X value is not valid for this function");
        }
    }
}
