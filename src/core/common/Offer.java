package core.common;

import core.Game;

public class Offer {
	
	private Player player;
	private College college;
	private Date dateOffered;
	
	public int offerID;
	
	public Offer(Player p, College c, Date d) {
		player = p;
		college = c;
		dateOffered = d;
		
		offerID = Game.db.addOffer(player, college);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public College getCollege() {
		return college;
	}
	
	public Date getDateOffered() {
		return dateOffered;
	}
}
