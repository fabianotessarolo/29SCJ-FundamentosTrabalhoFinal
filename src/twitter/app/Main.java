package twitter.app;

import java.time.LocalDate;
import java.util.List;

import twitter.services.APIService;
import twitter.utils.DateUtil;
import twitter.wrappers.TweetAnalyticsWrapper;
import twitter.wrappers.TweetWrapper;

public class Main {

	public static void main(String[] args) {
		// Hashtag selecionada para pesquisa
		String hashtag = "#javaone";
		LocalDate untilDate = LocalDate.now();
		LocalDate sinceDate = untilDate.minusWeeks(1);
		
		APIService service = new APIService();		
		
		// GET ALL TWEETS
		List<TweetWrapper> tweetsLastWeek = service.getTweetsLastWeek(hashtag);
		
		// GET TOTAL TWEETS
		List<TweetAnalyticsWrapper> analytics = service.getAnalytics(tweetsLastWeek);
		
		for (TweetAnalyticsWrapper analytic : analytics) {
			System.out.println("\r--------------------------------------------\r" + 
					DateUtil.formatter(analytic.getDate()) + ": " + hashtag + " " + 
					analytic.getTotalTweets() + " Tweets, " + 
					analytic.getTotalRetweets() + " Retweets, " +
					analytic.getTotalFavorites() + " Favorites.");
		}
		
		//TODO:	4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o último nome.
		
		//TODO: 5. Ordenar os tweets por data, e exibir a data mais recente e a menos recente.	

		
//		// POST RESULTS
//		Tweet tweet = new Tweet();
//		tweet.setMensagemTweet("\r--------------------------------------------\r" + formatter(gCalendarInicio)
//				+ ": " + hashtag + " " + contadorDeTweets + " tweets, " + contadorDeRetweets + " Retweets, "
//				+ contadorDeFavorites + " Favorites." + " @michelpf");
//		
//		service.post(tweet);
	}
}
