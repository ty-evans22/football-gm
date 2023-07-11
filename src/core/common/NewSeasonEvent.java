package core.common;

import core.Game;
import core.ui.MainWindow;

public class NewSeasonEvent extends Event {

	public NewSeasonEvent(Date d) {
		super(d);
		setUniversal();
	}
	
	private void resetRecordsAndSchedules() {
		for (int i = 0; i < Game.league.getNumSchools(); i++) {
			Game.league.schools.get(i).resetRecord();
			Game.league.schools.get(i).clearSchedule();
		}
	}
	
	@Override
	public void simEvent() {
		Game.newSeasonEvent = new NewSeasonEvent(Game.seasonStartDate);
		Game.league.calendar.newSeason();
		resetRecordsAndSchedules();
		Game.league.generateSchedule();
		Game.currentDate = Date.getNextDay();
		MainWindow.updateDay = false;
	}
	
	@Override
	public String toString() {
		return "New Season Start Date";
	}
}
