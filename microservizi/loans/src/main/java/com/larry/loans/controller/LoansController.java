package com.larry.loans.controller;

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

import com.larry.loans.constants.LoansConstants;
import com.larry.loans.dto.LoansDto;
import com.larry.loans.dto.ResponseDto;
import com.larry.loans.service.LoansService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {
	
	private LoansService loansService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@Valid @RequestParam  
			@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    		String mobileNumber) {
		loansService.createLoan(mobileNumber);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new ResponseDto(LoansConstants.STATUS_201, 
				LoansConstants.MESSAGE_201));
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<LoansDto> fetch(@Valid @RequestParam  
			@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    		String mobileNumber) {
		LoansDto loansDto = loansService.fetchLoan(mobileNumber);
		return ResponseEntity.ok(loansDto);
		
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody LoansDto loansDto) {
		boolean isUpdated = loansService.updateLoan(loansDto);
		if (isUpdated) {
			// Aggiornato correttamente
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(LoansConstants.STATUS_200, 
					LoansConstants.MESSAGE_200));
		} else {
			// Non aggiornato correttamente
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(LoansConstants.STATUS_417, 
					LoansConstants.MESSAGE_417_UPDATE));
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> delete(@Valid @RequestParam  
			@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    		String mobileNumber) {
				boolean isDeleted = loansService.deleteLoan(mobileNumber);
				if (isDeleted) {
					// Aggiornato correttamente
					return ResponseEntity
							.status(HttpStatus.OK)
							.body(new ResponseDto(LoansConstants.STATUS_200, 
							LoansConstants.MESSAGE_200));
				} else {
					// Non aggiornato correttamente
					return ResponseEntity
							.status(HttpStatus.EXPECTATION_FAILED)
							.body(new ResponseDto(LoansConstants.STATUS_417, 
							LoansConstants.MESSAGE_417_DELETE));
				}
    		}

}
