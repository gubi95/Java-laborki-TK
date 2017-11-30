package db_data;

public class User {	
	private int _nID;
	private String _strFirstName;
	private String _strLastName;
	private String _strPESEL;
	private boolean _bIsPatient;

	public int getID() {
		return _nID;
	}
	
	public String getFirstName() {
		return _strFirstName;
	}
	
	public String getLastName() {
		return _strLastName;
	}
	
	public String getPESEL() {
		return _strPESEL;
	}
	
	public boolean getIsPatient() {
		return _bIsPatient;
	}
	
	public void setID(int nID) {
		this._nID = nID;
	}
	
	public void setFirstName(String strFirstName) {
		this._strFirstName = strFirstName;
	}
	
	public void setLastName(String strLastName) {
		this._strLastName = strLastName;
	}
	
	public void setPESEL(String strPESEL) {
		this._strPESEL = strPESEL;
	}
	
	public void setIsPatient(boolean bIsPatient) {
		this._bIsPatient = bIsPatient;
	}
	
	@Override
	public String toString() {
		return this._strFirstName + " " + _strLastName;
	}
}
