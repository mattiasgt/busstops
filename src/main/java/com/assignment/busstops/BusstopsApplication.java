package com.assignment.busstops;

import com.assignment.busstops.fetcher.ApiReader;
import com.assignment.busstops.model.BusLine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.Comparator.*;

@SpringBootApplication
public class BusstopsApplication {
	interface Selector extends UnaryOperator<Stream<BusLine>> {}
	Function<BusLine, Integer> lengthOf = busLine -> busLine.stops().size();
	Function<Integer, Selector> selector = n -> busLines -> busLines.sorted(comparing(lengthOf)).limit(n);

	public static void main(String[] args) {
		SpringApplication.run(BusstopsApplication.class, args);
	}
	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {
			ApiReader reader = new ApiReader();
			reader.apply("line");
			reader.apply("jour");
			reader.apply("stop");
			reader.apply("tran");
			reader.apply("JourneyPatternPointOnLine");
		};
	}
}
