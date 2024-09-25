package com.larry.cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Card extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	
	private String mobileNumber;
	
	private String cardNumber;
	
	private String cardType;
	
	private int totalLimit;
	
	private int amountUsed;
	
	private int availableAmount;
	
}
