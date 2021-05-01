package projekat.identifikacija;

import projekat.osoba.Osoba;
import projekat.osoba.Radnik;

public class IdentifijacijaRadnika {
	private String ime;
	private String idRadnika;
	private Osoba radnik;

	public IdentifijacijaRadnika(Osoba radnik) {
		this.ime = radnik.getIme() + " " + radnik.getPrezime();
		this.idRadnika = ((Radnik) radnik).getId();
		this.radnik = radnik;
	}
}
