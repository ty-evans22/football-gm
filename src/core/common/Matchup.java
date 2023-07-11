package core.common;

public class Matchup extends Event {
	
	// PRIVATE FIELDS
	private SimEngine sim;
	
	/**
	 * The winner of this matchup.
	 */
	private College winner;
	
	/**
	 * The loser of this matchup.
	 */
	private College loser;
	
	/**
	 * The home team in this matchup.
	 */
	private College homeTeam;
	
	/**
	 * The away team in this matchup.
	 */
	private College awayTeam;
	
	private int week;
	
	// CONSTRUCTOR
	public Matchup(College homeTeam, College awayTeam, int week) {
		super(Date.getNextDay(week * 7));
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.week = week;
		
		sim = new RandomSim(this);
	}
	
	// PUBLIC METHODS
	
	public College getWinner() {
		return winner;
	}
	
	public College getLoser() {
		return loser;
	}
	
	public College getHomeTeam() {
		return homeTeam;
	}
	
	public College getAwayTeam() {
		return awayTeam;
	}
	
	public int getWeek() {
		return week;
	}
	
	@Override
	public String toString() {
		return awayTeam + " @ " + homeTeam;
	}

	@Override
	public void simEvent() {
		sim.simGame();
		winner = sim.getWinner();
		loser = sim.getLoser();
		winner.addWin();
		loser.addLoss();
	}
}
