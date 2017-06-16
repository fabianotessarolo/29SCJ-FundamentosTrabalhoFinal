package twitter.app;

import java.util.List;

public interface OperationsInterface {
	
	public int countTweetsLastWeek(String hashtag);
	public int countRetweetsLastWeek(String hashtag);
	public int countFavoritesLastWeek(String hashtag);
	public List<String> orderTweetsByAuthor(String hashtag);
	public List<String> orderTweetsByDate(String hashtag);
}
