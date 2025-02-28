package com.caching.service;

import com.caching.exception.ExternalApiException;
import com.caching.exception.InvalidParameterException;
import com.caching.model.GeocodeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Service class for handling geocoding operations.
 *
 */
@Service
public class GeocodingService {
    @Value("${api-key}")
    private String apiKey;

    private static final Logger logger = LoggerFactory.getLogger(GeocodingService.class);

    /**
     * Fetches the geocode data (latitude and longitude) for a given address.
     * The result is cached to avoid redundant API calls, except for the address "Goa".
     *
     * @param address The address for which geocode data is required.
     * @return The GeocodeResponse.GeocodeData containing latitude, longitude, and other details.
     * @throws InvalidParameterException if the provided address is null or empty and ExternalApiException, if no geocoding data is found.
     */
    @Cacheable(value = "geocoding", key = "#address", condition = "!#address.equalsIgnoreCase('goa')")
    public GeocodeResponse.GeocodeData getGeocode(String address) {
        if (address == null || address.isEmpty()) {
            logger.warn("Invalid address provided");
            throw new InvalidParameterException("Invalid address provided.");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.positionstack.com/v1/forward?access_key="+apiKey+"&query=" + address;
        logger.info("Fetching geocode for address: {}", address);
        GeocodeResponse response = restTemplate.getForObject(url, GeocodeResponse.class);

        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            logger.error("No geocoding data found for the given address.");
            throw new ExternalApiException("No geocoding data found for the given address");
        }
        GeocodeResponse.GeocodeData geocodeData = response.getData().get(0);
        logger.info("Geocode fetched successfully: {}", geocodeData);
        return geocodeData;
    }
}


