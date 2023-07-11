package core.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import core.Game;

public class Player extends Person {
	
	private final String FIRST_NAME_FILE = "src/lists/FirstNames.txt";
	private final String LAST_NAME_FILE = "src/lists/LastNames1.txt";
	private final int FIRST_NAME_FILE_LENGTH = 1183;
	private final int LAST_NAME_FILE_LENGTH = 173;
	
	/**
	 * The school that the player attends and plays for.
	 */
	private College college = null;
	
	/**
	 * The position that the player plays.
	 */
	private Position position;
	
	private Eligibility eligibility;
	
	public Ratings ratings;
	public int playerID;
	public ArrayList<Offer> offers;
	
	/**
	 * Constructor when all info is known.
	 * @param firstName
	 * 		the given first name for this player
	 * @param lastName
	 * 		the given last name for this player
	 * @param birthday
	 *		the given birthday for this player 
	 * @param position
	 * 		the given position for this player
	 */
	public Player(String firstName, String lastName, Date birthday, Position position) {
		super(firstName, lastName, birthday);
		
		this.position = position;
	}
	
	public Player(Position position, int collegeID, boolean recruit) {
		super("First", "Last", new Date(1, 1, 2000));
		
		offers = new ArrayList<Offer>();
		Random rand = new Random();
		
		// generate first name
		try {
			File first = new File(FIRST_NAME_FILE);
			Scanner scan = new Scanner(first);
			int line = rand.nextInt(FIRST_NAME_FILE_LENGTH) + 1;
			for (int i = 1; i < line; i++) {
				scan.next();
			}
			this.setFirstName(scan.next());
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: First name file not found.");
			e.printStackTrace();
		}
		
		// generate last name
		try {
			File last = new File(LAST_NAME_FILE);
			Scanner scan = new Scanner(last);
			int line = rand.nextInt(LAST_NAME_FILE_LENGTH) + 1;
			for (int i = 1; i < line; i++) {
				scan.next();
			}
			this.setLastName(scan.next());
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Last name file not found.");
			e.printStackTrace();
		}
		
		// generate birthday
		int month = rand.nextInt(12) + 1;
		int[] daysPerMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int day = rand.nextInt(daysPerMonth[month - 1]) + 1;
		int year = 2000;
		Date d = new Date(month, day, year);
		this.setBirthday(d);
		
		this.position = position;
		
		// add to database
		this.playerID = Game.db.addPlayer(this, collegeID);
	}
	
	/**
	 * Constructor for randomly generating player at specified position.
	 * @param position
	 * 		the given position for this player
	 */
	public Player(Position position, int collegeID) {
		super("First", "Last", new Date(1, 1, 2000));
		
		offers = new ArrayList<Offer>();
		Random rand = new Random();
		
		// generate first name
		try {
			File first = new File(FIRST_NAME_FILE);
			Scanner scan = new Scanner(first);
			int line = rand.nextInt(FIRST_NAME_FILE_LENGTH) + 1;
			for (int i = 1; i < line; i++) {
				scan.next();
			}
			this.setFirstName(scan.next());
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: First name file not found.");
			e.printStackTrace();
		}
		
		// generate last name
		try {
			File last = new File(LAST_NAME_FILE);
			Scanner scan = new Scanner(last);
			int line = rand.nextInt(LAST_NAME_FILE_LENGTH) + 1;
			for (int i = 1; i < line; i++) {
				scan.next();
			}
			this.setLastName(scan.next());
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Last name file not found.");
			e.printStackTrace();
		}
		
		// generate eligibility
		int elig = rand.nextInt(5);
		switch(elig) {
			case 0:
				eligibility = Eligibility.REDSHIRT;
				break;
			case 1:
				eligibility = Eligibility.FRESHMAN;
				break;
			case 2:
				eligibility = Eligibility.SOPHOMORE;
				break;
			case 3:
				eligibility = Eligibility.JUNIOR;
				break;
			case 4:
				eligibility = Eligibility.SENIOR;
				break;
		}
		
		// generate birthday
		int month = rand.nextInt(12) + 1;
		int[] daysPerMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int day = rand.nextInt(daysPerMonth[month - 1]) + 1;
		int year = 2000;
		Date d = new Date(month, day, year);
		this.setBirthday(d);
		
		this.position = position;
		
		// add to database
		this.playerID = Game.db.addPlayer(this, collegeID);
	}
	
	/**
	 * Gets the school that this player attends and plays for.
	 * @return
	 * 		the school that this player attends
	 */
	public College getCollege() {
		return college;
	}
	
	/**
	 * Sets this player's school to the given one.
	 * @param school
	 * 		the school to assign this player to
	 */
	public void setCollege(College college) {
		this.college = college;
	}
	
	/**
	 * Gets the position that this player plays at.
	 * @return
	 * 		this player's position
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Sets this player's position to the given position.
	 * @param position
	 * 		the new position for this player to play at
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Eligibility getEligibility() {
		return eligibility;
	}
	
	public void setEligibility(Eligibility e) {
		eligibility = e;
	}
	
	public void nextEligibility() {
		if (eligibility == Eligibility.REDSHIRT) {
			eligibility = Eligibility.FRESHMAN;
		} else if (eligibility == Eligibility.FRESHMAN) {
			eligibility = Eligibility.SOPHOMORE;
		} else if (eligibility == Eligibility.SOPHOMORE) {
			eligibility = Eligibility.JUNIOR;
		} else if (eligibility == Eligibility.JUNIOR) {
			eligibility = Eligibility.SENIOR;
		}
	}
}
