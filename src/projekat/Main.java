package projekat;

import projekat.osoba.Administrator;
import projekat.sistem.PravilaBiblioteke;
import projekat.util.ArgManager;
import projekat.util.debug.Logger;
import projekat.sistem.Login;

import javax.security.auth.login.CredentialException;


/* @author markonrt8519 */
public class Main {

    public static boolean debugMode = false; //U koliko je ovo true, sve poruke iz logger-a se pokazuju u konzoli;

    private static final Logger LOGGER = new Logger("MAIN");
    private static Administrator prijavljenAdmin;
    public static PravilaBiblioteke pravila;
    public static void main(String[] args) {

        if (args.length > 0) {
            new ArgManager(args); //Pokretanje provere argumenata, pokrece se jednom pri pokretanju programa.

        }

        int brPokusaja = 0;

        /*Prijavljivanje administratora, tj. korisnika, sistema*/
        while(prijavljenAdmin == null && brPokusaja < 3) {
            try {
                prijavljenAdmin = Login.login();
            } catch (CredentialException e) {
                e.printStackTrace();
            }
            brPokusaja++;
        }


//        Scanner scannerConsoleInput = new Scanner(System.in);
//        int opcija = 0, brPokusaja = 0;
//        String username = null, password;
//        while(opcija != 1 || opcija != 2) {
//            System.out.println("Odaberite opciju:\n1. Prijava\n2. Registracija");
//            System.out.println("Unos: "); opcija = scannerConsoleInput.nextInt();
//            scannerConsoleInput.nextLine();
//            switch (opcija) {
//                case 1:
//                    while (brPokusaja < 3) {
//                        brPokusaja++;
//                        System.out.println("Unesite korisnicko ime: "); username = scannerConsoleInput.nextLine();
//                        System.out.println("Unesite sifru: "); password = scannerConsoleInput.nextLine();
//                        if (new Login(username, password).getStatus()) {
//                            break;
//                        }
//                    }
//                    if (brPokusaja == 3) {
//                        LOGGER.error("Previse pokusaja prijavljivanja. Username: " + username);
//                        LOGGER.info("Program se zatvara");
//                        Logger.out();
//                        System.exit(1);
//                    }
//                    break;
//                case 2:
//                    Registracija.registrujClana();
//                    break;
//                default:
//                    break;
//            }
//            if (opcija != 0) {
//                Logger.out();
//                break;
//            }
//        }


    }
}
