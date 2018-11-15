package com.bankocr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int digitWidth = 3;
    private static final int digitHeight = 3;
    private static final int digitsPerLine = 9;
    private static List<String> bankOCR = new ArrayList<>();
    private static List<String> accountNumbers = new ArrayList<>();

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir") + "/account-numbers.txt");
        try {
            bankOCR = getAccountNumbers(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bankOCR);
        System.out.println(calculateCheckSum(bankOCR));
    }

    private static List<Integer> calculateCheckSum(List<String> AccountNumbers) {
        List<Integer> checkSum = new ArrayList<>();
        for (String current : AccountNumbers) {
            int sum = 0;
            int tempCheckSumHelper = 1;
            for (int j = current.length() - 1; j >= 0; --j) {
                sum += Character.getNumericValue(current.charAt(j)) * tempCheckSumHelper;
                ++tempCheckSumHelper;
            }
            checkSum.add(sum % 11);
        }
        return checkSum;
    }

    private static List<String> getAccountNumbers(File accountNumbersFile) throws IOException {
        List<String> content = Files.readAllLines(accountNumbersFile.toPath());
        for (int lineIndex = 0; lineIndex < content.size(); lineIndex += digitHeight + 1) {
            char[][] accountEntry = new char[digitHeight][digitWidth];
            accountEntry[0] = content.get(lineIndex).toCharArray();
            accountEntry[1] = content.get(lineIndex + 1).toCharArray();
            accountEntry[2] = content.get(lineIndex + 2).toCharArray();
            accountNumbers.add(parseAccountNumber(accountEntry));
        }
        return accountNumbers;
    }

    private static String parseAccountNumber(char[][] accountNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int digitIndex = 0; digitIndex < digitsPerLine; ++digitIndex) {
            char[][] digit = new char[digitHeight][digitWidth];
            int digitStartIndex = digitIndex * digitWidth;
            int digitEndIndex = digitStartIndex + digitWidth;
            for (int rowIndex = 0; rowIndex < digitHeight; ++rowIndex) {
                digit[rowIndex] = Arrays.copyOfRange(accountNumber[rowIndex], digitStartIndex, digitEndIndex);
            }
            stringBuilder.append(parseDigit(digit));
        }
        return stringBuilder.toString();
    }

    private static char parseDigit(char[][] digit) {
        if (Arrays.deepEquals(digit, Digits.ZERO)) return '0';
        if (Arrays.deepEquals(digit, Digits.ONE)) return '1';
        if (Arrays.deepEquals(digit, Digits.TWO)) return '2';
        if (Arrays.deepEquals(digit, Digits.THREE)) return '3';
        if (Arrays.deepEquals(digit, Digits.FOUR)) return '4';
        if (Arrays.deepEquals(digit, Digits.FIVE)) return '5';
        if (Arrays.deepEquals(digit, Digits.SIX)) return '6';
        if (Arrays.deepEquals(digit, Digits.SEVEN)) return '7';
        if (Arrays.deepEquals(digit, Digits.EIGHT)) return '8';
        if (Arrays.deepEquals(digit, Digits.NINE)) return '9';
        throw new IllegalArgumentException("Cannot parse digit " + Arrays.deepToString(digit));
    }
}