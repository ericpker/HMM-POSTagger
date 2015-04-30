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
	private int numberOfWord;
	
	public Word(String word) {
		this.setWord(word);
		this.setPos(PartOfSpeech.NN);
		observedProbability = new double[PartOfSpeech.total];
		for(int i = 0; i<PartOfSpeech.total; i++) {
			observedProbability[i] = 0;
		}
		numberPOS = new int[PartOfSpeech.total];
		for(int i = 0; i<PartOfSpeech.total; i++) {
			numberPOS[i]=0;
		}
		numberOfWord = 0;
	}
	
	public Word() {
		
	}

	public Word(double[] probability) {
		observedProbability = probability;
	}

	public void addInstance(PartOfSpeech currentPOS,PartOfSpeech previousPOS) {
		System.out.println("Word transitioning from " + previousPOS + " to " + currentPOS);
		int currentPOSIndex = currentPOS.getPOSIndex();
		int previousPOSIndex = previousPOS.getPOSIndex();
		numberOfWord++;
		numberPOS[currentPOSIndex]++;
		observedProbability[currentPOSIndex] = (double)numberPOS[currentPOSIndex]/numberOfWord;

		numberOfTransitions++;
		transitionCount[currentPOSIndex][previousPOSIndex]++;
	}
	
	public static void calculateProbabilities() {
		for(int currentPOSIndex = 0;currentPOSIndex<PartOfSpeech.total;currentPOSIndex++)
			for(int previousPOSIndex = 0; previousPOSIndex<PartOfSpeech.total;previousPOSIndex++)
				transitionProbability[currentPOSIndex][previousPOSIndex] = (double)(transitionCount[currentPOSIndex][previousPOSIndex])/numberOfTransitions;
	}
	
	public void smoothWords() {
		numberOfWord+=PartOfSpeech.total;
		for(int i=0;i<PartOfSpeech.total;i++) {
			numberPOS[i]++;
			observedProbability[i] = (double)numberPOS[i]/numberOfWord;
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
