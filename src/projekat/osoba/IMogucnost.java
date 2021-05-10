package projekat.osoba;

/**Interfejs za sve opste dozvole koje Administrator ili Korisnik mogu da imaju.
 * */
public interface IMogucnost {

	/**Provera da li je korisnik administrator*/
	boolean isAdmin();

	/*Provera da li korisnik moze da dodaje nove admine, clanove ili knjige*/
	boolean mozeDaDodajeNovogAdmina();
	boolean mozeDaDodajeNovogKorisnika();
	boolean mozeDaDodajeNovuKnjigu();

	/*Provera da li korisnik moze da brise nove admine, clanove ili knjige*/
	boolean mozeDaBriseKorisnike();
	boolean mozeDaBriseAdministratore();
	boolean mozeDaBriseKnjige();

	/*Provera da li korisnik moze da se registruje ili da pozajmi knjigu*/
	boolean mozeDaSeRegistruje();
	boolean mozeDaPozajmiKnjigu();
}
