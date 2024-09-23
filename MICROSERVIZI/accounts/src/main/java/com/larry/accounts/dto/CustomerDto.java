package com.larry.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
	name="Customer",
	description = "Schema per la gestione dati e informazioni cliente"
)

public class CustomerDto {
		
	@Schema(
		description = "Nome del cliente", example = "Carmelo Longo"
	)
	@NotEmpty(message="Il nome non può essere vuoto o nullo!")
	@Size(min = 5, max = 30, message ="La lunghzza del nome deve essere tra i 5 e i 30 caratteri!")
	private String name;
	
	@Schema(
		description = "Email del cliente", example = "long@gmail.com"
	)
	@NotEmpty(message="La email non può essere vuota o nulla!")
	@Email(message = "L'indirzzo email deve essere di tipo valido!")
	private String email;
	
	
	@Schema(
		description = "Cellulare del cliente", example = "3334455667"
	)
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Il numero cellulare deve essere di 10 cifre!")
	private String mobileNumber;
	
	
	private AccountsDto accountsDto;
}
