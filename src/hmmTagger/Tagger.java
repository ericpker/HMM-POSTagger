package hmmTagger;

import data.Sentence;
import data.Text;
import data.Word;

public class Tagger {
	
	double[][] viterbi = null;
	double[][] transitionProbability = null;
	double[]emissionProbability = null;
	int[][] backpointer = null;
	int N;
	int T;
	
	
	public Tagger(Text text){
		for(Sentence sentence: text.sentences) {
			int N = PartOfSpeech.total;
			int T = sentence.words.size();
			double previousMax = 0.0;
			int previousMaxIndex = 0;
			viterbi = new double[T][N];
			backpointer = new int[T][N];
			
			for(int state = 1; state<=N-2; state++) {
					double transitionFromStart = sentence.words.get(1).getTransitionProbability(state,0);
					double observedProbability = sentence.words.get(1).getObservedProbability(state);
					double viterbiResult = transitionFromStart * observedProbability;
					
					if(viterbiResult>previousMax) {
						previousMax = viterbiResult;
						previousMaxIndex = state;
					}
						
					viterbi[1][state] = viterbiResult;
					System.out.println(viterbi[1][state]);
					backpointer[1][state] = 0;
			}

			sentence.words.get(1).setPos(getPOS(previousMaxIndex));
			System.out.println("Viterbi Initialized");
			
			for(int wordIndex = 2; wordIndex<T; wordIndex++) {
				double currentMax = 0.0;
				int currentMaxIndex = 0;
				for(int state = 1; state<=N-2; state++) {
					double transitionProbabilityFromPrevious = sentence.words.get(wordIndex).getTransitionProbability(state,previousMaxIndex);
					double observedProbability = sentence.words.get(wordIndex).getObservedProbability(state);
					double viterbiResult = previousMax * transitionProbabilityFromPrevious * observedProbability;
					
					viterbi[wordIndex][state] = viterbiResult;
					backpointer[wordIndex][state] = previousMaxIndex;
					
					if(viterbiResult>currentMax) {
						currentMax = viterbiResult;
						currentMaxIndex = state;
					}
				}
				previousMax = currentMax;
				previousMaxIndex = currentMaxIndex;
				sentence.words.get(wordIndex).setPos(getPOS(currentMaxIndex));
			}
			
			/*for(int wordIndex = T-1; wordIndex>0;wordIndex--) {
				sentence.words.get(wordIndex).setPos(getPOS(backpointer[wordIndex][T]));
			}*/
			
/*			for(int t = 2; t<=T; t++) {
				double total = 0;
				double valmax = 0;
				int argmax = 0;
				System.out.println("~~~~~~~~~~" + t + "~~~~~~~~~~~~~");
				for(int state = 1; state<N; state++) {
	//				if(sentence.words.get(t)==null)	
					System.out.println("Checking word " + sentence.words.get(t));
					double v_prob = (double)( * sentence.words.get(t).getTransitionProbability(state, backpointer[t-1]) * sentence.words.get(t).getObservedProbability(state));
					System.out.println("Mult:" + viterbi[backpointer[t-1]][t-1] + " * "+ sentence.words.get(t).getTransitionProbability(state, backpointer[t-1]) + "* "+ sentence.words.get(t).getObservedProbability(state));
					if(v_prob>valmax){
						valmax = v_prob;
						argmax = state;
						System.out.println("Valmax changing to " + valmax);
						viterbi[state][t] = valmax;
						System.out.println("Argmax changing to " + argmax);
						backpointer[][t] = argmax;
						sentence.words.get(t-1).setPos(getPOS(argmax));
						System.out.println("Setting word " + sentence.words.get(t-1) + " POS to " + sentence.words.get(t-1).getPos());
					}				
				}
			}	
			
			System.out.println("vit");*/
			
			/*for(int t = T-1; t>0; t--) {
				double max = 0;
				int maxState = 0;
				for(int state = N-1;state>=0;state--) {
					PartOfSpeech pos = null;
					if(viterbi[state][t]>max){
						max = viterbi[state][t];
						maxState = state;
						pos = getPOS(backpointer[t]);
						System.out.println(pos);
					}
					Word w = sentence.words.get(t);
					if(w!=null)
						w.setPos(pos);
				}
			}*/
		}
	}

	public PartOfSpeech getPOS(int index) {
		switch (index) {
		case 0: return PartOfSpeech.START; //Start State
		case 1: return PartOfSpeech.CC; // Coordinating conjunction
		case 2: return PartOfSpeech.CD; // Cardinal number
		case 3: return PartOfSpeech.DT; // Determiner
		case 4: return PartOfSpeech.EX; // Existential there
		case 5: return PartOfSpeech.FW; // Foreign word
		case 6: return PartOfSpeech.IN; // Preposition or subordinating conjunction
		case 7: return PartOfSpeech.JJ; // Adjective
		case 8: return PartOfSpeech.JJR; // Adjective, comparative
		case 9: return PartOfSpeech.JJS; // Adjective, superlative
		case 10: return PartOfSpeech.LS; // List item marker
		case 11: return PartOfSpeech.MD; // Modal
		case 12: return PartOfSpeech.NN; // Noun, singular or mass
		case 13: return PartOfSpeech.NNS; // Noun, plural
		case 14: return PartOfSpeech.NNP; // Proper noun, singular
		case 15: return PartOfSpeech.NNPS; // Proper noun, plural
		case 16: return PartOfSpeech.PDT; // Predeterminer
		case 17: return PartOfSpeech.POS; // Possessive ending
		case 18: return PartOfSpeech.PRP; // Personal pronoun
		case 19: return PartOfSpeech.PRP$; // Possessive pronoun
		case 20: return PartOfSpeech.RB; // Adverb
		case 21: return PartOfSpeech.RBR; // Adverb, comparative
		case 22: return PartOfSpeech.RBS; // Adverb, superlative
		case 23: return PartOfSpeech.RP; // Particle
		case 24: return PartOfSpeech.SYM; // Symbol
		case 25: return PartOfSpeech.TO; // to
		case 26: return PartOfSpeech.UH; // Interjection
		case 27: return PartOfSpeech.VB; // Verb, base form
		case 28: return PartOfSpeech.VBD; // Verb, past tense
		case 29: return PartOfSpeech.VBG; // Verb, gerund or present participle
		case 30: return PartOfSpeech.VBN; // Verb, past participle
		case 31: return PartOfSpeech.VBP; // Verb, non-3rd person singular present
		case 32: return PartOfSpeech.VBZ; // Verb, 3rd person singular present
		case 33: return PartOfSpeech.WDT; // Wh-determiner
		case 34: return PartOfSpeech.WP; // Wh-pronoun
		case 35: return PartOfSpeech.WP$; // Possessive wh-pronoun
		case 36: return PartOfSpeech.WRB; // Wh-adverb
		case 37: return PartOfSpeech.END; //ending position for viterbi
		default: return PartOfSpeech.UNK; // Unknown word or unable to determine a tag
		}
	}

	public void tag() {
		
	}

}
