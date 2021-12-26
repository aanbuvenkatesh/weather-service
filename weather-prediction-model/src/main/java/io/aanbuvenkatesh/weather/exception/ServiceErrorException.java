package io.aanbuvenkatesh.weather.exception;

public class ServiceErrorException extends WeatherServiceException{

    private static final int HTTP_STATUS = 500;
    private static final String ERROR_CODE = ERROR_PREFIX + "-" + HTTP_STATUS;

    public ServiceErrorException() {
        super();
    }

    public ServiceErrorException(String message) {
        super(message);
    }

    public ServiceErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceErrorException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return ERROR_CODE;
    }
}
