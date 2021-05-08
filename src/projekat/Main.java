package projekat;

import projekat.debug.Logger;

import java.util.Scanner;

/* @author markonrt8519 */
public class Main {

    public static boolean debugMode; //U koliko je ovo true, sve poruke iz logger-a se pokazuju u konzoli;
    private static final Logger logger = new Logger("MAIN");

    public static void main(String[] args) {
        debugMode = args.length > 0 && args[0].equals("--debug");
        System.out.println(logger.debug("Debug mode: " + debugMode));

        Scanner scannerConsoleInput = new Scanner(System.in);
        int brPokusaja = 0;


        while (brPokusaja < 3) {
            String username, password;
            System.out.print("Unesite korisnicko ime: "); username = scannerConsoleInput.nextLine();
            System.out.print("Unesite korisnicko ime: "); password = scannerConsoleInput.nextLine();
            brPokusaja++;
        }
    }
}
