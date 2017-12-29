package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import GUI.MainGui;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
/*
 * Central class to application. Connects to twitter, authenticates user, opens twitter stream, stores data
 */
public class SearchListener implements ActionListener
{
	String filePath = "", OAuthConsumerKey = "", OAuthConsumerSecret = "", OAuthAccessToken = "", OAuthAccessTokenSecret = "";
	String consumerKey = "consumerKey", consumerSecret = "consumerSecret", accessToken = "accessToken", accessTokenSecret = "accessTokenSecret";
	
	private String dateString, content, username, profileLocation;//static twitter ouput vars
	private List<String[]> data = new ArrayList<String[]>();//ArrayList for twitter stream data
	private ConfigurationBuilder newConfig;
	private MainGui gui;
	private int total = 0;

	private TwitterStream twitStream;
	private StatusListener statusEar;
	
	private User user;
	
	private String[] rowData;

	private FilterQuery f;
	private String x;
	private String keywords[];
	
	public SearchListener(MainGui guiArg) {
		setGui(guiArg);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		authorizeApplication();
		setNewConfig(getBuildConfig());
		startTwitterListener(getNewConfig());
	}

	private void startTwitterListener(ConfigurationBuilder newConfig) {
		setTwitStream(new TwitterStreamFactory(getNewConfig().build()).getInstance());
		setStatusEar(new StatusListener()
		{
			public void 
			onException(Exception arg0)
			{
				//nothing here
			}
			
			public void onDeletionNotice(StatusDeletionNotice arg0)
			{
				//nothing here
			}
			
			public void onScrubGeo(long arg0, long arg1)
			{
				//nothing here
			}
			
			public void onStatus(Status status)// scrape all the twitters!!!
			{
				setUser(status.getUser());//gets user
				
				setUsername(status.getUser().getScreenName());// gets username
				setProfileLocation(user.getLocation());//location data
				setContent(status.getText());//tweet content
				setDateString(status.getCreatedAt().toString());//time of tweet
				
				System.out.println("\nTime: " + getDateString());
				System.out.println("By: " + getUsername());
				System.out.println("From: " + getProfileLocation());
				System.out.println("Tweet: " + getContent() + "\n");
				
				String[] localRowData = new String[4];
				
				localRowData[0] = getDateString();
				localRowData[1] = getUsername();
				localRowData[2] = getProfileLocation();
				localRowData[3] = getContent();
				
				setRowData(localRowData);
				
				getData().add(getRowData());
				
				total += 1;//counts total number of captured treats
				
				if(total%100==0)//on multiples of 100 -- or on every 100 tweets...
				{	
					getGui().setJtaText("");//resets text to blank screen -- long scrolling screen takes lots of ram, so reset to keep size contained.
				}
				
				getGui().getTotalCount().setText(String.valueOf(total));
			}
			
			@Override
			public void onTrackLimitationNotice(int arg0)
			{
				//nothing here
			}
			
			@Override
			public void onStallWarning(StallWarning warning)
			{
				//nothing here
			}
		});
		
		setF(new FilterQuery());
		setX(gui.getSearchTerms().getText());
		String localKeywords[] = {x};
		setKeywords(localKeywords);
		getF().track(getKeywords());
		getTwitStream().addListener(getStatusEar());
		getTwitStream().filter(getF());
		
	}

	private ConfigurationBuilder getBuildConfig()
	{
		//Twitter4J code block:
		ConfigurationBuilder localConfigBuilder = new ConfigurationBuilder();
		
		localConfigBuilder.setDebugEnabled(true);
		localConfigBuilder.setOAuthConsumerKey(getOAuthConsumerKey());
		localConfigBuilder.setOAuthConsumerSecret(getOAuthConsumerSecret());
		localConfigBuilder.setOAuthAccessToken(getOAuthAccessToken());
		localConfigBuilder.setOAuthAccessTokenSecret(getOAuthAccessTokenSecret());
		
		return localConfigBuilder;
	}

	private void authorizeApplication() {
		try
		{
			BufferedReader consumerKeyReader = new BufferedReader(new FileReader(getConsumerKey()));
			setOAuthConsumerKey(consumerKeyReader.readLine());
			consumerKeyReader.close();
		}
		catch(Exception consumerKeyReadErr)
		{
			System.out.println("Error reading consumer key from file...");
		}
		
		try
		{
			BufferedReader consumerSecretReader = new BufferedReader(new FileReader(getConsumerSecret()));
			setOAuthConsumerSecret(consumerSecretReader.readLine());
			consumerSecretReader.close();
		}
		
		catch(Exception consumerSecretReadErr)
		{
			System.out.println("Error reading consumer secret from file...");
		}
		
		try
		{
			BufferedReader accessTokenReader = new BufferedReader(new FileReader(getAccessToken()));
			setOAuthAccessToken(accessTokenReader.readLine());
			accessTokenReader.close();
		}
		catch(Exception accessTokenReadErr)
		{
			System.out.println("Error reading access token from file...");
		}
		try
		{
			BufferedReader accessTokenSecretReader = new BufferedReader(new FileReader(getAccessTokenSecret()));
			setOAuthAccessTokenSecret(accessTokenSecretReader.readLine());
			accessTokenSecretReader.close();
		}
		catch(Exception accessTokenSecretReaderErr)
		{
			System.out.println("Error reading access token secret from file...");
		}
		
	}

	// ============ ACCESS METHODS ==================== //
	
	public List<String[]> getData() {
		return data;
	}

	public void setData(List<String[]> data) {
		this.data = data;
	}

	public TwitterStream getTwitStream() {
		return twitStream;
	}

	public void setTwitStream(TwitterStream twitStream) {
		this.twitStream = twitStream;
	}

	public StatusListener getStatusEar() {
		return statusEar;
	}

	public void setStatusEar(StatusListener statusEar) {
		this.statusEar = statusEar;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getRowData() {
		return rowData;
	}

	public void setRowData(String[] rowData) {
		this.rowData = rowData;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOAuthConsumerKey() {
		return OAuthConsumerKey;
	}

	public void setOAuthConsumerKey(String oAuthConsumerKey) {
		OAuthConsumerKey = oAuthConsumerKey;
	}

	public String getOAuthConsumerSecret() {
		return OAuthConsumerSecret;
	}

	public void setOAuthConsumerSecret(String oAuthConsumerSecret) {
		OAuthConsumerSecret = oAuthConsumerSecret;
	}

	public String getOAuthAccessToken() {
		return OAuthAccessToken;
	}

	public void setOAuthAccessToken(String oAuthAccessToken) {
		OAuthAccessToken = oAuthAccessToken;
	}

	public String getOAuthAccessTokenSecret() {
		return OAuthAccessTokenSecret;
	}

	public void setOAuthAccessTokenSecret(String oAuthAccessTokenSecret) {
		OAuthAccessTokenSecret = oAuthAccessTokenSecret;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfileLocation() {
		return profileLocation;
	}

	public void setProfileLocation(String profileLocation) {
		this.profileLocation = profileLocation;
	}

	public ConfigurationBuilder getNewConfig() {
		return newConfig;
	}

	public void setNewConfig(ConfigurationBuilder newConfig) {
		this.newConfig = newConfig;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public MainGui getGui() {
		return gui;
	}

	public void setGui(MainGui gui) {
		this.gui = gui;
	}

	public FilterQuery getF() {
		return f;
	}

	public void setF(FilterQuery f) {
		this.f = f;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	
	
}


