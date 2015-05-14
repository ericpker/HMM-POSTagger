package data;

import pennTagSet.PartOfSpeech;

public class Word {
	private String word = "";
	private PartOfSpeech pos = null;
	static double[][] transitionProbability = new double[PartOfSpeech.total][PartOfSpeech.total];
	static int[][] transitionCount = new int[PartOfSpeech.total][PartOfSpeech.total];
	static int numberOfTransitions;
	private double[] observedProbability;
	private int[] numberPOS;
	private int numberOfWords;
	
	public Word(String word) {
		this.setWord(word);
	}
	
	public Word(String word, int count) {
		
	}

	public Word(double[] probability) {
		observedProbability = probability;
	}

	public void addInstance(PartOfSpeech currentPOS,PartOfSpeech previousPOS) {
		System.out.println("Word transitioning from " + previousPOS + " to " + currentPOS);
		int currentPOSIndex = currentPOS.getPOSIndex();
		int previousPOSIndex = previousPOS.getPOSIndex();
		numberOfWords++;
		numberPOS[currentPOSIndex]++;
		observedProbability[currentPOSIndex] = (double)numberPOS[currentPOSIndex]/numberOfWords;

		numberOfTransitions++;
		transitionCount[currentPOSIndex][previousPOSIndex]++;
	}
	
	public static void calculateProbabilities() {
		for(int currentPOSIndex = 0;currentPOSIndex<PartOfSpeech.total;currentPOSIndex++)
			for(int previousPOSIndex = 0; previousPOSIndex<PartOfSpeech.total;previousPOSIndex++)
				transitionProbability[currentPOSIndex][previousPOSIndex] = (double)(transitionCount[currentPOSIndex][previousPOSIndex])/numberOfTransitions;
	}
	
	public void smoothWords() {
		numberOfWords+=PartOfSpeech.total;
		for(int i=0;i<numberOfWords;i++) {
			numberPOS[i]++;
			observedProbability[i] = (double)numberPOS[i]/numberOfWords;
		}
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

	public double getTransitionProbability(int current, int previous) {
		return transitionProbability[current][previous];
	}

	public double getObservedProbability(int n) {
		return observedProbability[n];
	}

	public Word copy() {
		Word copy = new Word(this.observedProbability);
		copy.setWord(this.word);
//		for(int i=0;i<PartOfSpeech.total;i++)
//			System.out.println(copy.observedProbability[i]);
		return copy;
	}
}
