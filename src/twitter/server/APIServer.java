package twitter.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import twitter.connector.APIConnector;
import twitter.model.Tweet;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class APIServer {
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

	public List<Tweet> getTweetsLastWeek(String hashtag) {
		List<Tweet> tweets = new ArrayList<>();

		Twitter twitter = APIConnector.get();

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

			Tweet tweet = new Tweet();
			try {
				result = twitter.search(query);
				while (result.hasNext()) {
					query = result.nextQuery();
					for (Status status : result.getTweets()) {
						tweet.convert(status);
						
						
						tweets.add(tweet);
						
					}
					result = twitter.search(query);
				}

			}

			catch (Exception e) {
				e.printStackTrace();
			}

		}
		return tweets;
	}

	public void post(Tweet tweet){
		try {
			Twitter twitter = APIConnector.get();

			twitter.updateStatus(tweet.getMensagemTweet());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
