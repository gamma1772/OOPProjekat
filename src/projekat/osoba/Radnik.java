package projekat.osoba;

import java.util.Date;

public class Radnik extends Osoba implements IRacunanje {
	private double plata; //plata po satu
	private final String id;

	public Radnik(String ime, String prezime, String jmbg, String brTelefona, Date datumRodjenja, int pol, double plata, String id) {
		super(ime, prezime, jmbg, brTelefona, datumRodjenja, pol);
		this.plata = plata;
		this.id = id;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getId() {
		return id;
	}

	@Override
	public double racunajSumu(double plata, int brSati) {
		return plata * brSati;
	}
}
