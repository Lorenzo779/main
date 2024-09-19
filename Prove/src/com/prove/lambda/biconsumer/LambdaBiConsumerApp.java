package com.prove.lambda.biconsumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class LambdaBiConsumerApp {
	
	/* L'interfaccia built-in BiConsumer
	 * accetta 2 parametri in ingresso
	 * e non restituisce nulla*/
	
	public static void main(String[] args) {
		/* Versione semplice */
		BiConsumer<String, Integer> printAttribute = (text, number) -> System.out.println(text + number);
		printAttribute.accept("Punti: ", 100);
		printAttribute.accept("Prezzo: ", 200);
		
		/* Versione con lista parametri */
		BiConsumer<List<Integer>, Integer> multiplyNumbers = (list, number) -> {
			for (int i = 0; i < list.size(); i++) {
				list.set(i, list.get(i) * number);
			}
		};
		// Immettiamo la lista parametri ingresso
		List<Integer> numbers = Arrays.asList(5,4,3,2,1);
		multiplyNumbers.accept(numbers, 5);
		System.out.println(numbers);
	}

}
