package twitter.utils;

import java.util.Comparator;

import twitter.model.Tweet;

public class DataComparator implements Comparator<Tweet> {

	@Override
	public int compare(Tweet tweet1, Tweet tweet2) {
		return tweet1.getDataTweet().compareTo(tweet2.getDataTweet());
	}

}
