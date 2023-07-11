package core.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.common.Player;

@SuppressWarnings("serial")
public class PlayerInfoPanel extends JPanel {

	private JLabel playerName;
	private Player player = null;
	
	public PlayerInfoPanel(Player p) {
		player = p;
		
		/* initialize components */
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		if (player != null) {
			playerName = new JLabel(player.toString());
		}
		
		add(playerName);
	}
}
