package twitter.app;

import twitter.configuration.Config;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class RunApp {

	public static void main(String[] args) {

		Twitter twitter = new Factory().get();

		String selectedHashTag = "#openjdk";

			Status status = null;
			
			try {
				status = twitter.updateStatus("Ola Twitter2!");
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Tweet postado com sucesso! [" + status.getText() + "].");
		
	}
}
