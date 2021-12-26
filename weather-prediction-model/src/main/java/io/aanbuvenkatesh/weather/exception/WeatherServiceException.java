package io.aanbuvenkatesh.weather.exception;

/**
 * Base Exception for Weather Service. All other exception inherits this exception.
 *
 * @author aanbuvenkatesh
 */
public class WeatherServiceException extends Exception {

    protected static final String ERROR_PREFIX = "WEATHER-SERVICE";

    public WeatherServiceException() {
        super();
    }

    public WeatherServiceException(String message) {
        super(message);
    }

    public WeatherServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherServiceException(Throwable cause) {
        super(cause);
    }
}
