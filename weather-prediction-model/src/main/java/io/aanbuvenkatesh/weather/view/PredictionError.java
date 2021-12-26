package io.aanbuvenkatesh.weather.view;

public class PredictionError {

    private String code;
    private String message;

    public PredictionError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
