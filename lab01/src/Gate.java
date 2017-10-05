import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class Gate implements IGate, Serializable {
	private int _nID;
	private int _nEntryCount;
	private int _nExitCount;
	private boolean _bIsRunning;	
	private ICenter _objICenter;
	
	@Override
	public int[] getStatistics() throws RemoteException { 
		return new int[] { this._nEntryCount, this._nExitCount };
	}

	@Override
	public boolean shutdown() throws RemoteException {
		this._bIsRunning = false;
		return true;
	}

	@Override
	public int getID() throws RemoteException {
		return this._nID;
	}
	
	public void setID(int nID) {
		this._nID = nID;
	}	
	
	public void incremenetEntryCount() {
		if(this._bIsRunning) {
			this._nEntryCount++;
		}
	}
	
	public int getEntryCount() {
		return this._nEntryCount;
	}
	
	public void incremenetExitCount() {
		if(this._bIsRunning) {
			this._nExitCount++;
		}
	}
	
	public int getExitCount() {
		return this._nExitCount;
	}
	
	public void setIsRunning(boolean bIsRunning) {
		this._bIsRunning = bIsRunning;
	}
	
	public boolean getIsRunning() {
		return this._bIsRunning;
	}		
	
	public Gate(int nID, boolean bIsRunning) {
		this._nID = nID;
		this._bIsRunning = bIsRunning;		
		this._nEntryCount = 0;
		this._nExitCount = 0;
	}
	
	private boolean setCenter() {
		try {
			Registry objRegistry = LocateRegistry.getRegistry(Application.Port);
			this._objICenter = (ICenter) objRegistry.lookup("Center");
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void register() {
		try {
			this._nID = this._objICenter.registerGate(this);			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {		
		Gate objGate = new Gate(-1, true);	
		if(objGate.setCenter()) {
			objGate.register();
			new GateGUI(objGate).setupGUI();
		}		
	}
}
