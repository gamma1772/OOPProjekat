package projekat.osoba;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Clan extends AbstractOsoba {
	private Pozajmljivanje pozajmljivanje;

	public Clan(String UUID, String ime, String prezime, String adresa, String brTelefona, int pol, String email, Pozajmljivanje pozajmljivanje) {
		super(UUID, ime, prezime, adresa, brTelefona, email, pol);
		this.setPozajmljivanje(pozajmljivanje);
	}

	public Clan(String ime, String prezime, String adresa, String brTelefona, int pol, String email, Pozajmljivanje pozajmljivanje) {
		super(ime, prezime, adresa, brTelefona, email, pol);
		this.UUID = generateUUID();
		this.setPozajmljivanje(pozajmljivanje);
	}

	public Clan(String UUID, String ime, String prezime, String adresa, String brTelefona, int pol, String email) {
		super(UUID, ime, prezime, adresa, brTelefona, email, pol);
		this.UUID = generateUUID();
		this.pozajmljivanje = new Pozajmljivanje();
	}

	public Clan(String ime, String prezime, String adresa, String brTelefona, int pol, String email) {
		super(ime, prezime, adresa, brTelefona, email, pol);
		this.UUID = generateUUID();
		this.pozajmljivanje = new Pozajmljivanje();
	}

	public Clan() {
		this.UUID = generateUUID();
		this.pozajmljivanje = new Pozajmljivanje();
	}

	@Override
	public String getPunoIme() {
		return String.format("%s %s", getIme(), getPrezime());
	}

	@Override
	public String toPrettyString() {
		return String.format("Ime: %s, Prezime: ");
	}

	public Pozajmljivanje getPozajmljivanje() {
		return pozajmljivanje;
	}

	public void setPozajmljivanje(Pozajmljivanje pozajmljivanje) {
		this.pozajmljivanje = pozajmljivanje;
	}

	@Override
	public String generateUUID() {
		SimpleDateFormat format = new SimpleDateFormat("ddMMHHmmss");
		Random random = new Random();
		return String.format("%s-%04d", format.format(new Date()), random.nextInt(9999));
	}
}
