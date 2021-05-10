package projekat.osoba;

/**Interfejs za sve opste dozvole koje Administrator ili Korisnik mogu da imaju.
 * */
public interface IMogucnost {

	boolean isAdmin();

	boolean mozeDaDodajeNovogAdmina();
	boolean mozeDaDodajeNovogKorisnika();
	boolean mozeDaDodajeNovuKnjigu();
	boolean mozeDaSeRegistruje();
	boolean mozeDaPozajmiKnjigu();
	boolean mozeDaBriseKorisnike();
	boolean mozeDaBriseAdministratore();
	boolean mozeDaBriseKnjige();
}
