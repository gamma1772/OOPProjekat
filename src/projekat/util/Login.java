package projekat.util;

import projekat.debug.EnumTipovi;
import projekat.debug.Logger;
import projekat.osoba.Korisnik;
import projekat.osoba.Sifra;

import java.util.ArrayList;
import java.util.Scanner;

public class Login {
	private static final Scanner scannerConsoleInput = new Scanner(System.in);
	private static final Logger logger = new Logger("LOGIN");
	private static String username, password;

	private static ArrayList<Korisnik> korisnici = new ArrayList<>();
	private static ArrayList<Sifra> sifre = new ArrayList<>();

	public static void ulogujKorisnika() {
		int brPokusaja = 0;

		while (brPokusaja < 3) {

			System.out.print("Unesite korisnicko ime: "); username = scannerConsoleInput.nextLine();
			System.out.print("Unesite korisnicko ime: "); password = scannerConsoleInput.nextLine();
			for (Korisnik k : korisnici) {
				String UUID = k.getUUID();
				for (Sifra s : sifre) {

				}
			}
			brPokusaja++;
		}
		if (brPokusaja == 3) {
			System.out.println("Uneli ste pogresne informacije previse puta! Program se zatvara...");
			logger.poruka("Pokusaj prijavljivanja korisnika neuspesan: " + username, EnumTipovi.LOGIN_FAIL);
			System.exit(1);
		}
	}
}
