package com.assignment.busstops.fetcher;

import com.assignment.busstops.model.BusLine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

import static java.util.Arrays.asList;

public class ApiReader implements Function<String, BusLine> {
    static final String baseURL = "mam";

    @Override
    public BusLine apply(String busLine) {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseURL + busLine;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if(HttpStatus.OK.equals(response.getStatusCode())) {
            return new BusLine(busLine, response.getBody());
        } else {
            return new BusLine("BusLine couldn't be fetched", asList());
        }
    }
}