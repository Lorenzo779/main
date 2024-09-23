package com.larry.accounts.controller;

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

import com.larry.accounts.constants.AccountsConstants;
import com.larry.accounts.dto.CustomerDto;
import com.larry.accounts.dto.ErrorResponseDto;
import com.larry.accounts.dto.ResponseDto;
import com.larry.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;


@Tag(
		name = "CRUD REST APIs for accounts in lo.merola company",
		description="CRUD REST APIs for accounts in lo.merola company to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {
	
	private IAccountsService accountService;
	
	@Operation(
		summary = "Crea Account REST API",
		description = "REST API per creare un nuovo cliente & account"
	)
	@ApiResponse(
		responseCode = "201",
		description = "HTTP Status CREATED"
	)
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto>createAccount(@Valid @RequestBody CustomerDto customerDto) {
		
		// Realizziamo il nuovo cliente
		accountService.createAccount(customerDto);
		
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(new ResponseDto(AccountsConstants.STATUS_201, 
					AccountsConstants.MESSAGE_201));
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto>fetchAccountDetails (@RequestParam 
									@Pattern(regexp = "(^$|[0-9]{10})", message = "Il numero cellulare deve essere di 10 cifre!")
									String mobileNumber) {
		
		// Otteniamo il cliente dal numero telefonico
		CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(customerDto);
	}
	
	@Operation(
		summary = "Aggiorna dettagli account REST API",
		description = "REST API per aggiornare dettagli del cliente e account in base al numero conto"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "HTTP Status OK"
		),
		@ApiResponse(
			responseCode = "500",
			description = "HTTP Status Internal Server Error",
			content = @Content(
				schema = @Schema(implementation = ErrorResponseDto.class)	
			)
		)
	}
	)
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto>updateAccount(@Valid @RequestBody CustomerDto customerDto) {
		
		boolean isUpdated = accountService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto>deleteAccount(@RequestParam 
													@Pattern(regexp = "(^$|[0-9]{10})", 
													message = "Il numero cellulare deve essere di 10 cifre!") String mobileNumber) {
		
		boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
	}

}
