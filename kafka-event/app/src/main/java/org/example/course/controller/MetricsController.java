package org.example.course.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class MetricsController {

    private final MeterRegistry registry;

    public MetricsController(MeterRegistry registry) {
        this.registry = registry;
    }

    private double safeCount(String name) {
        return registry.find(name).counter() != null
                ? registry.find(name).counter().count()
                : 0.0;
    }

    @GetMapping
    public Map<String, Double> getStats() {
        return Map.of(
                "enrollments", safeCount("course_enroll_total"),
                "purchases", safeCount("course_buy_total")
        );
    }
}
