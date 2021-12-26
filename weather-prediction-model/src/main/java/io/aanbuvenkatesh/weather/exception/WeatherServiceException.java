package io.aanbuvenkatesh.weather.exception;

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
