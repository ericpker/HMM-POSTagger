package data;

import hmmTagger.Tagger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import tokenize.Tokenize;

public class DataManager {
	private String inputFileName;
	private String outputFileName;
	private String fileContent;
	private File inputFile;
	private File outputFile;
	private Scanner input;
	private Writer writer;
	private Text text;
	private Tagger tagger;
	private Tokenize tokenizer;
	
	public DataManager()
	{
		inputFileName = "text_1" + ".txt";
		outputFileName = "text_1_tagged" + ".txt";
		fileContent = "";
		text = new Text();
	}
	
	public static void main(String[] args)
	{
		DataManager dataManager = new DataManager();
		dataManager.run();
	}

	private void run()
	{
		try {
			inputFile = new File(inputFileName);
			outputFile = new File(outputFileName);
			Load(inputFile);
			tokenizer = new tokenize.Tokenize();
			tokenizer.tokenizeText(text);
			tagger = new Tagger(text);
			tagger.tagSentence(text);
			Save(outputFile);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void Load(File file)
	{
		System.out.println("Beginning to load...");
		
		try {
			input = new Scanner(inputFile);
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
	}
	
	private void Save(File file)
	{
		writer = null;

		try {
			
			file.createNewFile();
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String content = text.toString();
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
	
	public double[] queryWord(String word) {
		double[] observedProbabilities = new double[37];
		if(word!=null)
		if(word=="this"){
			double[] observed = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,};
			return observed;
		}
		if(word=="is"){
			double[] observed = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,};
			return observed;
		}
		if(word=="a"){
			double[] observed = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,};
			return observed;
		}
		if(word=="test"){
			double[] observed = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,};
			return observed;
		}
		
		return observedProbabilities;
	}
}