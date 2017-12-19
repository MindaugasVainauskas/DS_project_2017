package ie.gmit.sw.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Servitor extends Remote{

	public void search(String Word) throws RemoteException;
}
