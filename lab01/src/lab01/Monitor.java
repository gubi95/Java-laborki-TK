package lab01;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Monitor extends UnicastRemoteObject implements IMonitor, Serializable {
	protected Monitor() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;
	private ICenter _objICenter;
	private MonitorGUI _objMonitorGUI;
	
	@Override
	public void refresh() {
		try {			
			ArrayList<Object> listGates = this._objICenter.getRegisteredGates();
			
			if(this._objMonitorGUI == null) {
				this._objMonitorGUI = new MonitorGUI();
				this._objMonitorGUI.setupGUI();
			}
			_objMonitorGUI.refresh(listGates);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private boolean setCenter() {
		try {
			Registry objRegistry = LocateRegistry.getRegistry(Application.Port);
			this._objICenter = (ICenter) objRegistry.lookup("Center");			
			this._objICenter.registerMonitor(this);
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		Monitor objMonitor;
		try {
			objMonitor = new Monitor();
			if(objMonitor.setCenter()) {
				System.out.println("Monitor is ready to serve!");
				objMonitor.refresh();			
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
}
