package projekat.sistem;

import projekat.osoba.Administrator;
import projekat.osoba.Sifra;

import javax.security.auth.login.CredentialException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
	private static final Scanner scannerConsoleInput = new Scanner(System.in);

	public static Administrator login(ArrayList<Administrator> administratori) throws CredentialException {

		String korIme, sifra;

		Administrator a = null;
		System.out.print("Unesite Korisnicko ime: "); korIme = scannerConsoleInput.nextLine();
		System.out.print("Unesite lozinku: "); sifra = scannerConsoleInput.nextLine();

		if (administratori != null) {
			for (Administrator administrator : administratori) {
				if (administrator.getUsername().equals(korIme)) {
					if (Sifra.sifrujLozinku(administrator.getUUID(), sifra).equals(administrator.getPassword().getSifra())) {
						a = administrator;
					}
					else {
						throw new CredentialException("Lozinka neispravna");
					}
				}
			}
		}

		if (a == null) {
			throw new CredentialException("Neispravno korisnicko ime");
		}
		return a;
	}

	public static Administrator logout() {
		return null;
	}
}
