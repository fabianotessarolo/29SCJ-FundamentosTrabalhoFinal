package twitter.wrappers;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import twitter.models.Tweet;

public class TweetWrapper implements Serializable  {

	private static final long serialVersionUID = 3900353995010837616L;
	private LocalDate date;
	private List<Tweet> tweets;

	public TweetWrapper() {
		super();
	}

	public TweetWrapper(LocalDate date, List<Tweet> tweets) {
		super();
		this.date = date;
		this.tweets = tweets;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	@Override
	public String toString() {
		return "TweetWrapper [date=" + date + ", tweets=" + tweets + "]";
	}

}
