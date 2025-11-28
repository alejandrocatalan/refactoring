package com.kreitek.refactor.mal;

class  Main
{
    public static void main(String args[])
    {
        System.out.println("=====================");
        System.out.println("Vamos a refactorizar!");
        System.out.println("=====================");

        DNI dniCorrecto = new DNI(TIPODNI.DNI, "11111111H", null);
        Boolean esValidoDniCorrecto = (dniCorrecto.validarDNI() == 1);
        System.out.println( "DNI " + dniCorrecto.numDNI + " es: " + esValidoDniCorrecto);

        DNI dniIncorrecto = new DNI(TIPODNI.DNI, "24324356A", null);
        Boolean esValidoDniIncorrecto = (dniIncorrecto.validarDNI() == 1);
        System.out.println( "DNI " + dniIncorrecto.numDNI + " es: " + esValidoDniIncorrecto);

        DNI nieCorrecto = new DNI(TIPODNI.NIE, "X0932707B", null);
        Boolean esValidoNieCorrecto = (nieCorrecto.validarDNI() == 1);
        System.out.println( "NIE " + nieCorrecto.numDNI + " es: " + esValidoNieCorrecto);

        DNI nieIncorrecto = new DNI(TIPODNI.NIE, "Z2691139Z", null);
        Boolean esValidoNieIncorrecto = (nieIncorrecto.validarDNI() == 1);
        System.out.println( "NIE " + nieIncorrecto.numDNI + " es: " + esValidoNieIncorrecto);

        DNI cifCorrecto = new DNI(TIPODNI.CIF, "W9696294I", null);
        Boolean esValidoCifCorrecto = (cifCorrecto.validarDNI() == 1);
        System.out.println( "CIF " + cifCorrecto.numDNI + " es: " + esValidoCifCorrecto);

        DNI cifIncorrecto = new DNI(TIPODNI.CIF, "W9696294A", null);
        Boolean esValidoCifIncorrecto = (cifIncorrecto.validarDNI() == 1);
        System.out.println( "NIE " + cifIncorrecto.numDNI + " es: " + esValidoCifIncorrecto);
    }
}