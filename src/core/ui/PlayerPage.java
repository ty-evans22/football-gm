package core.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import core.common.Player;

@SuppressWarnings("serial")
public class PlayerPage extends JPanel {
	
	private boolean recruit = false;
	private Player player;
	private PlayerInfoPanel playerInfo;
	
	public PlayerPage(Player p) {
		player = p;
		
		setLayout(new BorderLayout());
		
		playerInfo = new PlayerInfoPanel(player);
		
		add(playerInfo, BorderLayout.NORTH);
		
		if (p.getCollege() == null) {
			recruit = true;
		}
		
		if (recruit) {
			RecruitPanel rp = new RecruitPanel(player);
			add(rp, BorderLayout.EAST);
		} else {
			
		}
	}
}
