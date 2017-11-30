package lab06;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import db_data.Room;
import db_data.RoomFactory;
import db_data.User;
import db_data.UserFactory;
import db_data.VisitingHour;
import db_data.VisitingHourFactory;

public class ModalDataTable extends JFrame {	
	private DefaultTableModel _objDefaultTableModel = null;
	private JTable _objJTable = null;
	
	public ModalDataTable(DefaultTableModel objDefaultTableModel, String strTitle) {
		this._objDefaultTableModel = objDefaultTableModel;		
		this.setupGUI(strTitle);
	}
	
	private void setupGUI(String strTitle) {
		this.setSize(600, 800);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle(strTitle);
		
		_objJTable = new JTable(_objDefaultTableModel);
		
		if(_objDefaultTableModel instanceof VisitDataTableAdapter) {			
			ArrayList<Room> listRoom = new RoomFactory().getAll();
			ArrayList<VisitingHour> listVisitingHour = new VisitingHourFactory().getAll();
			ArrayList<User> listUser = new UserFactory().getAll();
			
			ArrayList<User> listUserPatients = new ArrayList<>();
			ArrayList<User> listUserDoctors = new ArrayList<>();
			
			for(User objUser : listUser) {
				if(objUser.getIsPatient()) {
					listUserPatients.add(objUser);
				}
				else {
					listUserDoctors.add(objUser);
				}
			}
			
			JComboBox<Room> objJComboBoxRoom = new JComboBox<>(listRoom.toArray(new Room[listRoom.size()]));
			JComboBox<VisitingHour> objJComboBoxVisitingHours = new JComboBox<>(listVisitingHour.toArray(new VisitingHour[listVisitingHour.size()]));			
			JComboBox<User> objJComboBoxPatients = new JComboBox<>(listUserPatients.toArray(new User[listUserPatients.size()]));
			JComboBox<User> objJComboBoxDoctors = new JComboBox<>(listUserDoctors.toArray(new User[listUserDoctors.size()]));			
			
			_objJTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(objJComboBoxRoom));
			_objJTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(objJComboBoxVisitingHours));
			_objJTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(objJComboBoxPatients));
			_objJTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(objJComboBoxDoctors));
		}
		
		this.add(new JScrollPane(_objJTable), BorderLayout.CENTER);
		
		JButton btnNewRow = new JButton("Nowy wiersz");		
		btnNewRow.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				((ICustomDataTableAdapter)_objDefaultTableModel).addNewRow();
				_objJTable.revalidate();
				_objJTable.repaint();
			}
		});		
		
		JButton btnSaveData = new JButton("Zapisz");
		btnSaveData.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				((ICustomDataTableAdapter)_objDefaultTableModel).saveData();				
			}
		});	
		
		JButton btnDeleteRows = new JButton("Usuñ");
		btnDeleteRows.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] arrIndexes = _objJTable.getSelectedRows();
				
				if(arrIndexes == null) {
					return;
				}
				
				((ICustomDataTableAdapter)_objDefaultTableModel).deleteRows(arrIndexes);
				_objJTable.clearSelection();
				_objJTable.revalidate();
				_objJTable.repaint();
			}
		});	
		
		JPanel objJPanelSouth = new JPanel(new GridLayout(1, 3));
		objJPanelSouth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		objJPanelSouth.add(btnNewRow);
		objJPanelSouth.add(btnDeleteRows);
		objJPanelSouth.add(btnSaveData);		
		
		this.add(objJPanelSouth, BorderLayout.SOUTH);	
		this.show();
	}

}
