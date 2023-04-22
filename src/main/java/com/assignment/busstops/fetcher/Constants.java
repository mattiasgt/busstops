package com.assignment.busstops.fetcher;

import static com.assignment.busstops.PropertiesHandler.getProperty;

/**
 * String constants read from the properties file.
 */
public interface Constants {
    String API_KEY = getProperty("api.key");
    String KEY_PART = getProperty("key.url") + API_KEY;
    String BASE_URL = getProperty("base.url");
    String MODEL_PART = getProperty("model.url");
    String TRANSPORT_TYPE_PART = getProperty("transport.url");
    String JOURNEY_PATTERN = getProperty("journey.pattern");
    String STOPS = getProperty("stops");
}
