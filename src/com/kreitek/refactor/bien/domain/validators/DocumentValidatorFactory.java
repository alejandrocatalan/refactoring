package com.kreitek.refactor.bien.domain.validators;

import com.kreitek.refactor.bien.domain.DocumentType;

public class DocumentValidatorFactory {

    private DocumentValidatorFactory() {
    }

    public static DocumentValidator getValidator(DocumentType type) {
        if (type == null) {
            throw new IllegalArgumentException("Document type cannot be null");
        }

        return switch (type) {
            case DNI -> DniValidator.getInstance();
            case NIE -> NieValidator.getInstance();
            case CIF -> CifValidator.getInstance();
            default -> throw new IllegalArgumentException("Unsupported document type: " + type);
        };
    }
}
