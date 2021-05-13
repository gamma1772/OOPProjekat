package projekat.knjiga;

import projekat.osoba.AbstractKorisnik;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Zajam {
	private static final double MNOZILAC = 0.05D;
	private double dug = 0.0D;
	private Calendar datumPozajmljivanja, datumVracanja;
	private ArrayList<Knjiga> pozajmljeneKnjige; //Korisnik moze da pozajmi jednu ili vise knjiga

	public Zajam(double dug, ArrayList<Knjiga> pozajmljeneKnjige, Calendar datumPozajmljivanja, Calendar datumVracanja) {
		this.setDug(dug);
		this.setPozajmljeneKnjige(pozajmljeneKnjige);
		this.setDatumPozajmljivanja(datumPozajmljivanja);
		this.setDatumVracanja(datumVracanja);
	}

	public Zajam() {
		this.setDug(0.0);
		this.datumPozajmljivanja = Calendar.getInstance();
		this.datumVracanja = Calendar.getInstance();
		this.pozajmljeneKnjige = new ArrayList<>();
	}

	public double getDug() {
		return dug;
	}

	public void setDug(double dug) {
		this.dug = dug;
	}

	public ArrayList<Knjiga> getPozajmljeneKnjige() {
		return pozajmljeneKnjige;
	}

	public void setPozajmljeneKnjige(ArrayList<Knjiga> pozajmljeneKnjige) {
		this.pozajmljeneKnjige = pozajmljeneKnjige;
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
				this.dug = Math.abs(dug * MNOZILAC * pozajmljeneKnjige.size());
			}
		}
		System.out.println("Dug: " + dug);
	}
}
