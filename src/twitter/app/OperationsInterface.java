package twitter.app;

public interface OperationsInterface {
	
	public int getTweetsLastWeek(String hashtag);
	public String tweet(String who, String message);
}
