package projekat.osoba;

public class Administrator extends Korisnik{
	public Administrator(String ime, String prezime, String jmbg, String brTelefona, int pol, String username, String password, String email) {
		super(ime, prezime, jmbg, brTelefona, pol, username, password, email, true);
		adminDozvole();
	}

	private void adminDozvole() {
		this.dodavanjeAdmina = true;
		this.brisanjeAdmina = true;
		this.dodavanjeKnjiga = true;
		this.brisanjeKnjiga = true;
		this.dodavanjeKorisnika = true;
		this.brisanjeKorisnika = true;
	}
}
