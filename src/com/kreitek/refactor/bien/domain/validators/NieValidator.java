package com.kreitek.refactor.bien.domain.validators;

import com.kreitek.refactor.bien.utils.DocumentUtils;

public class NieValidator implements DocumentValidator {

    private static final char[] CONTROL_LETTERS = {
            'T','R','W','A','G','M','Y','F','P','D','X','B','N',
            'J','Z','S','Q','V','H','L','C','K','E'
    };
    private static final int NIE_LENGTH = 9;

    private static final NieValidator INSTANCE = new NieValidator();

    private NieValidator() { }

    public static NieValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValid(String documentNumber) {
        if (documentNumber == null) {
            return false;
        }

        String normalizedDocument = DocumentUtils.normalize(documentNumber);
        if (normalizedDocument.length() != NIE_LENGTH) {
            return false;
        }

        char first = normalizedDocument.charAt(0);
        char last = Character.toUpperCase(normalizedDocument.charAt(8));

        if (first != 'X' && first != 'Y' && first != 'Z') {
            return false;
        }

        // Posiciones 1 a 7 deben ser dígitos
        if (!DocumentUtils.arePositionsDigits(normalizedDocument, 1, 7)) {
            return false;
        }

        // Transformación X/Y/Z -> 0/1/2
        String prefix;
        if (first == 'X') {
            prefix = "0";
        } else if (first == 'Y') {
            prefix = "1";
        } else {
            prefix = "2";
        }

        String numericBody = normalizedDocument.substring(1, 8);
        int numericNie = Integer.parseInt(prefix + numericBody);

        int resto = numericNie % 23;
        char expected = CONTROL_LETTERS[resto];

        return last == expected;
    }
}
