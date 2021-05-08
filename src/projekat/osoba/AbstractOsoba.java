package projekat.osoba;

public abstract class AbstractOsoba implements IMogucnost{

	//Dozvole
	private boolean dodavanjeAdmina = false, dodavanjeKorisnika = false, dodavanjeKnjiga = false;
	private boolean brisanjeAdmina = false, brisanjeKorisnika = false, brisanjeKnjiga = false;
	private boolean pozajmljivanjeKnjiga = false, registracija = false;

	public enum pol {
		MUSKO, ZENSKO
	}
	protected String uuid, ime, prezime, korisnickoIme, sifra;
	protected String jmbg, brTelefona;
	protected int pol;
	protected boolean isAdmin;

	public abstract String generisiUUID();
	public abstract String generisiSifruSaUUID(String sifra);

	/**Stampa tablicu dozvola u terminal / konzolu*/
	public void proveraDozvoli() {
		System.out.println("Dozvole:\tDodavanje\tBrisanje\tRegistracija\tPozajmljivanje");
		System.out.println("Admini:\t\t" + dodavanjeAdmina + "\t\t" + brisanjeAdmina + "\t\t" + "X"+ "\t\t\t\t" + "X");
		System.out.println("Korisnici:\t" + dodavanjeKorisnika + "\t\t" + brisanjeKorisnika + "\t\t" + registracija + "\t\t\t" + "X");
		System.out.println("Knjige:\t\t" + dodavanjeKnjiga + "\t\t" + brisanjeKnjiga + "\t\t" + "X" + "\t\t\t\t" + pozajmljivanjeKnjiga);
	}

	@Override
	public boolean mozeDaDodajeNovogAdmina() {
		return dodavanjeAdmina = true;
	}

	@Override
	public boolean mozeDaDodajeNovogKorisnika() {
		return dodavanjeKorisnika = true;
	}

	@Override
	public boolean mozeDaDodajeNovuKnjigu() {
		return dodavanjeKnjiga = true;
	}

	@Override
	public boolean mozeDaSeRegistruje() {
		return registracija = true;
	}

	@Override
	public boolean mozeDaPozajmiKnjigu() {
		return pozajmljivanjeKnjiga = true;
	}

	@Override
	public boolean mozeDaBriseKorisnike() {
		return brisanjeKorisnika = true;
	}

	@Override
	public boolean mozeDaBriseAdministratore() {
		return brisanjeAdmina = true;
	}

	@Override
	public boolean mozeDaBriseKnjige() {
		return brisanjeKnjiga = true;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
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

	public int getPol() {
		return pol;
	}

	public void setPol(int pol) {
		this.pol = pol;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}
	/**Vraca string u zavisnosti od brojne vrednosti promenljive 'pol'.
	 * U koliko vrednost nije 0 ili 1, vraca String "Nepoznato"*/
	private String pretvoriPolUString(int pol) {
		switch(pol) {
			case 0: return "Musko";
			case 1: return "Zensko";
			default: return "Nepoznato";
		}
	}

	@Override
	public String toString() {
		return "UUID: " + getUuid()  + ", Ime: " + getIme() + " " + getPrezime() + ", Korisnicko Ime: " + getKorisnickoIme() + ", JMBG: " + getJmbg() + ", Broj telefona: " + getBrTelefona() + ", Pol: " + pretvoriPolUString(getPol());
	}
}