package com.larry.cards.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.cards.constants.CardConstants;
import com.larry.cards.dto.CardDto;
import com.larry.cards.entity.Card;
import com.larry.cards.exception.CardAlreadyExistsException;
import com.larry.cards.exception.ResourceNotFoundException;
import com.larry.cards.mapper.CardsMapper;
import com.larry.cards.repository.CardRepository;
import com.larry.cards.service.CardService;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardRepository cardRepository;

	@Override
	public void createCard(String mobileNumber) {
        Optional<Card> optionalCards= cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+ mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }

	@Override
	public CardDto fetchCard(String mobileNumber) {
		// Cerchiamo la card tramite il cellulare
		// o altrimenti segnaliamo risorsa non trovata
		Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
			()-> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		
		// Se lo abbiamo trovato, trasmettiamo all'oggetto dto
		return CardsMapper.mapsToCardDto(card, new CardDto());
	}

	@Override
	public boolean updateCard(CardDto cardDto) {
		Card cards = cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardDto.getCardNumber()));
        CardsMapper.mapstoCard(cardDto, cards);
        cardRepository.save(cards);
        return  true;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {
		Card cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(cards.getCardId());
        return true;
		
	}

}
