package data;

import pennTagSet.PartOfSpeech;

public class Word {
	private String word = "";
	private PartOfSpeech pos = null;
	private double[] observedProbability;
	
	public Word(String word) {
		this.setWord(word);
		this.setPos(PartOfSpeech.NN);
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
		return word + "_" + pos;		
	}

	public int getTransitionProbability(int i, int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getObservedProbability(int n) {
		// TODO Auto-generated method stub
		return 0;
	}
}
