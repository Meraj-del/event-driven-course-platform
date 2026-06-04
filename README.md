# Event-Driven Course Platform

A production-style event-driven application demonstrating asynchronous messaging, real-time monitoring, and observability using Spring Boot, Apache Kafka, React, Prometheus, Grafana, and Docker.

The platform simulates an online learning marketplace where users can enroll in courses or purchase courses. Every action generates Kafka events that are consumed asynchronously and transformed into metrics exposed through Prometheus and visualized in Grafana.

---

## Architecture

```text
React Frontend
       │
       ▼
Spring Boot REST API
       │
       ▼
Apache Kafka Topic
(course-events)
       │
       ▼
Kafka Consumer
       │
       ▼
Micrometer Metrics
       │
       ▼
Prometheus
       │
       ▼
Grafana Dashboard
```

---

## Key Features

### Event Publishing

Users can:

* Enroll in courses
* Purchase courses

Each action publishes an event to Kafka.

Example:

```json
{
  "eventType": "ENROLL",
  "courseId": "JAVA-101",
  "userId": "uuid",
  "timestamp": 1710000000
}
```

---

### Event Consumption

Kafka consumers process events asynchronously and update custom Micrometer counters.

Supported events:

* ENROLL
* BUY

---

### Real-Time Metrics

Custom application metrics:

```text
course_enroll_total
course_buy_total
```

Metrics are exposed through:

```text
/actuator/prometheus
```

---

## Monitoring Stack

### Prometheus

Scrapes metrics every 5 seconds.

Configured target:

```text
course-service:8090/actuator/prometheus
```

### Grafana

Provides dashboards for:

* Course Enrollments
* Course Purchases
* Event Processing Activity

---

## Grafana Dashboard

![Grafana Dashboard](docs/grafana-dashboard.png)

---

## Technology Stack

| Layer            | Technology           |
| ---------------- | -------------------- |
| Backend          | Java 21              |
| Framework        | Spring Boot 3        |
| Messaging        | Apache Kafka (KRaft) |
| Frontend         | React                |
| Metrics          | Micrometer           |
| Monitoring       | Prometheus           |
| Visualization    | Grafana              |
| Containerization | Docker               |
| Reverse Proxy    | Nginx                |

---

## API Endpoints

### Enroll Course

```http
POST /api/course/enroll?courseId=JAVA-101
```

### Buy Course

```http
POST /api/course/buy?courseId=JAVA-101
```

### Statistics

```http
GET /api/stats
```

Example response:

```json
{
  "enrollments": 10,
  "purchases": 4
}
```

---

## Docker Deployment

```bash
docker compose up -d
```

Services:

| Service    | Port |
| ---------- | ---- |
| Frontend   | 8080 |
| Backend    | 8090 |
| Prometheus | 9090 |
| Grafana    | 3000 |

---

## Kafka Integration

The application publishes and consumes events through a shared Apache Kafka broker running on a Docker external network.

Topic used:

```text
course-events
```

This approach demonstrates infrastructure reuse by allowing multiple independent applications to communicate through the same Kafka cluster while maintaining isolated topics.

---

## Observability

Implemented using:

* Micrometer
* Spring Boot Actuator
* Prometheus
* Grafana

This provides real-time visibility into application activity and event processing.

---

## Skills Demonstrated

* Event Driven Architecture
* Kafka Producers
* Kafka Consumers
* Asynchronous Processing
* Docker Compose
* Monitoring & Observability
* Spring Boot REST APIs
* React Integration
* Infrastructure Reuse with Shared Kafka Clusters

---

## Author

Md Meraj

Java Backend Developer

Focused on:

* Spring Boot
* Microservices
* Apache Kafka
* Docker
* Cloud-Native Systems
