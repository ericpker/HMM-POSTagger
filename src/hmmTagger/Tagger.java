package hmmTagger;

import data.Sentence;
import data.Text;

public class Tagger {
	
	double[][] viterbi = null;
	double[][] transitionProbability = null;
	double[]emissionProbability = null;
	int[][] backpointer = null;
	
	
	public Tagger(Text text){
		for(Sentence sentence: text.sentences) {
			int N = 37;
			int T = sentence.words.size()+1;
			viterbi = new double[N][T];
			backpointer = new int[N][T];
			
			for(int n = 0; n<N; n++) {
//					viterbi[n][0] = transitionProbability[0][n] * emissionProbability[0];
					viterbi[n][1] = sentence.words.get(1).getTransitionProbability(0,n) * sentence.words.get(1).getObservedProbability(n);
					backpointer[n][0] = 0;
			}
			
			for(int t = 0; t<T; t++) {
				double total = 0;
				double valmax = 0;
				for(int state = 1; state<N; state++) {
//					double v_prob = viterbi[t][state-1] * sentence.words.get(t).getTransitionProbability(t, state);
//					if(v_prob>valmax){
//						valmax = v_prob;
//					}
				}
			}			
		}
	}

	public void tagSentence(Text text) {
		
	}

}
