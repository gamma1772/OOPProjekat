package projekat.osoba;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Administrator extends AbstractKorisnik {

	public Administrator(String UUID, String ime, String prezime, String adresa, String brTelefona, String email, int pol, String username, Sifra password, Dozvole dozvole) {
		super(UUID, ime, prezime, adresa, brTelefona, email, pol, username, password, dozvole);
	}

	public Administrator(String ime, String prezime, String adresa, String brTelefona, String email, int pol, String username, Sifra password, Dozvole dozvole) {
		super(ime, prezime, adresa, brTelefona, email, pol, username, password, dozvole);
		this.UUID = generateUUID();
	}

	public Administrator() {

	}

	@Override
	public String getPunoIme() {
		return String.format("%s %s", getIme(), getPrezime());
	}

	@Override
	public String toPrettyString() {
		return String.format("Ime: %s, E-mail: %s, Broj telefona: %s, Adresa: %s, Pol: %s, Korisnicko ime: %s, UUID: %s",
				getPunoIme(), getEmail(), getBrTelefona(), getAdresa(), super.pretvoriPolUString(getPol()), getUsername(), getUUID());
	}

	@Override
	public String generateUUID() {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		Random random = new Random();
		return String.format("%s-%05d", format.format(new Date()), random.nextInt(99999));
	}
}
