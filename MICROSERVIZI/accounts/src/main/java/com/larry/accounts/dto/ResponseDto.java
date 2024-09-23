package com.larry.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(
	name="Response",
	description = "Schema per la gestione informazioni risposta"
)
public class ResponseDto {
	
	@Schema(
		description = "Codice stato risposta", example = "200"
	)
	private String statusCode;
	@Schema(
		description = "Stato messaggio risposta", example = "Richiesta processata correttamente"
	)
	private String statusMsg;
}
