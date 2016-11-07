import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/*
 * Captures OAuth data from user and stores in dedicated text files for use during authentication process when opening stream
 */
public class ConfigurationManager implements ActionListener
{
	String OAuthConsumerKey = "", OAuthConsumerSecret = "", OAuthAccessToken = "", OAuthAccessTokenSecret = "";
	String consumerKey = "consumerKey", consumerSecret = "consumerSecret", accessToken = "accessToken", accessTokenSecret = "accessTokenSecret";
	
	public void actionPerformed(ActionEvent d)
	{
		//set/reset strings to null
		OAuthConsumerKey = "";
		OAuthConsumerSecret = "";
		OAuthAccessToken = "";
		OAuthAccessTokenSecret = "";
		
		//get from user
		OAuthConsumerKey = JOptionPane.showInputDialog("Copy & Paste your OAuthConsumerKey Here:");
		try
		{
			BufferedWriter consumerKeyWriter = new BufferedWriter(new FileWriter("consumerKey"));
			consumerKeyWriter.write(OAuthConsumerKey);
			consumerKeyWriter.close();
		}
		catch(Exception ckErr)
		{
			System.out.println("Error writing consumer key to file. This data was most likely not saved...");
		}
		
		//get from user
		OAuthConsumerSecret = JOptionPane.showInputDialog("Copy & Paste your OAuthConsumerSecret Here:");
		try
		{
			BufferedWriter consumerSecretWriter = new BufferedWriter(new FileWriter("consumerSecret"));
			consumerSecretWriter.write(OAuthConsumerSecret);
			consumerSecretWriter.close();
		}
		catch(Exception csErr)
		{
			System.out.println("Error writing consumer secret to file. This data was most likely not saved...");
		}
		
		//get from user
		OAuthAccessToken = JOptionPane.showInputDialog("Copy & Paste your OAuthAccessToken Here:");
		try
		{
			BufferedWriter accessTokenWriter = new BufferedWriter(new FileWriter("accessToken"));
			accessTokenWriter.write(OAuthAccessToken);
			accessTokenWriter.close();
		}
		catch(Exception asErr)
		{
			System.out.println("Error writing access token to file. This data was most likely not saved...");
		}
		
		//get from user
		OAuthAccessTokenSecret = JOptionPane.showInputDialog("Copy your OAuthAccessTokenSecret Here:");
		try
		{
			BufferedWriter accessTokenSecretWriter = new BufferedWriter(new FileWriter("accessTokenSecret"));
			accessTokenSecretWriter.write(OAuthAccessTokenSecret);
			accessTokenSecretWriter.close();
		}
		catch(Exception tsErr)
		{
			System.out.println("Error writing access token secret to file. This data was most likely not saved...");
		}
		
		// confirm to user
		JOptionPane.showMessageDialog(null,  "Your OAuth data has been saved.");
	}
}
