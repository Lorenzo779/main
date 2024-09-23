package com.prove.lambda.predicate;

import java.util.Scanner;
import java.util.function.Predicate;

public class LambdaPredicateAppV1 {
	/*L'interfaccia built-in predicate ha 2 versioni:
		1) accetta un parametro e restituisce un booleano
		2) accetta 2 parametri e restituisce un booleano
	
		Questa è la prima versione: 
		1 parametro in ingresso, un booleano in uscita */
	public static void main(String[] args) {
		Predicate<String> moreThan5Letters = text -> text.length() > 5;
		Predicate<String> startsWithWelcome = text -> text.startsWith("Benvenuto");
		
		boolean blnStop = false; // Indicatore condizione
		do {
			// Prendiamo la parola di ingresso da analizzare
			Scanner input = new Scanner(System.in);
			
			System.out.println("Scrivi la parola");
			
			String sParola = input.nextLine();
			
			// applichiamo la parola
			boolean isMoreThan5Letters = moreThan5Letters.test(sParola);
			System.out.println("Il testo digitato è più lungo di 5 lettere: " + isMoreThan5Letters);
			
			boolean isStartsWithWelcome = startsWithWelcome.test(sParola);
			System.out.println("Il testo digitato inizia con \"Benvenuto\": " + isStartsWithWelcome);
			
			// Neghiamo (cioè diamo risultato inverso di) una risposta con negate
			boolean isLessThan5Letters = moreThan5Letters.negate().test(sParola);
			System.out.println("Il testo digitato è più corto di 5 lettere: " + isLessThan5Letters);
			
			// Combinazione con l'operatore AND
			boolean isCombinedAnd = moreThan5Letters.and(startsWithWelcome).test(sParola);
			System.out.println("Il testo digitato è più lungo di 5 lettere e inizia con \"Benvenuto\": " + isCombinedAnd);
			
			// Chiediamo se vogliamo ripetere
			System.out.println("Vuoi ripetere \"(s/n\")?");
			String sConferma = input.nextLine();
			if (sConferma.equals("n") || sConferma.equals("N")) {
				input.close();	// chiudiamo lo scanner
				blnStop = true;
			};
		}
		while (blnStop == false);
		System.out.println("Grazie per aver partecipato!");
	}

}
