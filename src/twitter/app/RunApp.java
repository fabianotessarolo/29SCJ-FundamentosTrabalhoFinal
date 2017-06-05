package twitter.app;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class RunApp {

	public static void main(String[] args) {
		try {
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.setOAuthConsumerKey("AidkAMQsmUr4NAza9TRz9wMsC");
			builder.setOAuthConsumerSecret("HoS31kBwXtnfqKLOVuNHcEBncUInmsvQIXFCAhCynlolaqTaA9");
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
		String token = "870724372163645440-aca7y32h9EVeWW7AzmGC4Ya4Dt3dQ1u";
		String tokenSecret = "Ot6rrmMtQKysRwmYuepZFOPxS4QttULVAaB2ARRLCccSj";
		return new AccessToken(token, tokenSecret);
	}
}
