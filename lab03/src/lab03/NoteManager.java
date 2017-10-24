package lab03;

import java.util.ArrayList;
import java.util.Random;

public class NoteManager {		
	private ArrayList<Note> _listNote = null;
	private IDateRangeValdiator _objIDateRangeValdiator = null;
	
	public NoteManager() {
		this._listNote = new ArrayList<>();
	}
	
	public void addNote(Note objNote) {		
		if(objNote != null /*&& _objIDateRangeValdiator != null && _objIDateRangeValdiator.isValid(objNote.getDate())*/) {
			objNote.setNoteID(this.getNextID());
			_listNote.add(objNote);
		}
	}
	
	public void removeNote(int nNoteID) {
		try {		
			int i = 0;
			for(Note objNote : _listNote) {
				if(objNote.getNoteID() == nNoteID) {
					_listNote.remove(i);
					return;
				}
				i++;
			}
		}
		catch(Exception ex) { }
	}
	
	public ArrayList<Note> getNotes() {
		return _listNote != null ? _listNote : new ArrayList<>();
	}
	
	public void setDateRandeValidator(IDateRangeValdiator objIDateRangeValdiator) {
		this._objIDateRangeValdiator = objIDateRangeValdiator;
	}
	
	private int getNextID() {
		int nNextID = -1;
		while(true) {
			nNextID = new Random().nextInt();			
			boolean bIsUnique = true;
			for (Note objNote : _listNote) {
				if(objNote.getNoteID() == nNextID) {
					bIsUnique = false;
				}
			}
			
			if(bIsUnique) {
				return nNextID;
			}
		}
	}
}
