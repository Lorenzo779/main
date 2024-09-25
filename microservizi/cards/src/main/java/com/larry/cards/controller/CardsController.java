package com.larry.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.larry.cards.constants.CardConstants;
import com.larry.cards.dto.CardDto;
import com.larry.cards.dto.ResponseDto;
import com.larry.cards.service.CardService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardsController {
	
	private CardService cardService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
            	cardService.createCard(mobileNumber);
            	return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
            }
	
	@GetMapping("/fetch")
	public ResponseEntity<CardDto> fetchCard(@Valid @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
			CardDto cardDto = cardService.fetchCard(mobileNumber);
			return ResponseEntity.status(HttpStatus.OK).body(cardDto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardDto cardDto) {
		// Prima prende la risposta dal service
		boolean isUpdated = cardService.updateCard(cardDto);
		if (isUpdated) {
			// Aggiornato correttamente
			return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
		}else{
			// Aggiornamento fallito
			return ResponseEntity
	                .status(HttpStatus.EXPECTATION_FAILED)
	                .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
		}
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteCard(@Valid @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
		// Prima prende la risposta dal service
		boolean isDeleted = cardService.deleteCard(mobileNumber);
		if (isDeleted) {
			// Eliminato correttamente
			return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
		}else{
			// Eliminazione fallito
			return ResponseEntity
	                .status(HttpStatus.EXPECTATION_FAILED)
	                .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
		}
	}
	
	

}
