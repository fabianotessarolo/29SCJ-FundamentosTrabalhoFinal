package twitter.app;

import java.util.List;

import twitter.models.Tweet;
import twitter.services.APIService;
import twitter.utils.DateUtil;
import twitter.wrappers.TweetWrapper;

public class Main {

	public static void main(String[] args) {
		
		APIService service = new APIService();
		
		// Hashtag selecionada para pesquisa
		String hashtag = "#javaone";
		
		// Total Tweets
		List<TweetWrapper> tweetsLastWeek = service.getAnalytics(hashtag);
		
		for (TweetWrapper tweetWrapper : tweetsLastWeek) {
			System.out.println("\r--------------------------------------------\r" + 
					DateUtil.formatter(tweetWrapper.getDate()) + ": " + hashtag + " " + 
					tweetWrapper.getTotalTweets() + " Tweets, " + 
					tweetWrapper.getTotalRetweets() + " Retweets, " +
					tweetWrapper.getTotalFavorites() + " Favorites.");
		}
		
		// All Tweets
		List<Tweet> tweets = service.getTweetsLastWeek(hashtag);

		
//		// Postar o resultado das analises
//		Tweet tweet = new Tweet();
//		tweet.setMensagemTweet("\r--------------------------------------------\r" + formatter(gCalendarInicio)
//				+ ": " + hashtag + " " + contadorDeTweets + " tweets, " + contadorDeRetweets + " Retweets, "
//				+ contadorDeFavorites + " Favorites." + " @michelpf");
//		
//		service.post(tweet);
	}
}
