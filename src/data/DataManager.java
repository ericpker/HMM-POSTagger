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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
	private String activeCorpus = "";
	
	PreparedStatement addTagSet = null;
	PreparedStatement addTaggedCorpus = null;
	PreparedStatement addPOS = null;
	PreparedStatement queryPOS = null;
	PreparedStatement addWord = null;
	PreparedStatement queryWord = null;
	PreparedStatement queryWordFull = null;
	
	public DataManager()
	{
		startConnection();
		PrepareStatements();
		activeCorpus = "BLT";
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
		
		String statement = "insert into corpus.tag_set(tag_set_name) values (?)";
		try {
			addTagSet = connection.prepareStatement(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		statement = "insert into corpus.tagged_corpus(corpus_name,tag_set_name) values (?,?)";
		try {
			addTaggedCorpus = connection.prepareStatement(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		statement = "insert into corpus.partofspeech(tag_name,description,tag_set_name) values (?,?,?)";
		try {
			addPOS = connection.prepareStatement(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		statement = "select * from corpus.tag where tag_name = ? and tag_set_name = ?";
		try {
			queryPOS = connection.prepareStatement(statement);
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
				addPOS.clearParameters();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				addPOS.setString(1, tag_name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				addPOS.executeUpdate();
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
		String statement = "insert into corpus.word(word,count,corpus_name) values (?,?,?)";
		try {
			addWord = connection.prepareStatement(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			addWord.clearParameters();
			addWord.setString(1, word);
			addWord.setLong(2, 0);
			addWord.setString(3, this.activeCorpus);
			addWord.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Word queryWord(String searchWord) {
		String statement = "select * from corpus.word where word = ?";
		try {
			queryWord = connection.prepareStatement(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet result = null;
		try {
			queryWord.clearParameters();
			queryWord.setString(1, searchWord);
			result = queryWord.executeQuery();
			Word word = new Word(result.getString(0),result.getInt(1));
			return word;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public Word queryWordProb(String searchWord) {
		return null;
		
	}
}