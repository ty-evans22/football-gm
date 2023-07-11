package core.common;

import core.Game;

public class SigningDayEvent extends Event {

	public SigningDayEvent(Date d) {
		super(d);
		setUniversal();
	}

	private void signRecruits() {
		System.out.println("Signing Recruits...");
		for (int i = 0; i < Game.league.recruits.size(); i++) {
			if (Game.league.recruits.get(i).offers.size() > 0) {
				Game.league.recruits.get(i).setCollege(Game.league.recruits.get(i).offers.get(0).getCollege());
				Game.db.updatePlayerCollege(Game.league.recruits.get(i), Game.league.recruits.get(i).offers.get(0).getCollege());
			}
		}
		System.out.println("Recruits signed.");
	}
	
	@Override
	public void simEvent() {
		signRecruits();
	}
	
	@Override
	public String toString() {
		return "Signing Day";
	}
}
