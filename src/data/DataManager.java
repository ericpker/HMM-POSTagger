package data;

import hmmTagger.Learner;
import hmmTagger.Tagger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import pennTagSet.PartOfSpeech;
import tokenize.Tokenize;

public class DataManager {
	private String inputFileName;
	private String outputFileName;
	private String trainingFileName;
	private String fileContent;
	private String pennTagFileLocation;
	private File inputFile;
	private File outputFile;
	private File pennTagSet;
	private Scanner input;
	private Writer writer;
	private Text text;
	private Tagger tagger;
	private Tokenize tokenizer;
	private File trainingFile;
	private Learner learner;
	private Connection connection = null;
	private Statement statement = null;
	

	PreparedStatement preparedAddPOS = null;
	PreparedStatement preparedAddWord = null;
	
	public DataManager()
	{
		startConnection();
		PrepareStatements();
		inputFileName = "text_1" + ".txt";
		outputFileName = "text_1_tagged" + ".txt";
		trainingFileName = "text_1_train" + ".txt";
	/*	pennTagFileLocation = "data\\PennTags.txt";
		pennTagSet = new File(pennTagFileLocation);
		LoadPOS(pennTagSet);*/
		fileContent = "";
		text = new Text();
	}
	
	private void PrepareStatements() {
		String statement = "insert into penntag.partofspeech(tag_name) values (?)";
		try {
			preparedAddPOS = connection.prepareStatement(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		statement = "insert into penntag.word(word) values (?)";
		try {
			preparedAddWord = connection.prepareStatement(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args)
	{
		DataManager dataManager = new DataManager();
		dataManager.run();
	}

	private void run()
	{
		try {
			trainingFile = new File(trainingFileName);
			learner = new Learner(trainingFile, this);
			inputFile = new File(inputFileName);
			outputFile = new File(outputFileName);
			Load(inputFile);
			tokenizer = new tokenize.Tokenize(this);
			tokenizer.tokenizeText(text);
			tagger = new Tagger(text);
			tagger.tag();
			Save(outputFile);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void LoadPOS(File file) {
		Scanner posScanner = null;
		try {
			posScanner = new Scanner(file);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		while(posScanner.hasNextLine()) {
			String tag_name = posScanner.nextLine();
			try {
				preparedAddPOS.clearParameters();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				preparedAddPOS.setString(1, tag_name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				preparedAddPOS.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			ex.printStackTrace();
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
	
	public void startConnection() {
		//add MySQl driver even though I don't think this is specifically necessary
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Get connection to database			
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/security", "root", "3330db003");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addWord(PartOfSpeech previousPOS, String word, PartOfSpeech currentPOS) {			
		try {
			preparedAddWord.clearParameters();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedAddWord.setString(1, word);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			preparedAddWord.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Word queryWord(String word) {
		return learner.getCopyWord(word);
	}
}