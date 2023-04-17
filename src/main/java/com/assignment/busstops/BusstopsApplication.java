package com.assignment.busstops;

import com.assignment.busstops.model.BusLine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.Comparator.*;

@SpringBootApplication
public class BusstopsApplication {
	interface Selector extends UnaryOperator<Stream<BusLine>> {}

	Comparator<BusLine> bySize = comparing(BusLine::size);

	Function<Integer, Selector> selector = n -> busLines -> busLines.sorted(bySize).limit(n);

	public static void main(String[] args) {
		SpringApplication.run(BusstopsApplication.class, args);
	}

}
