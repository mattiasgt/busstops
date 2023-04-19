package com.assignment.busstops.fetcher;

import com.assignment.busstops.model.BusLine;
import com.assignment.busstops.model.BusLine.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

import static java.util.Arrays.asList;

public class ApiReader implements Function<String, BusLine> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiReader.class);
    static final String API_KEY = "withheld";
    static final String KEY_PART = "&key=" + API_KEY;

    static final String TRANSPORT_TYPE_PART = "&DefaultTransportModeCode=BUS";
    static final String BASE_URL = "https://api.sl.se/api2/LineData.json?";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public BusLine apply(String busLine) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "model=" + busLine + KEY_PART + TRANSPORT_TYPE_PART;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if(HttpStatus.OK.equals(response.getStatusCode())) {
            LOGGER.error("Body " + response.getBody());

            Charset charset = Charset.forName("UTF-8");
            String s = response.getBody();
            Path path = new File(busLine + ".json").toPath();
            try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }

            return new BusLine(busLine, response.getBody().transform(body -> asList(new BusStop(body))));
        } else {
            return new BusLine("BusLine couldn't be fetched", asList());
        }
    }
}