package twitter.model;

import java.util.Comparator;

public class DataComparator implements Comparator<Tweet> {

	@Override
	public int compare(Tweet tweet1, Tweet tweet2) {
		return tweet1.getDataTweet().compareTo(tweet2.getDataTweet());
	}

}
