package core.ui;

import java.awt.*;
import javax.swing.*;
import core.common.*;

@SuppressWarnings("serial")
public class TeamPage extends JPanel {
	
	// PRIVATE FIELDS
	private College school;
	private TeamInfoPanel teamInfo;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel centralPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JTable roster;
	private JScrollPane scrollPane;
	
	// CONSTRUCTOR
	public TeamPage(College s) {
		school = s;
		
		/* set TeamPage settings */
		setLayout(new BorderLayout());
		
		/* top level components */
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		
		leftPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());
		
		/* next level */
		teamInfo = new TeamInfoPanel(school);
		centralPanel = new JPanel();
		roster = new JTable();
		scrollPane = new JScrollPane(roster);
		
		centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
		roster.setFillsViewportHeight(true);
		
		leftPanel.add(teamInfo, BorderLayout.NORTH);
		leftPanel.add(centralPanel, BorderLayout.CENTER);
		rightPanel.add(scrollPane, BorderLayout.CENTER);
		
		/* next level */
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
	}
	
	// PUBLIC METHODS
	public void createRosterTable() {
		Object[][] data = new Object[school.roster.size()][4];
		for (int i = 0; i < school.roster.size(); i++) {
			data[i][0] = school.roster.get(i).getFirstName();
			data[i][1] = school.roster.get(i).getLastName();
			data[i][2] = school.roster.get(i).getPosition();
			data[i][3] = school.roster.get(i).getBirthday();
		}
		
		RosterTableModel rtm = new RosterTableModel(data);
		roster.setModel(rtm);
	}
}
