package projekat;

import projekat.util.debug.Logger;
import projekat.util.Login;
import projekat.util.Registracija;

import java.util.Scanner;

/* @author markonrt8519 */
public class Main {

    public static boolean debugMode; //U koliko je ovo true, sve poruke iz logger-a se pokazuju u konzoli;
    private static final Logger LOGGER = new Logger("MAIN");

    public static void main(String[] args) {
        debugMode = args.length > 0 && args[0].equals("--debug");
        System.out.println(LOGGER.debug("Debug mode: " + debugMode));

        Scanner scannerConsoleInput = new Scanner(System.in);
        int opcija = 0, brPokusaja = 0;
        String username = "", password = "";
        while(opcija != 1 || opcija != 2) {
            System.out.println("Odaberite opciju:\n1. Prijava\n2. Registracija");
            System.out.println("Unos: "); opcija = scannerConsoleInput.nextInt();
            scannerConsoleInput.nextLine();
            switch (opcija) {
                case 1:
                    while (brPokusaja < 3) {
                        System.out.println("Unesite korisnicko ime: "); username = scannerConsoleInput.nextLine();
                        System.out.println("Unesite sifru: "); password = scannerConsoleInput.nextLine();
                        new Login(username, password);
                        brPokusaja++;
                    }
                    if (brPokusaja == 3) {
                        LOGGER.error("Previse pokusaja prijavljivanja. Username: " + username);
                        LOGGER.info("Program se zatvara");
                        Logger.out();
                        System.exit(1);
                    }
                    break;
                case 2:
                    Registracija.registrujClana();
                    break;
                default:
                    break;
            }
            if (opcija != 0) {
                break;
            }
        }
    }
}
