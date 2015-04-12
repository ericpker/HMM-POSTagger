package pennTagSet;

public enum PartOfSpeech {
	CC(0), // Coordinating conjunction
	CD(1), // Cardinal number
	DT(2), // Determiner
	EX(3), // Existential there
	FW(4), // Foreign word
	IN(5), // Preposition or subordinating conjunction
	JJ(6), // Adjective
	JJR(7), // Adjective, comparative
	JJS(8), // Adjective, superlative
	LS(9), // List item marker
	MD(10), // Modal
	NN(11), // Noun, singular or mass
	NNS(12), // Noun, plural
	NNP(13), // Proper noun, singular
	NNPS(14), // Proper noun, plural
	PDT(15), // Predeterminer
	POS(16), // Possessive ending
	PRP(17), // Personal pronoun
	PRP$(18), // Possessive pronoun
	RB(19), // Adverb
	RBR(20), // Adverb, comparative
	RBS(21), // Adverb, superlative
	RP(22), // Particle
	SYM(23), // Symbol
	TO(24), // to
	UH(25), // Interjection
	VB(26), // Verb, base form
	VBD(27), // Verb, past tense
	VBG(28), // Verb, gerund or present participle
	VBN(29), // Verb, past participle
	VBP(30), // Verb, non-3rd person singular present
	VBZ(31), // Verb, 3rd person singular present
	WDT(32), // Wh-determiner
	WP(33), // Wh-pronoun
	WP$(34), // Possessive wh-pronoun
	WRB(35), // Wh-adverb
	UNK(36); // Unknown word or unable to determine a tag
	
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
