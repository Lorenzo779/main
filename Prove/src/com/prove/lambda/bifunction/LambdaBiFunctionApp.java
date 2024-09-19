package com.prove.lambda.bifunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class LambdaBiFunctionApp {
	
	/* L'interfaccia built-in BiFunction
	prevede 2 parametri in ingresso (primi 2 parametri)
	e un tipo dato in uscita (terzo parametro) */

	public static void main(String[] args) {
		// Versione semplice
		BiFunction<Integer, Integer, Double> getPow = (numberOne, numberTwo) -> Math.pow(numberOne, numberTwo);
		System.out.println(getPow.apply(10, 2));
		
		// Versione con lista in uscita
		BiFunction<Integer, Integer, List<Integer>> generateList = (size, multiply) -> {
			// Realizziamo la lista di interi in uscita
			List<Integer> generatedList = new ArrayList<>();
				// Prendiamo ogni elemento dalla lista interi in ingresso
				// moltiplichiamo e trasmettiamo alla lista in uscita
				for (int i = 0; i < size; i++) {
					generatedList.add(i * multiply);
				}
			return generatedList;
		};
		System.out.println(generateList.apply(10, 2));
	};

}
