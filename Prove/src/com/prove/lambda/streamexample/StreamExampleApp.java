package com.prove.lambda.streamexample;

import java.util.List;
import java.util.stream.Collectors;

public class StreamExampleApp {

	public static void main(String[] args) {
		List<Integer> inputNumbers = List.of(15,23,31,44,57);
		
		List<Integer> outputNumbers = inputNumbers
				.stream()
				.map(number -> number * 2)
				.collect(Collectors.toList());
		
		System.out.println(outputNumbers);
	}

}
