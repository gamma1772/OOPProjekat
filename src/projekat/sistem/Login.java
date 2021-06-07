package projekat.sistem;

import projekat.osoba.Administrator;
import projekat.util.debug.Logger;
import projekat.osoba.Sifra;

import javax.security.auth.login.CredentialException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
	private static final Scanner scannerConsoleInput = new Scanner(System.in);
	private static final Logger LOGGER = new Logger("LOGIN");

	private static ArrayList<Administrator> administratori;

	public static Administrator login() throws CredentialException {
		//int brPokusaja = 0;
		String korIme, sifra;

		Administrator a = null;
		System.out.print("Unesite Korisnicko ime: "); korIme = scannerConsoleInput.nextLine();
		System.out.print("Unesite lozinku: "); sifra = scannerConsoleInput.nextLine();

//		for (int i = 0; i < 50; i++) {
//			System.out.println("\n");
//		}


		for (Administrator administrator : administratori) {
			if (administrator.getUsername().equals(korIme)) {
				if (Sifra.desifrujLozinku(administrator.getPassword().getSifra()).equals(sifra)) {
					a = administrator;
				}
				else {
					throw new CredentialException("Lozinka neispravna");
				}
			}
		}

		if (a == null) {
			throw new CredentialException("Neispravno korisnicko ime");
		}
		return a;
	}
//	private  String username, password;
//	private  AbstractKorisnik loggedKorisnik;
//
//	private boolean status;
//
//	private static ArrayList<AbstractKorisnik> korisnici;
//
//	static {
//		try {
//			korisnici = DataManager.deserializeKorisnici();
//		} catch (IOException exception) {
//			exception.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static ArrayList<Sifra> sifre = new ArrayList<>();
//
//	public Login(String username, String password) {
//		this.setUsername(username);
//		this.setPassword(password);
//		ulogujKorisnika();
//	}
//
//	public void ulogujKorisnika() {
//		int brPokusaja = 0;
//		String UUID = "";
//		AbstractKorisnik privremenKorisnik = null;
//		boolean tacnost = false;
//		while (brPokusaja < 1) {
//			for (AbstractKorisnik k : korisnici) {
//				if (username.equals(k.getUsername())) {
//					UUID = k.getUUID();
//					privremenKorisnik = k;
//					break;
//				}
//				else {
//					System.out.println("Netacno korisnicko ime ili lozinka.");
//					status = false;
//					break;
//				}
//			}
//			for (Sifra s : sifre) {
//				if (s.getKorisnickiUUID().equals(UUID)) {
//					if (Sifra.desifrujLozinku(s.getSifra()).equals(password)) {
//						tacnost = true;
//						break;
//					}
//					else {
//						tacnost = false;
//						break;
//					}
//				}
//			}
//			if (tacnost) {
//				loggedKorisnik = privremenKorisnik;
//				status = true;
//				LOGGER.info("Ulogovan korisnik ciji je UUID " + loggedKorisnik.getUUID());
//			}
//			brPokusaja++;
//		}
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public boolean getStatus() {
//		return status;
//	}

}
