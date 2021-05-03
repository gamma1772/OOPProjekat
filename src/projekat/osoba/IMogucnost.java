package projekat.osoba;

/**Interfejs za sve opste dozvole koje Administrator ili Korisnik mogu da imaju.
 * */
public interface IMogucnost {

	public boolean mozeDaDodajeNovogAdmina();
	public boolean mozeDaDodajeNovogKorisnika();
	public boolean mozeDaDodajeNovuKnjigu();
	public boolean mozeDaSeRegistruje();
	public boolean mozeDaPozajmiKnjigu();
	public boolean mozeDaBriseKorisnike();
	public boolean mozeDaBriseAdministratore();
	public boolean mozeDaBriseKnjige();
}
