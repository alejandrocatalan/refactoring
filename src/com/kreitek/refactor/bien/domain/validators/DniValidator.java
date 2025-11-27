package com.kreitek.refactor.bien.domain.validators;

import com.kreitek.refactor.bien.utils.DocumentUtils;

public class DniValidator implements DocumentValidator {

    private static final String LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final int DNI_LENGTH = 9;
    private static final int NUMERIC_PART_LENGTH = 8;

    private static final DniValidator INSTANCE = new DniValidator();

    private DniValidator() { }

    public static DniValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValid(String documentNumber) {
        if (documentNumber == null) {
            return false;
        }

        String normalizedDocument = DocumentUtils.normalize(documentNumber);
        if (normalizedDocument.length() != DNI_LENGTH) {
            return false;
        }

        String numberPart = normalizedDocument.substring(0, NUMERIC_PART_LENGTH);
        char controlLetter = normalizedDocument.charAt(NUMERIC_PART_LENGTH);

        if (!DocumentUtils.isNumeric(numberPart)) {
            return false;
        }

        int numericValue = Integer.parseInt(numberPart);
        int index = numericValue % LETTERS.length();
        char expectedLetter = LETTERS.charAt(index);

        return expectedLetter == controlLetter;
    }
}
