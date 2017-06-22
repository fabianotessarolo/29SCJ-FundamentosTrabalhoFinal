package twitter.app;

import java.util.GregorianCalendar;

import twitter.model.Tweet;
import twitter.server.APIServer;

public class Main {

	public static void main(String[] args) {
		// Hashtag selecionada para pesquisa
		String hashtag = "#BombouNaME";

		APIServer operations = new APIServer();

		operations.getTweetsLastWeek(hashtag);
		// analiticos
		
		// Postar o resultado das analises
		Tweet tweet = new Tweet();
		tweet.setMensagemTweet("\r--------------------------------------------\r" + formatador(gCalendarInicio)
				+ ": " + hashtag + " " + contadorDeTweets + " tweets, " + contadorDeRetweets + " Retweets, "
				+ contadorDeFavorites + " Favorites." + " @michelpf");
		
		operations.post(tweet);
		


	}

}
