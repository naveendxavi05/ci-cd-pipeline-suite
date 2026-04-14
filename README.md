# CI/CD Pipeline Suite

![CI](https://github.com/naveendxavi05/ci-cd-pipeline-suite/actions/workflows/ci.yml/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=naveendxavi05_ci-cd-pipeline-suite&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=naveendxavi05_ci-cd-pipeline-suite)
![Java](https://img.shields.io/badge/Java-21-blue)
![Docker](https://img.shields.io/badge/Docker-Selenium%20Grid-blue)

A production-grade Selenium 4 + TestNG CI/CD pipeline with Docker Selenium Grid,
GitHub Actions, SonarCloud quality gate, and live Allure reporting.

---

## Live Allure Report

🔗 [View Live Report](https://naveendxavi05.github.io/ci-cd-pipeline-suite)

---

## Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Selenium 4 | 4.21.0 | Browser automation |
| Java | 21 (Corretto) | Test language |
| TestNG | 7.9.0 | Test runner + parallel XML |
| Docker + Compose V2 | latest | Containerise the Grid |
| Selenium Grid 4 | 4.18.1 | Distributed cross-browser execution |
| GitHub Actions | — | CI pipeline |
| SonarCloud | Free | Code quality gate |
| Allure Reports | 2.27.0 | Test reporting + GitHub Pages |
| JavaFaker | 1.0.2 | Test data generation |
| SeleniumManager | Built-in | Automatic driver resolution |

---

## Project Structure

ci-cd-pipeline-suite/
├── src/test/java/com/naveen/
│   ├── base/          # BaseTest, DriverFactory, GridConfig
│   ├── listeners/     # ScreenshotListener (ITestListener)
│   ├── pages/         # LoginPage, ProductsPage, CartPage, CheckoutPage
│   ├── tests/         # LoginTest, ProductTest, CartTest, CheckoutTest
│   └── utils/         # WaitUtils
├── src/test/resources/
│   ├── testng-local.xml   # Chrome only, thread-count=1
│   └── testng-grid.xml    # Chrome + Firefox, parallel=tests, thread-count=2
├── docker/
│   └── docker-compose.yml # Hub + 2 Chrome + 1 Firefox nodes
├── .github/workflows/
│   └── ci.yml             # GitHub Actions pipeline
├── Jenkinsfile            # Declarative Jenkins pipeline
└── sonar-project.properties


---

## Test Suite

| Test Class | Tests | Groups |
|-----------|-------|--------|
| LoginTest | 4 | smoke, regression |
| ProductTest | 3 | regression |
| CartTest | 3 | regression |
| CheckoutTest | 1 | smoke, regression |
| **Total** | **11 local / 22 on Grid** | |

---

## Run Commands

### Local (Chrome only)
```bash
./mvnw test -Dtestng.suiteXml=src/test/resources/testng-local.xml
```

### Local Grid (Chrome + Firefox in parallel)
```bash
# Start Grid
cd docker && docker compose up -d

# Run tests
./mvnw test \
  -Dtestng.suiteXml=src/test/resources/testng-grid.xml \
  -DGRID_URL=http://localhost:4444

# Stop Grid
docker compose down
```

### CI (GitHub Actions)
Push to `main` or `develop` — pipeline triggers automatically.

---

## Pipeline Stages
Checkout → Start Grid → Wait for Grid → Run Tests → SonarCloud Scan → Upload Allure → Deploy Pages


---

## Screenshots

### Selenium Grid UI — 3 nodes registered
![Grid UI](docs/grid-ui.png)

### SonarCloud Dashboard — Quality Gate Passed
![SonarCloud](docs/sonarcloud.png)