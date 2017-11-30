package db_data;

public class Room {
	private int _nRoomID;
	private String _strName;
	
	public int getID() {
		return _nRoomID;
	}
	
	public String getName() {
		return _strName;
	}
	
	public void setID(int nID) {
		this._nRoomID = nID;
	}
	
	public void setName(String strName) {
		this._strName = strName;
	}
	
	@Override
	public String toString() {
		return this._strName;
	}
}
