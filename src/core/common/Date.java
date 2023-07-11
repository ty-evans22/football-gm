package core.common;

import java.util.ArrayList;
import core.Game;

public class Date {
	
	private int month, day, year;
	private String monthName;
	private static int[] daysPerMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public ArrayList<Event> eventsToday;
	
	public Date(int month, int day) {
		this.month = month;
		this.day = day;
		
		switch(month) {
		case 1:
			monthName = "January";
			break;
		case 2:
			monthName = "February";
			break;
		case 3:
			monthName = "March";
			break;
		case 4:
			monthName = "April";
			break;
		case 5:
			monthName = "May";
			break;
		case 6:
			monthName = "June";
			break;
		case 7:
			monthName = "July";
			break;
		case 8:
			monthName = "August";
			break;
		case 9:
			monthName = "September";
			break;
		case 10:
			monthName = "October";
			break;
		case 11:
			monthName = "November";
			break;
		case 12:
			monthName = "December";
			break;
		default:
			System.out.println("ERROR: Month is invalid. Is not a number between 1-12.");
		}
	}
	
	public Date(int month, int day, int year) {
		eventsToday = new ArrayList<Event>();
		
		this.month = month;
		this.day = day;
		this.year = year;
		
		switch(month) {
			case 1:
				monthName = "January";
				break;
			case 2:
				monthName = "February";
				break;
			case 3:
				monthName = "March";
				break;
			case 4:
				monthName = "April";
				break;
			case 5:
				monthName = "May";
				break;
			case 6:
				monthName = "June";
				break;
			case 7:
				monthName = "July";
				break;
			case 8:
				monthName = "August";
				break;
			case 9:
				monthName = "September";
				break;
			case 10:
				monthName = "October";
				break;
			case 11:
				monthName = "November";
				break;
			case 12:
				monthName = "December";
				break;
			default:
				System.out.println("ERROR: Month is invalid. Is not a number between 1-12.");
		}
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int newYear) {
		year = newYear;
	}
	
	/**
	 * Standard next day that gets the next day following the current day in the Game class.
	 * @return
	 * 		the date following the current date set in the Game class
	 */
	public static Date getNextDay() {
		Date currDate = Game.currentDate;
		int newMonth, newDay, newYear;
		
		if (currDate.day == daysPerMonth[currDate.month - 1]) {
			newDay = 1;
			if (currDate.month == 12) {
				newMonth = 1;
				newYear = currDate.year + 1;
			} else {
				newMonth = currDate.month + 1;
				newYear = currDate.year;
			}
		} else {
			newDay = currDate.day + 1;
			newMonth = currDate.month;
			newYear = currDate.year;
		}
		
		return new Date(newMonth, newDay, newYear);
	}
	
	public static Date getNextDay(int numDays, Date startingDate) {
		Date d = startingDate;
		
		for (int i = 0; i < numDays; i++) {
			int newMonth, newDay, newYear;
			
			if (d.day == daysPerMonth[d.month - 1]) {
				newDay = 1;
				if (d.month == 12) {
					newMonth = 1;
					newYear = d.year + 1;
				} else {
					newMonth = d.month + 1;
					newYear = d.year;
				}
			} else {
				newDay = d.day + 1;
				newMonth = d.month;
				newYear = d.year;
			}
			
			d = new Date(newMonth, newDay, newYear);
		}
		
		return d;
	}
	
	/**
	 * Gets the date following the current day in the Game class by a specified number of days.
	 * @param numDays
	 * 		the number of days in the future to return the date of
	 * @return
	 * 		the date following the current day set in the Game class by the specified number of days
	 */
	public static Date getNextDay(int numDays) {
		Date d = Game.currentDate;
		
		for (int i = 0; i < numDays; i++) {
			int newMonth, newDay, newYear;
			
			if (d.day == daysPerMonth[d.month - 1]) {
				newDay = 1;
				if (d.month == 12) {
					newMonth = 1;
					newYear = d.year + 1;
				} else {
					newMonth = d.month + 1;
					newYear = d.year;
				}
			} else {
				newDay = d.day + 1;
				newMonth = d.month;
				newYear = d.year;
			}
			
			d = new Date(newMonth, newDay, newYear);
		}
		
		return d;
	}
	
	public static Date getPreviousDay(int numDays, Date startingDate) {
		Date d = startingDate;
		
		for (int i = 0; i < numDays; i++) {
			int newMonth, newDay, newYear;
			
			if (d.day == 1) {
				if (d.month == 1) {
					newDay = daysPerMonth[11];
					newMonth = 12;
					newYear = d.year - 1;
				} else {
					newDay = daysPerMonth[d.month - 2];
					newMonth = d.month - 1;
					newYear = d.year;
				}
			} else {
				newDay = d.day - 1;
				newMonth = d.month;
				newYear = d.year;
			}
			
			d = new Date(newMonth, newDay, newYear);
		}
		
		return d;
	}
	
	public static Date dateFromText(String text) {
		String[] values = text.split("/");
		int month = Integer.parseInt(values[0]);
		int day = Integer.parseInt(values[1]);
		int year = Integer.parseInt(values[2]);
		Date d = new Date(month, day, year);
		return d;
	}
	
	/**
	 * Checks if one date is past the other.
	 * @param firstDate
	 * 			the date to check
	 * @param secondDate
	 * 			the date to see whether or not the first date is past it
	 * @return
	 * 			true if it is past that date, false if not
	 */
	public static boolean isPastDate(Date dateToCheck, Date comparisonDate) {
		if (dateToCheck.getMonth() > comparisonDate.getMonth()) {
			return true;
		} else if (dateToCheck.getMonth() == comparisonDate.getMonth()) {
			if (dateToCheck.getDay() > comparisonDate.getDay()) {
				return true;
			}
		}
		return false;
	}
	
	public String toDatabaseFormat() {
		return month + "/" + day + "/" + year;
	}
	
	@Override
	public String toString() {
		return monthName + " " + day + ", " + year;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (o == this) {
			return true;
		}
		
		if (!(o instanceof Date)) {
			return false;
		}
		
		Date d = (Date) o;
		
		return this.toString().equals(d.toString());
	}
}
