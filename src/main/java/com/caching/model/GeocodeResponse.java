package com.caching.model;

import java.util.List;


public class GeocodeResponse {
    private List<GeocodeData> data;

    public List<GeocodeData> getData() {
        return data;
    }

    public void setData(List<GeocodeData> data) {
        this.data = data;
    }


    public static class GeocodeData {
        private double latitude;
        private double longitude;
        private String name;
        private String label;
        private double distance;
        private String region;
        private String country;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
