package core.ui;

import java.awt.*;
import javax.swing.*;
import core.Game;
import core.common.*;

@SuppressWarnings("serial")
public class SchedulePanel extends JPanel {
	
	// FINAL FIELDS
	private final String NO_EVENT_STRING = "No Event Today";
	
	// PRIVATE FIELDS
	private JLabel title = new JLabel("Schedule");
	private JPanel day1;
	private JPanel day2;
	private JPanel day3;
	private JPanel day4;
	private JPanel day5;
	private JPanel day6;
	private JPanel day7;
	private JLabel day1Label;
	private JLabel day2Label;
	private JLabel day3Label;
	private JLabel day4Label;
	private JLabel day5Label;
	private JLabel day6Label;
	private JLabel day7Label;
	private JLabel day1Event;
	private JLabel day2Event;
	private JLabel day3Event;
	private JLabel day4Event;
	private JLabel day5Event;
	private JLabel day6Event;
	private JLabel day7Event;
	
	// CONSTRUCTOR
	public SchedulePanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		title.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(title, gbc);
		
		title.setHorizontalAlignment(JLabel.CENTER);
		
		/* initialize panels */
		day1 = new JPanel();
		day2 = new JPanel();
		day3 = new JPanel();
		day4 = new JPanel();
		day5 = new JPanel();
		day6 = new JPanel();
		day7 = new JPanel();
		
		/* initialize panel labels */
		day1Label = new JLabel(Game.currentDate.toString());
		day2Label = new JLabel(Date.getNextDay().toString());
		day3Label = new JLabel(Date.getNextDay(2).toString());
		day4Label = new JLabel(Date.getNextDay(3).toString());
		day5Label = new JLabel(Date.getNextDay(4).toString());
		day6Label = new JLabel(Date.getNextDay(5).toString());
		day7Label = new JLabel(Date.getNextDay(6).toString());
		day1Event = new JLabel(NO_EVENT_STRING);
		day2Event = new JLabel(NO_EVENT_STRING);
		day3Event = new JLabel(NO_EVENT_STRING);
		day4Event = new JLabel(NO_EVENT_STRING);
		day5Event = new JLabel(NO_EVENT_STRING);
		day6Event = new JLabel(NO_EVENT_STRING);
		day7Event = new JLabel(NO_EVENT_STRING);
		
		/* set panel settings */
		day1.setBackground(Color.MAGENTA);
		day2.setBackground(Color.MAGENTA);
		day3.setBackground(Color.MAGENTA);
		day4.setBackground(Color.MAGENTA);
		day5.setBackground(Color.MAGENTA);
		day6.setBackground(Color.MAGENTA);
		day7.setBackground(Color.MAGENTA);
		day1.setLayout(new BoxLayout(day1, BoxLayout.Y_AXIS));
		day2.setLayout(new BoxLayout(day2, BoxLayout.Y_AXIS));
		day3.setLayout(new BoxLayout(day3, BoxLayout.Y_AXIS));
		day4.setLayout(new BoxLayout(day4, BoxLayout.Y_AXIS));
		day5.setLayout(new BoxLayout(day5, BoxLayout.Y_AXIS));
		day6.setLayout(new BoxLayout(day6, BoxLayout.Y_AXIS));
		day7.setLayout(new BoxLayout(day7, BoxLayout.Y_AXIS));
		
		/* add labels to panels */
		day1.add(day1Label);
		day2.add(day2Label);
		day3.add(day3Label);
		day4.add(day4Label);
		day5.add(day5Label);
		day6.add(day6Label);
		day7.add(day7Label);	
		day1.add(day1Event);
		day2.add(day2Event);
		day3.add(day3Event);
		day4.add(day4Event);
		day5.add(day5Event);
		day6.add(day6Event);
		day7.add(day7Event);
		
