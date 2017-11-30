package lab06;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import db_data.User;
import db_data.UserFactory;

public class UsersDataTableAdapter extends DefaultTableModel implements ICustomDataTableAdapter {	
	private static final long serialVersionUID = 1L;
	private ArrayList<User> _listUser = null;	
	
	public UsersDataTableAdapter(ArrayList<User> listUser) {
		this._listUser = listUser != null ? listUser : new ArrayList<>();
	}	

	@Override
	public int getRowCount() {		
		return this._listUser != null ? this._listUser.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
	
	@Override
	public String getColumnName(int nColumnIndex) {		
		switch(nColumnIndex) {
			case 0: return "Imiê";
			case 1: return "Nazwisko";
			case 2: return "PESEL";
			case 3: return "Pacjent";
		}		
		return "";
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case 0: 
			case 1: 
			case 2: return String.class;
			case 3: return Boolean.class;
		}	
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	@Override
	public Object getValueAt(int nRowIndex, int nColumnIndex) {
		User objUser = _listUser.get(nRowIndex);		
		switch(nColumnIndex) {
			case 0: return objUser.getFirstName();
			case 1: return objUser.getLastName();
			case 2: return objUser.getPESEL();
			case 3: return objUser.getIsPatient();
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {		
		User objUser = _listUser.get(rowIndex);
		switch(columnIndex) {
			case 0: objUser.setFirstName((String) aValue); 	return;
			case 1: objUser.setLastName((String) aValue); 	return;
			case 2: objUser.setPESEL((String) aValue); 		return;
			case 3: objUser.setIsPatient((Boolean) aValue);	return;
		}		
	}
	@Override
	public void addTableModelListener(TableModelListener l) { }
	@Override
	public void removeTableModelListener(TableModelListener l) { }

	@Override
	public void addNewRow() {		
		User objUser = new User();
		objUser.setID(-1);
		objUser.setFirstName("");
		objUser.setLastName("");
		objUser.setPESEL("");
		objUser.setIsPatient(true);
		_listUser.add(objUser);		
	}
	
	@Override
	public void saveData() {
		for(int i = 0; i < _listUser.size(); i++) {
			User objUser = _listUser.get(i);
			if(_listUser.get(i).getID() == -1) {				
				objUser = new UserFactory().create(objUser);
			}
			else {
				new UserFactory().update(objUser);
			}
		}		
	}
	
	@Override
	public void deleteRows(int[] arrRowsIndex) {
		ArrayList<User> listUserToDelete = new ArrayList<>();
		for(int i = 0; i < arrRowsIndex.length; i++) {
			listUserToDelete.add(_listUser.get(arrRowsIndex[i]));
			new UserFactory().delete(_listUser.get(arrRowsIndex[i]).getID());			
		}		
		_listUser.removeAll(listUserToDelete);
	}
}















