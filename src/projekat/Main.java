package projekat;

import projekat.sistem.PravilaBiblioteke;
import projekat.util.debug.Logger;
import projekat.sistem.Login;
import projekat.sistem.Registracija;

import java.util.Scanner;

/* @author markonrt8519 */
public class Main {

    public static boolean debugMode; //U koliko je ovo true, sve poruke iz logger-a se pokazuju u konzoli;
    private static final Logger LOGGER = new Logger("MAIN");
    public static PravilaBiblioteke pravila;
    public static void main(String[] args) {
        debugMode = args.length > 0 && args[0].equals("--debug");
        System.out.println(LOGGER.debug("Debug mode: " + debugMode));

        pravila = new PravilaBiblioteke();

        Scanner scannerConsoleInput = new Scanner(System.in);
        int opcija = 0, brPokusaja = 0;
        String username = null, password;
        while(opcija != 1 || opcija != 2) {
            System.out.println("Odaberite opciju:\n1. Prijava\n2. Registracija");
            System.out.println("Unos: "); opcija = scannerConsoleInput.nextInt();
            scannerConsoleInput.nextLine();
            switch (opcija) {
                case 1:
                    while (brPokusaja < 3) {
                        brPokusaja++;
                        System.out.println("Unesite korisnicko ime: "); username = scannerConsoleInput.nextLine();
                        System.out.println("Unesite sifru: "); password = scannerConsoleInput.nextLine();
                        if (new Login(username, password).getStatus()) {
                            break;
                        }
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
                Logger.out();
                break;
            }
        }
    }
}
