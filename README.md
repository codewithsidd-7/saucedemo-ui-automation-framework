# SauceDemo Selenium Automation Project

This repository contains a Selenium-based automation framework built to test the core
functionalities of the SauceDemo web application.

The project was created as a hands-on learning initiative while transitioning from a
manual QA role toward an **SDET-1** role, with a focus on writing clean, maintainable,
and scalable automation code.

---

## Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven
- Log4j
- Page Object Model (POM)

---

## Key Highlights
- Page Object Model for better separation of test logic and UI interactions
- Centralized WebDriver setup and configuration management
- Explicit waits to improve test stability
- Config-driven test data using property files
- Logging implemented for easier debugging and traceability

---

## Test Scenarios Covered

### Login
- Login without username
- Login without password
- Login with invalid credentials
- Login with valid credentials

### Inventory
- Sort products A → Z
- Sort products Z → A
- Sort by price (Low → High)
- Sort by price (High → Low)

### Cart
- Add and remove single products
- Add and remove multiple products

### Order Flow
- Complete end-to-end order placement
- Checkout validations for missing user information

---

## Running the Tests
1. Clone the repository
2. Make sure Java and Maven are installed
3. Update configuration values if required
4. Execute the tests using:
   ```bash
   mvn test
