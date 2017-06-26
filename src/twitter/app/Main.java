package twitter.app;

import java.time.LocalDate;
import java.util.List;

import twitter.models.Tweet;
import twitter.services.APIService;
import twitter.utils.DateUtil;
import twitter.wrappers.TweetAnalyticsWrapper;
import twitter.wrappers.TweetWrapper;

public class Main {

	public static void main(String[] args) {

		final String NEW_LINE = "\n";
		final String LINE = NEW_LINE + "-------------------------------------" + NEW_LINE;
		StringBuilder log = new StringBuilder();
		
		APIService service = new APIService();

		// SELECTED HASHTAG
		String hashtag = "#javaone";
		LocalDate iniDate = DateUtil.pastDate(7);
		LocalDate endDate = DateUtil.today();

		// GET ALL TWEETS
		List<TweetWrapper> tweetsLastWeek = service.getTweetsLastWeek(hashtag, iniDate, endDate);

		// GET TOTAL TWEETS
		List<TweetAnalyticsWrapper> analytics = service.getAnalytics(tweetsLastWeek);

		for (TweetAnalyticsWrapper analytic : analytics) {
			log.append(LINE + 
					">>> Análise do dia " + DateUtil.formatter(analytic.getDate()) + " com a hashtag: " + hashtag +
					NEW_LINE + "Total de Tweets: " + analytic.getTotalTweets() + 
					NEW_LINE + "Total de Retweets: " + analytic.getTotalRetweets() + 
					NEW_LINE + "Total de Favorites: " + analytic.getTotalFavorites() + NEW_LINE);
		}

		List<Tweet> allTweets = service.getAllTweets(tweetsLastWeek);

		// SORT BY AUTHOR
		service.sortByAuthorAsc(allTweets);
		log.append(LINE + ">>> Ordernado alfabeticamente com o primeiro autor" + NEW_LINE + allTweets.get(0) + NEW_LINE);

		service.sortByAuthorDesc(allTweets);
		log.append(LINE + ">>> Ordernado alfabeticamente com o ultimo autor" + NEW_LINE + allTweets.get(0) + NEW_LINE);

		// SORT BY DATE
		service.sortByDateAsc(allTweets);
		log.append(LINE + ">>> Ordernado por data com o primeiro tweet" + NEW_LINE + allTweets.get(0) + NEW_LINE);

		service.sortByDateDesc(allTweets);
		log.append(LINE + ">>> Ordernado por data com o ultimo tweet" + NEW_LINE + allTweets.get(0) + NEW_LINE);

		log.append(LINE + ">>> Total de Tweets na semana: " + NEW_LINE + allTweets.size() + NEW_LINE);
		
		System.out.println(log.toString());

//		 // POST RESULTS
//		 Tweet tweet = new Tweet();
//		 log.append(" @michelpf");
//		 tweet.setMessage(log.toString());
//		 service.post(tweet);
	}
}
