package com.crossover.trial.weather.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.crossover.trial.weather.models.Airport;
import com.crossover.trial.weather.models.Atmosphereinfo;

/**
 * Simple in-memory data storage. Production system has to implement more reliable storage.
 */
public class Storage {

    /**
     * All known airports
     */
    public static Map<String, Airport> AIRPORT_DATA = new ConcurrentHashMap<>();

    /**
     * Atmospheric information for each airport
     */
    public static Map<String, Atmosphereinfo> ATMOSPHERIC_INFORMATION = new ConcurrentHashMap<>();

    /**
     * Internal performance counters to better understand most requested information, this map can be improved but
     * for now provides the basis for future performance optimizations. Due to the stateless deployment architecture
     * we don't want to write this to disk, but will pull it off using a REST request and aggregate with other
     * performance metrics {@link com.crossover.trial.weather.RestWeatherQueryEndpoint}
     */
    public static Map<Airport, AtomicInteger> REQUEST_FREQUENCY = new ConcurrentHashMap<>();
    public static Map<Double, AtomicInteger> RADIUS_FREQUENCY = new ConcurrentHashMap<>();

}
