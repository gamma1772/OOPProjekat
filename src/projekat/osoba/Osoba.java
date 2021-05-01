package projekat.osoba;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Osoba {
	private String ime, prezime, jmbg, brTelefona;
	private final int godine;
	private int pol;
	private Date datumRodjenja;
	private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	public Osoba(String ime, String prezime, String jmbg, String brTelefona, Date datumRodjenja, int pol) {
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.brTelefona = brTelefona;
		this.godine = izracunajGodine();
		this.datumRodjenja = datumRodjenja;
		this.pol = pol;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getBrTelefona() {
		return brTelefona;
	}

	public void setBrTelefona(String brTelefona) {
		this.brTelefona = brTelefona;
	}

	public int getGodine() {
		return godine;
	}

//	public void setGodine(int godine) {
//		this.godine = godine;
//	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	/** Racuna broj godina osobe **/
	private int izracunajGodine() {
		int tempGodine = 0;
		return tempGodine;
	}

	//Pretvara pol u string
	private String convertPol() {
		if (this.pol == 0) {
			return "Musko";
		}
		else {
			return "Zensko";
		}
	}

	@Override
	public String toString() {
		return "Ime: " + ime + " " + prezime + ", JMBG: " + jmbg  + ", Broj telefona: " + brTelefona + ", Godine: " + godine + ", Datum rodjenja: " + format.format(datumRodjenja) + ", Pol: " + convertPol();
	}
}
