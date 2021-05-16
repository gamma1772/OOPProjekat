package projekat.sistem;

public interface IPravila {

	/**Podrazumevane vrednosti*/
	int DEFAULT_MAX_PERIOD = 17;
	int DEFAULT_MAX_RELOAN = 2;
	double DEFAULT_MULTIPLIER = 0.15D;
	boolean DEFAULT_LOAN_MULTIPLE = true;
	boolean DEFAULT_LOAN_BEFORE_RETURNING_PREVIOUS = false;

	/**Maksimalan period za koji knjiga moze biti pozajmljena*/
	int getMaxPeriod();
	/**Koliko puta moze maksimalno da se produzi pozajmljivanje knjige*/
	int getMaxReloan();
	/**Broj sa kojim se mnozi dug */
	double getMultiplier();
	/**Da li moze da se pozajmi jedna ili vise knjiga u jednom terminu?*/
	boolean getLoanMultipleAtOnce();
	/**Da li moraju da se vrate prethodno pozajmljene knjige pre nego sto se pozajmi sledeca?*/
	boolean getLoanBeforeReturningPrevious();

}
