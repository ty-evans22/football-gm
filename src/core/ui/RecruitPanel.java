package core.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import core.Game;
import core.common.Offer;
import core.common.Player;

@SuppressWarnings("serial")
public class RecruitPanel extends JPanel {

	private JButton addOffer;
	private Player player;
	private JTable offerTable;
	private JScrollPane offerScrollPane;
	
	public RecruitPanel(Player p) {
		player = p;
		
		setLayout(new BorderLayout());
		
		offerTable = new JTable();
		offerScrollPane = new JScrollPane(offerTable);
		offerTable.setFillsViewportHeight(true);
		
		add(offerScrollPane, BorderLayout.CENTER);
		
		addOffer = new JButton("Submit Offer");
		addOffer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.offers.add(new Offer(player, Game.userSchool, Game.currentDate));
				updateOfferTable();
			}
		});
		
		add(addOffer, BorderLayout.NORTH);
	}
	
	public void updateOfferTable() {
		Object[][] data = new Object[player.offers.size()][2];
		for (int i = 0; i < player.offers.size(); i++) {
			data[i][0] = player.offers.get(i).getCollege();
			data[i][1] = player.offers.get(i).getDateOffered();
		}
		
		OfferTableModel otm = new OfferTableModel(data);
		offerTable.setModel(otm);
	}
}
