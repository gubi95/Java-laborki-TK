package lab01;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Gate extends UnicastRemoteObject implements IGate, Serializable {
	public static interface ICallbackAction {
		void doAction();
	}
	
	private ICallbackAction _objICallbackActionShutdown;	
	public void setShutdownCallbackAction(ICallbackAction objICallbackAction) {
		this._objICallbackActionShutdown = objICallbackAction;
	}
	
	protected Gate() throws RemoteException {
		super();
		this._bIsRunning = true;		
		this._nEntryCount = 0;
		this._nExitCount = 0;
	}

	private static final long serialVersionUID = 1L;
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
		_objICenter.removeGate(_nID);
		this._bIsRunning = false;
		
		if(_objICallbackActionShutdown != null) {
			_objICallbackActionShutdown.doAction();
		}
		
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
			try {
				this._objICenter.notifyChanges();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int getEntryCount() throws RemoteException {
		return this._nEntryCount;
	}
	
	public void incremenetExitCount() {
		if(this._bIsRunning) {
			this._nExitCount++;
			try {
				this._objICenter.notifyChanges();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int getExitCount() throws RemoteException {
		return this._nExitCount;
	}
	
	public void setIsRunning(boolean bIsRunning) {
		this._bIsRunning = bIsRunning;
	}
	
	public boolean getIsRunning() {
		return this._bIsRunning;
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
	
	public void register() {
		try {			
			this._nID = this._objICenter.registerGate(this);
			this._bIsRunning = true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {		
		try {
			Gate objGate = new Gate();
			if(objGate.setCenter()) {
				objGate.register();				
				new GateGUI(objGate).setupGUI();
			}		
		} catch (RemoteException e) {
			e.printStackTrace();
		}			
	}
}
