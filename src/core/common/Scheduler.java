package core.common;

public abstract class Scheduler {
	
	// PUBLIC FIELDS
	public League league;
	
	// CONTRUCTOR
	public Scheduler(League league) {
		this.league = league;
	}
	
	// ABSTRACT METHODS
	public abstract void createSchedule();
}
