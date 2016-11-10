import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
/*
 * Central class to application. Connects to twitter, authenticates user, opens twitter stream, stores data
 */
public class SearchListener implements ActionListener
{
	String filePath = "", OAuthConsumerKey = "", OAuthConsumerSecret = "", OAuthAccessToken = "", OAuthAccessTokenSecret = "";
	String consumerKey = "consumerKey", consumerSecret = "consumerSecret", accessToken = "accessToken", accessTokenSecret = "accessTokenSecret";
	public static String dateString, content, username, profileLocation;//static twitter ouput vars
	public static List<String[]> data = new ArrayList<String[]>();//ArrayList for twitter stream data
	int total = 0;
		
	public void actionPerformed(ActionEvent e)
	{
		MainGui.standardOut = System.out;//re-assigns standard output stream and error to text area
		System.setOut(MainGui.printStream);//re-assigns standard output stream to printStream var
		System.setErr(MainGui.printStream);//re-assigns error stream to printStream var
		try
		{
			BufferedReader consumerKeyReader = new BufferedReader(new FileReader(consumerKey));
			OAuthConsumerKey = consumerKeyReader.readLine();
			consumerKeyReader.close();
		}
		catch(Exception consumerKeyReadErr)
		{
			System.out.println("Error reading consumer key from file...");
		}
		try
		{
			BufferedReader consumerSecretReader = new BufferedReader(new FileReader(consumerSecret));
			OAuthConsumerSecret = consumerSecretReader.readLine();
			consumerSecretReader.close();
		}
		catch(Exception consumerSecretReadErr)
		{
			System.out.println("Error reading consumer secret from file...");
		}
		try
		{
			BufferedReader accessTokenReader = new BufferedReader(new FileReader(accessToken));
			OAuthAccessToken = accessTokenReader.readLine();
			accessTokenReader.close();
		}
		catch(Exception accessTokenReadErr)
		{
			System.out.println("Error reading access token from file...");
		}
		try
		{
			BufferedReader accessTokenSecretReader = new BufferedReader(new FileReader(accessTokenSecret));
			OAuthAccessTokenSecret = accessTokenSecretReader.readLine();
			accessTokenSecretReader.close();
		}
		catch(Exception accessTokenSecretReaderErr)
		{
			System.out.println("Error reading access token secret from file...");
		}
		
		//Twitter4J code block:
		ConfigurationBuilder c = new ConfigurationBuilder();
		c.setDebugEnabled(true);
		c.setOAuthConsumerKey(OAuthConsumerKey);
		c.setOAuthConsumerSecret(OAuthConsumerSecret);
		c.setOAuthAccessToken(OAuthAccessToken);
		c.setOAuthAccessTokenSecret(OAuthAccessTokenSecret);
		TwitterStream twitStream = new TwitterStreamFactory(c.build()).getInstance();
		StatusListener statusEar = new StatusListener()
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
				User user = status.getUser();//gets user
				username = status.getUser().getScreenName();// gets username
				profileLocation = user.getLocation();//location data
				content = status.getText();//tweet content
				dateString = status.getCreatedAt().toString();//time of tweet
				System.out.println("\nTime: " + dateString);
				System.out.println("By: " + username);
				System.out.println("From: " + profileLocation);
				System.out.println("Tweet: " + content + "\n");
				String[] rowData = {dateString, username, profileLocation, content};
				data.add(rowData);
				total += 1;//counts total number of captured treats
				if(total%100==0)//on multiples of 100 -- or on every 100 tweets...
				{	
					MainGui.jta.setText("");//resets text to blank screen -- long scrolling screen takes lots of ram, so reset to keep size contained.
				}
				MainGui.totalCount.setText(String.valueOf(total));
			}
			
			public void onTrackLimitationNotice(int arg0)
			{
				//nothing here
			}
			
			public void onStallWarning(StallWarning warning)
			{
				//nothing here
			}
		};
		FilterQuery f = new FilterQuery();
		String x = MainGui.searchTerms.getText();
		String keywords[] = {x};
		f.track(keywords);
		twitStream.addListener(statusEar);
		twitStream.filter(f);
	}
}
