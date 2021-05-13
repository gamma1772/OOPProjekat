package projekat.util;

import projekat.osoba.AbstractKorisnik;
import projekat.osoba.AbstractKorisnik.Dozvole;
import projekat.osoba.Clan;
import projekat.osoba.Sifra;
import projekat.util.debug.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Registracija implements IJedinstveniIdentifikator{

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
		String ime = "", prezime = "", korIme = "", sifra = "", jmbg = "", brTelefona = "", email = "";
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

			if (jmbg.equals("")) {
				System.out.println("Unesite JMBG: ");
				jmbg = registerScanner.nextLine();
			}

			if (email.equals("")) {
				System.out.println("Unesite E-Mail: ");
				email = registerScanner.nextLine();
			}

			if (korIme.equals("")) {
				System.out.println("Unesite Korisnicko ime: ");
				korIme = registerScanner.nextLine();
			}

			if (sifra.equals("")) {
				System.out.println("Unesite Sifru: ");
				sifra = registerScanner.nextLine();
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
		} while (!proveraUpisa(ime, prezime, korIme, sifra, jmbg, brTelefona, email) || pol == -1);
		noviClan.setIme(ime);
		noviClan.setPrezime(prezime);
		noviClan.setUsername(korIme);
		noviClan.setPassword(new Sifra(noviClan.getUUID(), sifra));
		noviClan.setJmbg(jmbg);
		noviClan.setBrTelefona(brTelefona);
		noviClan.setEmail(email);
		noviClan.setPol(pol);
		noviClan.setZajam(null); //Pocetna vrednost zajma je null. Kada korisnik prvi put pozajmi knjigu, pravi se novi zajam.
		noviClan.setDozvole(new Dozvole().standardClan()); //Postavlja dozvole korisnika na standardan sablon

		korisnici.add(noviClan);
		sifre.add(noviClan.getPassword());
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

	@Override
	public String generateAdminUUID() {
		return null;
	}

	@Override
	public String generateClanUUID() {
		return null;
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
