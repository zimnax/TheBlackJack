package com.andrew.safronov.sintez.theblackjack.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "logs", catalog = "blackjack")
public class Logs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "logId", nullable = false)
	@JsonIgnore
	private long logId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "gameId")
	private Game game;

	@Column(name = "action", nullable = false)
	private String action;

	@Column(name = "value", nullable = true)
	private double value;

	@Column(name = "participant", nullable = true)
	private String participant;

	@Column(name = "cardRank", nullable = true)
	private int cardRank;

	@Column(name = "time", nullable = true)
	private long time;

	public long getLogId() {
		return logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public int getCardRank() {
		return cardRank;
	}

	public void setCardRank(int cardRank) {
		this.cardRank = cardRank;
	}

}
