package projekat;

import projekat.debug.Logger;
import projekat.osoba.Korisnik;
import projekat.osoba.Osoba;

import java.util.Scanner;

/* @author markonrt8519 */
public class Main {

    public static boolean debugMode; //U koliko je ovo true, sve poruke iz logger-a se pokazuju u konzoli;
    private static final Logger logger = new Logger("MAIN");
    private static Scanner scannerConsoleInput;

    public static void main(String[] args) {
        if (args[0].equals("--debug")) debugMode = true; else debugMode = false;
        scannerConsoleInput = new Scanner(System.in);
        //System.out.println("Henlo world!");
        //logger.poruka("Ovo je informacija", EnumTipovi.INFO.ordinal());
        //Korisnik test = new Korisnik("Marko", "Dujovic", "dujovicm", "123456789", "0000000000000", "0000000000", Osoba.pol.MUSKO.ordinal());

        //test.proveraDozvoli();
        System.out.println(args.length +" "+ debugMode);
    }
}
