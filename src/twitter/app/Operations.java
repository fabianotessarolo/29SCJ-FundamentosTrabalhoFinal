package twitter.app;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Operations implements OperationsInterface {

	@Override
	public int countTweetsLastWeek(String hashtag) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countRetweetsLastWeek(String hashtag) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countFavoritesLastWeek(String hashtag) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> orderTweetsByAuthor(String hashtag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> orderTweetsByDate(String hashtag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String tweet(String who, String message) {
		Twitter twitter = Factory.get();
		Status status = null;
		try {
			status = twitter.updateStatus(message + " @" + who);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Tweet postado com sucesso! [" + status.getText() + "].";
	}

}
