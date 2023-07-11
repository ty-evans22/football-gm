package core.common;

import java.util.ArrayList;
import java.util.Random;

import core.Game;

public class League {
	
	private final double RECRUIT_QB_VALUE = 0.042;
	private final double RECRUIT_RB_VALUE = 0.109;
	private final double RECRUIT_WR_VALUE = 0.236;
	private final double RECRUIT_TE_VALUE = 0.303;
	private final double RECRUIT_OL_VALUE = 0.471;
	private final double RECRUIT_DL_VALUE = 0.639;
	private final double RECRUIT_DB_VALUE = 0.807;
	private final double RECRUIT_LB_VALUE = 0.126;
	private final double RECRUIT_K_VALUE = 0.933;
	
	public static final int NUM_GAMES = 15;
	
	/**
	 * The number of schools in this league.
	 */
	private int numSchools;
	
	private Scheduler scheduler;
	
	/**
	 * A list containing all of the schools in this league.
	 */
	public ArrayList<College> schools;
	
	public ArrayList<Conference> conferences;
	
	public ArrayList<Player> recruits;
	
	public Calendar calendar;
	
	public int numConferences;
	
	public League(int numSchools) {
		this.numSchools = numSchools;
		
		/* generate schools */
		schools = new ArrayList<College>();
		for (int i = 0; i < numSchools; i++) {
			schools.add(new College(0));
		}
		
		/* create calendar */
		calendar = new Calendar();
		
		scheduler = new BasicScheduler(this);
		
		Game.seasonStartDate.setYear(Game.seasonStartDate.getYear() + 1);
	}
	
	public League(int numConferences, String[] conferenceNames, int numSchoolsPerConference) {
		this.numConferences = numConferences;
		this.numSchools = numConferences * numSchoolsPerConference;
		
		conferences = new ArrayList<Conference>();
		schools = new ArrayList<College>();
		
		for (int i = 0; i < numConferences; i++) {
			Conference c = new Conference(conferenceNames[i]);
			
			for (int j = 0; j < numSchoolsPerConference; j++) {
				College s = new College(c.conferenceID);
				c.addMember(s);
				schools.add(s);
			}
			
			conferences.add(c);
		}
		
		/* create calendar */
		calendar = new Calendar();
		
		scheduler = new BasicScheduler(this);
		
		Game.seasonStartDate.setYear(Game.seasonStartDate.getYear() + 1);
		
		recruits = new ArrayList<Player>();
		generateRecruits();
	}
	
	public void generateRecruits() {
		// 27, 21, 29, 24, 24, 26, 24, 21, 26, 17, 23, 21, 26, 28, 22
		// avg 25
		Random rand = new Random();
		int numRecruits = (30 + rand.nextInt(5)) * numSchools;
		for (int i = 0; i < numRecruits; i++) {
			// determine player position
			double posD = rand.nextDouble();
			Position p;
			
			if (posD < RECRUIT_QB_VALUE) {
				p = Position.QUARTERBACK;
			} else if (posD < RECRUIT_RB_VALUE) {
				p = Position.RUNNING_BACK;
			} else if (posD < RECRUIT_WR_VALUE) {
				p = Position.WIDE_RECEIVER;
			} else if (posD < RECRUIT_TE_VALUE) {
				p = Position.TIGHT_END;
			} else if (posD < RECRUIT_OL_VALUE) {
				p = Position.OFFENSIVE_LINEMAN;
			} else if (posD < RECRUIT_DL_VALUE) {
				p = Position.DEFENSIVE_LINEMAN;
			} else if (posD < RECRUIT_DB_VALUE) {
				p = Position.DEFENSIVE_BACK;
			} else if (posD < RECRUIT_LB_VALUE) {
				p = Position.LINEBACKER;
			} else if (posD < RECRUIT_K_VALUE) {
				p = Position.KICKER;
			} else {
				p = Position.PUNTER;
			}
			
			// create player
			Player r = new Player(p, 0, true);
			
			// add player to recruits list
			recruits.add(r);
		}
	}
	
	public void generateSchedule() {
		scheduler.createSchedule();
	}
	
	public int getNumSchools() {
		return numSchools;
	}
	
	public void nextDay() {
		for (int i = 0; i < calendar.getEvents(Game.currentDate).size(); i++) {
			calendar.getEvents(Game.currentDate).get(i).simEvent();
		}
	}
}
