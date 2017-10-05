import java.io.Console;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Center implements ICenter {
	private List<Gate> _listGates = new ArrayList<>();
	
	private int generateNewID() {
		while(true) {
			int nID = new Random().nextInt();
			boolean bIsUnique = true;
			for(Gate objGate : this._listGates) {
				try {
					if(objGate.getID() == nID) {
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
	
	@Override
	public int registerGate(Object objGate) throws RemoteException {
		return generateNewID();
	}

	@Override
	public boolean removeGate(int nGateID) throws RemoteException {
		for(int i = 0; i < this._listGates.size(); i++) {
			
		}
		return false;
	}

	@Override
	public ArrayList<Object> getRegisteredGates() throws RemoteException {

		return null;
	}

	@Override
	public void registerMonitor(Object objMonitor) throws RemoteException {

		
	}

	@Override
	public void removeMonitor() throws RemoteException {
		
	}

	public static void main(String[] args) {		
		try {
			Center objCenter = new Center();
			ICenter objICenter = (ICenter) UnicastRemoteObject.exportObject(objCenter, 0);			
			Registry objRegistry = LocateRegistry.createRegistry(Application.Port);
			objRegistry.bind("Center", objICenter);
			System.out.println("Center binded!");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
