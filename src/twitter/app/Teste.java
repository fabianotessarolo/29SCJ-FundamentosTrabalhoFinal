package twitter.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import twitter.model.DataComparator;
import twitter.model.Tweet;
/*** 
 * 
 * @author dnoda
 *
 * CLASSE PARA TESTES DE ORDENAÇAO E UTILIZACAO DA CLASSE TWEET
 * 
 * DEVE SER APAGADA!!!!
 */
public class Teste {

	public static void main(String[] args) {
		Tweet tweet1 = new Tweet();
		Tweet tweet2 = new Tweet();
		Tweet tweet3 = new Tweet();

		Date dataTweet = new Date();
		Calendar calendar = Calendar.getInstance();
		
		tweet1.setDataTweet(dataTweet);
		tweet1.setMensagemTweet("Teste mensagem1");
		tweet1.setNomeAutor("Autor mensagem1");

		calendar.setTime(dataTweet);
		calendar.add(Calendar.DATE, 5);
		dataTweet = calendar.getTime();
		
		tweet2.setDataTweet(dataTweet);
		tweet2.setMensagemTweet("Teste mensagem2");
		tweet2.setNomeAutor("AAAAAA mensagem2");
		
		calendar.add(Calendar.DATE, -10);
		dataTweet = calendar.getTime();

		tweet3.setDataTweet(dataTweet);
		tweet3.setMensagemTweet("Teste mensagem2");
		tweet3.setNomeAutor("BBBB mensagem2");

		List<Tweet> tweets = new ArrayList<>();
		tweets.add(tweet1);
		tweets.add(tweet2);
		tweets.add(tweet3);
		
		System.out.println("Ordenando por autor (compareTo do Tweet)");
		Collections.sort(tweets);
		
		for (Tweet tweet : tweets) {
			System.out.println(tweet);
		}
		
		System.out.println("\n\nOrdenando por data (DataComparator)");
		DataComparator dataComparator = new DataComparator();
		Collections.sort(tweets, dataComparator);
		
		for (Tweet tweet : tweets) {
			System.out.println(tweet);
		}
	}
}
