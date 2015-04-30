package hmmTagger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Sentence;
import data.Text;
import data.Word;
import pennTagSet.PartOfSpeech;
import tokenize.English;

public class Learner {
	
	int[][] forward;
	String fileContent = "";
	Scanner input = null;
	private Text text;
	private ArrayList<Word> words;
	public Learner(File trainingFile) {
		words = new ArrayList<Word>();
		text = new Text();
		System.out.println("Beginning to load leaner...");
		
		try {
			input = new Scanner(trainingFile);
			while(input.hasNextLine())
			{
				fileContent += input.nextLine();
				if(input.hasNextLine())
					fileContent += "\r\n";
			}
			
			text.setContent(fileContent);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		
		System.out.println(fileContent);
		
		learn();
	}
	
	public void learn() {
		tokenizeText(this.text);
	}
	
		private Pattern pattern;
		private Pattern contractionPattern;
		private Matcher matcher;
		private Sentence sentence;
		private Word current;
		private Pattern punctuationPattern;
		
	public void tokenizeText(Text text) {
    	String[] sentences = text.getContent().split("(\\.|\\r\\n|\\r(?!\\n)|(?<!\\r)\\n)+");
    	PartOfSpeech currentPOS;
    	PartOfSpeech previousPOS;
    	for(String s:sentences) {
    		previousPOS=PartOfSpeech.START;
    		if(!s.isEmpty()) {
    		sentence = new Sentence();
    		String[] words = s.split(" ");
    		for(String w:words) {
    			if(!w.isEmpty()) {
    			String[] splitWord = w.split("_");
    			current = getWord(splitWord[0]);
				currentPOS = getEnumFromString(PartOfSpeech.class, splitWord[1]);
    			if(current!=null) {
	    			current.addInstance(currentPOS, previousPOS);
	    			this.words.add(current);
	    			current.setPos(currentPOS);
	    			sentence.addWord(current);
	    			System.out.println("Adding to word:" + current.getWord());
	    			}else {
	        			current = new Word(splitWord[0]);
	        			current.addInstance(currentPOS, previousPOS);
	        			this.words.add(current);
		    			current.setPos(currentPOS);
		    			sentence.addWord(current);
	        			System.out.println("Creating new word:" + current.getWord());
	    			}
    			previousPOS=currentPOS;
    			}
    		}
    		current.addInstance(PartOfSpeech.END, previousPOS);
    		sentence.addEnd();
    		text.addSentence(sentence);
    		System.out.println("Adding sentence:" + sentence);
    		}
    	}
    	
    	for(Word w:words)
    		w.smoothWords();
    }
	
	private Word getWord(String string) {
		for(Word w:words) {
			if(string.equals(w.getWord()))
				return w;
		}
		return null;
	}

	/**
	 * A common method for all enums since they can't have another base class
	 * @param <T> Enum type
	 * @param c enum type. All enums must be all caps.
	 * @param string case insensitive
	 * @return corresponding enum, or null
	 */
	public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
	    if( c != null && string != null ) {
	        try {
	            return Enum.valueOf(c, string.trim().toUpperCase());
	        } catch(IllegalArgumentException ex) {
	        	System.out.println("Could not find an Enum");
	        }
	    }
	    return null;
	}
	
	

	public Word getCopyWord(String word2) {
		for(Word w:words) {
			if(word2.equals(w.getWord()))
				return w.copy();
		}
		return null;
	}
}
