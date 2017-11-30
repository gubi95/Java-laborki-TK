package lab06;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import db_data.Room;
import db_data.RoomFactory;
import db_data.User;
import db_data.UserFactory;

public class RoomDataTableAdapter extends DefaultTableModel implements ICustomDataTableAdapter {	
	private static final long serialVersionUID = 1L;
	private ArrayList<Room> _listRoom = null;	
	
	public RoomDataTableAdapter(ArrayList<Room> listRoom) {
		this._listRoom = listRoom != null ? listRoom : new ArrayList<>();
	}	

	@Override
	public int getRowCount() {		
		return this._listRoom != null ? this._listRoom.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}
	
	@Override
	public String getColumnName(int nColumnIndex) {		
		switch(nColumnIndex) {
			case 0: return "Nazwa";			
		}		
		return "";
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case 0: return String.class;			
		}	
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	@Override
	public Object getValueAt(int nRowIndex, int nColumnIndex) {
		Room objRoom = _listRoom.get(nRowIndex);		
		switch(nColumnIndex) {
			case 0: return objRoom.getName();			
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {		
		Room objRoom = _listRoom.get(rowIndex);
		switch(columnIndex) {
			case 0: objRoom.setName((String) aValue); 	return;			
		}		
	}
	@Override
	public void addTableModelListener(TableModelListener l) { }
	@Override
	public void removeTableModelListener(TableModelListener l) { }

	@Override
	public void addNewRow() {		
		Room objRoom = new Room();
		objRoom.setID(-1);
		objRoom.setName("");
		_listRoom.add(objRoom);		
	}
	
	@Override
	public void saveData() {
		for(int i = 0; i < _listRoom.size(); i++) {
			Room objRoom = _listRoom.get(i);
			if(_listRoom.get(i).getID() == -1) {				
				objRoom = new RoomFactory().create(objRoom);
			}
			else {
				new RoomFactory().update(objRoom);
			}
		}		
	}
	
	@Override
	public void deleteRows(int[] arrRowsIndex) {
		ArrayList<Room> listRoomToDelete = new ArrayList<>();
		for(int i = 0; i < arrRowsIndex.length; i++) {
			listRoomToDelete.add(_listRoom.get(arrRowsIndex[i]));
			new RoomFactory().delete(_listRoom.get(arrRowsIndex[i]).getID());			
		}		
		_listRoom.removeAll(listRoomToDelete);
	}
}















