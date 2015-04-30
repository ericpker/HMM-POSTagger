package data;

import java.util.ArrayList;

import pennTagSet.PartOfSpeech;


public class Sentence {
	public ArrayList<Word> words;
	
	public Sentence()
	{
		 words = new ArrayList<Word>();
		 this.addStart();
	}
	
	public void addWord(Word word)
	{
		words.add(word);
	}
	
	public Word addStart() {
		Word start = new Word();
		start.setPos(PartOfSpeech.START);
		start.setWord("-Start-");
		words.add(start);
		System.out.println("adding start");
		return start;		
	}
	
	public Word addEnd() {
		Word end = new Word();
		end.setPos(PartOfSpeech.END);
		end.setWord("-END-");
		words.add(end);
		System.out.println("adding end");
		return end;		
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
