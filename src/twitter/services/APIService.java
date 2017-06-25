package twitter.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<TweetAnalyticsWrapper> getAnalytics(List<TweetWrapper> tweetsWrapper) {
		TweetAnalyticsWrapper analytic = null;
		List<TweetAnalyticsWrapper> analytics = new ArrayList<>();
		List<Tweet> allTweets = new ArrayList<>();
		
		for (TweetWrapper tweetWrapper : tweetsWrapper) {
			
			int totalTweets = tweetWrapper.getTweets().size();
			System.out.println("\n\nTotal de " + totalTweets + " tweets para a data " + tweetWrapper.getDate());
			int totalRetweets = tweetWrapper.getTweets().stream().mapToInt(Tweet::getRetweetsCount).sum();
			System.out.println("Total de " + totalRetweets + " retweets para a data " + tweetWrapper.getDate());
			int totalFavorites = tweetWrapper.getTweets().stream().mapToInt(Tweet::getFavoritesCount).sum();;
			System.out.println("Total de " + totalFavorites + " favoritos para a data " + tweetWrapper.getDate());

			analytic = new TweetAnalyticsWrapper(tweetWrapper.getDate(), totalTweets, totalRetweets, totalFavorites);
			analytics.add(analytic);
			
			tweetWrapper.getTweets().stream().forEachOrdered(allTweets::add);
		}

		Collections.sort(allTweets);
		System.out.println("\n\nOrdernado alfabeticamente com o primeiro autor\n" + allTweets.get(0));
		allTweets.sort(Collections.reverseOrder());
		System.out.println("\nOrdernado alfabeticamente com o ultimo autor\n" + allTweets.get(0));
		
		TweetComparator comparator = new TweetComparator();
		Collections.sort(allTweets, comparator);
		
		System.out.println("\n\nOrdernado por data com o primeiro tweet\n" + allTweets.get(0));

		allTweets.sort(Collections.reverseOrder(comparator));
		System.out.println("\nOrdernado por data com o ultimo tweet\n" + allTweets.get(0));

		System.out.println("\n\nTotal de tweets " + allTweets.size());
		
		return analytics;
	}
	
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
			
		    System.out.println(  "\n\nIniciando busca por tweets com a hastag: " + hashtag
 		                       + " postados desde " + yesterday
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
