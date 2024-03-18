package tpo.lab2.util;

import lombok.Data;
import tpo.lab2.calculator.Calculator;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

@Data
public class CsvWriter {
    private String filePathPrefix = "src/test/resources/";
    private char CSV_SEPARATOR = ';';

    public void write(String fileName, Calculator calculator, BigDecimal from, BigDecimal to, BigDecimal step) {
        String filePath = filePathPrefix + fileName;
        BigDecimal y;
        try (PrintStream printStream = new PrintStream(new FileOutputStream(filePath, true))) {
            for (BigDecimal x = from; x.compareTo(to) <= 0; x = x.add(step)) {
                try {
                    y = calculator.calculate(x).setScale(calculator.getAccuracy().scale(), RoundingMode.HALF_EVEN);
                } catch (IllegalArgumentException | ArithmeticException e) {
                    System.err.println(e.getMessage() + ", x=" + x);
                    continue;
                }
                printStream.printf(Locale.US, "%f%c %f\n", x, CSV_SEPARATOR, y);
            }
        } catch (Exception e) {
            System.err.println("Can't write to file: " + e.getMessage());
        }
    }
}
