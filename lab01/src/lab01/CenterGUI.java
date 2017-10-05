package lab01;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class CenterGUI extends JFrame {	
	private static final long serialVersionUID = 1L;

	private static class JTableButtonRenderer implements TableCellRenderer {        
        @Override 
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            return button;  
        }
    }
	
	private static class JTableButtonMouseListener extends MouseAdapter {
        private final JTable table;

        public JTableButtonMouseListener(JTable table) {
            this.table = table;
        }

        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
            int row    = e.getY()/table.getRowHeight(); //get the row of the button
                    /*Checking the row or column is valid or not*/
            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    /*perform a click event*/
                    ((JButton)value).doClick();
                }
            }
        }
    }
	
	
	private ArrayList<Object> _listGates = null;
	
	private JTable _objJTableGates = new JTable(new AbstractTableModel () {		
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getRowCount() {			
			return _listGates != null ? _listGates.size() : 0;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public String getColumnName(int columnIndex) {			
			switch(columnIndex) {
				case 0: return "ID bramki";				
				case 1: return "Iloœæ wejœæ";
				case 2: return "Iloœæ wyjœæ";
				case 3: return "Wy³¹cz";
				default: return "";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if(columnIndex == 3) {
				return JButton.class;
			}			
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
						case 3: final JButton button = new JButton("Wy³¹cz");
                        button.addActionListener(new ActionListener() {							
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									objIGate.shutdown();
								} catch (RemoteException e1) {
									e1.printStackTrace();
								}					
							}
						});
                        return button;
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
		this.setTitle("Centrala");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel objJPanel = new JPanel(new BorderLayout());
		_objJTableGates.getColumn("Wy³¹cz").setCellRenderer(new JTableButtonRenderer());
		_objJTableGates.addMouseListener(new JTableButtonMouseListener(_objJTableGates));
		
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
		
		if(_listGates != null) {
			for(int i = 0; i < _listGates.size(); i++) {
				IGate a = (IGate) _listGates.get(i);
				System.out.println("index:" + i);
				try {
					System.out.println(a.getID() + "|" + a.getEntryCount() + "|" + a.getExitCount());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		
		((AbstractTableModel)_objJTableGates.getModel()).fireTableChanged(null);
		_objJTableGates.revalidate();
		_objJTableGates.repaint();
	}			
}
