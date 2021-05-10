package projekat.osoba;

public class Administrator extends AbstractKorisnik {
	public Administrator(String ime, String prezime, String jmbg, String brTelefona, int pol, String username, Sifra password, String email, Dozvole dozvole) {
		super(ime, prezime, jmbg, brTelefona, pol, username, password, email, dozvole);
	}
}
