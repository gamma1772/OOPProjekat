package projekat.sistem;

import java.io.Serializable;

public class PravilaBiblioteke implements IPravila, Serializable {

	private int maxPeriod, maxReloan;
	private double multiplier;
	private boolean loanMultipleAtOnce, loanBeforeReturningPrevious;

	/**Inicijalizacija pravila biblioteke. Inicijalizuju se pri pokretanju programa, da bi se kasnije mogli menjati.
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

	public PravilaBiblioteke() {
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

	public void setLoanBreforeReturningPrevious(boolean loanBeforeReturningPrevious) {
		this.loanBeforeReturningPrevious = loanBeforeReturningPrevious;
	}


	@Override
	public int maxPeriod() {
		return maxPeriod;
	}

	@Override
	public int maxReloan() {
		return maxReloan;
	}

	@Override
	public double multiplier() {
		return multiplier;
	}

	@Override
	public boolean loanMultipleAtOnce() {
		return loanMultipleAtOnce;
	}

	@Override
	public boolean loanBeforeReturningPrevious() {
		return loanBeforeReturningPrevious;
	}
}
