package com.andrew.safronov.sintez.theblackjack.repository;

import org.springframework.data.repository.CrudRepository;

import com.andrew.safronov.sintez.theblackjack.entity.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

}
