package twitter.model;

import java.util.Date;

public class Tweet implements Comparable<Tweet>{
	private String nomeAutor;
	private String mensagemTweet;
	private Date dataTweet;
	
	public String getNomeAutor() {
		return nomeAutor;
	}


	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}


	public String getMensagemTweet() {
		return mensagemTweet;
	}

	public void setMensagemTweet(String mensagemTweet) {
		this.mensagemTweet = mensagemTweet;
	}

	public Date getDataTweet() {
		return dataTweet;
	}

	public void setDataTweet(Date dataTweet) {
		this.dataTweet = dataTweet;
	}

	@Override
	public String toString() {
		return  "Autor: " + this.getNomeAutor()
		      + "Tweet: " + this.getMensagemTweet()
		      + "Data: " + this.getDataTweet();
	}
	
	@Override
	public int compareTo(Tweet outroTweet) {
		return this.getNomeAutor().compareTo(outroTweet.getNomeAutor());
	}

}
