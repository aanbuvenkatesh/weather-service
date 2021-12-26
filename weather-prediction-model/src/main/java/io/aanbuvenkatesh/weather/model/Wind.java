package io.aanbuvenkatesh.weather.model;

/**
 * Wind class contains the information about the wind speed, deg and gust.
 *
 * @author  aanbuvenkatesh
 */
public class Wind {
    private Float speed;
    private Float deg;
    private Float gust;

    public Wind(Float speed, Float deg, Float gust) {
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Float getDeg() {
        return deg;
    }

    public void setDeg(Float deg) {
        this.deg = deg;
    }

    public Float getGust() {
        return gust;
    }

    public void setGust(Float gust) {
        this.gust = gust;
    }
}
