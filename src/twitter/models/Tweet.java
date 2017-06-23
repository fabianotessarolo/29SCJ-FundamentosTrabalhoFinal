package twitter.models;

import java.io.Serializable;
import java.util.Date;

import twitter4j.Status;

public class Tweet implements Comparable<Tweet>, Serializable  {

	private static final long serialVersionUID = -4372883939416466460L;
	private String author;
	private String message;
	private Date date;
	private int retweetsCount;
	private int favoritesCount;

	public Tweet() {
		super();
	}

	public Tweet(Status status) {
		super();
		this.author = status.getUser().getName();
		this.date = status.getCreatedAt();
		this.retweetsCount = status.getRetweetCount();
		this.favoritesCount = status.getFavoriteCount();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRetweetsCount() {
		return retweetsCount;
	}

	public void setRetweetsCount(int retweetsCount) {
		this.retweetsCount = retweetsCount;
	}

	public int getFavoritesCount() {
		return favoritesCount;
	}

	public void setFavoritesCount(int favoritesCount) {
		this.favoritesCount = favoritesCount;
	}

	@Override
	public String toString() {
		return "Tweet [nameAuthor=" + author + ", message=" + message + ", date=" + date + ", retweetsCount="
				+ retweetsCount + ", favoritesCount=" + favoritesCount + "]";
	}

	@Override
	public int compareTo(Tweet tweet) {
		return this.getAuthor().compareTo(tweet.getAuthor());
	}

}
