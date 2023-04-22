package com.assignment.busstops.model;

import static com.assignment.busstops.model.JsonModel.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

/**
 * Record class containing a cleaned up version of the Travel lines received from the API.
 * @param lineName - name of the line (often a number)
 * @param stops - the name of the stops of the line.
 */
public record TravelLine(String lineName, List<String> stops) {
    /**
     * Takes the results from the API calls for the journeys and stops, and converts them to a List of TravelLine objects.
     *
     * @param lineResult LineResult containing stop ids in a
     * @param stopResult StopResult listing stop ids and their corresponding name
     * @return Stream of TravelLine objects.
     */
    public static Stream<TravelLine> travelLinesOf(LineResult lineResult, StopResult stopResult, Integer size) {
        //Map containing the bus stop ids and their names
        Map<String, String> stopNames = stopResult.stops()
                .stream()
                .collect(toMap(StopEntry::stopId, StopEntry::stopName));

        //Convert the raw data from the api into groups based on their line number,
        //map their stop ids to names, and finally return a Stream of TravelLine objects.
        return lineResult.stops()
                .stream()
                .collect(groupingBy(
                        LineEntry::lineNumber,
                        mapping(entry -> stopNames.get(entry.stopId()), toList())))
                .entrySet()
                .stream()
                .map(entry -> new TravelLine(entry.getKey(), entry.getValue()));
    }
}