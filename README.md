# AML Monitoring Platform

[![CI](https://github.com/koketseraphasha/aml-monitoring-platform/actions/workflows/ci.yml/badge.svg)](https://github.com/koketseraphasha/aml-monitoring-platform/actions/workflows/ci.yml)

Anti-Money Laundering monitoring platform. Detects unusual transfer volumes, rapid account activity, structured transactions, and suspicious account relationships.

## Features
- Transaction monitoring
- Suspicious activity detection
- Case management
- Risk scoring
- Regulatory reporting
- Investigation workflows

## Stack
Java 21, Spring Boot, PostgreSQL, Docker

## Quick Start
```bash
docker compose up -d
```

## Deployment & Architecture

This project is designed with cloud-ready principles:

- **Containerized** using Docker for consistent deployment
- **Environment-based configuration** — no hardcoded secrets
- **Modular structure** for independent scaling
- **Stateless design** where applicable
- **Separation of concerns** for maintainability

### Run Locally

`ash
docker-compose up --build
`

---

*Part of the Kirov Dynamics Technology portfolio — backend engineering focused on security, scalability, and system design.*
