package twitter.comparators;

import java.util.Comparator;

import twitter.models.Tweet;

public class TweetComparator implements Comparator<Tweet> {

	@Override
	public int compare(Tweet tweet1, Tweet tweet2) {
		return tweet1.getDate().compareTo(tweet2.getDate());
	}

}
