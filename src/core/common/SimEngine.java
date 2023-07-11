package core.common;

public abstract class SimEngine {
	
	// PRIVATE FIELDS
	private College winner, loser;
	
	// PUBLIC FIELDS
	public Matchup matchup;
	
	// CONSTRUCTOR
	public SimEngine(Matchup m) {
		matchup = m;
	}
	
	// NON-ABSTRACT METHODS
	
	/**
	 * Gets the winner of the matchup.
	 * @return
	 * 		the winner of the matchup
	 */
	public College getWinner() {
		return winner;
	}
	
	/**
	 * Gets the loser of the matchup.
	 * @return
	 * 		the loser of the matchup
	 */
	public College getLoser() {
		return loser;
	}
	
	/**
	 * Sets the winner of the matchup.
	 * @param s
	 * 		the winner of the matchup
	 */
	public void setWinner(College s) {
		winner = s;
	}
	
	/**
	 * Sets the loser of the matchup.
	 * @param s
	 * 		the loser of the matchup
	 */
	public void setLoser(College s) {
		loser = s;
	}
	
	// ABSTRACT METHODS
	public abstract void simGame();
}
