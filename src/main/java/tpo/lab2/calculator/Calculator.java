package tpo.lab2.calculator;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Calculator implements Calculable {
    @Getter
    private final BigDecimal accuracy;
    public static BigDecimal TRIGONOMETRIC_CALC_ACCURACY = BigDecimal.valueOf(10E-300);
    public static BigDecimal LOGARITHMIC_CALC_ACCURACY = BigDecimal.valueOf(10E-50);

    public Calculator(double accuracy) {
        this(BigDecimal.valueOf(accuracy));
    }

    public Calculator(BigDecimal accuracy) {
        Objects.requireNonNull(accuracy, "Accuracy should not be NULL");
        if (accuracy.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Accuracy should be greater than zero");
        if (accuracy.compareTo(BigDecimal.ONE) >= 0)
            throw new IllegalArgumentException("Accuracy should be less than one");
        this.accuracy = accuracy;
    }
}
