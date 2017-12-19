package ie.gmit.sw.client;

public class Request {
	
	private String word;
	private String wordDesc;
	private int taskNum;
	
	//constructor
	public Request(String word, String wordDesc, int taskNum) {
		this.word = word;
		this.wordDesc = wordDesc;
		this.taskNum = taskNum;
	}

	//Getters and setters section -------------------------
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWordDesc() {
		return wordDesc;
	}

	public void setWordDesc(String wordDesc) {
		this.wordDesc = wordDesc;
	}

	public int getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}
	
	

}
