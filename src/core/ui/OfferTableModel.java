package core.ui;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class OfferTableModel extends AbstractTableModel {

	private String[] columnNames = {
		"College",
		"Date Offered"
	};
	
	private Object[][] data;
	
	public OfferTableModel(Object[][] data) {
		this.data = data;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

}
