package twitter.app;

public class Main {

	public static void main(String[] args) {
		//Twitta
		Operations operations = new Operations();
		String hashtag = "#openjdk";
		
		//System.out.println(operations.tweet("fabianovt" , "Olá2!"));
		
		operations.countTweetsLastWeek(hashtag);
		

		
		
	}

}
