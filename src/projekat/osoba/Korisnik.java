package projekat.osoba;

public abstract class Korisnik extends AbstractOsoba implements IMogucnost{

	//Dozvole korisnika
	protected boolean dodavanjeAdmina = false, dodavanjeKorisnika = false, dodavanjeKnjiga = false;
	protected boolean brisanjeAdmina = false, brisanjeKorisnika = false, brisanjeKnjiga = false;
	protected boolean pozajmljivanjeKnjiga = false, registracija = false;

	private String UUID;
	protected boolean isAdmin;
	protected String username, password, email;

	public Korisnik(String ime, String prezime, String jmbg, String brTelefona, int pol, String username, String password, String email, boolean isAdmin) {

		generisiUUID();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.brTelefona = brTelefona;
		this.pol = pol;
		this.isAdmin = isAdmin;
		this.username = username;
		generisiSifruSaUUID(password, getUUID());
		this.email = email;
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

	public void generisiSifruSaUUID(String sifra, String UUID) {
		this.setPassword("");
	}

	@Override
	public String punoIme(String ime, String prezime) {
		return null;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
