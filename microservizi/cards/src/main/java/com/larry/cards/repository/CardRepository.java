package com.larry.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.larry.cards.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

	// Ricerca card per numero cellulare
	Optional<Card> findByMobileNumber(String mobileNumber);
	
	// Ricerca per numero card number
	Optional<Card> findByCardNumber(String cardNumber);
	
}
