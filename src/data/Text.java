package data;

import java.util.ArrayList;

public class Text {
	
	public ArrayList<Sentence> sentences;
	private String content = "";

	public Text()
	{
		 sentences = new ArrayList<Sentence>();
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void addSentence(Sentence sentence)
	{
		sentences.add(sentence);
	}
	
	public String toString()
	{
		String textString = "";
		for(Sentence sentence:sentences)
		{
			textString += sentence + "\r\n";
		}
		
		return textString;
	}
	
}
