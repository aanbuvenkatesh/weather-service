package io.aanbuvenkatesh.weather.exception;

/**
 * {@code CityNotFoundException} is thrown when the provided city could not be found in weather service.
 *
 * @author aanbuvenkatesh
 */
public class CityNotFoundException extends WeatherServiceException {

    private static final int HTTP_STATUS = 404;
    private static final String ERROR_CODE = ERROR_PREFIX + "-" + HTTP_STATUS;
    private static final String MESSAGE = "City Not Found";

    public CityNotFoundException() {
        super(MESSAGE);
    }

    public CityNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public String getCode() {
        return ERROR_CODE;
    }

}
