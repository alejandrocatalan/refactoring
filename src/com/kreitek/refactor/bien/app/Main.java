package com.kreitek.refactor.bien.app;

import com.kreitek.refactor.bien.domain.DocumentType;
import com.kreitek.refactor.bien.domain.IdentityDocument;

public class Main {

    public static void main(String[] args) {
        System.out.println("=====================");
        System.out.println("Vamos a refactorizar!");
        System.out.println("=====================");

        printValidity(new IdentityDocument(DocumentType.DNI, "11111111H"));
        printValidity(new IdentityDocument(DocumentType.DNI, "24324356A"));

        printValidity(new IdentityDocument(DocumentType.NIE, "X0932707B"));
        printValidity(new IdentityDocument(DocumentType.NIE, "Z2691139Z"));

        printValidity(new IdentityDocument(DocumentType.CIF, "W9696294I"));
        printValidity(new IdentityDocument(DocumentType.CIF, "W9696294A"));
    }

    private static void printValidity(IdentityDocument document) {
        boolean isValid = document.isValid();
        System.out.println(document.getType() + " " + document.getNumber() + " es: " + isValid);
    }
}