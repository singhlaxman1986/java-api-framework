# API Automation Framework

This is a Maven-based Java API automation framework scaffold using RestAssured and JUnit 5.

Quick start

1. Build:

```bash
mvn -B -DskipTests=false clean test
```

2. Run tests against a specific base URL:

```bash
mvn -DbaseUrl=https://httpbin.org -DskipTests=false test
```

Git setup

```bash
git init
git add .
git commit -m "chore: scaffold API automation framework"
```

CI

A GitHub Actions workflow is provided at `.github/workflows/ci.yml` which runs `mvn test` on push and pull requests and uploads Allure results as an artifact.

Generating Allure report locally

1. Run tests to produce results:

```bash
mvn clean test
```

2. Generate an Allure report:

```bash
mvn allure:report
```

3. Serve the report locally:

```bash
mvn allure:serve
```

Logging

The framework uses SLF4J with Logback. Logs are printed to the console by default. To change the logging level, edit `src/main/resources/logback.xml` or provide a Logback configuration on the classpath.


