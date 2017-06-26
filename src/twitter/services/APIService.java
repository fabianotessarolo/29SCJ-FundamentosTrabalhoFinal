package twitter.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import twitter.auth.TwitterAPIAuth;
import twitter.comparators.TweetComparator;
import twitter.models.Tweet;
import twitter.utils.DateUtil;
import twitter.wrappers.TweetAnalyticsWrapper;
import twitter.wrappers.TweetWrapper;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class APIService {

	private TweetComparator comparator = new TweetComparator();

	public List<TweetWrapper> getTweetsLastWeek(String hashtag) {
		List<Tweet> tweets = null;
		List<TweetWrapper> tweetsWrapper = new ArrayList<>();
		Twitter twitter = TwitterAPIAuth.getAccess();

		Tweet tweet = null;
		TweetWrapper tweetWrapper = null;
		LocalDate today = DateUtil.today();
		LocalDate startDate = DateUtil.pastDate(7);

		while (startDate.isBefore(today)) {
			Query query = new Query(hashtag);
			tweets = new ArrayList<>();
			LocalDate yesterday = startDate.minusDays(1);
			query.setSince(DateUtil.formatter(yesterday));
			query.setUntil(DateUtil.formatter(startDate));
			query.setCount(1);

			QueryResult result;

			System.out.println("\nIniciando busca por tweets com a hastag: " + hashtag + " postados desde " + yesterday
					+ " até " + startDate);

			try {
				result = twitter.search(query);

				while (result.hasNext()) {
					query = result.nextQuery();
					for (Status status : result.getTweets()) {
						tweet = new Tweet(status);
						System.out.println(tweet);
						tweets.add(tweet);
					}
					result = twitter.search(query);
				}
				tweetWrapper = new TweetWrapper(startDate, tweets);
				tweetsWrapper.add(tweetWrapper);
				startDate = startDate.plusDays(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tweetsWrapper;
	}

	public List<TweetAnalyticsWrapper> getAnalytics(List<TweetWrapper> tweetsWrapper) {
		TweetAnalyticsWrapper analytic = null;
		List<TweetAnalyticsWrapper> analytics = new ArrayList<>();

		for (TweetWrapper tweetWrapper : tweetsWrapper) {
			int totalTweets = tweetWrapper.getTweets().size();
			int totalRetweets = tweetWrapper.getTweets().stream().mapToInt(Tweet::getRetweetsCount).sum();
			int totalFavorites = tweetWrapper.getTweets().stream().mapToInt(Tweet::getFavoritesCount).sum();

			analytic = new TweetAnalyticsWrapper(tweetWrapper.getDate(), totalTweets, totalRetweets, totalFavorites);
			analytics.add(analytic);
		}
		return analytics;
	}

	public List<Tweet> getAllTweets(List<TweetWrapper> tweetsWrapper) {

		List<Tweet> allTweets = new ArrayList<>();
		for (TweetWrapper tweetWrapper : tweetsWrapper) {
			tweetWrapper.getTweets().stream().forEachOrdered(allTweets::add);
		}
		return allTweets;
	}

	public void sortByAuthorAsc(List<Tweet> allTweets) {
		Collections.sort(allTweets);
	}

	public void sortByAuthorDesc(List<Tweet> allTweets) {
		allTweets.sort(Collections.reverseOrder());
	}

	public void sortByDateAsc(List<Tweet> allTweets) {
		Collections.sort(allTweets, comparator);
	}

	public void sortByDateDesc(List<Tweet> allTweets) {
		allTweets.sort(Collections.reverseOrder(comparator));
	}

	public void post(Tweet tweet) {
		try {
			Twitter twitter = TwitterAPIAuth.getAccess();
			twitter.updateStatus(tweet.getMessage());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
