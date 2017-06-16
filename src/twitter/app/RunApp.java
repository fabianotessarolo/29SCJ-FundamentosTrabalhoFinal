package twitter.app;

import twitter.configuration.Config;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class RunApp {

	public static void main(String[] args) {
		
		
		Config config = new Config();		
		try {
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.setOAuthConsumerKey(config.getOAuthConsumerKey());
			builder.setOAuthConsumerSecret(config.getOAuthConsumerSecret());
			Configuration configuration = builder.build();
			TwitterFactory factory = new TwitterFactory(configuration);

			Twitter twitter = factory.getInstance();
			AccessToken accessToken = loadAccessToken();
			twitter.setOAuthAccessToken(accessToken);
			Status status = twitter.updateStatus("Ola Twitter!");
			System.out.println("Tweet postado com sucesso! [" + status.getText() + "].");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static AccessToken loadAccessToken() {
		
		Config config = new Config();
		return new AccessToken(config.getAccessTokenKey(), config.getAccessTokenSecret());
	}
}
