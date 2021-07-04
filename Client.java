package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Client {
    Scanner scanner = new Scanner(System.in);
    int sourceBase;
    int targetBase;

    public void dialogueRequestBases() {
        while(true) {
           System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
           String input1 = scanner.next();
            if("/exit".equals(input1)) {
                System.exit(0);
            }
           String input2 = scanner.next();
           sourceBase = Integer.parseInt(input1);
           targetBase = Integer.parseInt(input2);
           dialogueRequestNumbers();
        }
    }

    public void dialogueRequestNumbers() {
        while(true) {
            System.out.printf("Enter number in base %d to convert to base %d ", sourceBase, targetBase);
            System.out.println("(To go back type /back)");
            String toConvert = scanner.next();
            if("/back".equals(toConvert)) {
                return;
            }

            printConversion(toConvert);
        }
    }


    public void printConversion(String toConvert) {
        System.out.print("Conversion result: ");
        String[] decomposition = toConvert.split("[.]");
        if(decomposition.length == 1) {
            BigInteger decimalStep = Conversion.convertToDecimal(decomposition[0], sourceBase);
            System.out.println(Conversion.convertFromDecimal(decimalStep, targetBase));
        } else {
            String wholePart = decomposition[0];
            String fractionalPart = "0." + decomposition[1];
            BigInteger decimalStepWholePart = Conversion.convertToDecimal(wholePart, sourceBase);
            BigDecimal decimalStepFractionalPart = Conversion.convertFractionalToDecimal(fractionalPart,sourceBase);

            String resultWholePart = Conversion.convertFromDecimal(decimalStepWholePart, targetBase);
            String resultFractionalPart = Conversion.convertFractionalFromDecimal(decimalStepFractionalPart, targetBase);

            System.out.println(resultWholePart + "." + resultFractionalPart);

        }
    }



}
