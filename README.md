# 🌮 Tacomex — Arquitectura de Microservicios, Eventos y Kubernetes

Plataforma distribuida de gestión y preparación de pedidos construida con **Java 17**, **Spring Boot**, **Spring Cloud**, **Spring Security (JWT)**, **Apache Kafka**, **PostgreSQL**, **Docker** y **Kubernetes**.

## 🏗️ Arquitectura del Sistema

```text
                       [ Cliente HTTP ]
                               │
                               ▼
                 ┌───────────────────────────┐
                 │  API GATEWAY (Puerto 8080)│ ◄── Spring Cloud Gateway MVC + JWT
                 └─────────────┬─────────────┘
                               │ (Service Discovery vía Eureka)
               ┌───────────────┴───────────────┐
               ▼                               ▼
┌──────────────────────────────┐ ┌──────────────────────────────┐
│  ORDER-SERVICE (8081)        │ │  KITCHEN-CONSUMER (8082)     │
│  • API REST + Spring Data JPA│ │  • @KafkaListener (Consumer) │
│  • Persistencia PostgreSQL   │ │  • Procesamiento asíncrono   │
│  • Kafka Producer            │ │  • Escalable horizontalmente │
└──────────────┬───────────────┘ └──────────────▲───────────────┘
               │                                │
               └────► [ Apache Kafka ] ─────────┘
                     (Tópico: pedidos-tacos)
```

## 🛠️ Stack Tecnológico

* **Lenguaje y Framework:** Java 17, Spring Boot 3.4+
* **Microservicios:** Spring Cloud Netflix Eureka, Spring Cloud Gateway MVC (`lb://`)
* **Seguridad:** Spring Security, Stateless JWT (`OncePerRequestFilter`, `JJWT`)
* **Mensajería Asíncrona:** Apache Kafka (KRaft mode) + Kafka UI
* **Base de Datos:** PostgreSQL 16 + Spring Data JPA
* **Contenedores y Orquestación:** Docker (Multi-stage builds), Docker Compose, Kubernetes (`Deployments`, `Services`, `LoadBalancer`)

## 🚀 Opción 1: Ejecutar todo con Docker Compose

```bash
docker compose up -d --build
```
* **API Gateway:** `http://localhost:8080`
* **Eureka Dashboard:** `http://localhost:8761`
* **Kafka UI:** `http://localhost:8090`

## ☸️ Opción 2: Desplegar en Kubernetes

```bash
kubectl apply -f k8s/01-postgres-eureka.yaml
kubectl apply -f k8s/02-kafka-microservicios.yaml
kubectl get pods
```

## 🔐 Prueba del Flujo Completo (Auth + Orden + Kafka)

1. **Obtener Token JWT:**
   ```bash
   curl -X POST http://localhost:8080/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username": "taquero", "password": "1234"}'
   ```

2. **Crear pedido protegido con JWT:**
   ```bash
   curl -X POST http://localhost:8080/api/orders \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer <TU_TOKEN>" \
     -d '{"cliente": "Elena", "tipoTaco": "Barbacoa", "cantidad": 4}'
   ```

3. **Consultar órdenes listas en cocina:**
   ```bash
   curl -H "Authorization: Bearer <TU_TOKEN>" http://localhost:8080/api/kitchen/listos
   ```
