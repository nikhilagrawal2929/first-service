package com.espire.service;

import com.espire.controller.FailureSimulationController;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if (FailureSimulationController.isSimulateFailure()) {
            return Health.down()
                          .withDetail("error", "Simulated failure")
                          .build();
        }
        return Health.up().build();
    }

    public void simulateFailure() {
        throw new RuntimeException("Simulated failure for testing self-healing feature.");
    }
}
