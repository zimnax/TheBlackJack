package com.andrew.safronov.sintez.theblackjack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purse", catalog = "blackjack")
public class Purse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purseId", nullable = false)
    private long purseId;

    @Column(name = "balance", nullable = false)
    private int balance;

    public long getPurseId() {
        return purseId;
    }

    public void setPurseId(long purseId) {
        this.purseId = purseId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
