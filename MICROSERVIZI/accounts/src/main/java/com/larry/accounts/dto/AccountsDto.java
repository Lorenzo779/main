package com.larry.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
	name="Accounts",
	description = "Schema di gestione informazioni conto"
)
public class AccountsDto {
	
	
	@NotEmpty(message="Il numero conto non deve essere vuoto o nullo!")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Il numero conto deve essere di 10 cifre!")
	@Schema(
		description = "Numero conto"
	)
	private Long accountNumber;
	
	@NotEmpty(message = "Il tipo di conto non deve essere vuoto o nullo!")
	@Schema(
		description = "Tipo di conto", example = "savings"
	)
	private String accountType;
		
	@NotEmpty(message = "L'indirizzo della filiale non deve essere vuoto o nullo!")
	@Schema(
		description = "Indirizzo sede conto"
	)
	private String branchAddress;
}
