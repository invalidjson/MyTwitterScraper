package GUI;

import javax.swing.*;

import Listeners.EndingListener;
import Listeners.SearchListener;
import Utils.ConfigurationManager;
import Utils.CustomOutputStream;

import java.awt.*;
import java.io.PrintStream;

/*
 * initializes all gui elements. Also sets buttons and action listeners. Launches SearchListener from this class.
 */
public class MainGui 
{
	private final int col = 110, rows = 40; //dims for jtextarea (output field) --> 140 char == max tweet length
	
	private JTextField searchTerms = new JTextField(20);//search query input field object
	private JTextField totalCount = new JTextField(10);//total count output window
	
	private JTextArea jta = new JTextArea("", rows, col);//output box object
	private JScrollPane jsp = new JScrollPane(getJta(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
	
	private CustomOutputStream cos; 
	
	//redirects console output to scrollpane on UI
	//new printstream for jtextarea
	private PrintStream standardOut, printStream;

	private SearchListener searchEar;
	private EndingListener endingEar;
	private ConfigurationManager configurationEar;

	//top container of UI - contains file name input box, search terms input box, and action buttons
	//center container of UI - contains scroll pane for output
	// bottom container of UI
	private JPanel topPanel, centerPanel, bottomPanel;

    //windows labels:
	private JLabel totalTweets, info, fileNamePrompt, spacer;
	
    //Action Buttons
	private JButton endButton, searchButton, configure;
 
	private FlowLayoutJFrame gui;//gui layout

	
	public void buildGui()
    {	 
		setCos(new CustomOutputStream(getJta()));
		setPrintStream(new PrintStream(getCos()));
		
    	setTopPanel(new JPanel());
    	setCenterPanel(new JPanel());
    	setBottomPanel(new JPanel());

        //windows labels:
        setTotalTweets(new JLabel("   Total Tweets: "));
        setInfo(new JLabel(" Enter Search Terms: "));
        setFileNamePrompt(new JLabel(" Session File Name: "));
    		
        //Action Buttons
        setEndButton(new JButton("Save & Close"));
        setSearchButton(new JButton("Capture Twitter Stream"));
        setConfigure(new JButton("OAuth Config"));
        
        setSpacer(new JLabel("       "));
        
    	setGui(new FlowLayoutJFrame());
		
		getJsp().setBounds(0, 0, 1000, 750);
		
		getJta().setOpaque(true);
		getJta().setBackground(Color.black);
		getJta().setForeground(Color.green);
		
		getSearchTerms().setOpaque(true);
		getSearchTerms().setBackground(Color.black);
		getSearchTerms().setForeground(Color.green);
		getTotalCount().setBackground(Color.black);
		getTotalCount().setForeground(Color.green);
		
		getTopPanel().setBackground(Color.DARK_GRAY);
		getCenterPanel().setBackground(Color.DARK_GRAY);
		getBottomPanel().setBackground(Color.DARK_GRAY);
		
		//window labels
		getTotalTweets().setForeground(Color.white);
		getInfo().setForeground(Color.white);
		getFileNamePrompt().setForeground(Color.white);
		
		getGui().setSize(1336,768);
		getGui().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//gui default buttons
		getGui().getContentPane().setBackground(Color.darkGray);
		
		setSearchEar(new SearchListener(this));
		setEndingEar(new EndingListener(searchEar));
		setConfigurationEar(new ConfigurationManager());
		
		//buttons
		getEndButton().addActionListener(endingEar);
		getSearchButton().addActionListener(searchEar);
		getConfigure().addActionListener(configurationEar);
		
		//gui initialization
		getTopPanel().add(getInfo());
		getTopPanel().add(getSearchTerms());
		getTopPanel().add(getSpacer());
		getTopPanel().add(getSearchButton());
		getTopPanel().add(getEndButton());
		getTopPanel().add(getTotalTweets());
		getTopPanel().add(getTotalCount());
		getTopPanel().add(getSpacer());
		getTopPanel().add(getConfigure());
		getCenterPanel().add(getJsp());
		
		getGui().add(getTopPanel());
		getGui().add(getCenterPanel());	
		getGui().setVisible(true);
    }

	
    public JTextField getSearchTerms() {
		return searchTerms;
	}

	public void setSearchTerms(JTextField searchTerms) {
		this.searchTerms = searchTerms;
	}

	public JTextField getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(JTextField totalCount) {
		this.totalCount = totalCount;
	}

	public JTextArea getJta() {
		return jta;
	}

	public void setJtaText(String newText) 
	{
		getJta().setText(newText);
	}

	public JScrollPane getJsp() {
		return jsp;
	}

	public void setJsp(JScrollPane jsp) {
		this.jsp = jsp;
	}
	
	public PrintStream getStandardOut() {
		return standardOut;
	}

	public void setStandardOut(PrintStream standardOut) {
		this.standardOut = standardOut;
	}

	public PrintStream getPrintStream() {
		return printStream;
	}

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public CustomOutputStream getCos() {
		return cos;
	}

	public void setCos(CustomOutputStream cos) {
		this.cos = cos;
	}

	public void setJta(JTextArea jta) {
		this.jta = jta;
	}

	public SearchListener getSearchEar() {
		return searchEar;
	}

	public void setSearchEar(SearchListener searchEar) {
		this.searchEar = searchEar;
	}

	public EndingListener getEndingEar() {
		return endingEar;
	}

	public void setEndingEar(EndingListener endingEar) {
		this.endingEar = endingEar;
	}

	public ConfigurationManager getConfigurationEar() {
		return configurationEar;
	}

	public void setConfigurationEar(ConfigurationManager configurationEar) {
		this.configurationEar = configurationEar;
	}

	public int getRows() {
		return rows;
	}

	public int getCol() {
		return col;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}

	public JLabel getTotalTweets() {
		return totalTweets;
	}

	public void setTotalTweets(JLabel totalTweets) {
		this.totalTweets = totalTweets;
	}

	public JLabel getInfo() {
		return info;
	}

	public void setInfo(JLabel info) {
		this.info = info;
	}

	public JLabel getFileNamePrompt() {
		return fileNamePrompt;
	}

	public void setFileNamePrompt(JLabel fileNamePrompt) {
		this.fileNamePrompt = fileNamePrompt;
	}

	public JLabel getSpacer() {
		return spacer;
	}

	public void setSpacer(JLabel spacer) {
		this.spacer = spacer;
	}

	public JButton getEndButton() {
		return endButton;
	}

	public void setEndButton(JButton endButton) {
		this.endButton = endButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public JButton getConfigure() {
		return configure;
	}

	public void setConfigure(JButton configure) {
		this.configure = configure;
	}

	public FlowLayoutJFrame getGui() {
		return gui;
	}

	public void setGui(FlowLayoutJFrame gui) {
		this.gui = gui;
	}
	
}



