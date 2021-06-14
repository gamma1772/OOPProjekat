package projekat.osoba;

import projekat.Main;
import projekat.knjiga.Knjiga;
import projekat.sistem.PravilaBiblioteke;
import projekat.util.serijalizacija.DataManager;
import projekat.util.serijalizacija.ISerijalizacija;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Pozajmljivanje implements ISerijalizacija {
	private static final double MNOZILAC = Main.pravila.getMultiplier();
	private String clanUUID;
	private double dug = 0.0D;
	private Calendar datumPozajmljivanja, datumVracanja;
	private Knjiga pozajmljenaKnjiga;
	private boolean razreseno;
	private int produzenoPuta;


	public Pozajmljivanje(String UUID, double dug, Knjiga pozajmljenaKnjiga, Calendar datumPozajmljivanja, Calendar datumVracanja, int produzenoPuta, boolean razreseno) {
		this.setClanUUID(UUID);
		this.setDug(dug);
		this.setPozajmljenaKnjiga(pozajmljenaKnjiga);
		this.setDatumPozajmljivanja(datumPozajmljivanja);
		this.setDatumVracanja(datumVracanja);
		this.setRazreseno(razreseno);
		this.setProduzenoPuta(produzenoPuta);
	}

	public Pozajmljivanje() {
		this.setClanUUID("");
		this.setDug(0);
		this.setDatumPozajmljivanja(null);
		this.setDatumVracanja(null);
		this.setPozajmljenaKnjiga(new Knjiga());
		this.setRazreseno(false);
		this.setProduzenoPuta(0);
	}

	public int getProduzenoPuta() {
		return produzenoPuta;
	}

	public void setProduzenoPuta(int produzenoPuta) {
		this.produzenoPuta = produzenoPuta;
	}

	public boolean isRazreseno() { return razreseno; }

	public void setRazreseno(boolean razreseno) { this.razreseno = razreseno; }

	public String getClanUUID() { return clanUUID; }

	public void setClanUUID(String clanUUID) { this.clanUUID = clanUUID; }

	public double getDug() {
		return dug;
	}

	public void setDug(double dug) {
		this.dug = dug;
	}

	public Knjiga getPozajmljenaKnjiga() {
		return pozajmljenaKnjiga;
	}

	public void setPozajmljenaKnjiga(Knjiga pozajmljeneKnjige) {
		this.pozajmljenaKnjiga = pozajmljeneKnjige;
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

		this.datumVracanja.add(Calendar.DAY_OF_MONTH, Main.pravila.getMaxPeriod());
		this.produzenoPuta += 1;
	}

	public void izracunajDug() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (calendar.get(Calendar.DAY_OF_YEAR) > datumVracanja.get(Calendar.DAY_OF_YEAR)) {
			this.dug = 1;
			for (int i = 0; i < Math.abs(ChronoUnit.DAYS.between(calendar.toInstant(), datumVracanja.toInstant())); i++) {
				this.dug *= MNOZILAC + 1.125;
			}
			//this.dug = Math.abs(ChronoUnit.DAYS.between(calendar.toInstant(), datumVracanja.toInstant()) * MNOZILAC); //Koristi se ChronoUnit klasa da se odredi raznak izmedju danasnjeg dana i datuma vracanja
		}
		//System.out.println("Dug: " + dug);

	}

	public void produziKnjigu() {
		if (this.getProduzenoPuta() < Main.pravila.getMaxReloan()) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, Main.pravila.getMaxPeriod());
			setDatumVracanja(calendar);
			setProduzenoPuta(+1);
		}
		else {
			System.out.println("Produzeno previse puta! Neuspesna operacija.");
		}
	}

	public void vratiKnjigu() {
		Calendar calendar = Calendar.getInstance();
		calendar.get(Calendar.DATE);
		setDatumVracanja(calendar);
		setDug(0);
		setRazreseno(true);
	}

	public String toStringSerializable() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return String.format("%s~%.2f~%s~%s~%s~%b~%d", getClanUUID(), getDug(), getPozajmljenaKnjiga().getId(), dateFormat.format(getDatumPozajmljivanja().getTime()), dateFormat.format(getDatumVracanja().getTime()), isRazreseno(), getProduzenoPuta());
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return String.format("%-19s %-21s %-21s %-14s %-7.2f %b",
				getClanUUID(), getPozajmljenaKnjiga().getImeKnjige(), dateFormat.format(getDatumPozajmljivanja().getTime()), dateFormat.format(getDatumVracanja().getTime()), getDug(), isRazreseno());
	}

	@Override
	public String serializedFileName() {
		return "pozajmljivanje.tdb";
	}

	@Override
	public void serialize() {
		try {
			DataManager.serializeString(toStringSerializable(), serializedFileName(), true);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
