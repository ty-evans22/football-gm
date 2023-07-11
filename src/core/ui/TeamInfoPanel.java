package core.ui;

import java.awt.Color;
import javax.swing.*;
import core.common.College;

@SuppressWarnings("serial")
public class TeamInfoPanel extends JPanel {
	
	private JLabel schoolName;
	private JLabel teamRecord;
	private JSeparator separator;
	private College school = null;
	
	public TeamInfoPanel(College s) {
		school = s;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		/* initialize components */
		separator = new JSeparator();
		if (school != null) {
			schoolName = new JLabel(school.toString());
			teamRecord = new JLabel(school.getWins() + "-" + school.getLosses());
		} else {
			schoolName = new JLabel("No User Team Defined Yet");
			teamRecord = new JLabel("0-0");
		}
		
		add(schoolName);
		add(separator);
		add(teamRecord);
	}
	
	public void updateTeamName() {
		schoolName.setText(school.toString());
	}
	
	public void updateTeamRecord() {
		teamRecord.setText(school.getWins() + "-" + school.getLosses());
	}
	
	public void setSchool(College s) {
		school = s;
		updateTeamName();
		updateTeamRecord();
	}
}
