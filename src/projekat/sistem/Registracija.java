package projekat.sistem;

import projekat.osoba.AbstractKorisnik;
import projekat.osoba.Clan;
import projekat.osoba.Sifra;
import projekat.util.fajl.DataManager;
import projekat.util.debug.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Registracija {

	private static final Logger LOGGER = new Logger("REGISTRACIJA");
	//private static boolean kompletnaRegistracija = false;
	private static final Scanner registerScanner = new Scanner(System.in);

	private static final ArrayList<AbstractKorisnik> korisnici = new ArrayList<>();
	private static final ArrayList<Sifra> sifre = new ArrayList<>();

	static {
	}

	/**Sluzi za registrovanje clanova biblioteke. Sadrzi privremene promenljive koje se na kraju koriste za dodavanje novog clana.*/
	public static void registrujClana() {
		Clan noviClan = new Clan();
		String ime = "", prezime = "", korIme = "", adresa = "", brTelefona = "", email = "";
		int pol = -1;
		//kompletnaRegistracija = true;
		do {
			if (ime.equals("")) {
				System.out.println("Unesite Ime: ");
				ime = registerScanner.nextLine();
			}

			if (prezime.equals("")) {
				System.out.println("Unesite Prezime: ");
				prezime = registerScanner.nextLine();
			}

			if (brTelefona.equals("")) {
				System.out.println("Unesite Broj telefona: ");
				brTelefona = registerScanner.nextLine();
			}

			if (adresa.equals("")) {
				System.out.println("Unesite Adresu: ");
				adresa = registerScanner.nextLine();
			}

			if (email.equals("")) {
				System.out.println("Unesite E-Mail: ");
				email = registerScanner.nextLine();
			}

			if (korIme.equals("")) {
				System.out.println("Unesite Korisnicko ime: ");
				korIme = registerScanner.nextLine();
			}

			if (pol == -1) {
				while (pol != 0 && pol != 1) {
					System.out.println("Odaberite pol: ");
					System.out.println("1. Musko\t2. Zensko");
					System.out.println("Izbor: ");
					pol = registerScanner.nextInt();
					if (pol == 1) pol = 0;
					if (pol == 2) pol = 1;
				}
			}
		} while (!proveraUpisa(ime, prezime, korIme, adresa, brTelefona, email) || pol == -1);

		try {
			DataManager.serializeKorisnike(korisnici);
			DataManager.serializeSifre(sifre);
		}
		catch (IOException exception) {
			exception.printStackTrace();
			LOGGER.error(String.format("Greska u serijalizaciji lista\n%s", exception.getMessage()));
		}
	}

	public static void registrujAdmina() {

	}

	/**Preliminarna provera da li su informacije pravilno unesene u formu. Prihvata proizvoljan broj String argumenata,
	 * i ako svaki argument zadovoljava uslov, vraca true.
	 * @param args Niz stringova ciji sadrzaj mora da se proveri
	 * @throws IllegalArgumentException ako je funkcija pozvana bez argumenata
	 * @return true, ako je za svaki String iz niza zadovoljen uslov.*/
	private static boolean proveraUpisa(String... args) throws IllegalArgumentException {
		if (args == null || args.length == 0) {
			throw new IllegalArgumentException("Funkcija mora da ima bar jedan argument.");
		}

		boolean pravilnoUpisano = false;

		for (String arg : args) {
			if (arg == null) {
				throw new IllegalArgumentException("Jedan od clanova niza je null.");
			}
			if (!arg.equals("") && !arg.equals("NULL")) {
				pravilnoUpisano = true;
			}
			else {
				pravilnoUpisano = false;
				break;
			}
		}
		return pravilnoUpisano;
	}
}
