package projekat.sistem;

import java.io.Serializable;

public class PravilaBiblioteke implements IPravila /*, Serializable*/ {

	private int maxPeriod, maxReloan;
	private double multiplier;
	private boolean loanMultipleAtOnce, loanBeforeReturningPrevious;

	/**Inicijalizacija pravila biblioteke. Ovaj metod se ne koristi pri pokretanju programa.
	 * @param maxPeriod Broj dana koji je dozvoljen da se zadrzi knjiga pre vracanja ili produzavanja pozajmljivanja
	 * @param maxReloan Koliko puta moze da se produzi zadrzavanje knjige
	 * @param multiplier Mnozilac duga
	 * @param loanMultipleAtOnce Da li moze da se pozajmi vise knjiga od jednom
	 * @param loanBeforeReturningPrevious Da li moze da se pozajmi knjiga pre nego sto se vrate prethodne*/
	public PravilaBiblioteke(int maxPeriod, int maxReloan, double multiplier, boolean loanMultipleAtOnce, boolean loanBeforeReturningPrevious) {
		this.maxPeriod = maxPeriod;
		this.maxReloan = maxReloan;
		this.multiplier = multiplier;
		this.loanMultipleAtOnce = loanMultipleAtOnce;
		this.loanBeforeReturningPrevious = loanBeforeReturningPrevious;
	}

	/**Poziva se pri pokretanju programa u slucaju da se program pokrece prvi put ili da fajl sa podacima ne postoji.
	 * Postavlja sve vrednosti na podrazumevane.*/
	public PravilaBiblioteke() {
		this.maxPeriod = DEFAULT_MAX_PERIOD;
		this.maxReloan = DEFAULT_MAX_RELOAN;
		this.multiplier = DEFAULT_MULTIPLIER;
		this.loanMultipleAtOnce = DEFAULT_LOAN_MULTIPLE;
		this.loanBeforeReturningPrevious = DEFAULT_LOAN_BEFORE_RETURNING_PREVIOUS;
	}

	//Lancane metode
	/**Maksimalan period za koji moze da se zadrzi knjiga pre vracanja
	 * Podrazumevano: {@link IPravila#DEFAULT_MAX_PERIOD}
	 * @param maxPeriod Broj dana*/
	public PravilaBiblioteke maxPeriod(int maxPeriod) {
		this.maxPeriod = maxPeriod; return this;
	}
	/**Maksimalan broj puta koliko moze da se produzi pozajmljivanje
	 * Podrazumevano: {@link IPravila#DEFAULT_MAX_RELOAN}
	 * @param maxReloan Broj mogucih produzavanja pozajmljivanja*/
	public PravilaBiblioteke maxReloan(int maxReloan) {
		this.maxReloan = maxReloan; return this;
	}
	/**Mnozilac duga. Formula preko koje se racuna dug; IF(period > maxPeriod) : multiplier * (brDanaPosleRoka + brKnjiga)
	 * Podrazumevano: {@link IPravila#DEFAULT_MAX_RELOAN}
	 * @param multiplier Mnozilac duga*/
	public PravilaBiblioteke multiplier(double multiplier) {
		this.multiplier = multiplier; return this;
	}
	/**Da li moze da se pozajmi vise knjiga od jednom?
	 * Podrazumevano: {@link IPravila#DEFAULT_LOAN_MULTIPLE}
	 * @param loanMultipleAtOnce Boolean*/
	public PravilaBiblioteke loanMultipleAtOnce(boolean loanMultipleAtOnce) {
		this.loanMultipleAtOnce = loanMultipleAtOnce; return this;
	}
	/**Da li moze da se pozajmi knjiga, pre nego sto se vrate prethodno pozajmljene
	 * Podrazumevano: {@link IPravila#DEFAULT_LOAN_BEFORE_RETURNING_PREVIOUS}
	 * @param loanBeforeReturningPrevious Boolean*/
	public PravilaBiblioteke loanBreforeReturningPrevious(boolean loanBeforeReturningPrevious) {
		this.loanBeforeReturningPrevious = loanBeforeReturningPrevious; return this;
	}

	public void setMaxPeriod(int maxPeriod) {
		this.maxPeriod = maxPeriod;
	}
	public void setMaxReloan(int maxReloan) {
		this.maxReloan = maxReloan;
	}
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	public void setLoanMultipleAtOnce(boolean loanMultipleAtOnce) {
		this.loanMultipleAtOnce = loanMultipleAtOnce;
	}
	public void setLoanBeforeReturningPrevious(boolean loanBeforeReturningPrevious) {
		this.loanBeforeReturningPrevious = loanBeforeReturningPrevious;
	}

	@Override
	public int getMaxPeriod() {
		return maxPeriod;
	}
	@Override
	public int getMaxReloan() {
		return maxReloan;
	}
	@Override
	public double getMultiplier() {
		return multiplier;
	}
	@Override
	public boolean getLoanMultipleAtOnce() {
		return loanMultipleAtOnce;
	}
	@Override
	public boolean getLoanBeforeReturningPrevious() {
		return loanBeforeReturningPrevious;
	}

	public String toStringSerializable() {
		return String.format("%d~%d~%.2f~%b~%b", getMaxPeriod(), getMaxReloan(), getMultiplier(), getLoanMultipleAtOnce(), getLoanBeforeReturningPrevious());
	}
}
