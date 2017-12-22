package ie.gmit.sw.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryService extends UnicastRemoteObject implements Servitor{
	
	//Default serial version ID added.
	private static final long serialVersionUID = 1L;
	
	DictionaryMap dMap;
	HashMap<String, ArrayList<String>> dHashMap;
	
	//Set up the dictionary during construction of this class. Declare Throwable exceptions.
	public DictionaryService() throws RemoteException{
		dMap = new DictionaryMap();
		try {
			dMap.csvRead();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dHashMap = dMap.getDictionary();
	}
	
	
	//search for the word in dictionary map. RemoteException is thrown as this method will be used in RMI
	//Return type is a Request object consisting of word and it's definition
	public String search(String Word) throws RemoteException{		
		if(dHashMap.containsKey(Word.toUpperCase())){
			ArrayList<String> definition = dHashMap.get(Word.toUpperCase().toString());
			//convert arraylist<String> to string
			StringBuilder sb = new StringBuilder();
			for(String str : definition){
				sb.append(str);
			}
			
			String def = sb.toString();
			
			return def;		
		}	
		else{
			return "No Definitions found";
		}
	}//end of search

}
