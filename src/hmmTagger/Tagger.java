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
			for(int index = 0; index<sentence.words.size(); index++) {
				viterbi[index][0] = transitionProbability[0][index] * emissionProbability[0];
//				viterbi[index][0] = word.getTransitionProbability(0,index) * word.getObservedProbability();
				backpointer[index][0] = 0;
			}
		}
	}

	public void tagSentence(Text text) {
		
	}

}
