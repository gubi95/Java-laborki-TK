package lab01;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IGate extends Remote {
	 int[] getStatistics() throws RemoteException;	 
	 boolean shutdown() throws RemoteException;
	 int getID() throws RemoteException;
	 int getEntryCount() throws RemoteException;
	 int getExitCount() throws RemoteException;
}
