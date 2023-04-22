package com.assignment.busstops.controller;

import com.assignment.busstops.model.TravelLine;

import static com.assignment.busstops.model.JsonModel.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static com.assignment.busstops.fetcher.Constants.*;
import static com.assignment.busstops.model.TravelLine.travelLinesOf;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Class responsible for handling REST queries from the web client, as well as querying the traffic data API and
 * processing the response.
 */
@RestController
public class ApiController {
    //Helper type to describe a Function that selects the longest lines in a Stream of TravelLines objects
    interface Selector extends UnaryOperator<Stream<TravelLine>> {}
    //Function that takes a TravelLine object and return the number of stops in it.
    final Function<TravelLine, Integer> lengthOf = busLine -> busLine.stops().size();

    //Use SAM conversion to create a function that instantiates a Selector object.
    final Function<Integer, Selector> selector = n -> busLines -> busLines.sorted(comparing(lengthOf).reversed()).limit(n);

    /**
     * Queries the API and maps the result to an object of type A.
     */
    private <A> A query(String transportType, String model, Class<A> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + MODEL_PART + model + KEY_PART + TRANSPORT_TYPE_PART + transportType;
        return restTemplate.getForObject(url, responseType);
    }

    /**
     * REST mapping for querying the longest travel lines of a specific type.
     * @param type - The travel type.
     * @param size - Number of lines to be displayed.
     * @return - A List of the longest travel lines for the type.
     */
    @GetMapping("/longest")
    public List<TravelLine> longest(@RequestParam(value = "type") String type, @RequestParam(value = "size") Integer size) {
        LineResult journeys = query(type, JOURNEY_PATTERN, LineResponseData.class).result();
        StopResult stopNames = query(type, STOPS, StopResponseData.class).result();
        Stream<TravelLine> lines = travelLinesOf(journeys, stopNames, size);

        return selector.apply(size).apply(lines).collect(toList());
    }
}