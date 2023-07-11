package core.common;

import java.util.ArrayList;

import core.Game;

public class Calendar {
	
	public static final int CALENDAR_LENGTH = 365;
	
	private Date[] calendar;
		
	public Calendar() {
		calendar = new Date[CALENDAR_LENGTH];
				
		for (int i = 0; i < CALENDAR_LENGTH; i++) {
			calendar[i] = Date.getNextDay(i);
		}
		
		Date d = Game.seasonStartDate;
		d.setYear(Game.currentDate.getYear());
		d = Date.getPreviousDay(1, d);
		if (Date.isPastDate(d, Game.currentDate)) {
			d.setYear(d.getYear());
		} else {
			d.setYear(d.getYear() + 1);
		}
		addEvent(d, Game.newSeasonEvent);
	}
	
	public Date getDate(Date d) {
		Date date = null;
		for (int i = 0; i < CALENDAR_LENGTH; i++) {
			if (calendar[i].equals(d)) {
				date = calendar[i];
				break;
			}
		}
		return date;
	}
	
	public void addEvent(Date d, Event e) {
		Date date = getDate(d);
		date.eventsToday.add(e);
	}
	
	/**
	 * Gets the events on the given date in the calendar.
	 * @param d
	 * 		the date to get the events on
	 * @return
	 * 		an ArrayList containing the events happening on the given date
	 */
	public ArrayList<Event> getEvents(Date d) {
		Date date = getDate(d);
		return date.eventsToday;
	}
	
	public void newSeason() {
		for (int i = 0; i < CALENDAR_LENGTH; i++) {
			calendar[i] = Date.getNextDay(i + 1);
		}
		
		Date d = Game.seasonStartDate;
		d.setYear(Game.currentDate.getYear());
		d = Date.getPreviousDay(1, d);
		if (Date.isPastDate(d, Game.currentDate)) {
			d.setYear(d.getYear());
		} else {
			d.setYear(d.getYear() + 1);
		}
		
		addEvent(d, Game.newSeasonEvent);
		
		Date s = Game.signingDayDate;
		if (Date.isPastDate(s, Game.currentDate)) {
			s.setYear(Game.currentDate.getYear());
		} else {
			s.setYear(Game.currentDate.getYear() + 1);
		}
		
		addEvent(s, Game.signingDayEvent);
		
		Date e = Game.endOfSeasonDate;
		if (Date.isPastDate(e, Game.currentDate)) {
			e.setYear(Game.currentDate.getYear());
		} else {
			e.setYear(Game.currentDate.getYear() + 1);
		}
		
		addEvent(e, Game.endOfSeasonEvent);
	}
}
