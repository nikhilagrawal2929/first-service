package com.espire.controller;

import com.espire.service.CustomHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/failure")
public class FailureSimulationController {

    // Control flag for simulating failure
    private static boolean simulateFailure = false;

    @Autowired
    private CustomHealthIndicator customHealthIndicator;

    @GetMapping("/simulate")
    public String simulateFailure() {
        // Set the flag to simulate failure
        simulateFailure = true;

        // Simulate the failure in the custom health indicator
        try {
            customHealthIndicator.simulateFailure();
        } catch (RuntimeException e) {
            // Optionally, you can log the exception here
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Simulated failure for testing self-healing feature.");
        }

        return "Failure simulated.";
    }

    public static boolean isSimulateFailure() {
        return simulateFailure;
    }

    public static void resetSimulation() {
        simulateFailure = false;
    }
}
