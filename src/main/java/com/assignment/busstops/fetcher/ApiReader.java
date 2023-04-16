package com.assignment.busstops.fetcher;

import com.assignment.busstops.model.BusLine;

import java.util.function.Function;

public interface ApiReader extends Function<String, BusLine>  {}