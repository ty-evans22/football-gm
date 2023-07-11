package core.common;

import java.util.Random;

public class RandomSim extends SimEngine {

	public RandomSim(Matchup m) {
		super(m);
	}

	@Override
	public void simGame() {
		Random rand = new Random();
		int winner = rand.nextInt(2);
		if (winner == 0) {
			this.setWinner(this.matchup.getHomeTeam());
			this.setLoser(this.matchup.getAwayTeam());
		} else {
			this.setWinner(this.matchup.getAwayTeam());
			this.setLoser(this.matchup.getHomeTeam());
		}
	}
}
