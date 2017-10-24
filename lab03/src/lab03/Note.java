package lab03;

import java.util.Calendar;

public class Note {	
	private int _nNoteID = -1;
	private Calendar _objCalendar = null;
	private String _strMessage = null;
	
	public Note(Calendar objCalendar, String strMessage) {		
		this._objCalendar = objCalendar;
		this._strMessage = strMessage;
	}
	
	public int getNoteID() {
		return this._nNoteID;
	}
	
	public Calendar getDate() {
		return this._objCalendar;
	}
	
	public String getMessage() {
		return this._strMessage;
	}
	
	public void setNoteID(int nID) {
		this._nNoteID = nID;
	}
	
	public void setDate(Calendar objCalendar) {
		this._objCalendar = objCalendar;
	}
	
	public void setMessage(String strMessage) {
		this._strMessage = strMessage;
	}
}
