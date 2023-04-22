package com.assignment.busstops.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Model mapping the content of the json response in the API calls to record classes.
 */
public interface JsonModel {
    record LineResponseData(@JsonProperty("ResponseData") LineResult result) {}
    record LineResult(@JsonProperty("Result") List<LineEntry> stops) {}
    record LineEntry(@JsonProperty("LineNumber") String lineNumber,
                     @JsonProperty("JourneyPatternPointNumber") String stopId) {}

    record StopResponseData(@JsonProperty("ResponseData") StopResult result) {}
    record StopResult(@JsonProperty("Result") List<StopEntry> stops) {}
    record StopEntry(@JsonProperty("StopPointNumber") String stopId,
                     @JsonProperty("StopPointName") String stopName) {
    }
}