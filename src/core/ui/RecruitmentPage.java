package core.ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import core.Game;
import core.common.Player;

@SuppressWarnings("serial")
public class RecruitmentPage extends JPanel {
	
	private JTable recruitTable;
	private JScrollPane sp;
	private JPopupMenu tablePopupMenu;
	
	public RecruitmentPage(JTabbedPane mainScreen) {
		recruitTable = new JTable();
		tablePopupMenu = new JPopupMenu();
		sp = new JScrollPane(recruitTable);
		recruitTable.setFillsViewportHeight(true);
		this.add(sp, BorderLayout.CENTER);
		
		tablePopupMenu.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						int rowAtPoint = recruitTable.rowAtPoint(SwingUtilities.convertPoint(tablePopupMenu, new Point(0, 0), recruitTable));
						if (rowAtPoint > -1) {
							recruitTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);
						}
					}
				});
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenuItem goToPlayerPage = new JMenuItem("Go to Player Page");
		goToPlayerPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Player r = Game.league.recruits.get(recruitTable.getSelectedRow());
				PlayerPage p = new PlayerPage(r);
				mainScreen.add(r.toString(), p);
			}
		});
		
		tablePopupMenu.add(goToPlayerPage);
		
		recruitTable.setComponentPopupMenu(tablePopupMenu);
		
		updateRecruitTable();
	}
	
	public void updateRecruitTable() {
		if (Game.league != null) {
			Object[][] data = new Object[Game.league.recruits.size()][4];
			for (int i = 0; i < Game.league.recruits.size(); i++) {
				data[i][0] = Game.league.recruits.get(i).getFirstName();
				data[i][1] = Game.league.recruits.get(i).getLastName();
				data[i][2] = Game.league.recruits.get(i).getPosition();
				data[i][3] = Game.league.recruits.get(i).getBirthday();
			}
			
			RecruitTableModel rtm = new RecruitTableModel(data);
			recruitTable.setModel(rtm);
		}
	}
}
