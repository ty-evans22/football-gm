package core.common;

import core.Game;

public class EndOfSeasonEvent extends Event {

	public EndOfSeasonEvent(Date d) {
		super(d);
		setUniversal();
	}

	@Override
	public void simEvent() {
		for (int i = 0; i < Game.league.getNumSchools(); i++) {
			for (int j = 0; j < Game.league.schools.get(i).roster.size(); j++) {
				if (Game.league.schools.get(i).roster.get(j).getEligibility() == Eligibility.SENIOR) {
					// graduate player
					Game.db.removePlayer(Game.league.schools.get(i).roster.get(j));
					Game.league.schools.get(i).roster.remove(j);
				} else {
					Game.league.schools.get(i).roster.get(j).nextEligibility();
					Game.db.updatePlayerEligibility(Game.league.schools.get(i).roster.get(j));
				}
			}
		}
		Game.mw.updateRosterTable();
	}
	
	@Override
	public String toString() {
		return "End of Season";
	}
}
