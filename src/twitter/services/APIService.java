package twitter.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import twitter.auth.TwitterAPIAuth;
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
	
	public List<TweetAnalyticsWrapper> getAnalytics(List<TweetWrapper> tweetsWrapper) {
		
		TweetAnalyticsWrapper analytic = null;
		List<TweetAnalyticsWrapper> analytics = new ArrayList<>();

		for (TweetWrapper tweetWrapper : tweetsWrapper) {
			
			int totalTweets = tweetWrapper.getTweets().size();
			int totalRetweets = 5;
			int totalFavorites = 6;
			//TODO: ARRUMAR STREAM
//			int totalRetweets = tweetWrapper.getTweets().stream().map(e -> e.getRetweetsCount()).reduce(0L, Integer::sum));
//			int totalFavorites = tweetWrapper.getTweets().stream().map(e -> e.getFavoritesCount()).reduce(0L, Integer::sum));
//			
			analytic = new TweetAnalyticsWrapper(tweetWrapper.getDate(), totalTweets, totalRetweets, totalFavorites);
			analytics.add(analytic);
		}

		return analytics;
		
	}

	public List<TweetWrapper> getTweetsLastWeek(String hashtag) {
		
		List<Tweet> tweets = new ArrayList<>();
		List<TweetWrapper> tweetsWrapper = new ArrayList<>();
		Twitter twitter = TwitterAPIAuth.getAccess();

		Tweet tweet = null;
		TweetWrapper tweetWrapper = null;
		LocalDate today = DateUtil.today();
		LocalDate startDate = DateUtil.pastDate(7);
		
		while (startDate.isBefore(today)) {
			Query query = new Query(hashtag);
			
			LocalDate yesterday = startDate.minusDays(1);
			query.setSince(DateUtil.formatter(yesterday));
			query.setUntil(DateUtil.formatter(startDate));
			query.setCount(1);
			
			QueryResult result;
			
			try {
				result = twitter.search(query);
				
				while (result.hasNext()) {
					query = result.nextQuery();
					for (Status status : result.getTweets()) {
						tweet = new Tweet(status);
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

	public void post(Tweet tweet){
		try {
			Twitter twitter = TwitterAPIAuth.getAccess();
			twitter.updateStatus(tweet.getMessage());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
