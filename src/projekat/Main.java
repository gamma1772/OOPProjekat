package projekat;

import projekat.knjiga.Autor;
import projekat.knjiga.Izdavac;
import projekat.knjiga.Knjiga;
import projekat.osoba.Administrator;
import projekat.osoba.Clan;
import projekat.osoba.Pozajmljivanje;
import projekat.osoba.Sifra;
import projekat.sistem.PravilaBiblioteke;
import projekat.util.ArgManager;
import projekat.sistem.Login;
import projekat.util.TokProgramaException;
import projekat.util.serijalizacija.DataManager;

import javax.security.auth.login.CredentialException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


/* @author markonrt8519 */
public class Main {

    public static boolean debugMode = false; //U koliko je ovo true, sve poruke iz logger-a se pokazuju u konzoli;

    public static Administrator prijavljenAdmin;
    public static PravilaBiblioteke pravila;

    private static ArrayList<Administrator> admini;
    private static ArrayList<Clan> clanovi;
    private static ArrayList<Sifra> sifre;
    private static ArrayList<Autor> autori;
    private static ArrayList<Izdavac> izdavaci;
    private static ArrayList<Knjiga> knjige;
    private static ArrayList<Pozajmljivanje> pozajmljivanja;
    private static ArrayList<Administrator.Dozvole> dozvole;

    public static void main(String[] args) {

        if (args.length > 0) {
            new ArgManager(args); //Pokretanje provere argumenata, pokrece se jednom pri pokretanju programa.
        }

        int brPokusaja = 0;
        deserialization(true);

        /*Prijavljivanje administratora, tj. korisnika, sistema*/
        while(prijavljenAdmin == null && brPokusaja < 3) {
            try {
                prijavljenAdmin = Login.login(admini);
                break;
            } catch (CredentialException e) {
                System.out.println(e.getMessage());
                prijavljenAdmin = null;
            }
            brPokusaja++;
        }
         init();
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

    private static void deserialization(boolean restricted) {
        if (restricted) {

            try {
                sifre = DataManager.deserializeSifre();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                dozvole = DataManager.deserializeDozvole();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                admini = DataManager.deserializeAdmins(sifre, dozvole);
            } catch (IOException | TokProgramaException exception) {
                exception.printStackTrace();
                if (exception instanceof IOException) {
                    System.out.println("Lista administratora ne postoji. Pokrenite program sa opcijom '--setup'");
                    System.exit(2);
                }
                else {
                    System.exit(3);
                }
            }

        } else {
            try {
                pravila = DataManager.deserializePravila();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            try {
                sifre = DataManager.deserializeSifre();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                dozvole = DataManager.deserializeDozvole();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                admini = DataManager.deserializeAdmins(sifre, dozvole);
            } catch (IOException | TokProgramaException exception) {
                exception.printStackTrace();
                if (exception instanceof TokProgramaException) {
                    System.out.println("Lista administratora ne postoji. Pokrenite program sa opcijom '--setup'");
                    System.exit(2);
                }
            }

            try {
                autori = DataManager.deserializeAutore();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                izdavaci = DataManager.deserializeIzdavace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                knjige = DataManager.deserializeKnjige(autori, izdavaci);
            } catch (IOException | TokProgramaException exception) {
                exception.printStackTrace();
                if (exception instanceof TokProgramaException) {
                    System.exit(3);
                }
            }
            try {
                pozajmljivanja = DataManager.deserializePozajmljivanje(knjige);
            } catch (IOException | TokProgramaException | ParseException exception) {
                exception.printStackTrace();
                if (exception instanceof TokProgramaException) {
                    System.exit(3);
                }
            }
            try {
                clanovi = DataManager.deserializeClanovi(pozajmljivanja);
            } catch (IOException | TokProgramaException exception) {
                exception.printStackTrace();
                if (exception instanceof TokProgramaException) {
                    System.exit(3);
                }
            }
        }
    }

    public static void init() {
        deserialization(false);
    }
}
