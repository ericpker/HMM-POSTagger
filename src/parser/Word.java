package parser;

import pennTagSet.PartOfSpeech;

public class Word {
	private String word = "";
	private PartOfSpeech pos = null; 
	
	public Word(String word) {
		this.setWord(word);
		this.setPos(PartOfSpeech.UNK);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public PartOfSpeech getPos() {
		return pos;
	}
	
	public void setPos(PartOfSpeech pos) {
		this.pos = pos;
	}
	
	@Override
	public String toString() {
		return word + pos;		
	}
}
