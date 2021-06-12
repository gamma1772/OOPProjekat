package projekat;

import projekat.knjiga.Autor;
import projekat.knjiga.Izdavac;
import projekat.knjiga.Knjiga;
import projekat.osoba.Administrator;
import projekat.osoba.Clan;
import projekat.osoba.Pozajmljivanje;
import projekat.osoba.Sifra;
import projekat.sistem.SistemManager;
import projekat.sistem.PravilaBiblioteke;
import projekat.util.ArgManager;
import projekat.sistem.Login;
import projekat.util.TokProgramaException;
import projekat.util.serijalizacija.DataManager;

import javax.security.auth.login.CredentialException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


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
    }

    private static void deserialization(boolean restricted) {
        if (restricted) {

            try {
                if (sifre == null)
                sifre = DataManager.deserializeSifre();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                if (dozvole == null)
                dozvole = DataManager.deserializeDozvole();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                if (admini == null)
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
                if (pravila == null)
                pravila = DataManager.deserializePravila();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            try {
                if (sifre == null)
                sifre = DataManager.deserializeSifre();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                if (dozvole == null)
                dozvole = DataManager.deserializeDozvole();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                if (admini == null)
                admini = DataManager.deserializeAdmins(sifre, dozvole);
            } catch (IOException | TokProgramaException exception) {
                exception.printStackTrace();
                if (exception instanceof TokProgramaException) {
                    System.out.println("Lista administratora ne postoji. Pokrenite program sa opcijom '--setup'");
                    System.exit(2);
                }
            }

            try {
                if (autori == null)
                autori = DataManager.deserializeAutore();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                if (izdavaci == null)
                izdavaci = DataManager.deserializeIzdavace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                if (knjige == null) {
                    if (!(autori.size() == 0) || !(izdavaci.size() == 0)) {
                        knjige = DataManager.deserializeKnjige(autori, izdavaci);
                    }
                }

            } catch (IOException | TokProgramaException exception) {
                exception.printStackTrace();
                if (exception instanceof TokProgramaException) {
                    System.exit(3);
                }
            }
            try {
                if (pozajmljivanja == null) {
                    if (!(knjige == null)) {
                        pozajmljivanja = DataManager.deserializePozajmljivanje(knjige);
                    }
                }

            } catch (IOException | TokProgramaException | ParseException exception) {
                exception.printStackTrace();
                if (exception instanceof TokProgramaException) {
                    System.exit(3);
                }
            }
            try {
                if (clanovi == null) {
                    if (!(pozajmljivanja == null)) {
                        clanovi = DataManager.deserializeClanovi(pozajmljivanja);
                    }
                }

            } catch (IOException | TokProgramaException exception) {
                exception.printStackTrace();
                if (exception instanceof TokProgramaException) {
                    System.exit(3);
                }
            }
        }
    }

    public static void init() {
        Scanner scanner = new Scanner(System.in);
        deserialization(false);

        pocetniMeni(scanner);

//        while (petlja) {
//            System.out.println("Unos: "); odabir = scanner.nextInt();
//            scanner.nextLine();
//            switch (odabir) {
//                case 1:
//                    break;
//                case 2:
//                    if (prijavljenAdmin.getDozvole().canLoanBooks()) {
//                        PozajmljivanjeManager.init();
//                    }
//                    else {
//                        System.out.println("Nemate dozvolu da pozajmljujete knjige.");
//                    }
//                    break;
//                case 3:
//                    prikaziOpcijeBiblioteke();
//                    odabir = scanner.nextInt();
//                    //TODO: Loop
//                    switch (odabir) {
//                        case 1:
//                            prikaziOpcijeAdministratora();
//                            break;
//                        case 2:
//                        case 3:
//                        case 4:
//                        case 5:
//                            prikaziOpcijeBiblioteke();
//                            break;
//                        default:
//
//                    }
//                    break;
//                case 4:
//                    //TODO: logout
//                    cls();
//                    System.out.println("Odjavljivanje...");
//                    prijavljenAdmin = Login.logout();
//                    try {
//                        prijavljenAdmin = Login.login(admini);
//                    } catch (CredentialException e) {
//                        e.printStackTrace();
//                    }
//                    init();
//                    petlja = false;
//                    break;
//                case 5:
//                    System.exit(0);
//                default:
//                    System.out.println("Niste uneli pravilnu opciju. Ponudjene opcije: [1, 2, 3, 4, 5]");
//                    break;
//            }
//        }
    }

    public static void cls() {
        for (int i = 0; i < 50; i++) {
            System.out.println('\n');
        }
    }

    public static void pocetniMeni(Scanner scanner) {
        boolean petlja = true;
        int odabir;



        while (petlja) {
            cls();
            System.out.println("==========POCETNI MENI==========");
            System.out.println("Odaberite opciju:\n");
            System.out.println("1. Dodaj novog clana biblioteke\n2. Pozajmi knjigu\n3. Opcije biblioteke\n4. Odjava\n5. Izlaz\n");
            System.out.print("Unos: "); odabir = scanner.nextInt();
            switch (odabir) {
                case 1:
                    break;
                case 2:
                    SistemManager.initPozajmljivanje(clanovi, pozajmljivanja, knjige);
                    break;
                case 3:
                    petlja = false;
                    prikaziOpcijeBiblioteke(scanner);
                    break;
                case 4:
                    petlja = false;
                    prijavljenAdmin = Login.logout();
                    //...
                    break;
                case 5:
                    petlja = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unesite jednu od postojecih opcija: [1, 2, 3, 4, 5]");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public static void prikaziOpcijeBiblioteke( Scanner scanner) {
        boolean petlja = true;
        int odabir;



        while (petlja) {
            cls();
            System.out.println("==========OPCIJE BIBLIOTEKE==========");
            System.out.println("1. Opcije administratora\n2. Dodaj novu knjigu\n3. Dodaj novog autora\n4. Dodaj novog izdavaca\n5. Pocetni meni\n");
            System.out.print("Unos: "); odabir = scanner.nextInt();
            switch (odabir) {
                case 1:
                    petlja = false;
                    prikaziOpcijeAdministratora(scanner);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    petlja = false;
                    pocetniMeni(scanner);
                    break;
                default:
                    System.out.println("Unesite jednu od postojecih opcija: [1, 2, 3, 4, 5]");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public static void prikaziOpcijeAdministratora(Scanner scanner) {
        boolean petlja = true;
        int odabir;

        while (petlja) {
            cls();
            System.out.println("==========OPCIJE ADMINISTRATORA==========");
            System.out.println("1. Dodaj administratora\n2. Izmeni podatke administratora\n3. Obrisi administratora\n4. Promeni pravila biblioteke\n5. Nazad\n6. Pocetni meni");
            System.out.print("Unos: "); odabir = scanner.nextInt();
            switch (odabir) {
                case 1 | 2 | 3: // nzm sto ovo radi ali ok
                    SistemManager.initAdminManager(prijavljenAdmin, odabir, admini);
                    break;
                case 4:
                    SistemManager.initPravilaManager(pravila);
                    break;
                case 5:
                    petlja = false;
                    prikaziOpcijeBiblioteke(scanner);
                    break;
                case 6:
                    petlja = false;
                    pocetniMeni(scanner);
                    break;
                default:
                    System.out.println("Unesite jednu od postojecih opcija: [1, 2, 3, 4, 5, 6]");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
