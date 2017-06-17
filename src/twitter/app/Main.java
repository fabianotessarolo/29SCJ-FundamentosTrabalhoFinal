package twitter.app;

import java.util.GregorianCalendar;

public class Main {

	public static void main(String[] args) {

		// Hashtag selecionada para pesquisa
		String hashtag = "#BombouNaME";

		Operations operations = new Operations();

		// Twitta
		System.out.println(operations.tweet("fabianovt", "Hora certa: " + new GregorianCalendar().getTime()));

		/**
		 * A implementação deste metodo não garante a quantidade correta de
		 * Tweets, e possui limitação de Rate(gerando erros 429-Rate limit
		 * exceeded). Para o trend correto, é necessária a utilização do
		 * Firehose que é pago, sendo assim segue uma implementação acadêmica de
		 * exemplo utilizando o Twitter4J.
		 * 
		 * Fonte:
		 * https://stackoverflow.com/questions/26429965/twitter4j-count-the-number-of-tweets-within-24-hours-return-an-integer
		 */
		operations.getTweetsLastWeek(hashtag);

	}

}
