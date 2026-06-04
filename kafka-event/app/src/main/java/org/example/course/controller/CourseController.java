package org.example.course.controller;

import org.example.course.model.CourseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "http://192.168.1.50:8080")
public class CourseController {


    private final KafkaTemplate<String, CourseEvent> kafkaTemplate;

    public CourseController(KafkaTemplate<String, CourseEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/enroll")
    public ResponseEntity<Map<String, String>> enroll(@RequestParam String courseId) {

        CourseEvent event = new CourseEvent(
                "ENROLL",
                courseId,
                UUID.randomUUID().toString(),
                System.currentTimeMillis()
        );

        kafkaTemplate.send("course-events", courseId, event);

        return ResponseEntity.ok(Map.of(
                "message", "Enrolled successfully",
                "courseId", courseId
        ));
    }

    @PostMapping("/buy")
    public ResponseEntity<Map<String, String>> buy(@RequestParam String courseId) {

        CourseEvent event = new CourseEvent(
                "BUY",
                courseId,
                UUID.randomUUID().toString(),
                System.currentTimeMillis()
        );

        kafkaTemplate.send("course-events", courseId, event);

        return ResponseEntity.ok(Map.of(
                "message", "Purchase successful",
                "courseId", courseId
        ));
    }
}
