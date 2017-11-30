package lab06;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import db_data.Room;
import db_data.User;
import db_data.Visit;
import db_data.VisitFactory;
import db_data.VisitingHour;

public class VisitDataTableAdapter extends DefaultTableModel implements ICustomDataTableAdapter {	
	private static final long serialVersionUID = 1L;
	private ArrayList<Visit> _listVisit = null;	
	
	public VisitDataTableAdapter(ArrayList<Visit> listVisit) {
		this._listVisit = listVisit != null ? listVisit : new ArrayList<>();
	}	

	@Override
	public int getRowCount() {		
		return this._listVisit != null ? this._listVisit.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}
	
	@Override
	public String getColumnName(int nColumnIndex) {		
		switch(nColumnIndex) {
			case 0: return "Pokój";
			case 1: return "Godzina";
			case 2: return "Pacjent";
			case 3: return "Lekarz";
			case 4: return "Zakoñczona";
		}		
		return "";
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case 0: return Room.class;
			case 1: return VisitingHour.class;
			case 2: return User.class;
			case 3: return User.class;
			case 4: return Boolean.class;
		}	
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	@Override
	public Object getValueAt(int nRowIndex, int nColumnIndex) {
		Visit objVisit = _listVisit.get(nRowIndex);		
		switch(nColumnIndex) {
			case 0: return objVisit.getRoom();
			case 1: return objVisit.getVisitingHour();
			case 2: return objVisit.getPatient();
			case 3: return objVisit.getDoctor();
			case 4: return objVisit.getIsFinished();
		}		
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {		
		Visit objVisit = _listVisit.get(rowIndex);
		
		if(columnIndex == 0) {
			Room objRoom = (Room) aValue;
			objVisit.setRoom(objRoom);
		}
		else if(columnIndex == 1) {
			VisitingHour objVisitingHour = (VisitingHour) aValue;
			objVisit.setVisitingHour(objVisitingHour);
		}
		else if(columnIndex == 2) {
			User objUserPatient = (User) aValue;
			objVisit.setPatient(objUserPatient);
		}
		else if(columnIndex == 3) {
			User objUserDcotor = (User) aValue;
			objVisit.setDoctor(objUserDcotor);
		}	
		else if(columnIndex == 4) {
			objVisit.setIsFinished((boolean) aValue);
		}
	}
	@Override
	public void addTableModelListener(TableModelListener l) { }
	@Override
	public void removeTableModelListener(TableModelListener l) { }	

	@Override
	public void addNewRow() {		
		Visit objVisit = new Visit();
		objVisit.setID(-1);
		
		Room objRoom = new Room() {{ setID(-1); setName("---"); }};
		VisitingHour objVisitingHour = new VisitingHour() {{ setID(-1); }};
		User objUserPatient = new User() {{ setID(-1); setFirstName(""); setLastName("");  }};
		User objUserDoctor = new User() {{ setID(-1); setFirstName(""); setLastName(""); }};
		
		objVisit.setRoom(objRoom);
		objVisit.setVisitingHour(objVisitingHour);
		objVisit.setPatient(objUserPatient);
		objVisit.setDoctor(objUserDoctor);
		objVisit.setIsFinished(false);
		
		_listVisit.add(objVisit);		
	}
	
	@Override
	public void saveData() {
		for(int i = 0; i < _listVisit.size(); i++) {
			Visit objVisit = _listVisit.get(i);
			if(_listVisit.get(i).getID() == -1) {				
				objVisit = new VisitFactory().create(objVisit);
			}
			else {
				new VisitFactory().update(objVisit);
			}
		}		
	}
	
	@Override
	public void deleteRows(int[] arrRowsIndex) {
		ArrayList<Visit> listVisitToDelete = new ArrayList<>();
		for(int i = 0; i < arrRowsIndex.length; i++) {
			listVisitToDelete.add(_listVisit.get(arrRowsIndex[i]));
			new VisitFactory().delete(_listVisit.get(arrRowsIndex[i]).getID());			
		}		
		_listVisit.removeAll(listVisitToDelete);
	}
}















