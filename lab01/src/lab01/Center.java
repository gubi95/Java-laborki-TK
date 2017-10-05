package lab01;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import javax.management.monitor.Monitor;

public class Center implements ICenter {
	private CenterGUI _objCenterGUI = null;
	private ArrayList<Object> _listGates = new ArrayList<>();
	private Object _objIMonitor = null;
	
	public Center(CenterGUI objCenterGUI) {
		this._objCenterGUI = objCenterGUI;
	}
	
	private int generateNewID() {
		while(true) {
			int nID = new Random().nextInt();
			boolean bIsUnique = true;
			System.out.println(this._listGates.size());
			for(Object objIGate : this._listGates) {
				try {
					if(((IGate) objIGate).getID() == nID) {
						bIsUnique = false;
						break;
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			
			if(bIsUnique) {
				return nID;
			}
		}
	}
	
	private void refreshMonitor() {
		if(this._objIMonitor != null) {						
			try {				
				((IMonitor) this._objIMonitor).refresh();				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		if(this._objCenterGUI != null) {
			this._objCenterGUI.refresh(_listGates);
		}
	}
	
	@Override
	public int registerGate(Object objIGateIn) throws RemoteException {		
		IGate objIGate = (IGate) objIGateIn;				
		_listGates.add(objIGate);		
		this.refreshMonitor();
		return this.generateNewID();		
	}

	@Override
	public boolean removeGate(int nGateID) throws RemoteException {		
		for(int i = 0; i < this._listGates.size(); i++) {			
			if(((IGate)this._listGates.get(i)).getID() == nGateID) {
				this._listGates.remove(i);
				this.refreshMonitor();
				return true;
			}
		}
		
		return false;
	}

	@Override
	public ArrayList<Object> getRegisteredGates() throws RemoteException {
		return this._listGates;		
	}

	@Override
	public void registerMonitor(Object objIMonitor) throws RemoteException {
		_objIMonitor = objIMonitor;		
	}

	@Override
	public void removeMonitor() throws RemoteException {
		this._objIMonitor = null;		
	}
	
	@Override
	public void notifyChanges() throws RemoteException {
		this.refreshMonitor();		
	}

	public static void main(String[] args) {		
		try {
			CenterGUI objCenterGUI = new CenterGUI();
			objCenterGUI.setupGUI();
			
			Center objCenter = new Center(objCenterGUI);
			ICenter objICenter = (ICenter) UnicastRemoteObject.exportObject(objCenter, 0);			
			Registry objRegistry = LocateRegistry.createRegistry(Application.Port);
			objRegistry.bind("Center", objICenter);
			System.out.println("Center ready!");			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}		
	}	
}
