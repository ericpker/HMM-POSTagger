package data;

import java.util.ArrayList;


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
		double[] observedProbability = new double[PartOfSpeech.total];
		observedProbability[PartOfSpeech.END.getPOSIndex()] = 1.0;
		Word start = new Word(observedProbability);
		start.setPos(PartOfSpeech.START);
		start.setWord("-Start-");
		words.add(start);
		System.out.println("adding start");
		return start;		
	}
	
	public Word addEnd() {
		double[] observedProbability = new double[PartOfSpeech.total];
		observedProbability[PartOfSpeech.END.getPOSIndex()] = 1.0;
		Word end = new Word(observedProbability);
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
			if(word!=null&&!(word.getPos()==(PartOfSpeech.START)||word.getPos()==(PartOfSpeech.END)))
				textString += word + " ";
		}
		
		return textString;
	}
}
