import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

interface ICenter extends Remote {
	int registerGate(Object objGate) throws RemoteException;	 
	boolean removeGate(int nGateID) throws RemoteException;
	ArrayList<Object> getRegisteredGates() throws RemoteException;
	void registerMonitor(Object objMonitor) throws RemoteException;
	void removeMonitor() throws RemoteException;
}