package lab06;

import java.lang.reflect.Array;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import db_data.Room;
import db_data.RoomFactory;
import db_data.User;
import db_data.UserFactory;
import db_data.VisitingHour;
import db_data.VisitingHourFactory;

public class VisitingHoursDataTableAdapter extends DefaultTableModel implements ICustomDataTableAdapter {	
	private static final long serialVersionUID = 1L;
	private ArrayList<VisitingHour> _listVisitingHour = null;	
	
	public VisitingHoursDataTableAdapter(ArrayList<VisitingHour> listVisitingHour) {
		this._listVisitingHour = listVisitingHour != null ? listVisitingHour : new ArrayList<>();
	}	

	@Override
	public int getRowCount() {		
		return this._listVisitingHour != null ? this._listVisitingHour.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public String getColumnName(int nColumnIndex) {		
		switch(nColumnIndex) {
			case 0: return "Data i godzina od";
			case 1: return "Data i godzina do";
		}		
		return "";
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case 0: return String.class;
			case 1: return String.class;	
		}	
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	@Override
	public Object getValueAt(int nRowIndex, int nColumnIndex) {
		VisitingHour objVisitingHour = _listVisitingHour.get(nRowIndex);		
		switch(nColumnIndex) {
			case 0: return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(objVisitingHour.getFromDateTime().getTime());
			case 1: return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(objVisitingHour.getToDateTime().getTime());
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {		
		VisitingHour objVisitingHour = _listVisitingHour.get(rowIndex);		
		switch(columnIndex) {
			case 0: 
				try {
					java.util.Date objDateFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) aValue);				
					Calendar objCalendarFrom = Calendar.getInstance();
					objCalendarFrom.setTime(objDateFrom);
					objVisitingHour.setFromDateTime(objCalendarFrom);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}				
				return;
			case 1: 
				try {
					java.util.Date objDateTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) aValue);				
					Calendar objCalendarTo = Calendar.getInstance();
					objCalendarTo.setTime(objDateTo);
					objVisitingHour.setToDateTime(objCalendarTo);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}	
				return;		
		}		
	}
	@Override
	public void addTableModelListener(TableModelListener l) { }
	@Override
	public void removeTableModelListener(TableModelListener l) { }

	@Override
	public void addNewRow() {		
		VisitingHour objVisitingHour = new VisitingHour();
		objVisitingHour.setID(-1);
		objVisitingHour.setFromDateTime(Calendar.getInstance());
		objVisitingHour.setToDateTime(Calendar.getInstance());
		_listVisitingHour.add(objVisitingHour);
	}
	
	@Override
	public void saveData() {
		for(int i = 0; i < _listVisitingHour.size(); i++) {
			VisitingHour objVisitingHour = _listVisitingHour.get(i);
			if(_listVisitingHour.get(i).getID() == -1) {				
				objVisitingHour = new VisitingHourFactory().create(objVisitingHour);
			}
			else {
				new VisitingHourFactory().update(objVisitingHour);
			}
		}		
	}
	
	@Override
	public void deleteRows(int[] arrRowsIndex) {
		ArrayList<VisitingHour> listVisitingHourToDelete = new ArrayList<>();
		for(int i = 0; i < arrRowsIndex.length; i++) {
			listVisitingHourToDelete.add(_listVisitingHour.get(arrRowsIndex[i]));
			new VisitingHourFactory().delete(_listVisitingHour.get(arrRowsIndex[i]).getID());			
		}		
		_listVisitingHour.removeAll(listVisitingHourToDelete);
	}
}















