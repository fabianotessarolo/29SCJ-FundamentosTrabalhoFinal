package twitter.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	
	private String OAuthConsumerKey = null;
	private String OAuthConsumerSecret = null;
	private String AccessTokenKey = null;
	private String AccessTokenSecret = null;

	public Config() {

		Properties prop = new Properties();
		InputStream input = null;
		
		

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value
			this.OAuthConsumerKey = prop.getProperty("OAuthConsumerKey");
			this.OAuthConsumerSecret = prop.getProperty("OAuthConsumerSecret");
			this.AccessTokenKey = prop.getProperty("AccessTokenKey");
			this.AccessTokenSecret = prop.getProperty("AccessTokenSecret");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public String getOAuthConsumerKey() {
		return OAuthConsumerKey;
	}

	public String getOAuthConsumerSecret() {
		return OAuthConsumerSecret;
	}

	public String getAccessTokenKey() {
		return AccessTokenKey;
	}

	public String getAccessTokenSecret() {
		return AccessTokenSecret;
	}	
	
	
}
