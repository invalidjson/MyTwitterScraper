package Utils;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import au.com.bytecode.opencsv.CSVWriter;
public class CSVFileCreator
{
	List<String[]> localData = new ArrayList<String[]>();
	String localFileName = "";
	
	//takes in List<String[]> data from SearchListener class -- this is the aggregate twitter data that was scraped in the session
	public CSVFileCreator(List<String[]> list, String fileName)
	{
		localData = list;
		localFileName = fileName;
	}
	
	public void makeFile()
	{
		try
		{
			CSVWriter writer = new CSVWriter(new FileWriter(localFileName));
			writer.writeAll(localData);
			writer.close();
		}
		catch(Exception err)
		{
			System.out.println("Error loading CSVWriter");
			err.printStackTrace(System.err);
		}
	}
}
