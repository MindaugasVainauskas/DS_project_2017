package ie.gmit.sw.server;

public class DictionaryServer {

	public static void main(String args[]){
		DictionaryMap dm = new DictionaryMap();
		
		try {
			dm.csvRead();
			
			dm.search("serious");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
