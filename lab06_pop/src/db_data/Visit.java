package db_data;

public class Visit {
	private int _nVisitID;
	private Room _objRoom;
	private VisitingHour _objVisitingHour;
	private User _objUserPatient;
	private User _objUserDoctor;
	private boolean _bIsFinished;
	
	public int getID() {
		return this._nVisitID;
	}
	
	public Room getRoom() {
		return this._objRoom;
	}
	
	public VisitingHour getVisitingHour() {
		return this._objVisitingHour;
	}
	
	public User getPatient() {
		return this._objUserPatient;
	}
	
	public User getDoctor() {
		return this._objUserDoctor;
	}
	
	public boolean getIsFinished() {
		return this._bIsFinished;
	}
	
	public void setID(int nID) {
		this._nVisitID = nID;
	}
	
	public void setRoom(Room objRoom) {
		this._objRoom = objRoom;
	}
	
	public void setVisitingHour(VisitingHour objVisitingHour) {
		this._objVisitingHour = objVisitingHour;
	}
	
	public void setPatient(User objUser) {
		this._objUserPatient = objUser;
	}
	
	public void setDoctor(User objUser) {
		this._objUserDoctor = objUser;
	}
	
	public void setIsFinished(boolean bIsFinished) {
		this._bIsFinished = bIsFinished;
	}
}
