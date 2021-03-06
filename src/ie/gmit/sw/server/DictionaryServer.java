package ie.gmit.sw.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DictionaryServer {

	public static void main(String[] args) throws Exception{
		Servitor ds = new DictionaryService();
		
		//Start up registry on port 1099(Default port)
		LocateRegistry.createRegistry(1099);
		//bind dictionary service with a name.
		Naming.rebind("dictionaryService", ds);
		
		//print out response to console that server is ready
		System.out.println("Dictionary server ready!");		
	}
}
