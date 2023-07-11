package core.common;

/**
 * Basic scheduler where every team plays each other once.
 * Only works in exponents of 2 currently.
 * @author Tyler Evans
 */
public class BasicScheduler extends Scheduler {

	public BasicScheduler(League league) {
		super(league);
	}

	@Override
	public void createSchedule() {
		int numSchools = this.league.getNumSchools();
		League l = this.league;
		
		// iterate through each school
		for (int i = 0; i < numSchools; i++) {
			// iterate through each week
			for (int w = 0; w < League.NUM_GAMES; w++) {
				// check if current school already has a matchup scheduled this week
				if (l.schools.get(i).schedule[w] == null) {
					// iterate through the remaining schools to find a matchup
					for (int t = i + 1; t < numSchools; t++) {
						// check if the selected school already has a matchup this week
						if (l.schools.get(t).schedule[w] == null) {
							// check if already scheduled matchup against selected school
							boolean newSchool = false;
							for (int m = 0; m < League.NUM_GAMES; m++) {
								if (l.schools.get(i).schedule[m] == null) {
									continue;
								}
								if (l.schools.get(i).schedule[m].getHomeTeam() == l.schools.get(t) || l.schools.get(i).schedule[m].getAwayTeam() == l.schools.get(t)) {
									newSchool = true;
									break;
								}
							}
							if (newSchool) {
								continue;
							}
							Matchup match = new Matchup(l.schools.get(i), l.schools.get(t), w + 1);
							l.schools.get(i).schedule[w] = match;
							l.schools.get(t).schedule[w] = match;
							l.calendar.addEvent(match.getDate(), match);
						} else {
							continue;
						}
						break;
					}
				}
			}
		}
	}
}
