package ie.gmit.sw.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Client copy of Servitor interface
public interface Servitor extends Remote{
	public void search(String Word) throws RemoteException;
}
