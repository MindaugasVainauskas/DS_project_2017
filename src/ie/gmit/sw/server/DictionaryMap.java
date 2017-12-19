package ie.gmit.sw.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryMap {
	

	//dictionary hashMap is going to be used to store dictionary words and definitions from project local
	// dictionary csv file
	HashMap<String, ArrayList<String>> dictHashMap = new HashMap<String, ArrayList<String>>();
	
	public void csvRead() throws Exception{
		//path to csv dictionary file
		String dictFile = "./dictionary.csv";
		
		String line = null;
		String key = null;		
		String charCheck = null;
		
		//declare and initialise the bufferedReader to parse dictionary file
		BufferedReader br = new BufferedReader(new FileReader(dictFile));
		
		//start splitting line after second comma
		while((line = br.readLine())!= null){	
			String[] keyString = line.split(",", 2);
			
			if(line.equals("")){
				continue;//skip the line if its empty
			}
			//charCheck will look for quotation mark
			charCheck = keyString[0].substring(0, 1);
			//keyString replaces quotation mark with nothing
			keyString[0] = keyString[0].replace("\"", "");

			if ("\"".equals(charCheck)) {
				//if first character read is quotation mark then key assigned is the first part of line read
				//(the first word) and then value assigned is the remainder of the line
				key = keyString[0];
				addDefinition(key.toUpperCase(), line);
			} else {//else the line is just read in into definition
				addDefinition(key.toUpperCase(), line);
			}			
			
		}
		br.close();//close off bufferedReader
	}//end of while loop
	
	//add definition to dictionary if word is not already present
	private void addDefinition(String key, String definition){
		//if this is first occurrence of this word in the dictionary it gets assigned into the map
		if(dictHashMap.get(key) == null){	
			dictHashMap.put(key, new ArrayList<String>());
		}
		//if word exists already its definition is added
		dictHashMap.get(key).add("\n"+definition);
	}
	
	//Return the hashmap when requested in another class
	public HashMap<String, ArrayList<String>> getDictionary(){		
		return this.dictHashMap;
	}
		

}
