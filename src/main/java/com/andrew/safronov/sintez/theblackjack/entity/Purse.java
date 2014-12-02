package com.andrew.safronov.sintez.theblackjack.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purse", catalog = "blackjack")
public class Purse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "purseId", nullable = false)
	private long purseId;

	@Column(name = "balance", nullable = false)
	private double balance;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "purse")
	private Game game;

	public long getPurseId() {
		return purseId;
	}

	public void setPurseId(long purseId) {
		this.purseId = purseId;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (purseId ^ (purseId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purse other = (Purse) obj;
		if (purseId != other.purseId)
			return false;
		return true;
	}

}
