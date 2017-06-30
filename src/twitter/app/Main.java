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
		StringBuilder log = new StringBuilder();
		
		APIService service = new APIService();

		// SELECTED HASHTAG
		String hashtag = "#javaone";
		LocalDate iniDate = DateUtil.pastDate(7);
		LocalDate endDate = DateUtil.today();
		
		log.append("29SCJ").append(NEW_LINE)
			.append("Diogo Tsuguio Noda-rm35132").append(NEW_LINE)
			.append("Fabiano Vanzella Tessarolo-rm37981").append(NEW_LINE)
			.append("Josinaldo Cipriano Fontes-rm31863").append(NEW_LINE)
			.append("Silas Ruiz Ianez-rm31324");

		 service.post(log, "@michelpf");
		 log.setLength(0);
		

		// GET ALL TWEETS
		List<TweetWrapper> tweetsLastWeek = service.getTweetsLastWeek(hashtag, iniDate, endDate);

		// GET TOTAL TWEETS
		List<TweetAnalyticsWrapper> analytics = service.getAnalytics(tweetsLastWeek);

		for (TweetAnalyticsWrapper analytic : analytics) {
			log.append(">>> Análise do dia ").append(DateUtil.formatter(analytic.getDate())).append(" com a hashtag: ").append(hashtag)
				.append(NEW_LINE + "Total de Tweets: ").append(analytic.getTotalTweets())
				.append(NEW_LINE + "Total de Retweets: " + analytic.getTotalRetweets())
				.append(NEW_LINE + "Total de Favorites: " + analytic.getTotalFavorites() + NEW_LINE);

			 service.post(log, "@michelpf");
			 log.setLength(0);
		}

		List<Tweet> allTweets = service.getAllTweets(tweetsLastWeek);

		// SORT BY AUTHOR
		service.sortByAuthorAsc(allTweets);
		log.append(">>> Ordernado alfabeticamente com o primeiro autor").append(NEW_LINE).append(allTweets.get(0));

		service.post(log, "@michelpf");
		log.setLength(0);

		service.sortByAuthorDesc(allTweets);
		log.append(">>> Ordernado alfabeticamente com o ultimo autor").append(NEW_LINE + allTweets.get(0));

		service.post(log, "@michelpf");
		log.setLength(0);

		// SORT BY DATE
		service.sortByDateAsc(allTweets);
		log.append(">>> Ordernado por data com o primeiro tweet").append(NEW_LINE).append(allTweets.get(0));

		service.post(log, "@michelpf");
		log.setLength(0);

		service.sortByDateDesc(allTweets);
		log.append(">>> Ordernado por data com o ultimo tweet" + NEW_LINE).append(allTweets.get(0));

		service.post(log, "@michelpf");
		log.setLength(0);

		log.append(">>> Total de Tweets na semana: ").append(NEW_LINE).append(allTweets.size());

		service.post(log, "@michelpf");
		log.setLength(0);
	}
}
