package com.andrew.safronov.sintez.theblackjack.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "game", catalog = "blackjack")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gameId", nullable = false)
	private long gameId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "purseId")
	private Purse purse;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "game")
	private List<Logs> gameLogs = new LinkedList<Logs>();

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public Purse getPurse() {
		return purse;
	}

	public void setPurse(Purse purse) {
		this.purse = purse;
	}

	public List<Logs> getGameLogs() {
		return gameLogs;
	}

	public void setGameLogs(List<Logs> gameLogs) {
		this.gameLogs = gameLogs;
	}

}
