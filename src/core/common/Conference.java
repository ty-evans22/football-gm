package core.common;

import java.util.ArrayList;

import core.Game;

public class Conference {
	
	public String name;
	public int conferenceID;
	
	public ArrayList<College> members;
	
	public Conference(String name) {
		this.name = name;
		
		members = new ArrayList<College>();
		
		this.conferenceID = Game.db.addConference(this);
	}
	
	public void addMember(College c) {
		members.add(c);
	}
}
