package com.larry.loans.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(
	name ="ErrorResponse",
	description = "Schema di gestione informazioni errore"
)
public class ErrorResponseDto {
	
	@Schema(
		description = "Percorso API invocato dal client"
	)
	private String apiPath;	// Percorso riferimento errore
	@Schema(
		description = "Numero codice dell'errore"
	)
	private HttpStatus errorCode;
	@Schema(
		description = "Messaggio errore"
	)
	private String errorMessage;
	@Schema(
		description = "Data e ora dell'errore"
	)
	private LocalDateTime errorTime;
	
}
