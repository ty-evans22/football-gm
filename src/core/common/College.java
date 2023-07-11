package core.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import core.Game;

public class College {
	
	private final String PREFIX_FILE_NAME = "src/lists/SchoolNamesPrefix.txt";
	private final String SUFFIX_FILE_NAME = "src/lists/SchoolNamesSuffix.txt";
	private final String TEAM_FILE_NAME = "src/lists/TeamNames.txt";
	private final int PREFIX_FILE_LENGTH = 63;
	private final int SUFFIX_FILE_LENGTH = 11;
	private final int TEAM_FILE_LENGTH = 33;
	// 119
	private final int NUM_QB = 5;
	private final int NUM_RB = 8;
	private final int NUM_WR = 15;
	private final int NUM_TE = 8;
	private final int NUM_OL = 20;
	private final int NUM_DL = 20;
	private final int NUM_DB = 20;
	private final int NUM_LB = 15;
	private final int NUM_K = 4;
	private final int NUM_P = 4;
	
	/**
	 * The name of the school.
	 */
	private String schoolName;
	
	/**
	 * The name of the school's team.
	 */
	private String teamName;
	
	/**
	 * The state that the school is located in.
	 */
	private String state;
	
	/**
	 * The city that the school is located in.
	 */
	private String city;
	
	private int wins = 0;
	private int losses = 0;
	
	/**
	 * List of all of the players on the school's roster.
	 */
	public ArrayList<Player> roster;
	
	public Matchup[] schedule;
	public int collegeID;
	
	/**
	 * Constructor for when all school info is known.
	 * @param schoolName
	 * 		the given name for this school
	 * @param teamName
	 * 		the given name for this school's team
	 */
	public College(String schoolName, String teamName) {
		this.schoolName = schoolName;
		this.teamName = teamName;
		
		roster = new ArrayList<Player>();
		schedule = new Matchup[League.NUM_GAMES];
	}
	
	/**
	 * Constructor for randomly generating a school.
	 */
	public College(int conferenceID) {
		roster = new ArrayList<Player>();
		schedule = new Matchup[League.NUM_GAMES];
		
		Random rand = new Random();
		
		// generate school name prefix
		try {
			File prefix = new File(PREFIX_FILE_NAME);
			Scanner scan = new Scanner(prefix);
			int line = rand.nextInt(PREFIX_FILE_LENGTH) + 1;
			for (int i = 1; i < line; i++) {
				scan.nextLine();
			}
			schoolName = scan.nextLine();
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: School name prefix file not found.");
			e.printStackTrace();
		}
		
		// generate school name suffix
		try {
			File suffix = new File(SUFFIX_FILE_NAME);
			Scanner scan = new Scanner(suffix);
			int line = rand.nextInt(SUFFIX_FILE_LENGTH) + 1;
			for (int i = 1; i < line; i++) {
				scan.nextLine();
			}
			schoolName += " " + scan.nextLine();
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: School name suffix file not found.");
			e.printStackTrace();
		}
		
		// generate team name
		try {
			File team = new File(TEAM_FILE_NAME);
			Scanner scan = new Scanner(team);
			int line = rand.nextInt(TEAM_FILE_LENGTH) + 1;
			for (int i = 1; i < line; i++) {
				scan.nextLine();
			}
			teamName = scan.nextLine();
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Team name file not found.");
			e.printStackTrace();
		}
		
		// add to database
		this.collegeID = Game.db.addCollege(this, conferenceID);
		
		// generate roster
		generateRoster();
	}
	
	private void generateRoster() {
		// add QBs
		for (int i = 0; i < NUM_QB; i++) {
			Player p = new Player(Position.QUARTERBACK, this.collegeID);
			this.addPlayerToRoster(p);
		}
				
		// add RBs
		for (int i = 0; i < NUM_RB; i++) {
			Player p = new Player(Position.RUNNING_BACK, this.collegeID);
			this.addPlayerToRoster(p);
		}
		
		// add WRs
		for (int i = 0; i < NUM_WR; i++) {
			Player p = new Player(Position.WIDE_RECEIVER, this.collegeID);
			this.addPlayerToRoster(p);
		}
		
		// add TEs
		for (int i = 0; i < NUM_TE; i++) {
			Player p = new Player(Position.TIGHT_END, this.collegeID);
			this.addPlayerToRoster(p);
		}
				
		// add OL
		for (int i = 0; i < NUM_OL; i++) {
			Player p = new Player(Position.OFFENSIVE_LINEMAN, this.collegeID);
			this.addPlayerToRoster(p);
		}
				
		// add DL
		for (int i = 0; i < NUM_DL; i++) {
			Player p = new Player(Position.DEFENSIVE_LINEMAN, this.collegeID);
			this.addPlayerToRoster(p);
		}
				
		// add DBs
		for (int i = 0; i < NUM_DB; i++) {
			Player p = new Player(Position.DEFENSIVE_BACK, this.collegeID);
			this.addPlayerToRoster(p);
		}
				
		// add LBs
		for (int i = 0; i < NUM_LB; i++) {
			Player p = new Player(Position.LINEBACKER, this.collegeID);
			this.addPlayerToRoster(p);
		}
				
		// add Ks
		for (int i = 0; i < NUM_K; i++) {
			Player p = new Player(Position.KICKER, this.collegeID);
			this.addPlayerToRoster(p);
		}
				
		// add Ps
		for (int i = 0; i < NUM_P; i++) {
			Player p = new Player(Position.PUNTER, this.collegeID);
			this.addPlayerToRoster(p);
		}		
	}
	
	/**
	 * Gets the full name of the school.
	 * @return
	 * 		the full name of the school
	 */
	public String getFullSchoolName() {
		return schoolName + " " + teamName;
	}
	
	/**
	 * Gets the name of the school's team.
	 * @return
	 * 		the name of the school's team
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Adds the given player to the school's roster.
	 * @param p
	 * 		the player to be added to the school's roster
	 */
	public void addPlayerToRoster(Player p) {
		p.setCollege(this);
		roster.add(p);
	}
	
	public String getState() {
		return state;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getLocation() {
		return city + ", " + state;
	}
	
	public int getWins() {
		return wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public void addWin() {
		wins++;
	}
	
	public void addLoss() {
		losses++;
	}
	
	public void resetRecord() {
		wins = 0;
		losses = 0;
	}
	
	public void clearSchedule() {
		for (int i = 0; i < schedule.length; i++) {
			schedule[i] = null;
		}
	}
	
	@Override
	public String toString() {
		return schoolName;
	}
}
