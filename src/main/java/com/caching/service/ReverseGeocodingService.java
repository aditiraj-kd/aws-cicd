package com.caching.service;

import com.caching.exception.ExternalApiException;
import com.caching.exception.InvalidParameterException;
import com.caching.model.GeocodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReverseGeocodingService {
    @Value("${api-key}")
    private String apiKey;

    private static final Logger logger = LoggerFactory.getLogger(ReverseGeocodingService.class);

    /**
     * Fetches the geocode data (address) for given latitude and longitude.
     * The result is cached to avoid redundant API calls.
     *
     * @param latitude The latitude for which geocode data is required.
     * @param longitude The longitude for which geocode data is required.
     * @return String containing address.
     * @throws InvalidParameterException if the provided latitude or longitude is null or empty and ExternalApiException, if no geocoding data is found.
     */
    @Cacheable(value = "reverse-geocoding", key = "{#latitude, #longitude}")
    public String getReverseGeocode(Double latitude, Double longitude) {
        if (latitude == null || latitude < -90 || latitude > 90) {
            logger.warn("Invalid latitude provided");
            throw new InvalidParameterException("Invalid latitude provided.");
        }
        if (longitude == null || longitude < -180 || longitude > 180) {
            logger.warn("Invalid longitude provided");
            throw new InvalidParameterException("Invalid longitude provided.");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.positionstack.com/v1/reverse?access_key=" + apiKey + "&query=" + latitude + "," + longitude;
        logger.info("Fetching reverse geocode for latitude: {}, longitude: {}", latitude, longitude);
        GeocodeResponse response = restTemplate.getForObject(url, GeocodeResponse.class);

        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            logger.warn("No reverse geocode data found for latitude: {}, longitude: {}", latitude, longitude);
            throw new ExternalApiException("Failed to fetch reverse geocoding data");
        }
        GeocodeResponse.GeocodeData geocodeData = response.getData().get(0);
        if(geocodeData==null){
            logger.info("Reverse geocode failed to fetch");
            throw new ExternalApiException("Reverse geocode failed to fetch.");
        }
        logger.info("Reverse geocode fetched successfully: {}", response.getData());
        return geocodeData.getLabel();

    }
}

