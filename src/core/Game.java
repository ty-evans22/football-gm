package core;

import javax.swing.SwingUtilities;
import core.common.*;
import core.ui.MainWindow;

public class Game {
	
	private static final int LEAGUE_STARTING_MONTH = 8;
	private static final int LEAGUE_STARTING_DAY = 25;
	private static final int LEAGUE_STARTING_YEAR = 2000;
	
	public static League league = null;
	public static College userSchool = null;
	public static Date seasonStartDate = new Date(8, 29);
	public static Date signingDayDate = new Date(2, 3);
	public static Date endOfSeasonDate = new Date(1, 5);
	public static Date currentDate = null;
	public static NewSeasonEvent newSeasonEvent = new NewSeasonEvent(seasonStartDate);
	public static SigningDayEvent signingDayEvent = new SigningDayEvent(new Date(2, 1, 2000));
	public static EndOfSeasonEvent endOfSeasonEvent = new EndOfSeasonEvent(new Date(1, 5, 2000));
	public static Database db = null;
	public static MainWindow mw = null;
	
	public static void main(String[] args) {
		
		currentDate = new Date(LEAGUE_STARTING_MONTH, LEAGUE_STARTING_DAY, LEAGUE_STARTING_YEAR);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mw = new MainWindow();
			}
		});
	}
}
