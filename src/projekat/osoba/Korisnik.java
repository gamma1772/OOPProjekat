package projekat.osoba;

public abstract class Korisnik implements IMogucnost{

	//Dozvole korisnika
	protected boolean dodavanjeAdmina = false, dodavanjeKorisnika = false, dodavanjeKnjiga = false;
	protected boolean brisanjeAdmina = false, brisanjeKorisnika = false, brisanjeKnjiga = false;
	protected boolean pozajmljivanjeKnjiga = false, registracija = false;

	private String UUID;
	protected String ime, prezime;
	protected String jmbg, brTelefona;
	protected int pol;
	protected boolean isAdmin;
	protected String username, email;
	protected Sifra password;

	public Korisnik(String ime, String prezime, String jmbg, String brTelefona, int pol, String username, Sifra password, String email, boolean isAdmin) {

		generisiUUID();
		this.setIme(ime);
		this.setPrezime(prezime);
		this.setJmbg(jmbg);
		this.setBrTelefona(brTelefona);
		this.setPol(pol);
		this.isAdmin = isAdmin;
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
	}

	/**Stampa tablicu dozvola u terminal / konzolu*/
	public void proveraMogucnosti() {
		System.out.println("Dozvole:\tDodavanje\tBrisanje\tRegistracija\tPozajmljivanje");
		System.out.println("Admini:\t\t" + dodavanjeAdmina + "\t\t" + brisanjeAdmina + "\t\t" + "X" + "\t\t\t\t" + "X");
		System.out.println("Korisnici:\t" + dodavanjeKorisnika + "\t\t" + brisanjeKorisnika + "\t\t" + registracija + "\t\t\t" + "X");
		System.out.println("Knjige:\t\t" + dodavanjeKnjiga + "\t\t" + brisanjeKnjiga + "\t\t" + "X" + "\t\t\t\t" + pozajmljivanjeKnjiga);
	}

	public void generisiUUID() {
		this.setUUID("");
	}

	public String punoIme() {
		return String.format("%s %s", getIme(), getPrezime());
	}

	@Override
	public boolean mozeDaDodajeNovogAdmina() {
		return false;
	}

	@Override
	public boolean mozeDaDodajeNovogKorisnika() {
		return false;
	}

	@Override
	public boolean mozeDaDodajeNovuKnjigu() {
		return false;
	}

	@Override
	public boolean mozeDaSeRegistruje() {
		return false;
	}

	@Override
	public boolean mozeDaPozajmiKnjigu() {
		return false;
	}

	@Override
	public boolean mozeDaBriseKorisnike() {
		return false;
	}

	@Override
	public boolean mozeDaBriseAdministratore() {
		return false;
	}

	@Override
	public boolean mozeDaBriseKnjige() {
		return false;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String UUID) {
		this.UUID = UUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Sifra getPassword() {
		return password;
	}

	public void setPassword(Sifra password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getPol() {
		return pol;
	}

	@Override
	public boolean isAdmin() {
		return true;
	}

	public void setPol(int pol) {
		this.pol = pol;
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
}
