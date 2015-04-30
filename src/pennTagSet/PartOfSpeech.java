package pennTagSet;

public enum PartOfSpeech {
	START(0), //Starting position for viterbi
	CC(1), // Coordinating conjunction
	CD(2), // Cardinal number
	DT(3), // Determiner
	EX(4), // Existential there
	FW(5), // Foreign word
	IN(6), // Preposition or subordinating conjunction
	JJ(7), // Adjective
	JJR(8), // Adjective, comparative
	JJS(9), // Adjective, superlative
	LS(10), // List item marker
	MD(11), // Modal
	NN(12), // Noun, singular or mass
	NNS(13), // Noun, plural
	NNP(14), // Proper noun, singular
	NNPS(15), // Proper noun, plural
	PDT(16), // Predeterminer
	POS(17), // Possessive ending
	PRP(18), // Personal pronoun
	PRP$(19), // Possessive pronoun
	RB(20), // Adverb
	RBR(21), // Adverb, comparative
	RBS(22), // Adverb, superlative
	RP(23), // Particle
	SYM(24), // Symbol
	TO(25), // to
	UH(26), // Interjection
	VB(27), // Verb, base form
	VBD(28), // Verb, past tense
	VBG(29), // Verb, gerund or present participle
	VBN(30), // Verb, past participle
	VBP(31), // Verb, non-3rd person singular present
	VBZ(32), // Verb, 3rd person singular present
	WDT(33), // Wh-determiner
	WP(34), // Wh-pronoun
	WP$(35), // Possessive wh-pronoun
	WRB(36), // Wh-adverb
	END(37) // ending position for Viterbi
	;
	public static int total = 36; //1 through 36 are the Penn Tags
	private int index;
	
	PartOfSpeech(int index) {
		this.setPOSIndex(index);
	}

	public int getPOSIndex() {
		return index;
	}
	
	public void setPOSIndex(int index) {
		this.index = index;
	}
}
