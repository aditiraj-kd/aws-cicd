# Geocoding and Reverse Geocoding Spring Boot Application

This project is a Spring Boot-based REST API for geocoding and reverse geocoding operations. It converts:
1. An address into geographic coordinates (latitude and longitude).
2. Geographic coordinates into a human-readable address.

The application includes in-memory caching for faster subsequent lookups, integrates with the PositionStack API for geocoding, and adheres to best practices for caching and logging.

---

## Features

- **Geocoding**: Convert an address into latitude and longitude.
- **Reverse Geocoding**: Convert latitude and longitude into a human-readable address.
- **In-Memory Caching**:
    - Cache geocoding and reverse geocoding results.
    - Cache eviction for managing stale/unused entries.
    - Caching bypass for specific cases (e.g., `goa`).
- **Integration with PositionStack API** for geocoding and reverse geocoding.#   a w s - c i c d  
 