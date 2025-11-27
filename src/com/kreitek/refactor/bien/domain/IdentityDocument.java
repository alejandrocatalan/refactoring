package com.kreitek.refactor.bien.domain;

import com.kreitek.refactor.bien.domain.validators.DocumentValidator;
import com.kreitek.refactor.bien.domain.validators.DocumentValidatorFactory;

public class IdentityDocument {

    private final DocumentType type;
    private final String number;

    public IdentityDocument(DocumentType type, String number) {
        this.type = type;
        this.number = number;
    }

    public DocumentType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public boolean isValid() {
        DocumentValidator validator = DocumentValidatorFactory.getValidator(type);
        return validator.isValid(number);
    }
}
