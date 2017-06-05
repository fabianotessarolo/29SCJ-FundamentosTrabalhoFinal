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
			builder.setOAuthConsumerKey("key");
			builder.setOAuthConsumerSecret("key");
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
		String token = "key";
		String tokenSecret = "key";
		return new AccessToken(token, tokenSecret);
	}
}
