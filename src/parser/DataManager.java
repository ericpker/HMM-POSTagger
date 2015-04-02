package parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class DataManager {
	private String inputFileName;
	private String outputFileName;
	private String fileContent;
	private File inputFile;
	private File outputFile;
	private Scanner input;
	private Writer writer;
	
	public DataManager()
	{
		inputFileName = "text_1" + ".txt";
		outputFileName = "text_1_tagged" + ".txt";
		fileContent = "";
	}
	
	public static void main(String[] args)
	{
		DataManager dataManager = new DataManager();
		dataManager.run();
	}

	private void run()
	{
		inputFile = new File(inputFileName);
		outputFile = new File(outputFileName);
		Load(inputFile);
		Save(outputFile);
		
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
			
			text.setText(fileContent);
			
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
}