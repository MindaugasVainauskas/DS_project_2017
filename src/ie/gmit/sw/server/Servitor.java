package ie.gmit.sw.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ie.gmit.sw.client.Request;

public interface Servitor extends Remote{

	public String search(String Word) throws RemoteException;
}
