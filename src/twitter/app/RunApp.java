package twitter.app;

import java.util.GregorianCalendar;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class RunApp {

	static final String hashTag = "#openjdk";
	static final int count = 100;
	static long sinceId = 0;
	static long numberOfTweets = 0;

	public static void main(String[] args) {

		GregorianCalendar gCalendarInicio = new GregorianCalendar(2017, 4, 16);

		String selectedHashTag = "";
		Twitter twitter = Factory.get();

		Query queryMax = new Query(hashTag);
		queryMax.setCount(count);
		getTweets(queryMax, twitter, "maxId");
		queryMax = null;

		// get tweets that may have occurred while processing above data
		// Fetch sinceId from database and get tweets, also at this point store
		// the sinceId
		do {
			Query querySince = new Query(hashTag);
			querySince.setCount(count);
			querySince.setSinceId(sinceId);
			getTweets(querySince, twitter, "sinceId");
			querySince = null;
		} while (checkIfSinceTweetsAreAvaliable(twitter));

	}

	private static boolean checkIfSinceTweetsAreAvaliable(Twitter twitter) {
		Query query = new Query(hashTag);
		query.setCount(count);
		query.setSinceId(sinceId);
		try {
			QueryResult result = twitter.search(query);
			if (result.getTweets() == null || result.getTweets().isEmpty()) {
				query = null;
				return false;
			}
		} catch (TwitterException te) {
			System.out.println("Couldn't connect: " + te);
			System.exit(-1);
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e);
			System.exit(-1);
		}
		return true;
	}

	private static void getTweets(Query query, Twitter twitter, String mode) {
		boolean getTweets = true;
		long maxId = 0;
		long whileCount = 0;

		while (getTweets) {
			try {
				QueryResult result = twitter.search(query);
				if (result.getTweets() == null || result.getTweets().isEmpty()) {
					getTweets = false;
				} else {
					System.out.println("***********************************************");
					System.out.println("Gathered " + result.getTweets().size() + " tweets");
					int forCount = 0;
					for (Status status : result.getTweets()) {
						if (whileCount == 0 && forCount == 0) {
							sinceId = status.getId();// Store sinceId in
														// database
							System.out.println("sinceId= " + sinceId);
						}
						System.out.println("Id= " + status.getId());
						System.out.println("@" + status.getUser().getScreenName() + " : " + status.getUser().getName()
								+ "--------" + status.getText());
						if (forCount == result.getTweets().size() - 1) {
							maxId = status.getId();
							System.out.println("maxId= " + maxId);
						}
						System.out.println("");
						forCount++;
					}
					numberOfTweets = numberOfTweets + result.getTweets().size();
					query.setMaxId(maxId - 1);
				}
			} catch (TwitterException te) {
				System.out.println("Couldn't connect: " + te);
				System.exit(-1);
			} catch (Exception e) {
				System.out.println("Something went wrong: " + e);
				System.exit(-1);
			}
			whileCount++;
		}
		System.out.println("Total tweets count=======" + numberOfTweets);
	}

}

// status = twitter.updateStatus("Ola Twitter3!");

// System.out.println("Tweet postado com sucesso! [" + status.getText() + "].");
