package tpo.lab2;

import tpo.lab2.calculator.logarithmic.Ln;
import tpo.lab2.calculator.logarithmic.Log10;
import tpo.lab2.calculator.logarithmic.Log2;
import tpo.lab2.calculator.logarithmic.Log3;
import tpo.lab2.calculator.system.FunctionsSystem;
import tpo.lab2.calculator.trigonometric.*;
import tpo.lab2.util.CsvWriter;

import java.math.BigDecimal;

import static java.lang.Math.PI;

public class Main {
    public static void main(String[] args) {
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.setFilePathPrefix("src/main/resources/");

        Cos cos = new Cos(0.00000001);
        csvWriter.write("cos.csv", cos, BigDecimal.valueOf(-PI), BigDecimal.valueOf(PI), BigDecimal.valueOf(0.1));

        Sin sin = new Sin(0.00000001);
        csvWriter.write("sin.csv", sin, BigDecimal.valueOf(-PI), BigDecimal.valueOf(PI), BigDecimal.valueOf(0.1));

        Tan tan = new Tan(0.00000001);
        csvWriter.write("tan.csv", tan, BigDecimal.valueOf(-PI), BigDecimal.valueOf(PI), BigDecimal.valueOf(0.1));

        Cot cot = new Cot(0.00000001);
        csvWriter.write("cot.csv", cot, BigDecimal.valueOf(-PI), BigDecimal.valueOf(PI), BigDecimal.valueOf(0.1));

        Sec sec = new Sec(0.00000001);
        csvWriter.write("sec.csv", sec, BigDecimal.valueOf(-PI), BigDecimal.valueOf(PI), BigDecimal.valueOf(0.1));

        Ln ln = new Ln(0.00000001);
        csvWriter.write("ln.csv", ln, BigDecimal.valueOf(0), BigDecimal.valueOf(3), BigDecimal.valueOf(0.1));

        Log2 log2 = new Log2(0.00000001);
        csvWriter.write("log2.csv", log2, BigDecimal.valueOf(0), BigDecimal.valueOf(5), BigDecimal.valueOf(0.2));

        Log3 log3 = new Log3(0.00000001);
        csvWriter.write("log3.csv", log3, BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(0.5));

        Log10 log10 = new Log10(0.00000001);
        csvWriter.write("log10.csv", log10, BigDecimal.valueOf(0), BigDecimal.valueOf(15), BigDecimal.valueOf(0.5));

        FunctionsSystem fs = new FunctionsSystem(0.00000001);
        csvWriter.write("fs.csv", fs, BigDecimal.valueOf(-3), BigDecimal.valueOf(3), BigDecimal.valueOf(0.1));
    }
}
