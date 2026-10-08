# BDD Framework Comparison: Cucumber vs JBehave vs Concordion

The same Spring Boot microservice and the same three business scenarios, tested with three different Java BDD frameworks. Use it to compare how each framework expresses specifications, wires step definitions and fits into a Spring Boot test setup.

## The scenarios

Every module implements an identical Employee Management feature for an HR team:

1. **Onboard** a new employee with a monthly salary in a given currency
2. **Deactivate** an employee's account
3. **Change** an employee's line manager

Because the application code is the same in all three modules, any difference you see is down to the testing framework alone.

## Modules

| Module | Framework | Spec format | Spec location | Runner |
|---|---|---|---|---|
| `app-with-cucumber` | Cucumber 7.13 | Gherkin `.feature` | `src/test/java/resources/employee/employee.feature` | `CucumberIntegrationTest` |
| `app-with-jbehave` | JBehave | `.story` | `src/test/resources/stories/employee.story` | `JBehaveTestRunner` |
| `app-with-concordion` | Concordion 3.2 | Markdown with embedded commands | `src/test/resources/com/concordioncode/employee/Employee.md` | `EmployeeFixture` |

### How the same scenario looks in each

**Cucumber / JBehave** — plain Given/When/Then text, matched to step methods by annotation:

```gherkin
Scenario: HR can deactivate an employee from the system
  Given the HR already onboarded Andy Micheal as an employee in the system
  When HR processed request for account deactivation
  Then the employee account status should be DE_ACTIVE
```

**Concordion** — a readable Markdown document where links carry the fixture calls, and which renders to an HTML report showing each assertion passing or failing:

```markdown
Then the employee account status should be [DE_ACTIVE](- "#status") [ ](- "theEmployeeAccountShouldStatusShouldBe(#status)")
```

## The application under test

Each module is a standalone Spring Boot 3.1 service exposing a small Employee REST API:

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/employee/create` | Onboard an employee |
| `GET` | `/employee/{id}` | Fetch an employee |
| `POST` | `/employee/deactivate/{id}` | Deactivate an employee |
| `POST` | `/employee/update/linemanager` | Change line manager |

Layers: controller → `EmployeeAction` service → Spring Data JPA repository, with MapStruct for DTO/entity mapping and Lombok to cut boilerplate.

## Tech stack

- Java 17
- Spring Boot 3.1 (Web, Data JPA)
- PostgreSQL at runtime, H2 in-memory for tests
- MapStruct, Lombok
- Cucumber 7, JBehave, Concordion 3
- Maven (wrapper in each module)

## Running the tests

Tests use an in-memory H2 database, so no setup is needed:

```bash
cd app-with-cucumber    # or app-with-jbehave, app-with-concordion
./mvnw test
```

Concordion writes its HTML specification report to `target/concordion`.

## Running an application

The Cucumber and JBehave apps expect PostgreSQL on `localhost:5432` (credentials in `src/main/resources/application.yaml`) and start on port `9000`:

```bash
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=mypg postgres
cd app-with-cucumber
./mvnw spring-boot:run
```

## Quick comparison

| | Cucumber | JBehave | Concordion |
|---|---|---|---|
| Spec readability for non-developers | High | High | Highest (prose document) |
| Living documentation output | Plugin reports | Plugin reports | Built-in HTML spec |
| Spring integration | `cucumber-spring` | Manual runner config | `concordion-spring-runner` |
| Community and ecosystem | Largest | Smaller, mature | Niche |

## Author

**Anand Kumar** — Senior Java Engineer & Tech Lead, fintech and payments systems.
