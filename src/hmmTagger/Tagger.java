package hmmTagger;

import data.Text;

public class Tagger {
	
	double[][] Viterbi = null;
	int[][] Backpointer = null;
	
	public Tagger(Text text){
//		Viterbi = new double[N][T];
//		Backpointer = new int[N][T];
//		for(int s = 1;s<N;s++) {
//			Viterbi[s][1] = 0;
//			Backpointer[s][1] = 0;
//		}

	}

	public Text tagText(Text text) {
		return text;
	}

}
