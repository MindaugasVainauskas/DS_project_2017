package ie.gmit.sw.client;

import java.io.Serializable;

public class Request implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String word;
	private String wordDesc;
	private int taskNum;
	
	//constructor
	public Request(String word, String wordDesc) {
		this.word = word;
		this.wordDesc = wordDesc;
		this.taskNum++;
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
}
