package com.caching.controller;

import com.caching.model.GeocodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.caching.service.ReverseGeocodingService;
import com.caching.service.GeocodingService;

/**
 * Controller class to define rest apis for forward and reverse geocoding
 */
@RestController
@RequiredArgsConstructor
public class GeocodingController {
    private final GeocodingService geocodingService;
    private final ReverseGeocodingService reverseGeocodingService;

    @GetMapping("/geocoding")
    public GeocodeResponse.GeocodeData getGeocode(@RequestParam String address) {
        return geocodingService.getGeocode(address);
    }

    @GetMapping("/reverse-geocoding")
    public String getReverseGeocode(@RequestParam Double latitude, @RequestParam Double longitude) {
        return reverseGeocodingService.getReverseGeocode(latitude, longitude);
    }
}

