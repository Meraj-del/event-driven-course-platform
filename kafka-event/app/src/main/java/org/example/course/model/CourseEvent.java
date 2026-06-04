package org.example.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEvent {
    private String eventType;
    private String courseId;
    private String userId;
    private long timestamp;
}