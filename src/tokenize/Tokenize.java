package tokenize;

import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Sentence;
import data.Text;
import data.Word;


public class Tokenize {
	
	private Pattern pattern;
	private Matcher matcher;
	private Sentence sentence;
	private Word word;

	public Tokenize()
	{
		pattern = Pattern.compile("([" + English.puctuation + "] *)");
	}
	
	public void tokenizeText(Text text) {
		matcher = pattern.matcher(text.getContent());
		
    	text.setContent(matcher.replaceAll(" $1 "));
    	
    	pattern = Pattern.compile(" +");
    	matcher = pattern.matcher(text.getContent());
    	text.setContent(matcher.replaceAll(" "));
    	
    	pattern = Pattern.compile("\\. ");
    	matcher = pattern.matcher(text.getContent());
    	text.setContent(matcher.replaceAll("\\."));

    	String[] sentences = text.getContent().split("(\\.|\\r\\n|\\r(?!\\n)|(?<!\\r)\\n)+");
    	for(String s:sentences) {
    		sentence = new Sentence();
    		String[] words = s.split(" ");
    		for(String w:words) {
    			word = new Word(w);
    			sentence.addWord(word);
    			System.out.println("Adding word:" + word.getWord());
    		}
    		text.addSentence(sentence);
    		System.out.println("Adding sentence:" + sentence);
    	}
	}
	
}
