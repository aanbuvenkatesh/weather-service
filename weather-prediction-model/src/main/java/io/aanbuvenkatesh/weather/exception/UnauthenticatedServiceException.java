package io.aanbuvenkatesh.weather.exception;

public class UnauthenticatedServiceException extends WeatherServiceException{

    private static final int HTTP_STATUS = 401;
    private static final String ERROR_CODE = ERROR_PREFIX + "-" + HTTP_STATUS;

    public UnauthenticatedServiceException() {
        super();
    }

    public UnauthenticatedServiceException(String message) {
        super(message);
    }

    public UnauthenticatedServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthenticatedServiceException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return ERROR_CODE;
    }

    public int getHttpStatus() {
        return HTTP_STATUS;
    }
}
