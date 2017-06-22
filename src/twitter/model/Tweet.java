package twitter.model;

import java.util.Date;

public class Tweet implements Comparable<Tweet>{
	private String nomeAutor;
	private String mensagemTweet;
	private Date dataTweet;
	private int qtdRetweet;
	private int qtdFavoritos;
	
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

	public int getQtdRetweet() {
		return qtdRetweet;
	}

	public void setQtdRetweet(int qtdRetweet) {
		this.qtdRetweet = qtdRetweet;
	}

	public int getQtdFavoritos() {
		return qtdFavoritos;
	}

	public void setQtdFavoritos(int qtdFavoritos) {
		this.qtdFavoritos = qtdFavoritos;
	}

	public String getNomeAutor() {
		return nomeAutor;
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
