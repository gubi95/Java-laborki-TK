package lab03;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class NotesTableAdapter extends DefaultTableModel {	
	private static final long serialVersionUID = 1L;
	private ArrayList<Note> _listNote = null;
	
	public NotesTableAdapter(ArrayList<Note> listNote) {
		this._listNote = listNote != null ? new ArrayList<>(listNote) : new ArrayList<>();
	}	

	@Override
	public int getRowCount() {		
		return this._listNote != null ? this._listNote.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public String getColumnName(int nColumnIndex) {
		String strColumnName = "";
		
		switch(nColumnIndex) {
			case 0: strColumnName = "Data"; 	break;
			case 1: strColumnName = "Treœæ"; 	break;
		}
		
		return strColumnName;
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
	public Object getValueAt(int nRowIndex, int nColumnIndex) {
		Note objNote = this._listNote.get(nRowIndex);		
		if(nColumnIndex == 0) {			
			return new SimpleDateFormat("dd.MM.yyyy").format(objNote.getDate().getTime());
		}
		else {
			return objNote.getMessage();
		}		
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) { }
	@Override
	public void addTableModelListener(TableModelListener l) { }
	@Override
	public void removeTableModelListener(TableModelListener l) { }
}
