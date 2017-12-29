package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Utils.CSVFileCreator;
/*
 * Saves data and closes program
 */
public class EndingListener implements ActionListener
{
	private SearchListener searchListener;
	private String filePath = "";

	private JFileChooser fileChooser;
	private File file;
	private CSVFileCreator csvFile;
	
	private int fc;
	
	
	public EndingListener(SearchListener searchEar) {
		setSearchListener(searchEar);
	}

	public void actionPerformed(ActionEvent e)
	{
		setFileChooser(new JFileChooser(getFilePath()));//opens dialog box for selecting save location
	   	getFileChooser().setDialogTitle("Select File Name and Save Location");
		getFileChooser().setDialogTitle("Select File Name and Save Location");
		
		setFc(fileChooser.showSaveDialog(null));//opens file dialog box to select file save location 
		if(getFc() == JFileChooser.APPROVE_OPTION)
		{
	   		setFile(getFileChooser().getSelectedFile()); //gets pathway for file
	        setFilePath(getFile().getAbsolutePath() + ".csv");//returns selected file pathway as string to be used in creating csv
		}
		
		try
		{
			setCsvFile(new CSVFileCreator(getSearchListener().getData(), getFilePath()));//create csv writer
			getCsvFile().makeFile();//write to file
		}
		catch(Exception exc)
		{
			System.out.println("Error creating CSVFile...");
		}
		
		JOptionPane.showMessageDialog(null,  "CSV File Successfully Created. Program Will Now End.");
		System.exit(0);
	}	
	
	public SearchListener getSearchListener() {
		return searchListener;
	}
	
	public void setSearchListener(SearchListener searchListener) {
		this.searchListener = searchListener;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public CSVFileCreator getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(CSVFileCreator csvFile) {
		this.csvFile = csvFile;
	}

	public int getFc() {
		return fc;
	}

	public void setFc(int fc) {
		this.fc = fc;
	}
	
	
	
}


