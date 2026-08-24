**Integration Project**

**Overview**

A Spring Boot based payment processing application demonstrating REST APIs, PostgreSQL persistence, JMS messaging with ActiveMQ Artemis, JWT authentication, Docker containerization, and monitoring using Prometheus and Grafana.

**Architecture**

```text
                        +----------------+
                        |    Swagger UI  |
                        +----------------+
                                 |
                                 v
+------------+          +----------------+
|   Client   | -------> | Spring Security|
+------------+          |      JWT       |
                         +----------------+
                                 |
                                 v
                         +----------------+
                         | Payment API    |
                         | Controller     |
                         +----------------+
                                 |
                                 v
                         +----------------+
                         | Service Layer  |
                         +----------------+
                           |          |
                           |          |
                           v          v
                  +-------------+  +-------------+
                  | PostgreSQL  |  |  Artemis MQ |
                  +-------------+  +-------------+
                                         |
                                         v
                                +----------------+
                                | MQ Consumer    |
                                +----------------+

Spring Boot Actuator
        |
        v
+----------------+
| Prometheus     |
+----------------+
        |
        v
+----------------+
| Grafana        |
+----------------+
```
**Technology Stack**

| Component         | Technology            |
| ----------------- | --------------------- |
| Language          | Java 21               |
| Framework         | Spring Boot           |
| Security          | Spring Security + JWT |
| Database          | PostgreSQL            |
| Messaging         | ActiveMQ Artemis      |
| API Documentation | Swagger/OpenAPI       |
| Monitoring        | Actuator              |
| Metrics           | Prometheus            |
| Dashboard         | Grafana               |
| Containerization  | Docker                |
| Build Tool        | Maven                 |

**Features**

JWT based authentication
RESTful payment APIs
PostgreSQL integration using Spring Data JPA
Asynchronous messaging using ActiveMQ Artemis
Swagger API documentation
Health monitoring with Spring Actuator
Metrics collection with Prometheus
Grafana dashboards
Dockerized deployment

**Project Structure**

```text
src/main/java/com.mainframe.integration
├── controller
├── service
├── model
├── messaging
├── config
├── filter
├── security
└── IntegrationProjectApplication

src/main/resources
└── application.properties
```

**APIs**

**Authentication**

POST /auth/login

**Create Payment**

POST /api/payments

**Sample Request**

{
  "customerId": "AAAA",
  "accountNumber": "AAAAA",
  "amount": 50,
  "currency": "DKK"
}

**Sample Response**

{
  "transactionId": "TXN-12345",
  "status": "PAYMENT_SUCCESS"
}

**Swagger**

http://localhost:8080/swagger-ui/index.html

**Health Check**

http://localhost:8080/actuator/health

**Prometheus Metrics**

http://localhost:8080/actuator/prometheus

**Running with Docker**

**Build Image**

docker build -t integration-project .

**Run Application**

docker run -d -p 8080:8080 --name payment-app integration-project

**Artemis**

http://localhost:8161/console/login

**Monitoring**

Prometheus collects application metrics from:

/actuator/prometheus

Grafana visualizes metrics through dashboards.

**Screenshots**

**Swagger UI**

**Payment API Success Response**

**Artemis Console**

**Prometheus Targets**

**Grafana Dashboard**

**Docker Containers**

**Author**

Jebastin
