# Event-Driven Course Platform

A production-style event-driven application built with Spring Boot, Apache Kafka, React, Prometheus, Grafana, and Docker.

The platform simulates an online learning marketplace where users can enroll in courses and purchase courses. User actions are published as Kafka events, processed asynchronously by consumers, exposed as Micrometer metrics, collected by Prometheus, and visualized through Grafana dashboards.

---

# Architecture

```text
React Frontend
       │
       ▼
Spring Boot REST API
       │
       ▼
Shared Apache Kafka Broker
       │
       ▼
Topic: course-events
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

# Project Structure

```text
event-driven-course-platform/
│
├── course-landing-page/
│
├── kafka-event/
│
├── prometheus/
│   └── prometheus.yml
│
├── docs/
│   └── grafana-dashboard.png
│
├── docker-compose.yml
│
├── README.md
│
└── .gitignore
```

---

# Features

## Event Publishing

Users can perform the following actions:

* Enroll in a course
* Purchase a course

Each action generates an event and publishes it to Kafka.

Example event:

```json
{
  "eventType": "ENROLL",
  "courseId": "JAVA-101",
  "userId": "user-123",
  "timestamp": 1710000000
}
```

---

## Event Consumption

Kafka consumers process events asynchronously and update application metrics.

Supported event types:

* ENROLL
* BUY

---

## Real-Time Monitoring

Custom Micrometer metrics:

```text
course_enroll_total
course_buy_total
```

Metrics endpoint:

```text
/actuator/prometheus
```

---

# Event Flow

1. User performs an action from the React frontend.
2. Spring Boot REST API receives the request.
3. Event is published to Kafka topic `course-events`.
4. Kafka consumer processes the event asynchronously.
5. Micrometer counters are updated.
6. Prometheus scrapes the metrics endpoint.
7. Grafana visualizes the metrics in real time.

---

# Kafka Integration

This application uses Apache Kafka for asynchronous event processing.

## Broker Configuration

The Kafka broker is intentionally not provisioned by this repository.

During development, this application connects to a shared Apache Kafka (KRaft) broker running in a separate Docker environment through an external Docker network.

Backend configuration:

```properties
spring.kafka.bootstrap-servers=kafka-kraft:9092
```

## Topic

```text
course-events
```

Using a shared Kafka broker allows multiple applications to reuse the same messaging infrastructure while maintaining isolation through dedicated topics.

---

# Monitoring Stack

## Prometheus

Prometheus collects metrics from the Spring Boot application through:

```text
/actuator/prometheus
```

Configured scrape target:

```text
course-service:8090
```

---

## Grafana

Grafana provides dashboards for:

* Course Enrollments
* Course Purchases
* Event Activity Monitoring
* Real-Time Metrics Visualization

---

# Dashboard Preview

The dashboard displays real-time application activity generated from Kafka events.

![Grafana Dashboard](docs/grafana-dashboard.png)

---

# API Endpoints

## Enroll Course

```http
POST /api/course/enroll?courseId=JAVA-101
```

## Purchase Course

```http
POST /api/course/buy?courseId=JAVA-101
```

## Statistics

```http
GET /api/stats
```

Example Response:

```json
{
  "enrollments": 10,
  "purchases": 4
}
```

---

# Docker Deployment

Start the application:

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

> Note: This repository expects access to an existing Kafka broker through a Docker external network.

---

# Technology Stack

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

# Observability

Implemented using:

* Spring Boot Actuator
* Micrometer
* Prometheus
* Grafana

This enables real-time visibility into event processing and application activity.

---

# Skills Demonstrated

* Event-Driven Architecture
* Apache Kafka Producers
* Apache Kafka Consumers
* Asynchronous Messaging
* Spring Boot REST APIs
* Docker Compose
* Monitoring & Observability
* Prometheus Metrics Collection
* Grafana Dashboards
* Micrometer Integration
* React Frontend Integration
* Infrastructure Reuse Through Shared Kafka Clusters

---

# Future Improvements

* Dead Letter Queue (DLQ)
* Kafka Streams Processing
* OpenTelemetry Distributed Tracing
* Prometheus Alertmanager Integration
* Kubernetes Deployment
