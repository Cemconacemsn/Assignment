# QA Automation Framework

Production-style UI and API test automation skeleton built with Java 21, Maven, Selenium 4, TestNG, RestAssured, WebDriverManager, AssertJ, and SLF4J.

## Prerequisites

- JDK 21
- Maven 3.9+
- Chrome (default browser; Firefox and Edge supported via config)
- Python 3 + Locust (optional, for load tests)

## Project layout

```
src/main/java/
  config/          # Layered properties loader
  driver/          # WebDriver factory + ThreadLocal manager

src/test/java/
  ui/
    pages/         # Page Object Model
    tests/         # UI TestNG tests
    utils/         # Base tests, waits, TestNG listener
  api/
    client/        # RestAssured request spec
    models/        # API DTOs / records
    tests/         # API TestNG tests
  load/
    locustfile.py  # Locust user tasks
    scenarios.md   # Load scenario catalog

src/test/resources/
  config/          # application.properties (+ env overlays)
  testng/          # TestNG suite XML files
```

## Run tests

From this directory (`insider-assignment`):

```bash
# Full suite (UI + API)
mvn clean test

# UI only
mvn clean test -Pui

# API only (no browser)
mvn clean test -Papi

# Local env overlay (application-local.properties)
mvn clean test -Plocal

# CI: headless + parallel methods
mvn clean test -Pci
```

Environment selection: `-Denv=ci` or `TEST_ENV=ci` (default: `local`).

## Load tests

```bash
pip install locust
locust -f src/test/java/load/locustfile.py
```

Details: [src/test/java/load/scenarios.md](src/test/java/load/scenarios.md).

## Configuration

Edit `src/test/resources/config/application.properties`, or add overrides in `application-{env}.properties`.

| Property | Description |
|----------|-------------|
| `browser` | `chrome`, `firefox`, or `edge` |
| `headless` | Run browser headless |
| `base.url` | Application under test |
| `api.base.uri` | RestAssured base URI |
| `explicit.wait.seconds` | WebDriverWait timeout |

System properties and environment variables override file values (`TEST_ENV` maps to active env file).

## Extending the framework

1. Add page objects under `ui.pages`.
2. Add UI tests extending `ui.utils.BaseUiTest`.
3. Add API models under `api.models` and tests extending `api.tests.BaseApiTest`.
4. Register new packages in `src/test/resources/testng/*.xml`.
