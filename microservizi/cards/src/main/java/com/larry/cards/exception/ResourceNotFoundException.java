package com.larry.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		// Poichè siamo in un costruttore di Runtime
		// che accetterà una sola stringa come parametro di ingresso,
		// uniamo i tre parametri in un unica stringa
		// nella seguente maniera
		super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
	}

}
