package org.example.course.consumer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.example.course.model.CourseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CourseEventConsumer {

    private final Counter enrollCounter;
    private final Counter buyCounter;

    public CourseEventConsumer(MeterRegistry registry) {

        enrollCounter = Counter.builder("course_enroll_total")
                .description("Total enroll events")
                .register(registry);

        buyCounter = Counter.builder("course_buy_total")
                .description("Total buy events")
                .register(registry);
    }

    @KafkaListener(
            topics = "course-events",
            groupId = "metrics-group"
    )
    public void consume(CourseEvent event) {

        System.out.println("RECEIVED EVENT = " + event);

        if (event == null) {
            return;
        }

        switch (event.getEventType()) {
            case "ENROLL" -> {
                enrollCounter.increment();
                log.info("Enroll event processed for courseId={}", event.getCourseId());
            }

            case "BUY" -> {
                buyCounter.increment();
                log.info("Buy event processed for courseId={}", event.getCourseId());
            }

            default -> log.warn("Unknown event type: {}", event.getEventType());
        }
    }
}