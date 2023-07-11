package core;

import core.common.*;

public class Test {
	
	/*
	 * TODO: 
	 * - Finish up recruiting
	 * - Add stats
	 * - Add staff
	 * - Add facilities
	 * - Add player ratings
	 * - Add player development
	 * 
	 * - Go through all code and cleanup and make reusable
	 * - Create basic UI
	 * - Finish team info panel
	 * - Fix date algorithm
	 * - Add depth chart
	 * - Save stats
	 * - Fix dates on matchups
	 * - Make schedule days into squares
	 * - Make clicking on schedule frame take you to full calendar
	 * - Make team page class
	 * - Make pages for other teams
	 * - Fix title in schedule frame (Redo schedule panel layout - use BoxLayout)
	 * - Add day of week to date
	 * - Fix player birthdates
	 * - Make league creation window separate class
	 * - Make recruit page look prettier
	 * - Add x buttons to tabs
	 * - Make recruit table update when switch to tab
	 */
	
	public static void main(String[] args) {
		testCalendar();
	}
	
	public static void testCalendar() {
		League l = new League(4);
		l.generateSchedule();
		
		for (int i = 0; i < Calendar.CALENDAR_LENGTH; i++) {
			System.out.println(l.calendar.getDate(Date.getNextDay(i)));
			System.out.println("Events Today: ");
			for (int j = 0; j < l.calendar.getEvents(Date.getNextDay(i)).size(); j++) {
				System.out.println(l.calendar.getEvents(Date.getNextDay(i)).get(j));
			}
			System.out.println();
		}
	}
	
	public static void testRosterCreation() {
		College s = new College(0);
		
		for (int i = 0; i < s.roster.size(); i++) {
			System.out.print(s.roster.get(i) + ", ");
			System.out.print(s.roster.get(i).getPosition() + ", ");
			System.out.print(s.roster.get(i).getCollege() + " - ");
			System.out.println(s.roster.get(i).getBirthday());
		}
	}
	
	public static void testLeague() {
		League l = new League(4);
		
		for (int i = 0; i < l.schools.size(); i++) {  
			System.out.println(l.schools.get(i));
		}
	}
	
	public static void testMatchup() {
		College s = new College(0);
		College t = new College(0);
		Matchup m = new Matchup(s, t, 0);
		
		m.simEvent();
		
		System.out.println("Winner: " + m.getWinner());
		System.out.println("Loser: " + m.getLoser());
	}
	
	public static void testRosterAddition() {
		Player p = new Player(Position.QUARTERBACK, 0);
		College s = new College(0);
		
		s.addPlayerToRoster(p);
		
		for (int i = 0; i < s.roster.size(); i++) {
			System.out.print(s.roster.get(i) + ", ");
			System.out.print(s.roster.get(i).getPosition() + ", ");
			System.out.print(s.roster.get(i).getCollege() + " - ");
			System.out.println(s.roster.get(i).getBirthday());
		}
	}
	
	public static void testScheduler() {
		League l = new League(16);
		
		l.generateSchedule();
		
		for (int w = 0; w < League.NUM_GAMES; w++) {
			System.out.println("Week " + (w + 1) + ":");
			
			for (int t = 0; t < l.getNumSchools(); t++) {
				System.out.print("Home Team: " + l.schools.get(t).schedule[w].getHomeTeam());
				System.out.print(" vs ");
				System.out.println("Away Team: " + l.schools.get(t).schedule[w].getAwayTeam());
			}
			
			System.out.println();
		}
	}
}
