package twitter.app;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Operations implements OperationsInterface {

	/**
	 * A utilização deste metodo não garante a quantidade correta de Tweets, e
	 * possui limitação de Rate(gerando erros 429-Rate limit exceeded). Para o
	 * trend correto, é necessária a utilização do Firehose que é pago, sendo
	 * assim segue uma implementação acadêmica de exemplo utilizando o
	 * Twitter4J.
	 * 
	 * Fonte:
	 * https://stackoverflow.com/questions/26429965/twitter4j-count-the-number-of-tweets-within-24-hours-return-an-integer
	 */
	@Override
	public int getTweetsLastWeek(String hashtag) {

		Twitter twitter = Factory.get();

		GregorianCalendar gCalendarAgora = calendarNow();
		GregorianCalendar gCalendarInicio = calendarPastWeek();

		while (gCalendarInicio.before(gCalendarAgora)) {
			Query query = new Query(hashtag);
			GregorianCalendar gCalendarAnterior = new GregorianCalendar();
			gCalendarAnterior.setTime(gCalendarInicio.getTime());
			gCalendarAnterior.add(GregorianCalendar.DAY_OF_MONTH, -1);
			query.setSince(formatador(gCalendarAnterior));
			query.setUntil(formatador(gCalendarInicio));
			query.setCount(1);
			QueryResult result;

			int contadorDeTweets = 0;
			int contadorDeRetweets = 0;
			int contadorDeFavorites = 0;

			try {
				result = twitter.search(query);
				while (result.hasNext()) {
					query = result.nextQuery();
					for (Status status : result.getTweets()) {
						contadorDeTweets++;
						contadorDeRetweets = contadorDeRetweets + status.getRetweetCount();
						contadorDeFavorites = contadorDeFavorites + status.getFavoriteCount();
					}
					result = twitter.search(query);
				}

				System.out.println("\r--------------------------------------------\r" + formatador(gCalendarInicio)
						+ ": " + hashtag + " " + contadorDeTweets + " tweets, " + contadorDeRetweets + " Retweets, "
						+ contadorDeFavorites + " Favorites.");
				gCalendarInicio.add(GregorianCalendar.DAY_OF_MONTH, 1);
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		}
		return 0;
	}

	private GregorianCalendar calendarPastWeek() {
		GregorianCalendar gCalendarInicio = new GregorianCalendar();
		gCalendarInicio.add(GregorianCalendar.DAY_OF_MONTH, -7);
		return gCalendarInicio;
	}

	private GregorianCalendar calendarNow() {
		GregorianCalendar gCalendarAgora = new GregorianCalendar();
		return gCalendarAgora;
	}

	private String formatador(GregorianCalendar date) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		return formatador.format(date.getTime());
	}

	@Override
	public String tweet(String who, String message) {
		System.out.println("Iniciando postagem de um Tweet: ");
		Twitter twitter = Factory.get();
		Status status = null;
		try {
			status = twitter.updateStatus(message + " @" + who);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Tweet postado com sucesso! [" + status.getText() + "].";
	}

}
