package io.aanbuvenkatesh.weather.app.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.withDetail("service", "weather-prediction")
                .withDetail("status", Status.HEALTHY)
                .up();
    }

    private enum Status {
        HEALTHY;
    }
}
