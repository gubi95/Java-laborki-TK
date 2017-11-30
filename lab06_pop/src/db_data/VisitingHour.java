package db_data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VisitingHour {
	private int _nVisitingHourID;
	private Calendar _dtFromDateTime;
	private Calendar _dtToDateTime;
	
	public int getID() {
		return this._nVisitingHourID;
	}
	
	public Calendar getFromDateTime() {
		return this._dtFromDateTime;
	}
	
	public Calendar getToDateTime() {
		return this._dtToDateTime;
	}
	
	public void setID(int nID) {
		this._nVisitingHourID = nID;		
	}
	
	public void setFromDateTime(Calendar objCalendar) {
		this._dtFromDateTime = 	objCalendar;
	}
	
	public void setToDateTime(Calendar objCalendar) {
		this._dtToDateTime = objCalendar;		
	}
	
	@Override
	public String toString() {	
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(_dtFromDateTime.getTime()) + " - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(_dtToDateTime.getTime());
		}
		catch (Exception e) {
			return "---";
		}
	}
}
