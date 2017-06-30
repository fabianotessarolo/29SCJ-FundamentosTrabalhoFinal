package twitter.wrappers;

import java.io.Serializable;
import java.time.LocalDate;

public class TweetAnalyticsWrapper implements Serializable  {

	private static final long serialVersionUID = 8431851801759305419L;
	private LocalDate date;
	private int totalTweets;
	private int totalRetweets;
	private int totalFavorites;

	public TweetAnalyticsWrapper() {
		super();
	}

	public TweetAnalyticsWrapper(LocalDate date, int totalTweets, int totalRetweets, int totalFavorites) {
		super();
		this.date = date;
		this.totalTweets = totalTweets;
		this.totalRetweets = totalRetweets;
		this.totalFavorites = totalFavorites;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getTotalTweets() {
		return totalTweets;
	}

	public void setTotalTweets(int totalTweets) {
		this.totalTweets = totalTweets;
	}

	public int getTotalRetweets() {
		return totalRetweets;
	}

	public void setTotalRetweets(int totalRetweets) {
		this.totalRetweets = totalRetweets;
	}

	public int getTotalFavorites() {
		return totalFavorites;
	}

	public void setTotalFavorites(int totalFavorites) {
		this.totalFavorites = totalFavorites;
	}

	@Override
	public String toString() {
		return "TweetAnalyticsWrapper [date=" + date + ", totalTweets=" + totalTweets + ", totalRetweets="
				+ totalRetweets + ", totalFavorites=" + totalFavorites + "]";
	}

}
