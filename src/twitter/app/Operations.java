package twitter.app;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Operations implements OperationsInterface {

	@Override
	public int countTweetsLastWeek(String hashtag) {
	
		Twitter twitter = Factory.get();
		GregorianCalendar gCalendarInicio = new GregorianCalendar(2017, 5, 7);
		System.out.println(gCalendarInicio.getTime());
		GregorianCalendar gCalendarAgora = new GregorianCalendar();
		//GregorianCalendar fCalendarAgora = new GregorianCalendar().getInstance();
		//fCalendarAgora = gCalendarAgora.add(GregorianCalendar.DAY_OF_MONTH, -7);
		System.out.println(gCalendarAgora.getTime());
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		
		while (gCalendarInicio.before(gCalendarAgora)) {
			Query query = new Query(hashtag);
			GregorianCalendar gCalendarAnterior = new GregorianCalendar(); 
			gCalendarAnterior.setTime(gCalendarInicio.getTime()); 
			gCalendarAnterior.add(GregorianCalendar.DAY_OF_MONTH, -1);
			query.setSince(formatador.format(gCalendarAnterior.getTime()));
			query.setUntil(formatador.format(gCalendarInicio.getTime()));
			query.setCount(100);
			QueryResult result;
			int contador=0;
			try {
				result = twitter.search(query);
				while (result.hasNext()) {
					query = result.nextQuery();
					for (Status status : result.getTweets()) {
				            contador++;
				            
				            /** if (contador>50){
				            	System.out.println("Usuário: " + status.getUser().getScreenName());
				            	System.out.println("Mensagem: " + status.getText());
				            	System.out.println("Data de Criação: " + status.getCreatedAt());
				            	System.out.println("Número de Favoritos: " + status.getFavoriteCount());
				            	System.out.println("Geolocalização: " + status.getGeoLocation());
				            	System.out.println("Lugar: " + status.getPlace());
				            	System.out.println("Número de Retweets: " + status.getRetweetCount());
				            }
				            **/
					}
					result = twitter.search(query); 
				}
				
				System.out.println(formatador.format(gCalendarInicio.getTime())+": " + hashtag + contador+" tweets.");
				gCalendarInicio.add(GregorianCalendar.DAY_OF_MONTH, 1);
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}	
		
		return 0;

	}

	@Override
	public int countRetweetsLastWeek(String hashtag) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countFavoritesLastWeek(String hashtag) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> orderTweetsByAuthor(String hashtag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> orderTweetsByDate(String hashtag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String tweet(String who, String message) {
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