		/* add panels to schedule */
		gbc.gridy = 1;
		gbc.weightx = 0.5;
		// up, left, down, right
		gbc.insets = new Insets(5, 15, 5, 15);
		add(day1, gbc);
		gbc.gridx = GridBagConstraints.RELATIVE;
		add(day2, gbc);
		add(day3, gbc);
		add(day4, gbc);
		add(day5, gbc);
		add(day6, gbc);
		add(day7, gbc);
	}
	
	// PUBLIC METHODS
	
	public void nextDay() {
		day1Label.setText(Game.currentDate.toString());
		day2Label.setText(Date.getNextDay().toString());
		day3Label.setText(Date.getNextDay(2).toString());
		day4Label.setText(Date.getNextDay(3).toString());
		day5Label.setText(Date.getNextDay(4).toString());
		day6Label.setText(Date.getNextDay(5).toString());
		day7Label.setText(Date.getNextDay(6).toString());
		
		College s = Game.userSchool;
		
		for (int i = 1; i <= 7; i++) {
			Date d = null;
			boolean isEvent = false;
			switch(i) {
				case 1:
					d = Game.currentDate;
					for (int j = 0; j < s.schedule.length; j++) {
						if (s.schedule[j] != null && s.schedule[j].getDate().equals(d)) {
							day1Event.setText(s.schedule[j].toString());
							isEvent = true;
							break;
						}
					}
					for (int j = 0; j < Game.league.calendar.getEvents(d).size(); j++) {
						if (Game.league.calendar.getEvents(d).get(j).isUniversal()) {
							if (isEvent) {
								day1Event.setText(day1Event.getText() + "\n" + Game.league.calendar.getEvents(d).get(j));
							} else {
								day1Event.setText(Game.league.calendar.getEvents(d).get(j).toString());
								isEvent = true;
							}
						}
					}
					if (!isEvent) {
						day1Event.setText(NO_EVENT_STRING);
					}
					break;
				case 2:
					d = Date.getNextDay();
					for (int j = 0; j < s.schedule.length; j++) {
						if (s.schedule[j] != null && s.schedule[j].getDate().equals(d)) {
							day2Event.setText(s.schedule[j].toString());
							isEvent = true;
							break;
						}
					}
					for (int j = 0; j < Game.league.calendar.getEvents(d).size(); j++) {
						if (Game.league.calendar.getEvents(d).get(j).isUniversal()) {
							if (isEvent) {
								day2Event.setText(day2Event.getText() + "\n" + Game.league.calendar.getEvents(d).get(j));
							} else {
								day2Event.setText(Game.league.calendar.getEvents(d).get(j).toString());
								isEvent = true;
							}
						}
					}
					if (!isEvent) {
						day2Event.setText(NO_EVENT_STRING);
					}
					break;
				case 3:
					d = Date.getNextDay(2);
					for (int j = 0; j < s.schedule.length; j++) {
						if (s.schedule[j] != null && s.schedule[j].getDate().equals(d)) {
							day3Event.setText(s.schedule[j].toString());
							isEvent = true;
							break;
						}
					}
					for (int j = 0; j < Game.league.calendar.getEvents(d).size(); j++) {
						if (Game.league.calendar.getEvents(d).get(j).isUniversal()) {
							if (isEvent) {
								day3Event.setText(day3Event.getText() + "\n" + Game.league.calendar.getEvents(d).get(j));
							} else {
								day3Event.setText(Game.league.calendar.getEvents(d).get(j).toString());
								isEvent = true;
							}
						}
					}
					if (!isEvent) {
						day3Event.setText(NO_EVENT_STRING);
					}
					break;
				case 4:
					d = Date.getNextDay(3);
					for (int j = 0; j < s.schedule.length; j++) {
						if (s.schedule[j] != null && s.schedule[j].getDate().equals(d)) {
							day4Event.setText(s.schedule[j].toString());
							isEvent = true;
							break;
						}
					}
					for (int j = 0; j < Game.league.calendar.getEvents(d).size(); j++) {
						if (Game.league.calendar.getEvents(d).get(j).isUniversal()) {
							if (isEvent) {
								day4Event.setText(day4Event.getText() + "\n" + Game.league.calendar.getEvents(d).get(j));
							} else {
								day4Event.setText(Game.league.calendar.getEvents(d).get(j).toString());
								isEvent = true;
							}
						}
					}
					if (!isEvent) {
						day4Event.setText(NO_EVENT_STRING);
					}
					break;
				case 5:
					d = Date.getNextDay(4);
					for (int j = 0; j < s.schedule.length; j++) {
						if (s.schedule[j] != null && s.schedule[j].getDate().equals(d)) {
							day5Event.setText(s.schedule[j].toString());
							isEvent = true;
							break;
						}
					}
					for (int j = 0; j < Game.league.calendar.getEvents(d).size(); j++) {
						if (Game.league.calendar.getEvents(d).get(j).isUniversal()) {
							if (isEvent) {
								day5Event.setText(day5Event.getText() + "\n" + Game.league.calendar.getEvents(d).get(j));
							} else {
								day5Event.setText(Game.league.calendar.getEvents(d).get(j).toString());
								isEvent = true;
							}
						}
					}
					if (!isEvent) {
						day5Event.setText(NO_EVENT_STRING);
					}
					break;
				case 6:
					d = Date.getNextDay(5);
					for (int j = 0; j < s.schedule.length; j++) {
						if (s.schedule[j] != null && s.schedule[j].getDate().equals(d)) {
							day6Event.setText(s.schedule[j].toString());
							isEvent = true;
							break;
						}
					}
					for (int j = 0; j < Game.league.calendar.getEvents(d).size(); j++) {
						if (Game.league.calendar.getEvents(d).get(j).isUniversal()) {
							if (isEvent) {
								day6Event.setText(day6Event.getText() + "\n" + Game.league.calendar.getEvents(d).get(j));
							} else {
								day6Event.setText(Game.league.calendar.getEvents(d).get(j).toString());
								isEvent = true;
							}
						}
					}
					if (!isEvent) {
						day6Event.setText(NO_EVENT_STRING);
					}
					break;
				case 7:
					d = Date.getNextDay(6);
					for (int j = 0; j < s.schedule.length; j++) {
						if (s.schedule[j] != null && s.schedule[j].getDate().equals(d)) {
							day7Event.setText(s.schedule[j].toString());
							isEvent = true;
							break;
						}
					}
					for (int j = 0; j < Game.league.calendar.getEvents(d).size(); j++) {
						if (Game.league.calendar.getEvents(d).get(j).isUniversal()) {
							if (isEvent) {
								day7Event.setText(day7Event.getText() + "\n" + Game.league.calendar.getEvents(d).get(j));
							} else {
								day7Event.setText(Game.league.calendar.getEvents(d).get(j).toString());
								isEvent = true;
							}
						}
					}
					if (!isEvent) {
						day7Event.setText(NO_EVENT_STRING);
					}
					break;
			}
		}
	}
}
