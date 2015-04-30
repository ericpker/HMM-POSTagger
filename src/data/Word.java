package data;

import pennTagSet.PartOfSpeech;

public class Word {
	private String word = "";
	private PartOfSpeech pos = null;
	static double[][] transitionProbability = new double[PartOfSpeech.total][PartOfSpeech.total];
	static int[][] transitionCount = new int[PartOfSpeech.total][PartOfSpeech.total];
	static int[] transition = new int[PartOfSpeech.total];
	private double[] observedProbability;
	private int[] numberPOS;
	private int number;
	
	public Word(String word) {
		this.setWord(word);
		this.setPos(PartOfSpeech.NN);
		observedProbability = new double[PartOfSpeech.total];
		for(int i = 0; i<PartOfSpeech.total; i++) {
			observedProbability[i]=0;
		}
		numberPOS = new int[PartOfSpeech.total];
		for(int i = 0; i<PartOfSpeech.total; i++) {
			numberPOS[i]=0;
		}
		number = 0;
	}

	public Word(double[] probability) {
		observedProbability = probability;
	}

	public void addInstance(PartOfSpeech pos,PartOfSpeech previous) {
		System.out.println("ADDING INSTANCE");
		number++;
		numberPOS[pos.getPOSIndex()]++;
		observedProbability[pos.getPOSIndex()] = (double)numberPOS[pos.getPOSIndex()]/number;
		transitionCount[previous.getPOSIndex()][pos.getPOSIndex()]++;
		transition[pos.getPOSIndex()]++;
		transitionProbability[pos.getPOSIndex()][previous.getPOSIndex()] = (double)transitionCount[previous.getPOSIndex()][pos.getPOSIndex()]/transition[pos.getPOSIndex()];
	}
	
	public void smoothWords() {
		number+=PartOfSpeech.total;
		for(int i=0;i<PartOfSpeech.total;i++) {
			numberPOS[i]++;
			observedProbability[i] = (double)numberPOS[i]/number;
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

	public double getTransitionProbability(int i, int n) {
		return transitionProbability[i][n];
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
