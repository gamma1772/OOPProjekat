package projekat.util;

import projekat.util.debug.EnumTipovi;
import projekat.util.debug.Logger;
import projekat.osoba.AbstractKorisnik;
import projekat.osoba.Sifra;

import java.util.ArrayList;
import java.util.Scanner;

public class Login {
	private static final Scanner scannerConsoleInput = new Scanner(System.in);
	private static final Logger logger = new Logger("LOGIN");
	private  String username, password;
	private  AbstractKorisnik ulogovanKorisnik;

	private static ArrayList<AbstractKorisnik> korisnici = new ArrayList<>();
	private static ArrayList<Sifra> sifre = new ArrayList<>();

	public Login(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
		ulogujKorisnika();
	}

	public void ulogujKorisnika() {
		int brPokusaja = 0;
		String UUID = "";
		AbstractKorisnik privremenKorisnik = null;
		boolean tacnost = false;
		while (brPokusaja < 1) {
			for (AbstractKorisnik k : korisnici) {
				if (username.equals(k.getUsername())) {
					UUID = k.getUUID();
					privremenKorisnik = k;
					break;
				}
				else {
					System.out.println("Netacno korisnicko ime ili lozinka.");
					break;
				}
			}
			for (Sifra s : sifre) {
				if (s.getKorisnickiUUID().equals(UUID)) {
					if (Sifra.desifrujLozinku(s.getSifra()).equals(password)) {
						tacnost = true;
						break;
					}
					else {
						tacnost = false;
						break;
					}
				}
			}
			if (tacnost) {
				ulogovanKorisnik = privremenKorisnik;
				logger.info("Ulogovan korisnik ciji je UUID " + ulogovanKorisnik.getUUID());
			}
			brPokusaja++;
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
