package lab01;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MonitorGUI extends JFrame {
	private ArrayList<Object> _listGates = null;
	
	private JTable _objJTableGates = new JTable(new AbstractTableModel () {		
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getRowCount() {			
			return _listGates != null ? _listGates.size() : 0;
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int columnIndex) {			
			switch(columnIndex) {
				case 0: return "ID bramki";				
				case 1: return "Iloœæ wejœæ";
				case 2: return "Iloœæ wyjœæ";
				default: return "";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if(_listGates != null) {
				IGate objIGate = (IGate) _listGates.get(rowIndex);
				try {
				switch(columnIndex) {
					case 0: return String.valueOf(objIGate.getID());					
					case 1: return String.valueOf(objIGate.getEntryCount());
					case 2: return String.valueOf(objIGate.getExitCount());
				}
				}
				catch(Exception e) {
					e.printStackTrace();
				}				
			}			
			return "";
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) { }
		@Override
		public void addTableModelListener(TableModelListener l) { }
		@Override
		public void removeTableModelListener(TableModelListener l) { }
	});
	
	public void setupGUI() {
		this.setTitle("Monitor");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel objJPanel = new JPanel(new BorderLayout());
		_objJTableGates.setBackground(Color.GREEN);
		_objJTableGates.getTableHeader().setReorderingAllowed(false);
		_objJTableGates.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		_objJTableGates.getColumnModel().getColumn(0).setPreferredWidth(113);		
		
		objJPanel.add(new JScrollPane(_objJTableGates), BorderLayout.CENTER);		
		this.add(objJPanel, BorderLayout.CENTER);
		this.show();
	}

	public void refresh(ArrayList<Object> listGates) {
		_listGates = listGates;		
		((AbstractTableModel)_objJTableGates.getModel()).fireTableChanged(null);
		_objJTableGates.revalidate();
		_objJTableGates.repaint();
	}			
}
