package core.common;

public abstract class Event {
	
	// matchup, signing day, practice, nfl draft, hall of fame announcement, poll update release, awards announcement, new season
	
	private Date date;
	private boolean universal = false;
	
	public Event(Date d) {
		date = d;
	}
	
	public Date getDate() {
		return date;
	}
	
	public boolean isUniversal() {
		return universal;
	}
	
	public void setUniversal() {
		universal = true;
	}
	
	/**
	 * Method that is called when event is to be simmed. Executes all code pertaining to the event.
	 */
	public abstract void simEvent();
}
