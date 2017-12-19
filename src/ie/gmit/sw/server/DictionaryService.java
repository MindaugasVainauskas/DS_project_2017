package ie.gmit.sw.server;

import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryService extends RemoteObject implements Servitor{
	
	//Default serial version ID added.
	private static final long serialVersionUID = 1L;
	
	DictionaryMap dMap;
	HashMap<String, ArrayList<String>> dHashMap;
	
	//Set up the dictionary during construction of this class. Declare Throwable exceptions.
	public DictionaryService() throws Exception, RemoteException{
		dMap = new DictionaryMap();
		dMap.csvRead();
		dHashMap = dMap.getDictionary();
	}
	
	
	//search for the word in dictionary map. RemoteException is thrown as this method will be used in RMI
	public void search(String Word) throws RemoteException{		
		if(dHashMap.containsKey(Word.toUpperCase())){			
			System.out.println(Word.toUpperCase()+" --> "+dHashMap.get(Word.toUpperCase()).toString());			
		}	
		else{
			System.out.println(Word.toUpperCase()+" --> This word is undefined in this dictionary!");
		}
	}//end of search

}
