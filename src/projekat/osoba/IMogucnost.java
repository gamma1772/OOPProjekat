package projekat.osoba;

/**Interfejs za sve opste dozvole koje Administrator ili Korisnik mogu da imaju.
 * */
public interface IMogucnost {

	/**Provera da li je korisnik administrator*/
	boolean isAdmin();

	/*Provera da li korisnik moze da dodaje nove admine, clanove ili knjige*/
	boolean canAddAdmins();
	boolean canAddMembers();
	boolean canAddBooks();

	boolean hasMasterRule();
	boolean canAlterRules();

	/*Provera da li korisnik moze da brise nove admine, clanove ili knjige*/
	boolean canDeleteMembers();
	boolean canDeleteAdmins();
	boolean canDeleteBooks();

	boolean canLoanBooks();

	/*Provera da li korisnik moze da vrsi izmene nad adminima, clanovima ili knjigama*/
	boolean canEditMembers();
	boolean canEditAdmins();
	boolean canEditBooks();
}
