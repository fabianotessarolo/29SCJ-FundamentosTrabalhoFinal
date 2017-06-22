package twitter.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import twitter.auth.TwitterAPIAuth;
import twitter.models.Tweet;
import twitter.utils.DateUtil;
import twitter.wrappers.TweetWrapper;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class APIService {
	
	public List<TweetWrapper> getAnalytics(String hashtag) {
		
		List<Tweet> tweets = this.getTweetsLastWeek(hashtag);
		List<Tweet> tweetsByDate = null;
		List<TweetWrapper> tweetsAnalytics = new ArrayList<>();
		
		TweetWrapper tweetAnaltic = null;
		
		//TODO FAZER FILTRO POR DATA 
//		tweetsByDate = tweets.stream (byDate)
		
		for (Tweet tweet : tweetsByDate) {
			tweetAnaltic = new TweetWrapper();
			tweetAnaltic.setDate(tweet.getDate());
			tweetAnaltic.setTotalTweets(tweets.size());
			tweetAnaltic.setTotalRetweets(tweets.stream().map(e -> e.getRetweetsCount()).reduce(0L, Integer::sum));
			tweetAnaltic.setTotalFavorites(tweets.stream().map(e -> e.getFavoritesCount()).reduce(0L, Integer::sum));
			tweetsAnalytics.add(tweetAnaltic);
		}
		
		return tweetsAnalytics;
		
	}

	public List<Tweet> getTweetsLastWeek(String hashtag) {
		
		List<Tweet> tweets = new ArrayList<>();
		Twitter twitter = TwitterAPIAuth.getAccess();

		Tweet tweet = null;
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
				startDate = startDate.plusDays(1);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tweets;
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
