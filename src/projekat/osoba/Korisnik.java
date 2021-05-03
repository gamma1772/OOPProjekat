package projekat.osoba;

import projekat.debug.EnumTipovi;
import projekat.debug.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Korisnik extends Osoba{

	private static final Logger logger = new Logger("KORISNIK");

	public Korisnik( String ime, String prezime, String korisnickoIme, String sifra, String jmbg, String brTelefona, int pol) {
		this.uuid = generisiUUID();
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.sifra = generisiSifruSaUUID(sifra);
		this.jmbg = jmbg;
		this.brTelefona = brTelefona;
		this.pol = pol;
		this.isAdmin = false;

		this.mozeDaSeRegistruje();
		this.mozeDaPozajmiKnjigu();
	}

	@Override
	public String generisiUUID() {
		Date datum = new Date();
		SimpleDateFormat formatDatuma = new SimpleDateFormat("ddMMyyyyhhmmmss");
		Random random = new Random();
		int end = random.nextInt(1772);
		logger.poruka("Generisan UUID: " + formatDatuma.format(datum) + "-" + String.format("%04d", end), EnumTipovi.INFO.ordinal());

		return formatDatuma.format(datum) + "-" + String.format("%05d", end);
	}

	@Override
	public String generisiSifruSaUUID(String sifra) {
		return null;
	}


}
