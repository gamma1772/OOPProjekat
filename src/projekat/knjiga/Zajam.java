package projekat.knjiga;

import projekat.osoba.AbstractKorisnik;

import java.util.Calendar;
import java.util.Date;

public class Zajam {
	private static final double MNOZILAC = 0.05D;
	private double dug = 0.0D;
	private Calendar datumPozajmljivanja, datumVracanja;
	private Knjiga[] pozajmljeneKnjige;
	private AbstractKorisnik korisnik;

	public Zajam(double dug, Knjiga[] pozajmljeneKnjige, AbstractKorisnik korisnik, Calendar datumPozajmljivanja, Calendar datumVracanja) {
		this.setDug(dug);
		this.setPozajmljeneKnjige(Zajam.this.pozajmljeneKnjige);
		this.setKorisnik(korisnik);
		this.setDatumPozajmljivanja(datumPozajmljivanja);
		this.setDatumVracanja(datumVracanja);
	}

	public double getDug() {
		return dug;
	}

	public void setDug(double dug) {
		this.dug = dug;
	}

	public Knjiga[] getPozajmljeneKnjige() {
		return pozajmljeneKnjige;
	}

	public void setPozajmljeneKnjige(Knjiga[] pozajmljeneKnjige) {
		this.pozajmljeneKnjige = pozajmljeneKnjige;
	}

	public AbstractKorisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(AbstractKorisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Calendar getDatumPozajmljivanja() {
		return datumPozajmljivanja;
	}

	public void setDatumPozajmljivanja(Calendar datumPozajmljivanja) {
		this.datumPozajmljivanja = datumPozajmljivanja;
	}

	public Calendar getDatumVracanja() {
		return datumVracanja;
	}

	public void setDatumVracanja(Calendar datumVracanja) {
		this.datumVracanja = datumVracanja;
	}

	public void produziZajam() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 17);
		this.datumVracanja = calendar;
	}

	public void izracunajDug() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (calendar.get(Calendar.DATE) > datumVracanja.get(Calendar.DATE)) {
			while (calendar.get(Calendar.DATE) != datumVracanja.get(Calendar.DATE)) {
				this.dug *= MNOZILAC * pozajmljeneKnjige.length;
			}
		}
		System.out.println("Dug: " + dug);
	}
}
