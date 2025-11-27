package com.kreitek.refactor.bien.utils;

public final class DocumentUtils {

    private DocumentUtils() {
    }

    // Quita espacios y pone en mayúsculas.
    public static String normalize(String value) {
        if (value == null) {
            return "";
        }
        return value.trim().replace(" ", "").toUpperCase();
    }

    public static boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Devuelve true si desde start hasta end (ambos incluidos) son dígitos.
    public static boolean arePositionsDigits(String value, int start, int end) {
        if (value == null || start < 0 || end >= value.length() || start > end) {
            return false;
        }
        for (int i = start; i <= end; i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

