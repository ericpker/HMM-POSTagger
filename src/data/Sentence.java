package data;

import java.util.ArrayList;


public class Sentence {
	public ArrayList<Word> words;
	
	public Sentence()
	{
		 words = new ArrayList<Word>();
		 words.add(null);
	}
	
	public void addWord(Word word)
	{
		words.add(word);
	}
	
	public String toString()
	{
		String textString = "";
		for(Word word:words)
		{
			if(word!=null)
				textString += word + " ";
		}
		
		return textString;
	}
}
