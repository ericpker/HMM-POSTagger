package parser;

import java.util.ArrayList;

public class Text {
	
	private ArrayList<Word> words;
	private String content = ""; 

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<Word> getWords() {
		return words;
	}

	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}

	public void addWord(Word word) {
		this.words.add(word);
	}
	
	public String toString() {
		String result = "";
		for(Word word:words)
		{
			result += word;
		}
		
		return result;
	}

}
