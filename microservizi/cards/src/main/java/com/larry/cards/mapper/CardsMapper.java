package com.larry.cards.mapper;

import com.larry.cards.dto.CardDto;
import com.larry.cards.entity.Card;

public class CardsMapper {

	
	// Da Card a CardDto
	public static CardDto mapsToCardDto(Card card, CardDto cardDto) {
		cardDto.setMobileNumber(card.getMobileNumber());
		cardDto.setCardNumber(card.getCardNumber());
		cardDto.setCardType(card.getCardType());
		cardDto.setAmountUsed(card.getAmountUsed());
		cardDto.setAvailableAmount(card.getAvailableAmount());
		cardDto.setTotalLimit(card.getTotalLimit());
		return cardDto;
	}
	
	
	// Da CardDto a Card
	public static Card mapstoCard(CardDto cardDto, Card card) {
		card.setMobileNumber(cardDto.getMobileNumber());
		card.setCardNumber(cardDto.getCardNumber());
		card.setCardType(cardDto.getCardType());
		card.setAmountUsed(cardDto.getAmountUsed());
		card.setAvailableAmount(cardDto.getAvailableAmount());
		card.setTotalLimit(cardDto.getTotalLimit());
		return card;
	}
	
	
}
