package twitter.auth;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAPIAuth {

	private static Twitter twitter;

	public static Twitter getAccess() {

		TwitterAPI config = new TwitterAPI();
		try {
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.setOAuthConsumerKey(config.getOAuthConsumerKey());
			builder.setOAuthConsumerSecret(config.getOAuthConsumerSecret());
			Configuration configuration = builder.build();
			TwitterFactory factory = new TwitterFactory(configuration);

			twitter = factory.getInstance();
			AccessToken accessToken = new AccessToken(config.getAccessTokenKey(), config.getAccessTokenSecret());
			twitter.setOAuthAccessToken(accessToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitter;

	}

}
