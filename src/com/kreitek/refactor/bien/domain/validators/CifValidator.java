package com.kreitek.refactor.bien.domain.validators;

import com.kreitek.refactor.bien.utils.DocumentUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CifValidator implements DocumentValidator {

    private static final String VALID_INITIALS = "ABCDEFGHJKLMNPQRSUVW";
    private static final String CONTROL_CHARS = "JABCDEFGHI";
    private static final Pattern CIF_PATTERN =
            Pattern.compile("[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]");

    private static final CifValidator INSTANCE = new CifValidator();

    private CifValidator() { }

    public static CifValidator getInstance() {
        return INSTANCE;
    }

    private enum LastCharType {
        LETTER,
        DIGIT,
        BOTH
    }

    @Override
    public boolean isValid(String documentNumber) {
        if (documentNumber == null) {
            return false;
        }

        String normalizedDocument = DocumentUtils.normalize(documentNumber);
        if (normalizedDocument.isEmpty()) {
            return false;
        }

        char first = normalizedDocument.charAt(0);

        // Primer carácter debe estar en el conjunto válido
        if (VALID_INITIALS.indexOf(first) == -1) {
            return false;
        }

        Matcher matcher = CIF_PATTERN.matcher(normalizedDocument);
        if (!matcher.matches()) {
            return false;
        }

        char last = normalizedDocument.charAt(normalizedDocument.length() - 1);

        LastCharType lastCharType = determineLastCharType(first, last);
        if (lastCharType == null) {
            return false;
        }

        String digits = normalizedDocument.substring(1, normalizedDocument.length() - 1);

        int sumEven = sumEvenPositions(digits);
        int sumOdd = sumOddPositions(digits);
        int total = sumEven + sumOdd;

        int numControl = 10 - (total % 10);
        int pos = (numControl == 10) ? 0 : numControl;
        char controlChar = CONTROL_CHARS.charAt(pos);

        return switch (lastCharType) {
            case DIGIT -> {
                int lastDigit = Character.digit(last, 10);
                yield pos == lastDigit;
            }
            case LETTER -> controlChar == last;
            case BOTH -> validateLastCharBoth(last, pos, controlChar);
        };
    }

    private LastCharType determineLastCharType(char first, char last) {
        // P,Q,S,K,W --> último carácter debe ser LETRA
        if (first == 'P' || first == 'Q' || first == 'S'
                || first == 'K' || first == 'W') {
            if (!(last >= 'A' && last <= 'Z')) {
                return null;
            }
            return LastCharType.LETTER;
        }

        // A,B,E,H --> último carácter debe ser NÚMERO
        if (first == 'A' || first == 'B' || first == 'E' || first == 'H') {
            if (!(last >= '0' && last <= '9')) {
                return null;
            }
            return LastCharType.DIGIT;
        }

        // Resto --> puede ser letra o número
        if (!Character.isLetterOrDigit(last)) {
            return null;
        }
        return LastCharType.BOTH;
    }

    private int sumEvenPositions(String digits) {
        int sum = 0;
        for (int i = 1; i <= digits.length() - 1; i += 2) {
            sum += Character.digit(digits.charAt(i), 10);
        }
        return sum;
    }

    private int sumOddPositions(String digits) {
        int sum = 0;
        for (int i = 0; i <= digits.length() - 1; i += 2) {
            int cal = Character.digit(digits.charAt(i), 10) * 2;
            if (cal > 9) {
                cal = (cal / 10) + (cal % 10);
            }
            sum += cal;
        }
        return sum;
    }

    private boolean validateLastCharBoth(char last, int pos, char controlChar) {
        int ultCar = CONTROL_CHARS.indexOf(last);

        if (ultCar < 0) {
            if (!Character.isDigit(last)) {
                return false;
            }
            ultCar = Character.digit(last, 10);
            if (pos != ultCar) {
                return false;
            }
        }

        return pos == ultCar || controlChar == last;
    }
}
