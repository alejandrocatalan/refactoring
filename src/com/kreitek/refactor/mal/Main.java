package com.kreitek.refactor.mal;

class  Main
{
    public static void main(String args[])
    {
        System.out.println("=====================");
        System.out.println("Vamos a refactorizar!");
        System.out.println("=====================");

        DNI dniCorrecto = new DNI(TIPODNI.DNI, "11111111H", null);
        Boolean esValido = (dniCorrecto.validarDNI() == 1);
        System.out.println( "DNI " + dniCorrecto.numDNI + " es: " + esValido);

        DNI dniIncorrecto = new DNI(TIPODNI.DNI, "24324356A", null);
        Boolean esValido01 = (dniIncorrecto.validarDNI() == 1);
        System.out.println( "DNI " + dniIncorrecto.numDNI + " es: " + esValido01);

        DNI nieCorrecto = new DNI(TIPODNI.NIE, "X0932707B", null);
        Boolean esValidoNie = (nieCorrecto.validarDNI() == 1);
        System.out.println( "NIE " + nieCorrecto.numDNI + " es: " + esValidoNie);

        DNI nieIncorrecto = new DNI(TIPODNI.NIE, "Z2691139Z", null);
        Boolean esValidoNieIncorrecto = (nieIncorrecto.validarDNI() == 1);
        System.out.println( "NIE " + nieIncorrecto.numDNI + " es: " + esValidoNieIncorrecto);

        DNI cifCorrecto = new DNI(TIPODNI.CIF, "W9696294I", null);
        Boolean esValidoCIF = (cifCorrecto.validarDNI() == 1);
        System.out.println( "CIF " + cifCorrecto.numDNI + " es: " + esValidoCIF);

        DNI cifIncorrecto = new DNI(TIPODNI.CIF, "W9696294A", null);
        Boolean esValidoCifIncorrecto = (cifIncorrecto.validarDNI() == 1);
        System.out.println( "NIE " + cifIncorrecto.numDNI + " es: " + esValidoCifIncorrecto);
    }
}