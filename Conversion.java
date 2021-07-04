package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Conversion {
    public static String convertFromDecimal(BigInteger toConvert, int targetBase) {
        StringBuilder result = new StringBuilder("");
        BigInteger tempConvert = toConvert;
        BigInteger bigBase = new BigInteger(String.valueOf(targetBase));

        if(tempConvert.intValue() == 0) {
            return "0";
        }

        while (tempConvert.intValue() != 0) {
            BigInteger nextDigit = tempConvert.mod(bigBase);
            appendAtStart(result, bigIntToLetter(nextDigit));
            tempConvert = tempConvert.divide(bigBase);
        }

        return result.toString();
    }


    public static BigDecimal convertFractionalToDecimal(String toConvert, int sourceBase) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal bigBase = new BigDecimal(String.valueOf(sourceBase));
        String formattedToConvert = toConvert + "00000";
        for(int i = 0; i <= 5; i++) {
            BigDecimal valOfCurrentPosition =
                    BigDecimal.ONE.divide(bigBase.pow(i + 1), 100 ,RoundingMode.HALF_UP)
                            .multiply(letterToBigDec(formattedToConvert.charAt(i + 2)));
            result = result.add(valOfCurrentPosition);
        }

        return result;
    }


    public static BigInteger convertToDecimal(String toConvert, int sourceBase) {
        BigInteger result = BigInteger.ZERO;
        BigInteger bigBase = new BigInteger(String.valueOf(sourceBase));

        for(int i = 0; i < toConvert.length(); i++) {
            result = result.add(bigBase.pow(i).multiply(letterToBigInt(toConvert.charAt(toConvert.length() - i - 1))));
        }

        return result;
    }


    //takes a fractional part of the shape 0.xyz...
    public static String convertFractionalFromDecimal (BigDecimal toConvert, int targetBase) {
        StringBuilder result = new StringBuilder("");
        BigDecimal tempConvert = toConvert;
        BigDecimal bigBase = new BigDecimal(String.valueOf(targetBase));
        int counter = 0;

        while (counter < 5) {
            BigDecimal nextDigit = tempConvert.multiply(bigBase).setScale(0, RoundingMode.DOWN);
            result.append(intToLetter(nextDigit.intValue()));
            tempConvert = tempConvert.multiply(bigBase).subtract(nextDigit);
            counter++;
        }

        return result.toString();
    }


    private static void appendAtStart(StringBuilder string, char toAppend) {
        string.reverse();
        string.append(toAppend);
        string.reverse();
    }

    private static BigInteger letterToBigInt(char letter) {
        if('0' <= letter && letter <= '9') {
            return new BigInteger(String.valueOf(letter - '0'));
        }
        if('A' <= letter && letter <= 'Z') {
            return new BigInteger(String.valueOf(letter - 'A' + 10));
        }
        if('a' <= letter && letter <= 'z') {
            return new BigInteger(String.valueOf(letter - 'a' + 10));
        } else {
            System.out.println("ohweia");
            return BigInteger.ZERO;
        }
    }

    private static BigDecimal letterToBigDec(char letter) {
        if('0' <= letter && letter <= '9') {
            return new BigDecimal(String.valueOf(letter - '0'));
        }
        if('A' <= letter && letter <= 'Z') {
            return new BigDecimal(String.valueOf(letter - 'A' + 10));
        }
        if('a' <= letter && letter <= 'z') {
            return new BigDecimal(String.valueOf(letter - 'a' + 10));
        } else {
            System.out.println("ohweia");
            return BigDecimal.ZERO;
        }
    }


    private static char intToLetter(int num) {
        if(num < 0) {
            System.out.println("ohweia");
            return '0';
        }
        if(num <= 9) {
            return (char) ('0' + num);
        }
        char result = (char) ('A' + num - 10);
        return result;
    }

    private static char bigIntToLetter(BigInteger num) {
        if(num.intValue() < 0) {
            System.out.println("ohweia");
                    return '0';
        }
        if(num.intValue() <= 9) {
            return (char) ('0' + num.intValue());
        }
        char result = (char) ('A' + num.intValue() - 10);
        return result;
    }

}


