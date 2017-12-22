package ie.gmit.sw.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ie.gmit.sw.server.Servitor;

public class DictionaryServiceClient {
	
	private Servitor dService;
	private BlockingQueue<Request> inQueue;
	private Map<Integer, String> outQueue;
	
	public DictionaryServiceClient() throws RemoteException{		
		try {
			dService = (Servitor) Naming.lookup("rmi://localhost:1099/dictionaryService");
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
		dictSetup();
	}
	
	//Add new request to the inQueue
	public void addRequest(Request r){
		inQueue.add(r);
	}
	
	//Process requests from inQuue to outQueue
	private void dictSetup(){

		//Instantiate queues as concrete subtypes of queue interface.
		inQueue = new LinkedBlockingQueue<Request>();
		outQueue = new HashMap<Integer, String>();
		Request req;
		String w = "";
		String def = "";
		
				
				//look for definition from request
				try {
					req = inQueue.take(); //Get the request from the queue
					
					w = req.getWord();
					
					def = dService.search(w);

					//put the result of the dictionary search to the out queue.
					outQueue.put(req.getTaskNum(), def);
				} catch (RemoteException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
	
	public String getDefinition(String r){
		int i = Integer.parseInt(r);
		if(outQueue.containsKey(i)){
			return outQueue.get(i);
		}else{
			return "Definition not found";
		}		
	}
}
