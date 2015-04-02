package parser;

import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Tagger {
	
	private Pattern pattern;
	private Matcher matcher;
	private Console console;
	private Text text;
	private Sentence sentence;
	private Word word;
	
	private boolean newSentence = true;

	public Tagger()
	{
		console = System.console();
		pattern = Pattern.compile("\\.|\\b\\S+?\\b|\\,|\\r\\n|(?<!\\r)\\n|\\r(?!\\n)");
	}
	public void tagText(Text text) {
		matcher = pattern.matcher(text.getText());
		
		boolean found = false;
        while (matcher.find()) {
        	if(newSentence)
        	{
        		sentence = new Sentence();
        		text.addSentence(sentence);
        		newSentence = false;
        	}
        	
        	String currentFind = matcher.group();
        	word = new Word(currentFind);
        	if(word.getWord() == "\\r\\n" || word.getWord() == "\\r" || word.getWord() == "\\n")
        		word.setPOS("");
            sentence.addWord(word);
            if(currentFind == ".")
            	newSentence = true;
            
            found = true;
        }
        if(!found){
            console.format("No match found.%n");
        }
	}

}
