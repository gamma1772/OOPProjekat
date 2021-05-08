package projekat.osoba;

public class Clan extends Korisnik{
	public Clan(String ime, String prezime, String jmbg, String brTelefona, int pol, String username, String password, String email) {
		super(ime, prezime, jmbg, brTelefona, pol, username, password, email, false);
		clanDozvole();
	}

	private void clanDozvole() {
		this.registracija = true;
		this.pozajmljivanjeKnjiga = true;
	}
}
