package core.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import core.Game;
import core.common.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	public static boolean updateDay = true;
	
	/* team page panels */
	private TeamInfoPanel teamInfo;
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	private JMenuBar menuBar;
	private JMenu settingsMenu;
	private JMenu leagueMenu;
	private JMenu teamMenu;
	private JMenu teamsSubMenu;
	private JMenuItem menuItem;
	private JTable roster;
	private Container cp;
	private JScrollPane scrollPane;
	private JPanel sideMenu;
	private JTabbedPane mainScreen;
	private JPanel userTeamHomePage;
	private SchedulePanel schedule;
	
	public MainWindow() {
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		/* window settings */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("College Football Manager");
		setMaximizedBounds(env.getMaximumWindowBounds());
		setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
		setVisible(true);
		
		/* initialize main components */
		menuBar = new JMenuBar();
		roster = new JTable();
		sideMenu = new JPanel();
		scrollPane = new JScrollPane(roster);
		roster.setFillsViewportHeight(true);
		mainScreen = new JTabbedPane();
		userTeamHomePage = new JPanel();
		schedule = new SchedulePanel();
		teamInfo = new TeamInfoPanel(null);
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		
		/* create the menu bar */
		createMenuBar(menuBar);
		
		/* create the side menu */
		addButtonsToSideMenu();
		
		/* set component settings */
		leftPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());
		sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
		sideMenu.setSize(100, 500);
		sideMenu.setBackground(Color.BLUE);
		userTeamHomePage.setLayout(new BorderLayout());
		mainScreen.add("Team Home", userTeamHomePage);
		
		/* add main components */
		cp.add(sideMenu, BorderLayout.WEST);
		cp.add(mainScreen, BorderLayout.CENTER);
		leftPanel.add(schedule, BorderLayout.SOUTH);
		rightPanel.add(scrollPane, BorderLayout.CENTER);
		userTeamHomePage.add(teamInfo, BorderLayout.NORTH);
		userTeamHomePage.add(leftPanel, BorderLayout.CENTER);
		userTeamHomePage.add(rightPanel, BorderLayout.EAST);
		setJMenuBar(menuBar);
	}
	
	protected void addButtonsToSideMenu() {
		JButton advanceDay = new JButton("Advance Day");
		JButton openRecruitPage = new JButton("Recruits");
		advanceDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.league.nextDay();
				
				/* DEBUG */
//				System.out.println();
//				System.out.println(Game.currentDate);
//				System.out.println("Events Today:");
//				for (int j = 0; j < Game.league.calendar.getEvents(Game.currentDate).size(); j++) {
//					System.out.println(Game.league.calendar.getEvents(Game.currentDate).get(j));
//				}
				
				teamInfo.updateTeamRecord();
				
				if (updateDay) {
					Game.currentDate = Date.getNextDay();
				} else {
					updateDay = true;
				}
				
				schedule.nextDay();
			}
		});
		openRecruitPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecruitmentPage recruitPage = new RecruitmentPage(mainScreen);
				mainScreen.add("Recruits", recruitPage);
			}
		});
		sideMenu.add(advanceDay);
		sideMenu.add(openRecruitPage);
	}
	
	protected void createRosterTable() {
		Object[][] data = new Object[Game.userSchool.roster.size()][4];
		for (int i = 0; i < Game.userSchool.roster.size(); i++) {
			data[i][0] = Game.userSchool.roster.get(i).getFirstName();
			data[i][1] = Game.userSchool.roster.get(i).getLastName();
			data[i][2] = Game.userSchool.roster.get(i).getPosition();
			data[i][3] = Game.userSchool.roster.get(i).getBirthday();
		}
		
		RosterTableModel rtm = new RosterTableModel(data);
		roster.setModel(rtm);
	}
	
	public void updateRosterTable() {
		Object[][] data = new Object[Game.userSchool.roster.size()][4];
		for (int i = 0; i < Game.userSchool.roster.size(); i++) {
			data[i][0] = Game.userSchool.roster.get(i).getFirstName();
			data[i][1] = Game.userSchool.roster.get(i).getLastName();
			data[i][2] = Game.userSchool.roster.get(i).getPosition();
			data[i][3] = Game.userSchool.roster.get(i).getBirthday();
		}
		
		RosterTableModel rtm = new RosterTableModel(data);
		roster.setModel(rtm);
	}
	
	protected void addTeamsToMenuBar() {
		for (int i = 0; i < Game.league.schools.size(); i++) {
			menuItem = new JMenuItem(Game.league.schools.get(i).toString());
			teamsSubMenu.add(menuItem);
		}
	}
	
	protected void createMenuBar(JMenuBar menuBar) {
		settingsMenu = new JMenu("Game Settings");
		leagueMenu = new JMenu("League");
		teamMenu = new JMenu("Team");
		
		/* add the menus to the menu bar */
		menuBar.add(settingsMenu);
		menuBar.add(leagueMenu);
		menuBar.add(teamMenu);
		
		/* build the game settings menu */
		
		/* build the league menu */
		menuItem = new JMenuItem("Create new league");
		teamsSubMenu = new JMenu("Teams");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
				JPanel p = new JPanel();
				SpinnerModel numConfModel = new SpinnerNumberModel(1, 1, 20, 1);
				JSpinner s = new JSpinner(numConfModel);
				SpinnerModel numSchoolModel = new SpinnerNumberModel(4, 4, 16, 4);
				JSpinner s2 = new JSpinner(numSchoolModel);
				p.setLayout(new GridLayout(3, 2));
				p.add(new JLabel("Name of League:"));
				p.add(new JTextField(5));
				p.add(new JLabel("Number of Conferences:"));
				p.add(s);
				p.add(new JLabel("Number of Schools Per Conference"));
				p.add(s2);
				Object[] options = {"Cancel", "Create League"};
				int result = JOptionPane.showOptionDialog(null, p, "League Creation", JOptionPane.OK_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (result == JOptionPane.OK_OPTION) {
					// actually cancel
				} else {
					// create new league
					System.out.println("Creating new league...");
					Game.db = new Database("footballgm.db");
					String[] conf = {"ACC", "Big 12", "Big 10", "SEC"};
					League l = new League((int) s.getValue(), conf, (int) s2.getValue());
					System.out.println("League created.");
					Game.league = l;
					
					// user selects their team
					String[] choices = new String[Game.league.getNumSchools()];
					for (int i = 0; i < Game.league.getNumSchools(); i++) {
						choices[i] = Game.league.schools.get(i).toString();
					}
					String input = (String) JOptionPane.showInputDialog(null, "Select from the list...", "Select Your School", 
							JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
					int school = 0;
					for (int i = 0; i < Game.league.getNumSchools(); i++) {
						if (input.equals(Game.league.schools.get(i).toString())) {
							school = i;
							break;
						}
					}
					Game.userSchool = Game.league.schools.get(school);
					createRosterTable();
					teamInfo.setSchool(Game.userSchool);
					addTeamsToMenuBar();
					menuItem.setEnabled(false);
				}
			}
		});
		leagueMenu.add(menuItem);
		leagueMenu.add(teamsSubMenu);
		
		/* build the team menu */
	}
}
