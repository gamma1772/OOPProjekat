package projekat.osoba;

import projekat.knjiga.Zajam;

public class Clan extends AbstractKorisnik {
	private Zajam zajam;
	public Clan(String ime, String prezime, String jmbg, String brTelefona, int pol, String username, Sifra password, String email, Zajam zajam, Dozvole dozvole) {
		super(ime, prezime, jmbg, brTelefona, pol, username, password, email, dozvole);
		this.setZajam(zajam);
	}

	public Clan(String ime, String prezime, String jmbg, String brTelefona, int pol, String username, Sifra password, String email, Dozvole dozvole) {
		super(ime, prezime, jmbg, brTelefona, pol, username, password, email, dozvole);
		this.zajam = new Zajam();
	}

	public Zajam getZajam() {
		return zajam;
	}

	public void setZajam(Zajam zajam) {
		this.zajam = zajam;
	}
}
