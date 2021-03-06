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

    public static Administrator prijavljenAdmin;
    public static PravilaBiblioteke pravila;

    private static ArrayList<Administrator> admini = new ArrayList<>();
    private static ArrayList<Clan> clanovi = new ArrayList<>();
    private static ArrayList<Sifra> sifre = new ArrayList<>();
    private static ArrayList<Autor> autori = new ArrayList<>();
    private static ArrayList<Izdavac> izdavaci = new ArrayList<>();
    private static ArrayList<Knjiga> knjige = new ArrayList<>();
    private static ArrayList<Pozajmljivanje> pozajmljivanja = new ArrayList<>();
    private static ArrayList<Administrator.Dozvole> dozvole = new ArrayList<>();

    public static void main(String[] args) {

        try {
                deserialization(true);
            } catch (IOException | TokProgramaException exception) {
                exception.printStackTrace();
            }
        
        if (args.length > 0) {
            new ArgManager(args); //Pokretanje provere argumenata, pokrece se jednom pri pokretanju programa.
        }

        if (prijavljenAdmin == null) {

            int brPokusaja = 0;
            

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
    }

    private static void deserialization(boolean restricted) throws IOException, TokProgramaException {
        if (restricted) {
            sifre = DataManager.deserializeSifre();
            dozvole = DataManager.deserializeDozvole();

            if (sifre.size() > 0 && dozvole.size() > 0)
            admini = DataManager.deserializeAdmins(sifre, dozvole);
        } else {
            pravila = DataManager.deserializePravila();
            sifre = DataManager.deserializeSifre();
            dozvole = DataManager.deserializeDozvole();

            if (sifre.size() > 0 && dozvole.size() > 0)
            admini = DataManager.deserializeAdmins(sifre, dozvole);

            autori = DataManager.deserializeAutore();
            izdavaci = DataManager.deserializeIzdavace();

            if (autori.size() > 0 && izdavaci.size() > 0)
            knjige = DataManager.deserializeKnjige(autori, izdavaci);

            if (knjige.size() > 0) {
                try {
                    pozajmljivanja = DataManager.deserializePozajmljivanje(knjige);
                } catch (IOException | ParseException | TokProgramaException exception) {
                    exception.printStackTrace();
                }
            }

            clanovi = DataManager.deserializeClanovi(pozajmljivanja);
        }
    }

    public static void init() {
        Scanner scanner = new Scanner(System.in);
        try {
            deserialization(false);
        } catch (IOException | TokProgramaException exception) {
            exception.printStackTrace();
        }

        pocetniMeni(scanner);
    }

    private static void pocetniMeni(Scanner scanner) {
        boolean petlja = true;
        int odabir;

        while (petlja) {
            cls();
            System.out.println("==========POCETNI MENI==========");
            System.out.println("Odaberite opciju:\n");
            System.out.println("1. Dodaj novog clana biblioteke\n2. Pozajmljivanje knjiga\n3. Opcije biblioteke\n4. Izlaz\n");
            try {
                System.out.print("Unos: "); odabir = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                continue;
            }
            switch (odabir) {
                case 1:
                    SistemManager.initMemberManager(odabir, clanovi, pozajmljivanja);
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
                    exit();
                    break;
                default:
                    System.out.println("Unesite jednu od postojecih opcija: [1, 2, 3, 4]");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private static void prikaziOpcijeBiblioteke(Scanner scanner) {
        boolean petlja = true;
        String odabir;

        while (petlja) {
            cls();
            System.out.println("==========OPCIJE BIBLIOTEKE==========");
            System.out.println("1. Opcije administratora\n2. Opcije Knjiga\n3. Opcije autora\n4. Opcije izdavaca\n5. Opcije clanova\n6. Pocetni meni\n");
            System.out.print("Unos: "); odabir = scanner.nextLine();
            switch (odabir) {
                case "1":
                    petlja = false;
                    prikaziOpcijeAdministratora(scanner);
                    break;
                case "2":
                    prikaziOpcijeKnjiga(scanner);
                    break;
                case "3":
                    prikaziOpcijeAutora(scanner);
                    break;
                case "4":
                    prikaziOpcijeIzdavaca(scanner);
                    break;
                case "5":
                    prikaziOpcijeClanova(scanner);
                    break;
                case "6":
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

    private static void prikaziOpcijeAdministratora(Scanner scanner) {
        boolean petlja = true;
        String odabir;

        while (petlja) {
            cls();
            System.out.println("==========OPCIJE ADMINISTRATORA==========");
            System.out.println("1. Dodaj administratora\n2. Izmeni podatke administratora\n3. Obrisi administratora\n4. Promeni pravila biblioteke\n5. Nazad\n6. Pocetni meni");
            System.out.print("Unos: "); odabir = scanner.nextLine();
            switch (odabir) {
                case "1": case "2": case "3":
                    try {
                        SistemManager.initAdminManager(prijavljenAdmin, Integer.parseInt(odabir), admini, dozvole, sifre);
                    } catch (NumberFormatException exception) {
                        continue;
                    }

                    break;
                case "4":
                    SistemManager.initPravilaManager(pravila);
                    break;
                case "5":
                    petlja = false;
                    prikaziOpcijeBiblioteke(scanner);
                    break;
                case "6":
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

    private static void prikaziOpcijeKnjiga(Scanner scanner) {
        boolean petlja = true;
        int odabir;

        while (petlja) {
            cls();
            System.out.println("==========KNJIGE==========");
            System.out.println("1. Dodaj novu knjigu\n2. Izmeni knjigu\n3. Obrisi knjigu\n4. Nazad\n5. Pocetni meni");
            try {
                System.out.print("Unos: "); odabir = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                continue;
            }
            switch (odabir) {
                case 1: case 2: case 3:
                    SistemManager.initBookManager(odabir, knjige, autori, izdavaci, pozajmljivanja);
                    break;
                case 4:
                    petlja = false;
                    prikaziOpcijeBiblioteke(scanner);
                    break;
                case 5:
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

    private static void prikaziOpcijeAutora(Scanner scanner) {
        boolean petlja = true;
        int odabir;

        while (petlja) {
            cls();
            System.out.println("==========AUTORI==========");
            System.out.println("1. Dodaj novog autora\n2. Izmeni autora\n3. Obrisi autora\n4. Nazad\n5. Pocetni meni");
            try {
                System.out.print("Unos: "); odabir = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                continue;
            }

            switch (odabir) {
                case 1: case 2: case 3:
                    SistemManager.initAuthorManager(odabir, autori, knjige);
                    break;
                case 4:
                    petlja = false;
                    prikaziOpcijeBiblioteke(scanner);
                    break;
                case 5:
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

    private static void prikaziOpcijeIzdavaca(Scanner scanner) {
        boolean petlja = true;
        int odabir;

        while (petlja) {
            cls();
            System.out.println("==========IZDAVACI==========");
            System.out.println("1. Dodaj novog izdavaca\n2. Izmeni izdavaca\n3. Obrisi izdavaca\n4. Nazad\n5. Pocetni meni");
            try {
                System.out.print("Unos: "); odabir = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                continue;
            }
            switch (odabir) {
                case 1: case 2: case 3:
                    SistemManager.initPublisherManager(odabir, izdavaci, knjige);
                    break;
                case 4:
                    petlja = false;
                    prikaziOpcijeBiblioteke(scanner);
                    break;
                case 5:
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

    private static void prikaziOpcijeClanova(Scanner scanner) {
        boolean petlja = true;
        int odabir;

        while (petlja) {
            cls();
            System.out.println("==========CLANOVI==========");
            System.out.println("1. Dodaj novog clana\n2. Izmeni clana\n3. Obrisi clana\n4. Nazad\n5. Pocetni meni");
            try {
                System.out.print("Unos: "); odabir = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                continue;
            }
            switch (odabir) {
                case 1: case 2: case 3:
                    SistemManager.initMemberManager(odabir, clanovi, pozajmljivanja);
                    break;
                case 4:
                    petlja = false;
                    prikaziOpcijeBiblioteke(scanner);
                    break;
                case 5:
                    petlja = false;
                    pocetniMeni(scanner);
                    break;
                default: {
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

    public static void cls() {
        for (int i = 0; i < 50; i++) {
            System.out.println('\n');
        }
    }
    public static void save() {
        DataManager.serializeFromGenericArray(admini);
        DataManager.serializeFromGenericArray(sifre);
        DataManager.serializeFromGenericArray(dozvole);
        DataManager.serializeFromGenericArray(knjige);
        DataManager.serializeFromGenericArray(autori);
        DataManager.serializeFromGenericArray(izdavaci);
        DataManager.serializeFromGenericArray(clanovi);
        DataManager.serializeFromGenericArray(pozajmljivanja);
        DataManager.serializeGeneric(pravila);
    }

    public static void save(int check) {
        switch (check) {
            case 0:
                DataManager.serializeFromGenericArray(admini);
                break;
            case 1:
                DataManager.serializeFromGenericArray(sifre);
                break;
            case 2:
                DataManager.serializeFromGenericArray(dozvole);
                break;
            case 3:
                DataManager.serializeFromGenericArray(knjige);
                break;
            case 4:
                DataManager.serializeFromGenericArray(autori);
                break;
            case 5:
                DataManager.serializeFromGenericArray(izdavaci);
                break;
            case 6:
                DataManager.serializeFromGenericArray(clanovi);
                break;
            case 7:
                DataManager.serializeFromGenericArray(pozajmljivanja);
                break;
            case 8:
                DataManager.serializeGeneric(pravila);
                break;
            default: {
                System.out.println("Nepostojeca opcija");
            }
        }
    }

    public static void exit() {

        save();

        admini = null;
        sifre = null;
        dozvole = null;
        knjige = null;
        autori = null;
        izdavaci = null;
        clanovi = null;
        pozajmljivanja = null;
        pravila = null;

        System.exit(0);
    }
}
